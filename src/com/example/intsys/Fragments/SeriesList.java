package com.example.intsys.Fragments;

import java.util.ArrayList;

import com.example.intsys.CameraActivity;
import com.example.intsys.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class SeriesList extends ListFragment {
	ArrayList<String> mvalueList = new ArrayList<String>();
	private final int NUM_ENTRIES;
	Class childActivity;// = Details.class;
	public SeriesList()
	{
		NUM_ENTRIES = 3;
	    for (int i = 0; i < NUM_ENTRIES ; i++)
	    {
	    	mvalueList.add("value"+i);
	    }
	}
	@Override
    public void onAttach(Activity activity) {
		super.onAttach(activity);
		
	   	Activity myAtivity = getActivity();
	    Context myContext = myAtivity.getBaseContext();
	    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(myContext, android.R.layout.simple_list_item_1, mvalueList);
	    setListAdapter(arrayAdapter);
    }
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		//return super.onCreateView(inflater, container, savedInstanceState);
        
		View rootView = inflater.inflate(R.layout.fragment_list_bottombutton, container, false);
		
		rootView.findViewById(R.id.cameraButton).setOnClickListener(
			new View.OnClickListener() {
        		@Override
        		public void onClick(View view) {
        			Intent intent = new Intent(getActivity(), CameraActivity.class);
        			getActivity().startActivity(intent);
        		}
    		}							
		);
		
		return rootView;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id){
//		Intent intent = new Intent(getActivity(), childActivity);
//    	startActivity(intent);
	}
	
}
