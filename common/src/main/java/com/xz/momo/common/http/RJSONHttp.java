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

/**
 * @author cisco_luo
 */

public final class RJSONHttp {
//    private RHttp mRHttp = new RHttp();
//
//    public RJSONHttp() {
//
//    }
//
//    public RJSONHttp setEncrypt(REncrypt encrypt) {
//        mRHttp.setEncrypt(encrypt);
//        return this;
//    }
//
//    public <T> void get(String url, final RCallback<T> cb) {
//        mRHttp.get(url, new JSONRCallback<T>(cb));
//    }
//
//    public <T> void post(String url, RCallback<T> cb) {
//        mRHttp.post(url, null, new JSONRCallback<T>(cb));
//    }
//
//    public <T> void post(String url, Map<String, String> params, RCallback<T> cb) {
//        mRHttp.post(url, params, new JSONRCallback<T>(cb));
//    }
//
//    class JSONRCallback<T> implements RCallback<String> {
//        private RCallback<T> callback;
//
//        JSONRCallback(RCallback<T> callback) {
//            this.callback = callback;
//        }
//
//        @Override
//        public void onSuccess(String body) {
//            try {
//                Gson gson = new Gson();
//                T result = gson.fromJson(body, new TypeToken<T>(){}.getType());
//                callback.onSuccess(result);
//            } catch(JsonSyntaxException ex) {
//                RLog.d(RLog.LIB_TGA, "json parse fail.", "resp="+body);
//                callback.onFailure(-1, "json parse fail");
//            } catch(ClassCastException ex1) {
//                RLog.d(RLog.LIB_TGA, "Class cast exception.", "resp="+body);
//                callback.onFailure(-1, "class cast exception");
//            }
//        }
//
//        @Override
//        public void onFailure(int code, String errMsg) {
//            callback.onFailure(code, errMsg);
//        }
//    }

}
