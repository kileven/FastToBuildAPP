package com.lenuy.fasttobuildapp;

import com.lenuy.fasttobuildapp.utils.SharedPrefUtils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				boolean guid=SharedPrefUtils.getBooleanFromSharedPrefrences(SplashActivity.this, "guid");
				Intent intent;
				if(guid){
					intent=new Intent(SplashActivity.this,MainActivity.class);
				}
				else{
					intent=new Intent(SplashActivity.this,GuidActivity.class);
				}
				startActivity(intent);
				finish();
			}
		}, 3000);
	}
}
