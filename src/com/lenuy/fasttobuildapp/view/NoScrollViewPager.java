package com.lenuy.fasttobuildapp.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScrollViewPager extends ViewPager {

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NoScrollViewPager(Context context) {
		super(context);
	}
	/**
	 * 表示事件是否拦截，返回false表示布不拦截，可以让嵌套的viewpager响应滑动事件
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		
		return false;
	}
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		return false;
	}

}
