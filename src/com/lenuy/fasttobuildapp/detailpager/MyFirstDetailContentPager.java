package com.lenuy.fasttobuildapp.detailpager;

import com.lenuy.fasttobuildapp.R;
import com.lenuy.fasttobuildapp.view.RefrshListView;
import com.viewpagerindicator.CirclePageIndicator;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyFirstDetailContentPager extends BaseDetailContentPager {

	private static final int[] imageList={R.drawable.image01,R.drawable.image02,R.drawable.image03,R.drawable.image04};
	private RefrshListView rlv_detail_info;
	private ViewPager vp_top;
	private CirclePageIndicator circle_indicator;
	private TextView tv_titles;

	public MyFirstDetailContentPager(Context context) {
		super(context);	
		
		rlv_detail_info.setAdapter(new RefrshListViewAdapter());
		
		View view=View.inflate(mContext, R.layout.top_view_pager, null);
		vp_top = (ViewPager) view.findViewById(R.id.vp_top);
		circle_indicator=(CirclePageIndicator)view.findViewById(R.id.circle_indicator);
		tv_titles=(TextView)view.findViewById(R.id.tv_titles);
		vp_top.setAdapter(new PagerAdapter() {
			
			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0==arg1;
			}
			
			@Override
			public int getCount() {
				return imageList.length;
			}
			
			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				ImageView iv=new ImageView(mContext);
				iv.setBackgroundResource(imageList[position]);
				container.addView(iv);
				return iv;
			}
			
			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView((View)object);
			}
		});
		circle_indicator.setViewPager(vp_top);
		circle_indicator.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				tv_titles.setText("title"+arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
		rlv_detail_info.addHeaderView(view);
	}

	@Override
	public View initViews() {
		View view=View.inflate(mContext, R.layout.my_detail_content_pager, null);
		rlv_detail_info = (RefrshListView) view.findViewById(R.id.rlv_detail_info);
		return view;
	}
	
	@Override
	public void initData() {
		
	}
	
	class RefrshListViewAdapter extends BaseAdapter
	{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 10;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView==null){
				convertView=View.inflate(mContext, R.layout.refresh_listview_item, null);
				
			}
			return convertView;
		}
		
	}

}
