package com.example.kotlin.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.securepreferences.SecurePreferences;

/**
 * Created by fuyuxian on 2016/8/9.
 * SharePreference工具类，提供两种类型的sharePreference，一种是系统的，另一种是加密的
 */
public class PreferenceUtil {

    private static final String DEFAULT = "DEFAULT";
    private static final String SECURE = "SECURE";

    private static boolean sSecurePreferenceInited = false;

    private static SecurePreferences sSecurePreferences;
    private static SharedPreferences sSharedPreferences;

    private static final Object sLock = new Object();


    public static SharedPreferences getDefaultPreference(final Context context) {
        return context.getSharedPreferences(DEFAULT, Context.MODE_PRIVATE);
    }


    public static synchronized SharedPreferences getSecurePreference() {
        synchronized (sLock) {
            if (sSecurePreferences == null ) {
                if (sSecurePreferenceInited) {
                    return sSharedPreferences;
                }
                throw new IllegalStateException(
                        "initSecurePreference must be called at least once!!!");
            }
            return sSecurePreferences;
        }
    }

    /**
     * 初始化SecurePreferences，应该在应用启动的时候调用
     *
     * @param context
     */
    public static void initSecurePreference(final Context context) {
        synchronized (sLock) {
            sSecurePreferenceInited = true;
            if (sSecurePreferences != null) return;
            try {
                sSecurePreferences = new SecurePreferences(context, "4234dfse2332r", SECURE);
            } catch (Exception e) { //部分机型不支持特点的加密算法，导致崩溃
                sSharedPreferences = context.getSharedPreferences(DEFAULT, Context.MODE_PRIVATE);
                e.printStackTrace();
            }
        }
    }
}
