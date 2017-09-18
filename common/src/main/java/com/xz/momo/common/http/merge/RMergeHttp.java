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

package com.xz.momo.common.http.merge;

/**
 * @author cisco_luo
 */

public final class RMergeHttp {
//    private RJSONHttp mRJSONHttp = new RJSONHttp();
//
//    public RMergeHttp() {}
//
//    public RMergeHttp setEncrypt(REncrypt encrypt) {
//        mRJSONHttp.setEncrypt(encrypt);
//        return this;
//    }
//
//    /**
//     * get
//     * @param url1
//     * @param url2
//     * @param cb
//     * @param <T>
//     * @param <K>
//     */
//    public <T, K> void get(String url1, String url2, RMergeCallback<T, K> cb) {
//        final ReduceResult rr = new ReduceResult(cb);
//        mRJSONHttp.get(url1, new RCallback<T>() {
//            @Override
//            public void onSuccess(T body) {
//                rr.setUrl1Body(body);
//            }
//
//            @Override
//            public void onFailure(int code, String errMsg) {
//                rr.setError(code, 0, errMsg);
//            }
//        });
//        mRJSONHttp.get(url2, new RCallback<K>() {
//            @Override
//            public void onSuccess(K body) {
//                rr.setUrl2Body(body);
//            }
//
//            @Override
//            public void onFailure(int code, String errMsg) {
//                rr.setError(0, code, errMsg);
//            }
//        });
//    }
//
//    public <T, K> void post(String url1, String url2, RMergeCallback<T, K> cb) {
//        post(url1, null, url2, null, cb);
//    }
//
//    /**
//     * post
//     * @param url1
//     * @param params1
//     * @param url2
//     * @param params2
//     * @param cb
//     * @param <T>
//     * @param <K>
//     */
//    public <T, K> void post(String url1, Map<String, String> params1,
//                                   String url2, Map<String, String> params2,
//                                   RMergeCallback<T, K> cb) {
//        final ReduceResult rr = new ReduceResult(cb);
//        mRJSONHttp.post(url1, params1, new RCallback<T>() {
//            @Override
//            public void onSuccess(T body) {
//                rr.setUrl1Body(body);
//            }
//
//            @Override
//            public void onFailure(int code, String errMsg) {
//                rr.setError(code, 0, errMsg);
//            }
//        });
//        mRJSONHttp.post(url2, params2, new RCallback<K>() {
//            @Override
//            public void onSuccess(K body) {
//                rr.setUrl2Body(body);
//            }
//
//            @Override
//            public void onFailure(int code, String errMsg) {
//                rr.setError(0, code, errMsg);
//            }
//        });
//    }
//
//    static class ReduceResult<T, K> {
//        private T body1;
//        private K body2;
//        private RMergeCallback callback;
//        private boolean isCloseError = false;
//
//        ReduceResult(RMergeCallback<T, K> cb) {
//            callback = cb;
//        }
//
//        void setUrl1Body(T t) {
//            body1 = t;
//            reduce();
//        }
//
//        void setUrl2Body(K k) {
//            body2 = k;
//            reduce();
//        }
//
//        void setError(int code1, int code2, String errMsg) {
//            if(!isCloseError) {
//                isCloseError = true;
//                callback.onFailure(code1, code2, errMsg);
//            }
//        }
//
//        void reduce() {
//            if(body1 != null && body2 != null) {
//                callback.onSuccess(body1, body2);
//            }
//        }
//
//    }

}
