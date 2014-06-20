package com.example.intsys.Fragments;

import java.util.ArrayList;

import com.example.intsys.R;
import com.example.intsys.SessionActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * A Fragment containing a list.
 */
public class SessionList extends Fragment {
	int num_entries;
	Class childActivity;

    public SessionList() {
    	num_entries = 40;
    	childActivity = SessionActivity.class;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	ArrayList<String> valueList = new ArrayList<String>();
	    for (int i = 0; i < num_entries ; i++)
	    {
	    	valueList.add("value"+i);
	    }
	    
	    ListAdapter adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.list_item,R.id.list_content, valueList);
    	
    	
        View rootView = inflater.inflate(R.layout.fragment_list_bottombutton, container, false);
        ListView listView = (ListView) rootView.findViewById(android.R.id.list);
        //ListView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
        listView.setAdapter(adapter);
        
        
        
        listView.setOnItemClickListener(new OnItemClickListener() {
	        @Override
	        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
	        {
	        	Intent intent = new Intent(getActivity(), childActivity);
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

