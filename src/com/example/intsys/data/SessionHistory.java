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
			Date date = new Date();
			date.setTime(date.getTime()-Math.min(randomGenerator.nextLong(),10000));
			
			int numberOfSeries = 1 + (randomGenerator.nextInt(15)-1);
			
			mSessions.add(new Session("Max Mustermann",date, numberOfSeries, 10, randomGenerator));
		}
	}
}
