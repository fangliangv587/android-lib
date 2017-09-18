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

import java.util.HashMap;
import java.util.Map;

/**
 * @author cisco_luo
 */

public final class RCloudMock {
    private static String mMockDomain;
    private static boolean sIsOpenMock;
    private static Map<String, String> sMockApis = new HashMap<>();

    private RCloudMock() {}

    /**
     * open mock
     * @param mockDomain
     * @param apis
     */
    public static void openMock(String mockDomain, String[] apis) {
        sIsOpenMock = true;
        mMockDomain = mockDomain;
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
     * get domain
     * @return
     */
    public static String getDomain() {
        return mMockDomain;
    }

}
