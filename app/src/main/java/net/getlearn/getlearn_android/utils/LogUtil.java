package net.getlearn.getlearn_android.utils;

import android.util.Log;

/**
 * ------author----------日期--------改动-------
 * ------${CYP}--------2019/6/17------更新------
 * 日志打印util
 */

public class LogUtil {

        private static String TAG = "__GetLearn";
        //开关
        private static boolean debug = true;

        private LogUtil() {

        }
        public static void setTAG(String TAG) {
            LogUtil.TAG = TAG;
        }

        public static void setDebug(boolean debug) {
            LogUtil.debug = debug;
        }

        public static void v(String msg) {
            if (debug)
                Log.v(TAG, String.valueOf(msg));
        }

        public static void d(String msg) {
            if (debug)
                Log.d(TAG, String.valueOf(msg));
        }

        public static void i(String msg) {
            if (debug)
                Log.i(TAG, String.valueOf(msg));
        }

        public static void w(String msg) {
            if (debug)
                Log.w(TAG, String.valueOf(msg));
        }

        public static void e(String msg) {
            if (debug)
                Log.e(TAG, String.valueOf(msg));
        }

    //自己传入tag
    public static void v(String tag,String msg) {
        if (debug)
            Log.v(tag, String.valueOf(msg));
    }

    public static void d(String tag,String msg) {
        if (debug)
            Log.d(tag, String.valueOf(msg));
    }

    public static void i(String tag,String msg) {
        if (debug)
            Log.i(tag, String.valueOf(msg));
    }

    public static void w(String tag,String msg) {
        if (debug)
            Log.w(tag, String.valueOf(msg));
    }

    public static void e(String tag,String msg) {
        if (debug)
            Log.e(tag, String.valueOf(msg));
    }

}
