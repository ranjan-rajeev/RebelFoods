package com.horizonlabs.rebelfoods.utils;

import android.util.Log;

import com.horizonlabs.rebelfoods.BuildConfig;

/**
 * Created by Rajeev Ranjan -  ABPB on 20-07-2019.
 */

public class Logger {
    public static final String TAG = "Logger";
    private static boolean DEBUG_ENABLED = BuildConfig.DEBUG;

    private Logger() {
    }

    public static void v(String msg) {
        if (isDebugEnabled()) {
            Log.v(TAG, msg);
        }

    }


    public static void v(String tag, String msg) {
        if (isDebugEnabled()) {
            Log.v(tag, msg);
        }

    }

    public static void d(String msg) {
        if (isDebugEnabled()) {
            Log.d(TAG, msg);
        }

    }


    public static void d(String tag, String msg) {
        if (isDebugEnabled()) {
            Log.d(tag, msg);
        }

    }

    public static void i(String msg) {
        if (isDebugEnabled()) {
            Log.i(TAG, msg);
        }

    }


    public static void i(String tag, String msg) {
        if (isDebugEnabled()) {
            Log.i(tag, msg);
        }

    }

    public static void w(String msg) {
        if (isDebugEnabled()) {
            Log.w(TAG, msg);
        }

    }


    public static void w(String tag, String msg) {
        if (isDebugEnabled()) {
            Log.w(tag, msg);
        }

    }

    public static void e(String msg) {

        if (isDebugEnabled()) {
            Log.e(TAG, msg);
        }

    }


    public static void e(String tag, String msg) {
        if (isDebugEnabled()) {
            Log.e(tag, msg);
        }

    }

    public static void f(String msg) {
    }


    public static void f(String tag, String msg) {
    }

    public static boolean isDebugEnabled() {
        return DEBUG_ENABLED;
    }

    public static void setLogStatus(boolean value) {
        DEBUG_ENABLED = value;
    }
}
