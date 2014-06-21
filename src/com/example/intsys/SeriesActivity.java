package com.example.intsys;

import com.example.intsys.Fragments.Fragment_statisitcs;
import com.example.intsys.Fragments.SessionList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.view.PagerAdapter;


public class SeriesActivity extends TabActivity
   {
	
	public static final int NUM_PAGES = 2;


    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
//        mNavigationDrawerFragment = (NavigationDrawerFragment)
//                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
//        mNavigationDrawerFragment.setUp(
//                R.id.navigation_drawer,
//                (DrawerLayout) findViewById(R.id.drawer_layout));
	    
    }
      


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.man_bar_entries, menu);;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.action_newItem)
        {
//        	Intent intent = new Intent(this, CreateSessionActivity.class);
//        	startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
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



	@Override
	protected PagerAdapter getPagerAdapter() {
		return new ScreenSlidePagerAdapter(getSupportFragmentManager());
	}


}
