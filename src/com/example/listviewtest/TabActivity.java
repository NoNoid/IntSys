package com.example.listviewtest;

import com.example.shuetzenApp.R;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar.Tab;
import android.view.Menu;
import android.view.MenuItem;

public abstract class TabActivity extends ActionBarActivity {

	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;

	abstract protected PagerAdapter getPagerAdapter();
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_list);
		 
		// Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = getPagerAdapter();
        mPager.setAdapter(mPagerAdapter);
        
        setUpActionBar();
	}


	private void setUpActionBar() {
		final ActionBar actionbar = getSupportActionBar();
        
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.
            	actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
                actionbar.setSelectedNavigationItem(position);
            }
        });
        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
            actionbar.addTab(
                    actionbar.newTab()
                            .setText(mPagerAdapter.getPageTitle(i))
                            .setTabListener(new TabListener() {
								
								@Override
								public void onTabUnselected(Tab tab, FragmentTransaction ft) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void onTabSelected(Tab tab, FragmentTransaction ft) {
									mPager.setCurrentItem(tab.getPosition());
									
								}
								
								@Override
								public void onTabReselected(Tab tab, FragmentTransaction ft) {
									// TODO Auto-generated method stub
									
								}
							}));
        }
	}
	
	public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(getTitle());
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		restoreActionBar();
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
		return super.onOptionsItemSelected(item);
	}
}
