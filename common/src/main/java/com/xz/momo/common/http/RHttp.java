/*
 * Copyright (c) 2017.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xz.momo.common.http;

import android.text.TextUtils;

import com.xz.momo.common.http.callback.RCallback;
import com.xz.momo.common.http.encrypt.REncrypt;
import com.xz.momo.common.http.intercept.SimpleIntercept;
import com.xz.momo.common.http.mock.RCloudMock;
import com.xz.momo.common.http.mock.RLocalMock;
import com.xz.momo.common.log.RLog;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;

/**
 * @author cisco_luo
 */

public final class RHttp {
    private static OkHttpClient sOkHttpClient;
    private REncrypt mREncrypt;

    public RHttp() {
        if (sOkHttpClient == null) {
            synchronized (this) {
                createOkHttpClient();
            }
        }
    }

    /**
     * create OkHttpClient
     * @return
     */
    private void createOkHttpClient() {
        if(sOkHttpClient != null) {
            return ;
        }

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(60000, TimeUnit.SECONDS);
        builder.writeTimeout(60000, TimeUnit.SECONDS);

        if(RHttpConfig.sCache != null) {
            builder.cache(RHttpConfig.sCache);
        }
        sOkHttpClient = builder.build();
    }

    /**
     * set encrypt
     * @param encrypt
     */
    public RHttp setEncrypt(REncrypt encrypt) {
        mREncrypt = encrypt;
        return this;
    }

    /**
     * get
     * @param url
     * @param cb
     */
    public void get(String url, RCallback<String> cb) {
        if(preIntercept(null)) {
            return ;
        }

        String newUrl = createUrl(url);
        if(RLocalMock.hasMockApi(url)) {
            RLocalMock.mockData(newUrl, cb, RHttpConfig.sRIntercept);
            return ;
        }

        Request.Builder builder = new Request.Builder().url(newUrl);
        addHeaders(builder);
        Request request = builder.build();
        Call call = sOkHttpClient.newCall(request);
        call.enqueue(new HandleCallback(newUrl, cb));
    }

    /**
     * post with no body
     * @param url
     * @param cb
     */
    public void post(String url, RCallback<String> cb) {
        post(url, null, cb);
    }

    /**
     * post
     * @param url
     * @param params
     * @param cb
     */
    public void post(String url, Map<String, String> params, RCallback<String> cb) {
        if(preIntercept(params)) {
            return ;
        }

        String newUrl = createUrl(url);
        if(RLocalMock.hasMockApi(url)) {
            RLocalMock.mockData(newUrl, cb, RHttpConfig.sRIntercept);
            return ;
        }

        Request.Builder builder = new Request.Builder().url(newUrl);
        addHeaders(builder);
        params = appendHeadersToBody(params);
        RequestBody body = createPostBody(params);
        if (body != null) {
            builder.post(body);
        }
        Request request = builder.build();
        Call call = sOkHttpClient.newCall(request);

        call.enqueue(new HandleCallback(newUrl, cb));
    }

    /**
     * post
     * @param url
     * @param params
     * @param cb
     */
    public void postStream(String url, Map<String, String> params, RCallback<String> cb) {
        if(preIntercept(params)) {
            return ;
        }

        String newUrl = createUrl(url);
        if(RLocalMock.hasMockApi(url)) {
            RLocalMock.mockData(newUrl, cb, RHttpConfig.sRIntercept);
            return ;
        }

        params = appendHeadersToBody(params);
        Request.Builder builder = new Request.Builder()
//                .url("http://192.168.2.97:8080/data/patroltask_rundata_put");
                .url(newUrl);
        addHeaders(builder);

        RequestBody body = gzip(createRequestBody(params));
        if (body != null) {
            builder.post(body);
        }
        Request request = builder.build();
        Call call = sOkHttpClient.newCall(request);
        call.enqueue(new HandleCallback(newUrl, cb));
    }

