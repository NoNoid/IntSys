/**
 * 
 */
package com.example.intsys.data;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author NoNoid
 *
 */
public class Series {
	private ArrayList<Target> mTarget;
	final static private int MINIMUM_CAPACITY_FOR_EMPTY_SERIES = 10;
	
	public Target getTarget(int i) {
		return mTarget.get(i);
	}
	
	public int getNumberOfTargets() {
		return mTarget.size();
	}
	
	public void addTarget(Target targetToAdd) {
		mTarget.add(targetToAdd);
	}
	
	public void deleteTarget(int indexOfTargetToDelete) {
		mTarget.remove(indexOfTargetToDelete);
	}
	
	public Series() {
		mTarget = new ArrayList<Target>(MINIMUM_CAPACITY_FOR_EMPTY_SERIES);
	}
	
	public Series(int numberOfInitialItems, Random randomGenerator) {
		mTarget = new ArrayList<Target>(numberOfInitialItems);
		for(int i = 0;i<numberOfInitialItems;++i) {
			mTarget.add(new Target(randomGenerator));
		}
	}
}
