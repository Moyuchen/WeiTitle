package com.bwie.test.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.bwie.test.MyApp;

/**
 * User: 张亚博
 * Date: 2017-09-01 15:10
 * Description：
 */
public class SharepreferencesUtils {
    private  final static String KEY="common_data";

    /**
     * 获取sharedpreferences对象
     * @return
     */
    public static SharedPreferences getSharepreferences() {
        return MyApp.Mcontext.getSharedPreferences(KEY, Context.MODE_PRIVATE);
    }

    /**
     * 添加注册对象
     * @param key
     * @param value
     */
    public static void putSharepreferences(String key,String value){
        SharedPreferences.Editor edit = getSharepreferences().edit();
        edit.putString(key,value);
        edit.commit();

    }

    /**
     * 获取value值
     * @param key
     * @return
     */

    public static  String getSharedPreferencesValue(String key){
        SharedPreferences sharepreferences = getSharepreferences();
        String value = sharepreferences.getString(key, null);

        return value;

    }

    /**
     * 清理指定的数据
     * @param key
     */
    public static void clearSharedPreference(String key){
        SharedPreferences sharepreferences = getSharepreferences();

        SharedPreferences.Editor edit = sharepreferences.edit();
        edit.remove(key);
        edit.commit();
    }

    /**
     * 清理所有数据
     */
    public static void clearSharedPreferences(){

        SharedPreferences sharepreferences = getSharepreferences();

        SharedPreferences.Editor edit = sharepreferences.edit();
        SharedPreferences.Editor clear = edit.clear();
        clear.commit();

    }

}