    /**
     * createRequestBody
     * @param params
     * @return
     */
    private RequestBody createRequestBody(final Map<String, String> params) {
        if(params == null) {
            return null;
        }
        RequestBody  body = new RequestBody() {
            @Override
            public MediaType contentType() {
                return null;
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                Iterator iter = params.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<String, String> entry = (Map.Entry) iter.next();
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if(!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                        sink.writeUtf8(key);
                        sink.writeUtf8("==");
                        sink.writeUtf8(value);
                        sink.writeUtf8("&");
                    }
                }
            };
        };
        return body;
    }
    /**
     * 对body 内容进行压缩
     * @param body
     * @return
     */
    private RequestBody gzip(final RequestBody body) {
        return new RequestBody() {
            @Override
            public MediaType contentType() {
                return body.contentType();
            }
            @Override
            public long contentLength() {
                return -1; // 无法知道压缩后的数据大小
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                GzipSink input=new GzipSink(sink);
                BufferedSink gzipSink = Okio.buffer(new GzipSink(sink));
                body.writeTo(gzipSink);
                gzipSink.close();
            }
        };
    }

    /**
     * create request url
     * @param url
     * @return
     */
    static protected String createUrl(String url) {
        String domain = RCloudMock.hasMockApi(url) ?
                RCloudMock.getDomain() : RHttpConfig.sDomain;
        if(url == null) {
            return domain;
        }

        StringBuilder urlBuilder = new StringBuilder();
        if(TextUtils.isEmpty(url) || TextUtils.isEmpty(RHttpConfig.sDomain)
                || url.startsWith("http://") || url.startsWith("https://")) {
            urlBuilder.append(url);
        } else {
            String concat = "/";
            if(domain.endsWith("/") || url.startsWith("/")) {
                concat = "";
            }
            urlBuilder.append(domain).append(concat).append(url);
        }

        String newUrl = urlBuilder.toString();
        newUrl = newUrl.startsWith("http") ? newUrl : "http://"+newUrl;
        RLog.d(RLog.LIB_TGA, "createUrl="+newUrl);
        return newUrl;
    }

    /**
     * create headers
     * @return
     */
    static protected Map<String, String> appendHeadersToBody(Map<String, String> params) {
        if(params == null) {
            params = new HashMap<>();
        }

        if (RHttpConfig.sHeaders == null || RHttpConfig.sHeaders.isEmpty()) {
            return params;
        }

        for (String key : RHttpConfig.sHeaders.keySet()) {
            String value = RHttpConfig.sHeaders.get(key);
            if(!TextUtils.isEmpty(value)) {
                params.put(key, value);
            }
        }

        return params;
    }

    /**
     * add header
     * @param builder
     */
    protected void addHeaders(Request.Builder builder) {
        if (RHttpConfig.sHeaders == null || RHttpConfig.sHeaders.isEmpty()) {
            return ;
        }

        for (String key : RHttpConfig.sHeaders.keySet()) {
            String value = RHttpConfig.sHeaders.get(key);
            if(!TextUtils.isEmpty(value)) {
                builder.addHeader(key, value);
            }
        }
    }

    /**
     * create post body
     * @param params
     * @return
     */
    private RequestBody createPostBody(Map<String, String> params) {
        if(params == null) {
            return null;
        }

        FormBody.Builder builder = new FormBody.Builder();
        Iterator iter = params.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry) iter.next();
            String key = entry.getKey();
            String value = entry.getValue();
            if(!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                builder.add(key, encrypt(value));
            }
        }
        return builder.build();
    }

    /**
     * encrypt
     * @param value
     * @return
     */
    private String encrypt(String value) {
        if(mREncrypt != null) {
            return mREncrypt.encode(value);
        } else {
            return value;
        }
    }

    /**
     * Callback's impl
     */
    class HandleCallback implements Callback {
        private String url;
        private RCallback cb;

        HandleCallback(String url, RCallback cb) {
            this.url = url;
            this.cb = cb;
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            RLog.d(RLog.LIB_TGA, "http code="+response.code()+ ",message="+response.message());

            if(!response.isSuccessful()) {
                cb.onFailure(response.code(), "http response error, "+response.message());
                return ;
            }
            Headers headers = response.headers();
            String body = response.body().string();
            if(mREncrypt != null) {
                body = mREncrypt.decode(body);
            }

            //out resp body
//            RLog.d(RLog.LIB_TGA, "request url="+url+ "\n response data="+body);

            if(RHttpConfig.sRIntercept == null) {
                cb.onSuccess(body,headers);
                return ;
            }

            SimpleIntercept.ProcessModel pm = RHttpConfig.sRIntercept.process(body);
            if(pm == null) {
                cb.onFailure(-1, "");
            } else {
                if (pm.isSuccess) {
                    cb.onSuccess(pm.body,headers);
                } else {
                    cb.onFailure(toInt(pm.code), pm.errMsg);
                }
            }
        }

        @Override
        public void onFailure(Call call, IOException e) {
            cb.onFailure(-1, e.getMessage());
        }

        private int toInt(String value) {
            try {
                return Integer.parseInt(value);
            } catch (Exception ex) {
                return -1;
            }
        }

    }

    /**
     * pre intercept
     * @param body
     */
    private boolean preIntercept(Map<String, String> body) {
        RPreIntercept intercept = RHttpConfig.sRPreIntercept;
        if(intercept != null) {
            return intercept.process(RHttpConfig.sHeaders, body);
        } else {
            return false;
        }
    }

}
