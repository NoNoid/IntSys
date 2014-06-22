package com.example.intsys.Fragments;

import java.util.ArrayList;

import com.example.intsys.CameraActivity;
import com.example.intsys.DetailActivity;
import com.example.intsys.R;
import com.example.intsys.SeriesActivity;
import com.example.intsys.data.DataSingleton;
import com.example.intsys.data.Target;
import com.example.intsys.data.TargetArrayAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AbsListView.MultiChoiceModeListener;


public class TargetList extends ListFragment {
	ArrayList<String> mvalueList = new ArrayList<String>();
	ArrayList<Target> m_targetList = new ArrayList<Target>();
	private int NUM_ENTRIES;
	private final static Class childActivity = DetailActivity.class;
	private  int seriesNr;
	private int sessionIdx;
	
	
	
	public TargetList()
	{
//		NUM_ENTRIES = 3;
//	    for (int i = 0; i < NUM_ENTRIES ; i++)
//	    {
//	    	mvalueList.add("value"+i);
//	    }
	    
	}
	@Override
    public void onAttach(Activity activity) {
		super.onAttach(activity);
		
	   	Activity myAtivity = getActivity();
	    Context myContext = myAtivity.getBaseContext();
//	    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(myContext, android.R.layout.simple_list_item_1, mvalueList);
//	    setListAdapter(arrayAdapter);
	    
	    TargetArrayAdapter targetArrayAdapter = new TargetArrayAdapter(myContext, R.layout.list_item_complex, m_targetList);
	    setListAdapter(targetArrayAdapter);
	    
	    seriesNr = getArguments().getInt("SeriesNr", -1);
	    sessionIdx = getArguments().getInt("SessionIdx",-1);
	    
		DataSingleton dataSingleton = DataSingleton.getInstance();
		if(sessionIdx >= 0)
		{
		    if(seriesNr >= 0)
		    {
		    	NUM_ENTRIES = dataSingleton.getSessionHistory().getSession(sessionIdx).getSeries(seriesNr).getNumberOfTargets();
		    	for(int i = 0; i < NUM_ENTRIES; i++)
			    {
			    	m_targetList.add(dataSingleton.getSessionHistory().getSession(sessionIdx).getSeries(seriesNr).getTarget(i));
			    }
		    }
		}
    }
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		//return super.onCreateView(inflater, container, savedInstanceState);
        
		View rootView;
		if(DataSingleton.getInstance().checkIfCurrentSessionExists()) {
			rootView = inflater.inflate(R.layout.fragment_list_bottombutton, container, false);
			
			rootView.findViewById(R.id.cameraButton).setOnClickListener(
					new View.OnClickListener() {
		        		@Override
		        		public void onClick(View view) {
		        			Intent intent = new Intent(getActivity(), CameraActivity.class);
		        			getActivity().startActivity(intent);
		        		}
		    		}							
				);
			
		}else {
			rootView = inflater.inflate(R.layout.fragment_list, container, false);
		}

		ListView seriesListView = (ListView) rootView.findViewById(android.R.id.list);
		seriesListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		//seriesListView.setSelector(R.drawable.list_item_selector);
		seriesListView.setMultiChoiceModeListener(new MultiChoiceModeListener() {

		    @Override
		    public void onItemCheckedStateChanged(ActionMode mode, int position,
		                                          long id, boolean checked) {
		        // Here you can do something when items are selected/de-selected,
		        // such as update the title in the CAB
		    }

		    @Override
		    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
		        // Respond to clicks on the actions in the CAB
		        switch (item.getItemId()) {
		        case R.id.action_delete:
		        	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		        	
		        	builder.setMessage(R.string.alert_message)
		            .setTitle(R.string.alert_title);

		        	
		        	builder.setPositiveButton(R.string.alert_ok, new DialogInterface.OnClickListener() {
		        	           public void onClick(DialogInterface dialog, int id) {
		        	               // User clicked OK button
		        	           }
		        	       });
		        	builder.setNegativeButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {
		        	           public void onClick(DialogInterface dialog, int id) {
		        	               // User cancelled the dialog
		        	           }
		        	       });
		        	
		        	AlertDialog dialog = builder.create();
		        	dialog.show();
		        	mode.finish();
		        	return true;
	            default:
	                return false;
		        }
		    }

		    @Override
		    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		        // Inflate the menu for the CAB
		        MenuInflater inflater = mode.getMenuInflater();
		        inflater.inflate(R.menu.edit_list_bar, menu);
		        return true;
		    }

		    @Override
		    public void onDestroyActionMode(ActionMode mode) {
		        // Here you can make any necessary updates to the activity when
		        // the CAB is removed. By default, selected items are deselected/unchecked.
		    }

		    @Override
		    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
		        // Here you can perform updates to the CAB due to
		        // an invalidate() request
		        return false;
		    }
		});
		
		return rootView;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id){
		Intent intent = new Intent(getActivity(), childActivity);
    	startActivity(intent);
	}

}
