/**
 * 
 */
package com.example.intsys.data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.example.intsys.data.Session.SessionType;

/**
 * @author NoNoid
 *
 */
public class SessionHistory {
	private ArrayList<Session> mSessions;
	
	public Session getSession(int index) {
		return mSessions.get(index);
	}
	
	public void addSession(Session SessionToAdd) {
		mSessions.add(0, SessionToAdd);
	}
	
	public int getNumberOfSessions(){
		return mSessions.size();
	}
	
	public SessionHistory(int numberOfSessions, Random randomGenerator) {
		mSessions = new ArrayList<Session>(numberOfSessions);
		for(int i = 0; i < numberOfSessions; ++i) {
			Calendar date = Calendar.getInstance();
			date.set(Calendar.YEAR,date.get(Calendar.YEAR)-randomGenerator.nextInt(10));
			date.set(Calendar.MONTH,1+randomGenerator.nextInt(9));
			date.set(Calendar.DAY_OF_MONTH,1+randomGenerator.nextInt(20));
			
			int numberOfSeries = 5 + (randomGenerator.nextInt(15));
			
			mSessions.add(new Session("Max Mustermann", "Musterhausen",date, numberOfSeries, 10, randomGenerator));
		}
	}
}
