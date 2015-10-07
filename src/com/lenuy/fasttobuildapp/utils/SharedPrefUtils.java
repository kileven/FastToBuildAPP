package com.lenuy.fasttobuildapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPrefUtils {
	public static boolean getBooleanFromSharedPrefrences(Context context,String key)
	{
		SharedPreferences sp=context.getSharedPreferences("config", context.MODE_PRIVATE);
		return sp.getBoolean(key, false);
	}
	
	public static void setBooleanToSharedPrefrences(Context context,String key,boolean value)
	{
		SharedPreferences sp=context.getSharedPreferences("config", context.MODE_PRIVATE);
		Editor editor=sp.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
}
