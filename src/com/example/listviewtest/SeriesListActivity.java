package com.example.listviewtest;

import com.example.listviewtest.Fragments.Fragment_statisitcs;
import com.example.shuetzenApp.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

public class SeriesListActivity extends TabActivity {

	private final int NUM_PAGES = 2;
	@Override
	protected PagerAdapter getPagerAdapter() {
		return new ScreenSlidePagerAdapter(getSupportFragmentManager());
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		//setContentView(R.layout.activity_serieslist);
		super.onCreate(savedInstanceState);
	}
	
	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
          if(position == 0)
          {
        	  return new SeriesList();
          }
          else
          {
        	  return new Fragment_statisitcs();
          }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
        
        @Override
        public CharSequence getPageTitle(int position) {
            String title = "";
        	switch(position)
            {
            case 0:
            	title = getString(R.string.title_tab_session_browser);
            	break;
            case 1:
            	title = getString(R.string.title_tab_statistics);
            	break;
            }
        	return title;
        }
    }

}
