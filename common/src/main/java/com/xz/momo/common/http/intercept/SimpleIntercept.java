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

package com.xz.momo.common.http.intercept;

import android.text.TextUtils;

import com.xz.momo.common.http.RIntercept;


/**
 * @author cisco_luo
 */

public abstract class SimpleIntercept extends RIntercept {
    private String mCodeName;
    private String mErrMsgName;
    private String mResultName;

    public SimpleIntercept(String codeName, String errMsgName, String resultName) {
        mCodeName = codeName;
        mErrMsgName = errMsgName;
        mResultName = resultName;
    }

    @Override
    public ProcessModel process(String body) {
        ProcessModel pm = new ProcessModel();
        pm.code = getJSONItemString(body, mCodeName);
        if(TextUtils.isEmpty(pm.code)) {
            return pm;
        }

        pm.errMsg = getJSONItemString(body, mErrMsgName);
        if(!processCode(pm.code, pm.errMsg)) {
            pm.isSuccess = true;
            pm.body = getJSONItemString(body, mResultName);
            return pm;
        } else {
            return pm;
        }
    }

    public abstract boolean processCode(String code, String errMsg);

    public static class ProcessModel {
        public boolean isSuccess;
        public String code;
        public String errMsg;
        public String body;
    }

}
