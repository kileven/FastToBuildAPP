package com.lenuy.fasttobuildapp.detailpager;

import com.lenuy.fasttobuildapp.R;

import android.content.Context;
import android.view.View;

public abstract class BaseDetailContentPager {
	public View mDetailPager;
	protected Context mContext;
	public BaseDetailContentPager(Context context)
	{
		mContext=context;
		mDetailPager=initViews();
		
	}
	public abstract View initViews();
	public void initData()
	{
		
	}
}
