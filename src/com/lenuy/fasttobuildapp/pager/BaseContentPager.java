package com.lenuy.fasttobuildapp.pager;

import com.lenuy.fasttobuildapp.R;

import android.content.Context;
import android.view.View;

public class BaseContentPager {
	public View mBasePager;
	private Context mContext;
	public BaseContentPager(Context context) {
		mContext=context;
		initViews();
	}
	public void initViews()
	{
		mBasePager=View.inflate(mContext, R.layout.base_content_pager, null);
		
	}
	public void initData()
	{
		
	}
}
