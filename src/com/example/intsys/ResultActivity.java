package com.example.intsys;

import java.util.Random;

import com.example.intsys.data.DataSingleton;
import com.example.intsys.data.Series;
import com.example.intsys.data.Session;
import com.example.intsys.data.Session.SessionType;
import com.example.intsys.data.Target;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		setTitle(getResources().getString(R.string.activity_title_ResultActivity));

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
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
		return super.onOptionsItemSelected(item);
	}
	
	
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_result,
					container, false);
			
            rootView.findViewById(R.id.commitResultButton).setOnClickListener(
				new View.OnClickListener() {
	        		@Override
	        		public void onClick(View view) {
	        			
	        			AlertDialog.Builder b = new Builder(getActivity());
	        		    b.setTitle("Add to Series");
	        		    DataSingleton data = DataSingleton.getInstance();
	        		    int numSeries = data.getCurrentSession().getNumberOfSeries();
	        		    String[] types = new String[numSeries + 1];
	        		    
	        		    for(int i = 1; i < types.length; i++)
	        		    {
	        		    	types[i] = "Series Nr " + i;
	        		    }
	        		    types[0] = "Create new Series";
	        		    
	        		    final int typesSize = types.length;
	        		    
	        		    b.setItems(types, new OnClickListener() {

	        		        @Override
	        		        public void onClick(DialogInterface dialog, int which) {

	        		            dialog.dismiss();
	        		            DataSingleton privData = DataSingleton.getInstance();
	        		            if(privData.checkIfCurrentSessionExists())
	        		            {
	        		            	Session curSession = privData.getCurrentSession();
	        		            	if(which == 0)
	        		            	{
//	        		            		Intent intent = new Intent(getActivity(), CreateSeriesActivity.class);
//	        		            		getActivity().startActivity(intent);
	        		            		curSession.addSeries(new Series(Series.ShootingPose.PRONE));
	        		            		curSession.getSeries(curSession.getNumberOfSeries()-1).addTarget(new Target(new Random()));
	        		            	}
	        		            	else
	        		            	{
	        		            		for(int i = 1; i < typesSize; i++)
			        		            {
			        		            	if(i == which)
			        		            	{
			        		            		Series selectedSeries = curSession.getSeries(i-1);
			        		            		selectedSeries.addTarget(new Target(new Random()));
			        		            		break;
			        		            	}
			        		            }
	        		            	}
		        		            int stopForDebug = 0;
		        		            int foo = stopForDebug -3;
	        		            }
	        		            getActivity().finish();
	        		        }

	        		    });

	        		    b.show();
	        		}
	    		}							
			);
			
			return rootView;
		}
	}

}
