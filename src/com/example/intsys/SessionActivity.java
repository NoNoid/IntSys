package com.example.intsys;

import com.example.intsys.Fragments.SeriesList;
import com.example.intsys.data.DataSingleton;
import com.example.intsys.data.Session;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class SessionActivity extends FragmentActivity implements ActionBar.TabListener {

	private int sessionIdx = -1;
	
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide fragments for each of the
     * three primary sections of the app. We use a {@link android.support.v4.app.FragmentPagerAdapter}
     * derivative, which will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    AppSectionsPagerAdapter mAppSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will display the three primary sections of the app, one at a
     * time.
     */
    ViewPager mViewPager;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());
        
        // get Index of Session selected in SessionList
        sessionIdx = getIntent().getIntExtra("SessionIdx", -1);

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();

        // Specify that we will be displaying tabs in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Set up the ViewPager, attaching the adapter and setting up a listener for when the
        // user swipes between sections.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mAppSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
    }
    
    @Override
    public void onDestroy() {
    	DataSingleton data = DataSingleton.getInstance();
    	if(data.checkIfCurrentSessionExists()) {
    		Session OldSession = data.EndCurrentSession();
    		data.getSessionHistory().addSession(OldSession);
    		Toast.makeText(this, "Current Session Ended", Toast.LENGTH_LONG).show();
    	}
    	super.onDestroy();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.session, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        
        if (id == R.id.action_settings) {
        	Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();
        }
        
        if (id == R.id.action_end_Session) {
        	this.finish();
        }        
        
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the primary
     * sections of the app.
     */
    private class AppSectionsPagerAdapter extends FragmentPagerAdapter {
    	private final int numberOfTabs = 2;
    	
        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                {
                    Fragment fragment = new SeriesList();
                    Bundle args = new Bundle();
                    args.putInt("SessionIdx", sessionIdx);
                    fragment.setArguments(args);
                    return fragment;
                }
                case 1:
                {
                    Fragment fragment = new SessionOptionsMockUpFragment();
                    Bundle args = new Bundle();
                    fragment.setArguments(args);
                    return fragment;
                }
                default:
                {
                    // The other sections of the app are dummy placeholders.
                    Fragment fragment = new DummySectionFragment();
                    Bundle args = new Bundle();
                    fragment.setArguments(args);
                    return fragment;
                }
            }
        }

        @Override
        public int getCount() {
            return numberOfTabs;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
	            case 0: {
	            	return "Series";
	            }
	            case 1: {
	            	return "Statistics";
	            }
	            default: {
	            	return "Anonymous Tab";
	            }
            }
        }
    }

    /**
     * A dummy fragment representing a section of the app, but that simply displays dummy text.
     */
    public static class DummySectionFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_section_dummy, container, false);
            ((TextView) rootView.findViewById(android.R.id.text1)).setText("DummySectionFragment");
            return rootView;
        }
    }
    
    public static class CameraMockUpFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_camera_mockup, container, false);
            return rootView;
        }
    }
    
    public static class SessionViewMockUpFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_session_view_mock, container, false);
            return rootView;
        }
    }
    
    public static class SessionOptionsMockUpFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_session_options_mock, container, false);
            
            rootView.findViewById(R.id.endSessionButton).setOnClickListener(
				new View.OnClickListener() {
	        		@Override
	        		public void onClick(View view) {
	        			getActivity().finish();
	        		}
	    		}							
			);

            return rootView;
        }
    }
}
