/**
 * 
 */
package com.example.intsys.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * @author NoNoid
 *
 */
public class Session {
	final static private int MINIMUM_CAPACITY_FOR_EMPTY_SESSION = 5;
	
	private ArrayList<Series> mSeries;
	public String ShooterName;
	public Date date;
	
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
	
	public Session(String ShooterName) {
		date = new Date();
		this.ShooterName = ShooterName;
		mSeries = new ArrayList<Series>(MINIMUM_CAPACITY_FOR_EMPTY_SESSION);
	}
	
	public Session(String ShooterName ,Date date, int numberOfSeries, int maxNumberOfTargets, Random randomGenerator) {
		this.date = date;
		this.ShooterName = ShooterName;
		mSeries = new ArrayList<Series>(numberOfSeries);
		for(int i = 0; i < numberOfSeries; ++i) {
			int numberOfTargets = 1+(randomGenerator.nextInt(maxNumberOfTargets)-1);
			mSeries.add(new Series(numberOfTargets, randomGenerator));
		}
	}
}
