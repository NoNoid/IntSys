package com.example.intsys;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.content.DialogInterface;
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
	        		    String[] types = {"Nr 1", "Nr 2", "Nr 3","Nr 4","Nr 5","Nr 6","Nr 7","Nr 8","Nr 9","Nr 10","Nr 11","Nr 12","Nr 13","Nr 14","Nr 15","Nr 16"};
	        		    b.setItems(types, new OnClickListener() {

	        		        @Override
	        		        public void onClick(DialogInterface dialog, int which) {

	        		            dialog.dismiss();
	        		            switch(which){
	        		            case 0:
	        		                //onZipRequested();
	        		                break;
	        		            case 1:
	        		                //onCategoryRequested();
	        		                break;
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
