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

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

/**
 * @author cisco_luo
 */

public final class RFileHttp {
    private static final MediaType MEDIA_FILE = MediaType.parse("application/octet-stream");
    private static final int TIMEOUT = 50;

    private static OkHttpClient sOkHttpClient = new OkHttpClient();

    private RFileHttp() {}

    /**
     * upload file
     * @param url
     * @param params
     * @param cb
     */
    public void upLoadFile(String url, HashMap<String, Object> params, RFileCallback cb) {
        String newUrl = RHttp.createUrl(url);
        RequestBody body = createBody(params, cb);
        Request request = new Request.Builder().url(newUrl).post(body).build();
        Call call = sOkHttpClient.newBuilder()
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build().newCall(request);
        call.enqueue(new HandleCallback(cb));
    }

    /**
     * create body
     * @param paramsMap
     * @return
     */
    private RequestBody createBody(HashMap<String, Object> paramsMap, RFileCallback cb) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (String key : paramsMap.keySet()) {
            Object object = paramsMap.get(key);
            if (object instanceof File) {
                File file = (File) object;
                builder.addFormDataPart(key, file.getName(), new ProgressBody(file, cb));
            } else {
                builder.addFormDataPart(key, object.toString());
            }
        }
        return builder.build();
    }

    /**
     * progress body
     */
    class ProgressBody extends RequestBody {
        private File file;
        private RFileCallback cb;

        ProgressBody(File file, RFileCallback cb) {
            this.file = file;
            this.cb = cb;
        }

        @Override
        public MediaType contentType() {
            return MEDIA_FILE;
        }

        @Override
        public long contentLength() {
            return file.length();
        }

        @Override
        public void writeTo(BufferedSink sink) throws IOException {
            Source source = Okio.source(file);
            Buffer buf = new Buffer();
            long total = contentLength();
            long current = 0;
            for (long readCount; (readCount = source.read(buf, 2048)) != -1; ) {
                sink.write(buf, readCount);
                current += readCount;
                cb.onProgress(current, total);
            }
        }
    }

    /**
     * intercept callback
     */
    class HandleCallback implements Callback {
        private RFileCallback cb;

        HandleCallback(RFileCallback cb) {
            this.cb = cb;
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            if (response.isSuccessful()) {
                cb.onSuccess(response.body().string());
            } else {
                cb.onFailure(-1, "upload fail.");
            }
        }

        @Override
        public void onFailure(Call call, IOException e) {
            cb.onFailure(-1, e.getMessage());
        }
    }

}
