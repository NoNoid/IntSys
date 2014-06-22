/**
 * 
 */
package com.example.intsys.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.example.intsys.data.Series.ShootingPose;

/**
 * @author NoNoid
 *
 */
public class Session {
	
	public static enum SessionType{
		Training,
		districtChampinship,
		regionalChampionship,
		nationalChampionship,
		internationalChampionship
	}
	
	final static private int MINIMUM_CAPACITY_FOR_EMPTY_SESSION = 5;
	
	private ArrayList<Series> mSeries;
	public String title;
	public String ShooterName;
	public String place;
	public SessionType type;
	public Calendar date;
	
	public Series getSeries(int i) {
		return mSeries.get(i);
	}
	
	public int getNumberOfSeries() {
		return mSeries.size();
	}
	
	public void addSeries(Series seriesToAdd) {
		mSeries.add(seriesToAdd);
	}
	
	public void deleteSeries(int indexOfSeriesToDelete) {
		mSeries.remove(indexOfSeriesToDelete);
	}
	
	public float getMax(){
		float max = 0;
		if(mSeries.size() > 0)
		{
			for(int i = 0; i < mSeries.size(); i++)
			{
				float serMax = mSeries.get(i).getMax();
				max = (max < serMax) ? serMax : max;
			}

		}
		return max;
	}
	
	public float getMean(){
		float mean = 0;
		if(mSeries.size() > 0)
		{
			for(int i = 0; i < mSeries.size(); i++)
			{
				mean += mSeries.get(i).getMean();
			}
			mean /= mSeries.size();
		}
		return mean;
	}
	
	public Session(String title, String ShooterName, String place, SessionType type) {
		date = Calendar.getInstance();
		this.title = title;
		this.ShooterName = ShooterName;
		this.place = place;
		this.type = type;
		mSeries = new ArrayList<Series>(MINIMUM_CAPACITY_FOR_EMPTY_SESSION);
	}
	
	public Session(String ShooterName ,String place, Calendar date, int numberOfSeries, int maxNumberOfTargets, Random randomGenerator) {
		this.title = "RandomGenerated";
		this.date = date;
		this.ShooterName = ShooterName;
		this.place = place;
		this.type = SessionType.values()[randomGenerator.nextInt(5)];
		mSeries = new ArrayList<Series>(numberOfSeries);
		for(int i = 0; i < numberOfSeries; ++i) {
			int numberOfTargets = 5+(randomGenerator.nextInt(maxNumberOfTargets));
			mSeries.add(new Series(numberOfTargets, randomGenerator));
		}
	}
}
