package com.example.listviewtest.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.listviewtest.SessionList;
import com.example.shuetzenApp.R;

public class Fragment_ViewPager_Session_Statisitcs extends Fragment {

	
    public static final int NUM_PAGES = 2;
	private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    
    @Override
    public void onActivityCreated(Bundle bundle)
    {
    	// Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) getActivity().findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }
    
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	View rootView = inflater.inflate(R.layout.viewpager_session_statistics, container, false);
    	
    	return rootView;
    }
    
    
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
          if(position == 0)
          {
        	  return new SessionList();
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
