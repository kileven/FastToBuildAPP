package com.lenuy.fasttobuildapp.fragment;

import java.util.ArrayList;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lenuy.fasttobuildapp.MainActivity;
import com.lenuy.fasttobuildapp.R;
import com.lenuy.fasttobuildapp.pager.BaseContentPager;
import com.lenuy.fasttobuildapp.pager.MyFirstContentPager;
import com.lenuy.fasttobuildapp.pager.MyFourthContentPager;
import com.lenuy.fasttobuildapp.pager.MySecondContentPager;
import com.lenuy.fasttobuildapp.pager.MyThirdContentPager;
import com.lenuy.fasttobuildapp.view.NoScrollViewPager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class ContentFargment extends BaseFragment {
	
	private ArrayList<BaseContentPager> mPagerList;
	private NoScrollViewPager noscroll_vp_content;
	private RadioGroup rg_bottom_tab;
	private ImageButton ib_switch;
	@Override
	public View initViews() {
		View view=View.inflate(mActivity, R.layout.content_fragment, null);
		noscroll_vp_content=(NoScrollViewPager) view.findViewById(R.id.noscroll_vp_content);
		rg_bottom_tab = (RadioGroup) view.findViewById(R.id.rg_bottom_tab);
		return view;
	}
	
	@Override
	public void initData() {
		noscroll_vp_content.setAdapter(new NoScrollViewPagerAdapter());
	
		rg_bottom_tab.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rb_item01:
					noscroll_vp_content.setCurrentItem(0, false);
					break;
				case R.id.rb_item02:
					noscroll_vp_content.setCurrentItem(1, false);				
					break;
				case R.id.rb_item03:
					noscroll_vp_content.setCurrentItem(2, false);
					break;
				case R.id.rb_item04:
					noscroll_vp_content.setCurrentItem(3, false);
					break;
				default:
					break;
				}
			}
		});
		
		mPagerList=new ArrayList<BaseContentPager>();
		//这里写了第一页，后面几页使用BaseContentPager
		mPagerList.add(new MyFirstContentPager(mActivity));
		mPagerList.add(new MySecondContentPager(mActivity));
		mPagerList.add(new MyThirdContentPager(mActivity));
		mPagerList.add(new MyFourthContentPager(mActivity));
		
		
		
	}
	
	class NoScrollViewPagerAdapter extends PagerAdapter implements OnClickListener
	{	
		@Override
		public int getCount() {
			return mPagerList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0==arg1;
		}
		
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			BaseContentPager pager=mPagerList.get(position);
			View view=pager.mBasePager;
			TextView tv=(TextView) view.findViewById(R.id.tv_title);
			ib_switch = (ImageButton) view.findViewById(R.id.ib_switch);
			ib_switch.setOnClickListener(this);
			tv.setText("item"+position);
			pager.initData();
			container.addView(view);
			return view;
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View)object);
		}
		
		@Override
		public void onClick(View v) {
			MainActivity mainUi=(MainActivity) mActivity;
			SlidingMenu menu=mainUi.getSlidingMenu();
			menu.toggle();
		}
	}

}
