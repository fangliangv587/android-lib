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

import com.xz.momo.common.http.RHttpConfig;
import com.xz.momo.common.http.RIntercept;
import com.xz.momo.common.http.callback.RCallback;
import com.xz.momo.common.http.intercept.SimpleIntercept;
import com.xz.momo.common.log.RLog;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * @author cisco_luo
 */

public final class MockHandle {
    private String mMockFolder = "";

    /**
     * set mock's folder
     * @param mockFolder
     */
    public void setMockFolder(String mockFolder) {
        this.mMockFolder = mockFolder == null ? "" : mockFolder;
    }

    /**
     * mock data
     * @param ctx
     * @param url
     * @param cb
     * @param intercept
     */
    public void mockData(Context ctx, String url, RCallback<String> cb, RIntercept intercept) {
        try {
            String path;
            String domain1 = RHttpConfig.getDomain();
            String domain2 = RCloudMock.getDomain();
            if(url.contains(domain1)) {
                path = url.substring(domain1.length());
            } else if(url.contains(domain2)) {
                path = url.substring(domain2.length());
            } else {
                URL newUrl = new URL(url);
                path = newUrl.getPath();
            }

            if(path.startsWith("//")) {
                path = path.substring(2);
            } else if(path.startsWith("/")) {
                path = path.substring(1);
            }
            path = path.replaceAll("/", "-");
            RLog.d(RLog.LIB_TGA, "mock reflect path= "+path);
            readData(ctx, path, cb, intercept);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            RLog.d(RLog.LIB_TGA, "mock path parse fail."+e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            RLog.d(RLog.LIB_TGA, "mock path read fail."+e.getMessage());
        }
    }

    /**
     * read data
     * @param ctx
     * @param path
     * @param cb
     * @param intercept
     * @throws IOException
     */
    private void readData(Context ctx, String path, RCallback<String> cb,
                          RIntercept intercept) throws IOException {
        InputStream is = ctx.getResources().getAssets().open(mMockFolder+"/"+path);
        Scanner scanner = new Scanner(is, "UTF-8");
        String text = scanner.useDelimiter("\\A").next();
        scanner.close();

        if(intercept == null) {
            cb.onSuccess(text,null);
        } else {
            SimpleIntercept.ProcessModel pm = intercept.process(text);
            if (pm.isSuccess) {
                cb.onSuccess(pm.body,null);
            }
        }
    }

}
