package com.lenuy.fasttobuildapp.fragment;

import com.lenuy.fasttobuildapp.R;

import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LeftMenuFragment extends BaseFragment {
	
	private static final String[] items={"item1","item2","item3","item4"};
	private ListView lv_leftmenu;
	@Override
	public View initViews() {
		
		View view=View.inflate(mActivity, R.layout.leftmenu_fargment, null);
		lv_leftmenu=(ListView) view.findViewById(R.id.lv_leftmenu);
		return view;
	}
	
	@Override
	public void initData() {
		lv_leftmenu.setAdapter(new SlidingMenuAdpter());
	}
	
	class SlidingMenuAdpter extends BaseAdapter
	{

		@Override
		public int getCount() {
			return items.length;
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view=View.inflate(mActivity, R.layout.leftmenu_item, null);
			TextView tv=(TextView) view.findViewById(R.id.lv_leftmenu_item);
			tv.setText(items[position]);
			return view;
		}
		
	}

}
