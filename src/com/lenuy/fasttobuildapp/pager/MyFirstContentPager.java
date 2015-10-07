package com.lenuy.fasttobuildapp.pager;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lenuy.fasttobuildapp.MainActivity;
import com.lenuy.fasttobuildapp.R;
import com.lenuy.fasttobuildapp.detailpager.BaseDetailContentPager;
import com.lenuy.fasttobuildapp.detailpager.MyFirstDetailContentPager;
import com.viewpagerindicator.TabPageIndicator;

public class MyFirstContentPager extends BaseContentPager {
	
	private static final String[] pagerTitle={"pager1","pager2","pager3","pager4"};
	private ArrayList<BaseDetailContentPager> mDetailPagerList;
	private FrameLayout fl_content;
	private Context mContext;
	private ViewPager vp_detail;
	private TabPageIndicator tb_indicator;
	private ImageButton ib_next_item;
	public MyFirstContentPager(Context context) {
		super(context);
		this.mContext=context;
	}
	
	@Override
	public void initData() {
		fl_content = (FrameLayout) mBasePager.findViewById(R.id.fl_content);
		View view=View.inflate(mContext, R.layout.first_content_pager, null);
		vp_detail = (ViewPager) view.findViewById(R.id.vp_detail);
		tb_indicator = (TabPageIndicator) view.findViewById(R.id.tb_indicator);
		ib_next_item = (ImageButton) view.findViewById(R.id.ib_next_item);
		ib_next_item.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int currentItem=vp_detail.getCurrentItem();
				vp_detail.setCurrentItem(++currentItem);
			}
		});
		mDetailPagerList=new ArrayList<BaseDetailContentPager>();
		
		for(int i=0;i<4;i++){
			mDetailPagerList.add(new MyFirstDetailContentPager(mContext));
		}
		
		vp_detail.setAdapter(new DetailPager());
		tb_indicator.setViewPager(vp_detail);
		tb_indicator.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				MainActivity mainUi=(MainActivity)mContext;
				SlidingMenu menu=mainUi.getSlidingMenu();
				System.out.println("setOnPageChangeListener:"+arg0);
				if(arg0==0){
					menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
				}
				else{
					menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		
		fl_content.addView(view);
	}
	
	class DetailPager extends PagerAdapter
	{

		@Override
		public int getCount() {
			return mDetailPagerList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			BaseDetailContentPager pager=mDetailPagerList.get(position);
			pager.initData();
			View view=pager.mDetailPager;
			container.addView(view);
			return view;
			
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		}
		
		@Override
		public CharSequence getPageTitle(int position) {	
			return pagerTitle[position];
		}
	}

}
