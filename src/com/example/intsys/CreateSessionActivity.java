package com.example.intsys;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.doomonafireball.betterpickers.calendardatepicker.CalendarDatePickerDialog;
import com.example.intsys.data.DataSingleton;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;


public class CreateSessionActivity extends FragmentActivity{
	private Calendar SessionDate = Calendar.getInstance();
	private EditText personNameEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_session);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_session, menu);
		return true;
	}
	
    @Override
    protected void onStop() {
    	super.onStop();
    	this.finish();
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	private class PlaceholderFragment extends Fragment
		implements CalendarDatePickerDialog.OnDateSetListener {
		
		private FragmentActivity fragmentContext;

		public PlaceholderFragment() {
		}

		@Override
		public void onAttach(Activity activity) {
		    fragmentContext=(FragmentActivity) activity;
		    super.onAttach(activity);
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_create_session,
					container, false);
			
			personNameEditText = (EditText)rootView.findViewById(R.id.createSessionEditPersonName);
			
            rootView.findViewById(R.id.confirmCreateSessionButton).setOnClickListener(
				new View.OnClickListener() {
	        		@Override
	        		public void onClick(View view) {
	        			DataSingleton data = DataSingleton.getInstance();
	        			data.createNewCurrentSession(personNameEditText.getText().toString(), SessionDate.getTime());
	            		Toast.makeText(getActivity(), "Current Session Begin", Toast.LENGTH_LONG).show();
            			Intent intent = new Intent(getActivity(), SessionActivity.class);
            			getActivity().startActivity(intent);
	        		}
	    		}							
			);
            
            rootView.findViewById(R.id.datePickerButon).setOnClickListener(
    				new View.OnClickListener() {
    	        		@Override
    	        		public void onClick(View view) {
    	        			FragmentManager fm = fragmentContext.getSupportFragmentManager();
    	                    CalendarDatePickerDialog calendarDatePickerDialog = CalendarDatePickerDialog
    	                            .newInstance(PlaceholderFragment.this, 2014, 1,
    	                                    1);
    	                    calendarDatePickerDialog.show(fm,"someText");    	                    
    	        		}
    	    		}							
    			);
			
			return rootView;
		}

		@Override
		public void onDateSet(CalendarDatePickerDialog dialog, int year,
				int monthOfYear, int dayOfMonth) {
			// TODO Auto-generated method stub
			SessionDate = Calendar.getInstance();
            SessionDate.set(year, monthOfYear, dayOfMonth);
            SimpleDateFormat date = new SimpleDateFormat("EE d/MM/yy");          
            
            Button sessionDate = (Button) fragmentContext.findViewById(R.id.datePickerButon);
            sessionDate.setText( date.format(SessionDate.getTime()) );
		}
	}

}
