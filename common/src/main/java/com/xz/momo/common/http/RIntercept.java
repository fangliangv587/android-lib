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



import com.xz.momo.common.http.intercept.SimpleIntercept;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author cisco_luo
 */

public abstract class RIntercept {

    public abstract SimpleIntercept.ProcessModel process(String body);

    protected String getJSONItemString(String json, String name) {
        try {
            JSONObject jb = new JSONObject(json);
            return jb.getString(name);
        } catch (JSONException e) {
            return null;
        }
    }

    protected int getJSONItemInt(String json, String name) {
        try {
            JSONObject jb = new JSONObject(json);
            return jb.getInt(name);
        } catch (JSONException e) {
            return -1;
        }
    }

}
