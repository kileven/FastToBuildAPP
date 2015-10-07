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
		
		//���ò����һЩ����
		SlidingMenu left_menu=getSlidingMenu();//��ȡ���������
		left_menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//���ô�����ʽΪȫ������
		left_menu.setBehindOffset(200);//���ò�����Ŀ��
		
		//ʹ��fragment���framelayout
		FragmentManager fm=getSupportFragmentManager();
		FragmentTransaction transaction=fm.beginTransaction();
		transaction.replace(R.id.fl_leftmenu, new LeftMenuFragment());
		transaction.replace(R.id.fl_main, new ContentFargment());
		transaction.commit();
		
	}
}
