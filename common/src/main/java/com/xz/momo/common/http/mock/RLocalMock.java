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

package com.xz.momo.common.http.mock;

import android.content.Context;


import com.xz.momo.common.http.RIntercept;
import com.xz.momo.common.http.callback.RCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cisco_luo
 */

public final class RLocalMock {
    private static Context mContext;
    private static boolean sIsOpenMock;
    private static Map<String, String> sMockApis = new HashMap<>();
    private static MockHandle sMockHandle = new MockHandle();

    private RLocalMock() {}

    /**
     * open mock
     * @param ctx
     * @param apis
     */
    public static void openMock(Context ctx, String[] apis, String mockFolder) {
        mContext = ctx;
        sIsOpenMock = true;
        sMockHandle.setMockFolder(mockFolder);
        if(apis != null) {
            for (String api : apis) {
                sMockApis.put(api, api);
            }
        }
    }

    /**
     * whether is mock api
     * @param url
     * @return
     */
    public static boolean hasMockApi(String url) {
        return sIsOpenMock && (sMockApis.containsKey(url) || sMockApis.size()==0);
    }

    /**
     * mock data
     * @param url
     * @param cb
     */
    public static void mockData(String url, RCallback<String> cb, RIntercept intercept) {
        sMockHandle.mockData(mContext, url, cb, intercept);
    }

}
