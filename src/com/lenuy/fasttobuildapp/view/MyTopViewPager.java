package com.lenuy.fasttobuildapp.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyTopViewPager extends ViewPager {

	private int startX;
	private int startY;
	private int endX;
	private int endY;

	public MyTopViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyTopViewPager(Context context) {
		super(context);
	}
	
	//请求父控件以及祖先控件不要拦截事件
	/**
	 * 1.当前界面位于第一个，且向右滑动时需要父控件拦截，自己不处理
	 * 2.当前界面位于最后一个时，且向左滑动时，需要父控件拦截，自己不处理
	 * 3.上下滑动时，需要需要父控件拦截，自己不处理
	 */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
    	switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			getParent().requestDisallowInterceptTouchEvent(true);//父控件不要拦截，否则拿不到坐标
			startX = (int) event.getRawX();
			startY = (int) event.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			endX = (int) event.getRawX();
			endY = (int) event.getRawY();
			int dx=endX-startX;
			int dy=endY-startY;
			if(Math.abs(dy)>Math.abs(dx)){
				//上下滑动
				getParent().requestDisallowInterceptTouchEvent(false);//父控件要拦截，我不处理
			}
			else{
				//右滑动
				if(dx>0)
				{
					if(getCurrentItem()==0){
						//当前界面位于第一个，且向右滑动时 拦截
						getParent().requestDisallowInterceptTouchEvent(false);
					}
					else{
						//不拦截
						getParent().requestDisallowInterceptTouchEvent(true);
					}
				}
				//左滑动
				else{
					if(getCurrentItem()==getAdapter().getCount()-1 && dx<0){
						//当前界面位于最后一个时，且向左滑动  拦截
						getParent().requestDisallowInterceptTouchEvent(false);
					}
					else{
						//不拦截
						getParent().requestDisallowInterceptTouchEvent(true);
					}
				}
			}
			break;
		default:
			break;
		}
    	return super.dispatchTouchEvent(event);
    }
}
