package com.lenuy.fasttobuildapp.view;

import com.lenuy.fasttobuildapp.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RefrshListView extends ListView implements OnScrollListener{

	private int startY=-1;
	private int endY;
	private int mHeight;
	private View mHead_view;
	private TextView tv_state;
	private ProgressBar pb_refresh;
	private static final int PULL_TO_REFRESH=0;
	private static final int RELEASE_TO_REFRESH=1;
	private static final int REFRESHING=2;
	private int currentState=PULL_TO_REFRESH;
	private boolean isLoading=false;
	
	private OnRefreshListListener mListRefresh;
	private View mFootView;
	private int mFoot;

	public RefrshListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initHeadViews();
		initFootViews();
	}

	public RefrshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initHeadViews();
		initFootViews();
	}

	public RefrshListView(Context context) {
		super(context);
		initHeadViews();
		initFootViews();
	}
	
	private void initFootViews() {
		mFootView=View.inflate(getContext(), R.layout.refresh_listview_footer, null);
		mFootView.measure(0, 0);
		mFoot = mFootView.getMeasuredHeight();
		mFootView.setPadding(0, -mFoot, 0, 0);
		this.addFooterView(mFootView);	
		this.setOnScrollListener(this);
	}

	private void initHeadViews()
	{
		mHead_view=View.inflate(getContext(), R.layout.refresh_head, null);	
		tv_state=(TextView) mHead_view.findViewById(R.id.tv_state);
		pb_refresh=(ProgressBar) mHead_view.findViewById(R.id.pb_refresh);
		mHead_view.measure(0, 0);
		mHeight = mHead_view.getMeasuredHeight();
		mHead_view.setPadding(0, -mHeight, 0, 0);
		this.addHeaderView(mHead_view);	
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startY=(int)ev.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			if(startY==-1){//ȷ��Y��������Ч
				startY=(int)ev.getRawY();
			}
			endY=(int)ev.getRawY();
			int dy=endY-startY;  //��ȡλ��
			if(currentState==REFRESHING){
				//�����ǰ״̬������ˢ�£���������
				break;
			}
			if(dy>0 && getFirstVisiblePosition()==0){
				System.out.println("���»���dy:"+dy);
				//���»��� �ҵ�һ���ɼ��Ķ����ǵ�һ��
				mHead_view.setPadding(0, dy-mHeight, 0, 0);
				int padding=mHead_view.getPaddingTop();
				if(padding>0){
					currentState=RELEASE_TO_REFRESH;
				}
				else{
					currentState=PULL_TO_REFRESH;
				}			
				if(padding>mHeight){//����һ����ֵ����Ҫ���û���ֹ��������
					mHead_view.setPadding(0, mHeight, 0, 0);
				}
				refreshState(currentState);
				return true;
			}
			break;
		case MotionEvent.ACTION_UP:
			startY=-1;
			if(currentState==RELEASE_TO_REFRESH){
				currentState=REFRESHING;
				mHead_view.setPadding(0, 0, 0, 0);
				if(mListRefresh!=null)
					mListRefresh.onRefreshing();
			}
			else if(currentState==PULL_TO_REFRESH){
				//�û�������ˢ��״̬�£��ɿ��֣�ֱ����headView���أ������ع����п��Լ��붯��
				mHead_view.setPadding(0, -mHeight, 0, 0);
			}
			refreshState(currentState);
			break;

		default:
			break;
		}
		return super.onTouchEvent(ev);
	}
	
	private void refreshState(int state)
	{
		switch (state) {
		case PULL_TO_REFRESH:
			tv_state.setText("����ˢ��");
			pb_refresh.setVisibility(View.INVISIBLE);
			break;
		case RELEASE_TO_REFRESH:
			tv_state.setText("�ɿ�ˢ��");	
			pb_refresh.setVisibility(View.INVISIBLE);
			break;
		case REFRESHING:
			tv_state.setText("����ˢ��...");
			pb_refresh.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}
	public void setOnRefreshListListener(OnRefreshListListener listener){
		mListRefresh=listener;
	}
	
	public interface OnRefreshListListener{
		public void onRefreshing();
		public void onLoadMore(); 
	}
	
	public void OnRefreshComplete()
	{
		if(isLoading){
			mFootView.setPadding(0, -mFoot, 0, 0);
			isLoading=false;
		}
		else{
			currentState=PULL_TO_REFRESH;
			mHead_view.setPadding(0, -mHeight, 0, 0);
			pb_refresh.setVisibility(View.INVISIBLE);
			tv_state.setText("����ˢ��");	
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if(scrollState==SCROLL_STATE_IDLE||scrollState==SCROLL_STATE_FLING){
			if(getLastVisiblePosition()==getCount()-1 && isLoading==false){
				//��ʾ���������һ��
				System.out.println("�����ˡ�������");
				mFootView.setPadding(0, 0, 0, 0);
				setSelection(getCount()-1);
				if(mListRefresh!=null)
					mListRefresh.onLoadMore();
				isLoading=true;
			}
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		
	}
}
