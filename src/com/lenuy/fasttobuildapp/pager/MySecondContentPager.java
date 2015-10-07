package com.lenuy.fasttobuildapp.pager;

import com.lenuy.fasttobuildapp.R;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

public class MySecondContentPager extends BaseContentPager {
	
	private FrameLayout fl_content;
	private Context mContext;
	public MySecondContentPager(Context context) {
		super(context);
		this.mContext=context;
	}
	
	@Override
	public void initData() {
		fl_content = (FrameLayout) mBasePager.findViewById(R.id.fl_content);
		View view=View.inflate(mContext, R.layout.second_content_pager, null);
		fl_content.addView(view);
	}

}
