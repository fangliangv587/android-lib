package com.xz.momo.common.http.callback;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.xz.momo.common.log.RLog;

import okhttp3.Headers;

/**
 * Created by Administrator on 2017/5/14.
 */

public abstract class XCallBack implements RCallback<String> {

    private static final int success = 1;
    private static final int fail = 0;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case success:

                    finish();

                    ResponseObj obj = (ResponseObj) msg.obj;

                    success(obj.body, obj.headers);


                    break;
                case fail:
                    finish();
                    fail(msg.arg1, (String) msg.obj);
                    break;
            }
        }
    };

    @Override
    public void onSuccess(String body, Headers headers) {

        ResponseObj obj = new ResponseObj();
        obj.body = body;
        obj.headers = headers;

        Message mes = Message.obtain();
        mes.what = success;
        mes.obj = obj;
        handler.sendMessage(mes);
    }

    @Override
    public void onFailure(int code, String errMsg) {
        Message mes = Message.obtain();
        mes.what = fail;
        mes.arg1 = code;

        mes.obj = errMsg;
        handler.sendMessage(mes);

        RLog.e(errMsg);
    }


    public abstract void success(String result, Headers headers);

    public abstract void fail(int code, String errMsg);

    public abstract void finish();

    class ResponseObj {
        private String body;
        private Headers headers;
    }

}
