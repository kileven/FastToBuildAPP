package com.lenuy.fasttobuildapp;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.lenuy.fasttobuildapp.fragment.ContentFargment;
import com.lenuy.fasttobuildapp.fragment.LeftMenuFragment;


public class MainActivity extends SlidingFragmentActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.layout_main);
		setBehindContentView(R.layout.layout_leftmenu);
		
		//设置侧边栏一些属性
		SlidingMenu left_menu=getSlidingMenu();//获取侧边栏对象
		left_menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置触摸方式为全屏触摸
		left_menu.setBehindOffset(200);//设置侧边栏的宽度
		
		//使用fragment填充framelayout
		FragmentManager fm=getSupportFragmentManager();
		FragmentTransaction transaction=fm.beginTransaction();
		transaction.replace(R.id.fl_leftmenu, new LeftMenuFragment());
		transaction.replace(R.id.fl_main, new ContentFargment());
		transaction.commit();
		
	}
}
