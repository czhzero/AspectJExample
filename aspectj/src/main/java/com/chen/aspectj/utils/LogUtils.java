package com.chen.aspectj.utils;

import android.util.Log;

/**
 * Created by chenzhaohua on 17-8-2.
 */

public class LogUtils {

    public static final String TAG = "czh";

    public static void d(String tag, String msg) {
        Log.d(tag, msg);
    }

    public static void d(String msg) {
        Log.d(TAG, msg);
    }
}
