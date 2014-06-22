package com.example.intsys.data;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.intsys.R;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SeriesArrayAdapter extends ArrayAdapter<Series> {
	private ArrayList<Series> seriesList;
	
	public SeriesArrayAdapter(Context context, int resource, ArrayList<Series> objects) {
		super(context, resource, objects);
		seriesList = objects;
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
		
		
		Series series = seriesList.get(position);

		if (series != null) {
			TextView title = (TextView) v.findViewById(R.id.list_content_title);
			TextView info1 = (TextView) v.findViewById(R.id.list_content_info1);
			TextView info2 = (TextView) v.findViewById(R.id.list_content_info2);
			TextView info3 = (TextView) v.findViewById(R.id.list_content_info3);
			TextView info4 = (TextView) v.findViewById(R.id.list_content_info4);
			
			if(title != null)
			{
				title.setText("SeriesNr. " + (position+1));
			}
			if(info3 != null)
			{
				DecimalFormat form = new DecimalFormat("0.0");
				String formattedText = form.format(seriesList.get(position).getMax());
				info3.setText(Html.fromHtml("<u>Max:</u><br>" + formattedText));
			}
			if(info4 != null)
			{
				DecimalFormat form = new DecimalFormat("0.00");
				String formattedText = form.format(seriesList.get(position).getMean());
				info4.setText(Html.fromHtml("<u>Mean:</u><br>" + formattedText));
			}
			if(info1 != null)
			{
				info1.setText(Html.fromHtml("<u>Pose:</u><br>" + seriesList.get(position).getPose()));
			}
			if(info2 != null)
			{
				info2.setText(Html.fromHtml("<u>#Targets:</u><br>" + seriesList.get(position).getNumberOfTargets()));
			}
		}

		// the view must be returned to our activity
		return v;

	}

}
