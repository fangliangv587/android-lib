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

import android.content.Context;

import java.util.Map;

import okhttp3.Cache;

/**
 * @author cisco_luo
 */

public final class RHttpConfig {
    protected static String sDomain = "";
    protected static Map<String, String> sHeaders;
    protected static RIntercept sRIntercept;
    protected static RPreIntercept sRPreIntercept;
    protected static Cache sCache;

    private RHttpConfig() {}

    /**
     * set domain
     * @param domain
     */
    public static void setDomain(String domain) {
        sDomain = domain;
    }

    /**
     * set headers
     * @param headers
     */
    public static void setHeader(Map<String, String> headers) {
        sHeaders = headers;
    }

    /**
     * set pre intercept
     * @param intercept
     */
    public static void setPreIntercept(RPreIntercept intercept) {
        sRPreIntercept = intercept;
    }

    /**
     * set intercept
     * @param intercept
     */
    public static void setIntercept(RIntercept intercept) {
        sRIntercept = intercept;
    }

    /**
     * get domain
     * @return
     */
    public static String getDomain() {
        return sDomain;
    }

    /**
     * set cache
     * @param ctx
     * @param cacheSize
     */
    public static void setCache(Context ctx, int cacheSize) {
        if(ctx != null) {
            sCache = new Cache(ctx.getCacheDir(), cacheSize);
        }
    }

}
