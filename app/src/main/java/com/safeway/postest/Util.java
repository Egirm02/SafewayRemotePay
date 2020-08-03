package com.safeway.postest;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;

public class Util {

    public static final String APP_PREFERENCE = "preference";
    public static final String USER_NAME = "user_name";
    public static final String STORE_ID = "storeId";

    public static void saveString(Context context, String key, String value){
        SharedPreferences sharedPref = context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        sharedPref.edit().putString(key, value).commit();

    }


    public static String getString(Context context, String key, String defaultValue){

        SharedPreferences sharedPref = context.getSharedPreferences(APP_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPref.getString(key, defaultValue);

    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }
}
