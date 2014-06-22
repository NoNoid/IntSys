package com.example.intsys.data;

import java.util.ArrayList;

import com.example.intsys.R;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SessionArrayAdapter extends ArrayAdapter<Session> {

	private ArrayList<Session> sessionList = new ArrayList<Session>();
	
	public SessionArrayAdapter(Context context, int resource,
			ArrayList<Session> objects) {
		super(context, resource, objects);
		sessionList = objects;
	}
	
	public View getView(int position, View convertView, ViewGroup parent){

		// assign the view we are converting to a local variable
		View v = convertView;

		// first check to see if the view is null. if so, we have to inflate it.
		// to inflate it basically means to render, or show, the view.
		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.list_item_complex, null);
		}		
		
		
		Session session = sessionList.get(position);

		if (session != null) {
			TextView title = (TextView) v.findViewById(R.id.list_content_title);
			TextView info1 = (TextView) v.findViewById(R.id.list_content_info1);
			TextView info2 = (TextView) v.findViewById(R.id.list_content_info2);
			TextView info3 = (TextView) v.findViewById(R.id.list_content_info3);
			TextView info4 = (TextView) v.findViewById(R.id.list_content_info4);
			
			if(title != null)
			{
				title.setText(session.title);
			}
			if(info1 != null)
			{
				info1.setText(Html.fromHtml("<u>Date:</u><br>" + session.date.getDate() + "/" +  session.date.getMonth() + "/" + session.date.getYear()));
			}
			if(info2 != null)
			{
				info2.setText(Html.fromHtml("<u>Location:</u> " + session.place));
			}
			if(info3 != null)
			{
				String type = "";
				switch(session.type)
				{
				case Training:
					type = "Training";
					break;
				case districtChampinship:
					type = "district Championchip";
					break;
				case regionalChampionship:
					type = "regional Championship";
					break;
				case nationalChampionship:
					type = "national Championship";
					break;
				case internationalChampionship:
					type = "international Championship";
					break;
				}
				info3.setText(Html.fromHtml("<u>Type:</u><br>" + type));
			}
			if(info4 != null)
			{
				info4.setText(Html.fromHtml("<u>Mean:</u><br> " + session.getMean()));
			}
		}

		// the view must be returned to our activity
		return v;

	}

}
