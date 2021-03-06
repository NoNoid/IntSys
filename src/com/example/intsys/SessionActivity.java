package com.example.intsys;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.intsys.Fragments.Fragment_statisitcs;
import com.example.intsys.Fragments.SeriesList;
import com.example.intsys.data.DataSingleton;
import com.example.intsys.data.Session;

public class SessionActivity extends FragmentActivity implements ActionBar.TabListener {

	private int sessionIdx = -1;
	
	private boolean UserWantsToExit = false;
	
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
    
    public boolean checkIfUserWantsToExit() {
    	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	
    	builder.setMessage(R.string.leaveCurrentSessionAlertMessage)
        .setTitle(R.string.leaveCurrentSessionAlertTitle);

    	
    	builder.setPositiveButton(R.string.alert_ok, new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   UserWantsToExit = true;
    	           }
    	       });
    	builder.setNegativeButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   UserWantsToExit = false;    	        	   
    	           }
    	       });
    	
    	AlertDialog dialog = builder.create();
    	dialog.show();
    	
	    return UserWantsToExit;
    }

    @Override
    public void onWindowFocusChanged (boolean hasFocus){
    	if(UserWantsToExit && hasFocus)
    	{
    		finish();
    	}
    	super.onWindowFocusChanged(hasFocus);
    }
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        // Create the adapter that will return a fragment for each of the three primary sections
        // of the app.
        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());
        
        // get Index of Session selected in SessionList
        sessionIdx = getIntent().getIntExtra("SessionIdx", -1);
        
        DataSingleton ds = DataSingleton.getInstance();
        ds.getCurrentSession();
        
        if(sessionIdx < 0)
        {
        	if(sessionIdx == -3)
        	{
        		setTitle("new Session");
        	}
        	else
        	{
        		setTitle(getResources().getString(R.string.activity_title_SessionActivity));
        	}
        }
        else
        {
        	Session session = ds.getSessionHistory().getSession(sessionIdx);
        	setTitle(session.title + " " + (ds.getSessionHistory().getNumberOfSessions()-sessionIdx));
        }
      

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
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
        	if(DataSingleton.getInstance().checkIfCurrentSessionExists()) {
	        	if(checkIfUserWantsToExit()) {
	            		super.onKeyDown(keyCode, event);
	            }else {
	            	return true;
	            }
        	}else{
        		super.onKeyDown(keyCode, event);
        	}
        }

        return super.onKeyDown(keyCode, event);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	if(DataSingleton.getInstance().checkIfCurrentSessionExists())
        	getMenuInflater().inflate(R.menu.session, menu);
    	else
    		getMenuInflater().inflate(R.menu.session_without_current_session, menu);
    	
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        
        if (id == R.id.action_settings) {
        	Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show();
        }
        
        if (id == R.id.action_end_Session) {
        	if(DataSingleton.getInstance().checkIfCurrentSessionExists())
        	{
        		if(checkIfUserWantsToExit()) {
        			this.finish();
        		}
        	}else{
        		this.finish();
        	}
        }
        
        if (id == R.id.action_view_SessionHistory) {
			Intent intent = new Intent(this, SessionListActivity.class);
			startActivity(intent);
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
                    Fragment fragment = new Fragment_statisitcs();
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
