package com.example.intsys.Fragments;

import java.util.ArrayList;

import com.example.intsys.R;
import com.example.intsys.SessionActivity;
import com.example.intsys.data.DataSingleton;
import com.example.intsys.data.Session;
import com.example.intsys.data.SessionArrayAdapter;
import com.example.intsys.data.TargetArrayAdapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 *Shows Session History
 */
public class SessionList extends Fragment {
	int num_entries;
	Class childActivity;
	private ArrayList<Session> m_sessionList = new ArrayList<Session>();

    public SessionList() {
    	num_entries = 40;
    	childActivity = SessionActivity.class;
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	m_sessionList.clear();
    	// create MockupList with MockUp Data
        Activity myAtivity = getActivity();
		Context myContext = myAtivity.getBaseContext();
			
		DataSingleton dataSingleton = DataSingleton.getInstance();
    	num_entries = dataSingleton.getSessionHistory().getNumberOfSessions();
    	for(int i = 0; i < num_entries; i++)
	    {
	    	m_sessionList.add(dataSingleton.getSessionHistory().getSession(i));
	    }
    	ListView listView = (ListView) getActivity().findViewById(android.R.id.list);
		SessionArrayAdapter sessionAdapter = new SessionArrayAdapter(myContext, R.layout.list_item_complex, m_sessionList);
		listView.setAdapter(sessionAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
//    	ArrayList<String> valueList = new ArrayList<String>();
//	    for (int i = 0; i < num_entries ; i++)
//	    {
//	    	valueList.add("value"+i);
//	    }
//	    
//	    ListAdapter adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.list_item,R.id.list_content, valueList);
//    	
    	
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        ListView listView = (ListView) rootView.findViewById(android.R.id.list);
        //listView.setAdapter(adapter);
        
        
        
     // ####		Copy of SriesList		######### 
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		//seriesListView.setSelector(R.drawable.list_item_selector);
		listView.setMultiChoiceModeListener(new MultiChoiceModeListener() {

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
		
		// #####	/endCopy	######
        
		
        listView.setOnItemClickListener(new OnItemClickListener() {
	        @Override
	        public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3)
	        {
	        	Intent intent = new Intent(getActivity(), childActivity);
	        	Bundle bundle = new Bundle();
	        	bundle.putInt("SessionIdx", position);
	        	intent.putExtras(bundle);
	        	startActivity(intent);
	        }
        });
        
        
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
       super.onAttach(activity);
    }
}

