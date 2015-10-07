package com.lenuy.fasttobuildapp;

import com.lenuy.fasttobuildapp.utils.SharedPrefUtils;
import com.viewpagerindicator.CirclePageIndicator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class GuidActivity extends Activity {
	
	private static final int[] imageSrc={R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
	private ViewPager vp_guid;
	private CirclePageIndicator vp_indicator;
	private Button btn_start;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guid);
		vp_guid = (ViewPager) findViewById(R.id.vp_guid);
		vp_indicator = (CirclePageIndicator) findViewById(R.id.vp_indicator);
		
		vp_guid.setAdapter(new GuidAdapter()); //为ViewPager设置适配器
		vp_indicator.setViewPager(vp_guid); //将indicator和ViewPager关联
		btn_start = (Button) findViewById(R.id.btn_start);
		
		btn_start.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SharedPrefUtils.setBooleanToSharedPrefrences(GuidActivity.this, "guid", true);
				Intent intent=new Intent(GuidActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
		});//button这只监听
		
		vp_indicator.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				if(arg0!=imageSrc.length-1){
					btn_start.setVisibility(View.INVISIBLE);
				}
				else{
					btn_start.setVisibility(View.VISIBLE);
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});//CirclePageIndicator设置监听，这样方便判断按键是否显示
	}
	
	class GuidAdapter extends PagerAdapter
	{

		@Override
		public int getCount() {
			return imageSrc.length;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView image=new ImageView(GuidActivity.this);
			image.setBackgroundResource(imageSrc[position]);
			container.addView(image);
			return image;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((ImageView)object);
		}
	}
}
