package com.hq.uitest.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 *
 * @author heqiang
 */
public abstract class BaseApplication extends Application {
    private static SharedPreferences mPreferences;
    private static Context instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mPreferences = this.getSharedPreferences("data", Context.MODE_PRIVATE);
        create();
    }

    protected abstract void create();

    /**
     * 对外提供Application Context
     * @return
     */
    public static Context getContext() {
        return instance;
    }



    /**
     *  SharedPreferences
     */
    public static void putValueByKey(String key, String value) {
        mPreferences.edit().putString(key, value).commit();
    }

    public static void putValueByKey(String key, boolean value) {
        mPreferences.edit().putBoolean(key, value).commit();
    }

    public static void putValueByKey(String key, int value) {
        mPreferences.edit().putInt(key, value).commit();
    }

    public static String getValueByKey(String key, String defValue) {
        return mPreferences.getString(key, defValue);
    }

    public static boolean getValueByKey(String key, boolean defValue) {
        return mPreferences.getBoolean(key, defValue);
    }

    public static int getValueByKey(String key, int defValue) {
        return mPreferences.getInt(key, defValue);
    }

    public static long getValueByKey(String key, long defValue) {
        return mPreferences.getLong(key, defValue);
    }
    /**
     * Object Set
     *
     * @param name
     * @param obj
     */
    public static void putValueByKey(String name, Object obj) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            String obj_Base64 = new String(Base64.encodeToString(baos.toByteArray(),Base64.DEFAULT));
            mPreferences.edit().putString(name, obj_Base64).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Object Get
     *
     * @param name
     * @return
     */
    public static Object getValueByKey(String name) {

        Object obj = null;
        try {
            String data = mPreferences.getString(name, " ");
            if ((null == data || " ".equals(data))) {
                return null;
            }
            byte[] base64 = Base64.decode(data.getBytes(),Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64);
            ObjectInputStream bis = new ObjectInputStream(bais);
            obj = bis.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }




}
