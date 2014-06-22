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

public class TargetArrayAdapter extends ArrayAdapter<Target> {

	private ArrayList<Target> targetList;
	public TargetArrayAdapter(Context context, int resource,
			ArrayList<Target> objects) {
		super(context,resource,objects);
		
		this.targetList = objects;
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
		
		
		Target target = targetList.get(position);

		if (target != null) {
			TextView title = (TextView) v.findViewById(R.id.list_content_title);
			TextView info1 = (TextView) v.findViewById(R.id.list_content_info1);
			TextView info2 = (TextView) v.findViewById(R.id.list_content_info2);
			TextView info3 = (TextView) v.findViewById(R.id.list_content_info3);
			TextView info4 = (TextView) v.findViewById(R.id.list_content_info4);
			
			if(title != null)
			{
				title.setText("TargetNr. " + (position+1));
			}
			if(info1 != null)
			{
				info1.setText(Html.fromHtml("<u>Max:</u><br>" + targetList.get(position).max));
			}
			if(info2 != null)
			{
				info2.setText(Html.fromHtml("<u>Mean:</u><br>" + targetList.get(position).mean));
			}
//			if(info3 != null)
//			{
//				info3.setText(Html.fromHtml("<u>Windage:</u><br>" + targetList.get(position).windage));
//			}
//			if(info4 != null)
//			{
//				info4.setText(Html.fromHtml("<u>Elevation:</u><br>" + targetList.get(position).elevation));
//			}
			
			info3.setVisibility(android.view.View.GONE);
			info4.setVisibility(android.view.View.GONE);
			
		}


		// the view must be returned to our activity
		return v;

	}
}
