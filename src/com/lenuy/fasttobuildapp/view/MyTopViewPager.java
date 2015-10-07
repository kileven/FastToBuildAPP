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
	
	//���󸸿ؼ��Լ����ȿؼ���Ҫ�����¼�
	/**
	 * 1.��ǰ����λ�ڵ�һ���������һ���ʱ��Ҫ���ؼ����أ��Լ�������
	 * 2.��ǰ����λ�����һ��ʱ�������󻬶�ʱ����Ҫ���ؼ����أ��Լ�������
	 * 3.���»���ʱ����Ҫ��Ҫ���ؼ����أ��Լ�������
	 */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
    	switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			getParent().requestDisallowInterceptTouchEvent(true);//���ؼ���Ҫ���أ������ò�������
			startX = (int) event.getRawX();
			startY = (int) event.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			endX = (int) event.getRawX();
			endY = (int) event.getRawY();
			int dx=endX-startX;
			int dy=endY-startY;
			if(Math.abs(dy)>Math.abs(dx)){
				//���»���
				getParent().requestDisallowInterceptTouchEvent(false);//���ؼ�Ҫ���أ��Ҳ�����
			}
			else{
				//�һ���
				if(dx>0)
				{
					if(getCurrentItem()==0){
						//��ǰ����λ�ڵ�һ���������һ���ʱ ����
						getParent().requestDisallowInterceptTouchEvent(false);
					}
					else{
						//������
						getParent().requestDisallowInterceptTouchEvent(true);
					}
				}
				//�󻬶�
				else{
					if(getCurrentItem()==getAdapter().getCount()-1 && dx<0){
						//��ǰ����λ�����һ��ʱ�������󻬶�  ����
						getParent().requestDisallowInterceptTouchEvent(false);
					}
					else{
						//������
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
