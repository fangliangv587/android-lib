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

package com.xz.momo.common.store;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * @author cisco_luo
 */

public final class RShare {
    private static final String SHARE_NAME = "core_share";

    private RShare() {}

    /**
     * put
     * @param ctx
     * @param key
     * @param value
     */
    public static void putInt(Context ctx, String key, int value) {
        getEditor(ctx).putInt(key, value).commit();
    }

    public static void putBoolean(Context ctx, String key, boolean value) {
        getEditor(ctx).putBoolean(key, value).commit();
    }

    public static void putString(Context ctx, String key, String value) {
        getEditor(ctx).putString(key, value).commit();
    }

    public static void putSet(Context ctx, String key, Set<String> value) {
        getEditor(ctx).putStringSet(key, value).commit();
    }

    /**
     * get
     * @param ctx
     * @param key
     * @return
     */
    public static int getInt(Context ctx, String key) {
        return getSP(ctx).getInt(key, -1);
    }

    public static String getString(Context ctx, String key) {
        return getSP(ctx).getString(key, "");
    }

    public static boolean getBoolean(Context ctx, String key) {
        return getSP(ctx).getBoolean(key, false);
    }

    public static Set<String> getSet(Context ctx, String key) {
        return getSP(ctx).getStringSet(key, null);
    }

    /**
     * SharedPreferences
     * @param ctx
     * @return
     */
    private static SharedPreferences getSP(Context ctx) {
        return ctx.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(Context ctx) {
        return getSP(ctx).edit();
    }

}
