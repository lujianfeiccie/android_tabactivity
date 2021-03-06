package com.lujianfei.sample_tabactivity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TabWidget;
import android.widget.TextView;

/**
 * @ClassName: TabHome
 * @Description: 应用程序主界面
 * @author hepengcheng
 * @date 2014-10-31 上午11:42:53
 * 
 */
public class TabHome extends TabHostActivity{
	// 控件
	private List<TabItem> mItems;

	@Override
	protected void prepare() {
		//文本，文本图标，文本背景，跳转类
		TabItem tab1 = new TabItem(getString(R.string.didi_text1),
				R.drawable.selector_index_tab1,
				0, new Intent(
						getApplicationContext(), TabActivity1.class));
		TabItem tab2 = new TabItem(getString(R.string.didi_text2), // title
				R.drawable.selector_index_tab2, // icon
				0, 
				new Intent(getApplicationContext(), TabActivity2.class)); // intent
		mItems = new ArrayList<TabItem>();
		mItems.add(tab1);
		mItems.add(tab2);
		
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
			TabWidget tabWidget = getTabWidget();
			tabWidget.setDividerDrawable(R.drawable.index_tab_normal);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setCurrentTab(0);
	}

	@Override
	protected void setTabItemTextView(TextView textView, int position) {
		textView.setPadding(0, 15, 0, 5);
		textView.setText(mItems.get(position).getTitle());
		textView.setBackgroundResource(mItems.get(position).getBg());
		textView.setCompoundDrawablesWithIntrinsicBounds(0, mItems
				.get(position).getIcon(), 0, 0);
	}

	@Override
	protected String getTabItemId(int position) {
		return mItems.get(position).getTitle();
	}

	@Override
	protected Intent getTabItemIntent(int position) {
		return mItems.get(position).getIntent();
	}

	@Override
	protected int getTabItemCount() {
		return mItems.size();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mItems.clear();
		mItems = null;
		System.gc();
	}
}
