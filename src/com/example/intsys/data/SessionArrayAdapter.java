package com.example.intsys.data;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
	private int mNum_Series = 0;
	
	public SessionArrayAdapter(Context context, int resource,
			ArrayList<Session> objects) {
		super(context, resource, objects);
		sessionList = objects;
		mNum_Series = sessionList.size();
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
				title.setText(session.title + " " + (mNum_Series - position));
			}
			if(info1 != null)
			{
				info1.setText(Html.fromHtml("<u>Date:</u><br>" + session.date.get(Calendar.DAY_OF_MONTH) + "/" +  session.date.get(Calendar.MONTH) + "/" + session.date.get(Calendar.YEAR)));
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
				DecimalFormat form = new DecimalFormat("0.00");
				String formattedText = form.format(sessionList.get(position).getMean());
				info4.setText(Html.fromHtml("<u>Mean:</u><br> " + formattedText));
			}
		}

		// the view must be returned to our activity
		return v;

	}

}
