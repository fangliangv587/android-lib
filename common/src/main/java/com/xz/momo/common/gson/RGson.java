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

package com.xz.momo.common.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

/**
 * @author cisco_luo
 */

public final class RGson {
    private static Gson mGson;

    static {
        GsonBuilder builder = new GsonBuilder();
        builder.disableHtmlEscaping();
        mGson = builder.create();
    }

    private RGson() {}

    public static <T> String toJson(T t) {
        return mGson.toJson(t);
    }

    public static <T> String toJson(List<T> list) {
        return mGson.toJson(list);
    }

    public static <T> T fromJson(String json, Class<T> cls) {
        return mGson.fromJson(json, cls);
    }

    public static boolean has(String json, String name) {
        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(json);
        JsonObject jsonObj = element.getAsJsonObject();
        return jsonObj.has(name);
    }

    public static String getString(String json, String name) {
        try {
            JsonParser jsonParser = new JsonParser();
            JsonElement element = jsonParser.parse(json);
            JsonObject jsonObj = element.getAsJsonObject();
            return jsonObj.get(name).toString();
        } catch (Exception e) {
            return null;
        }
    }

}
