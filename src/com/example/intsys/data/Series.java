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
	public static enum ShootingPose
	{
		PRONE,
		SITTING,
		KNEELING,
		STANDING
	}
	
	
	private ArrayList<Target> mTarget;
	final static private int MINIMUM_CAPACITY_FOR_EMPTY_SERIES = 10;
	
	private ShootingPose m_pose;
	
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
	
	public float getMax()
	{
		float max = 0;
		for(int i = 0; i < mTarget.size(); i++)
		{
			max = (max < mTarget.get(i).max) ? mTarget.get(i).max : max; 
		}
		return max;
	}
	
	public float getMean()
	{
		float mean = 0;
		if(mTarget.size() != 0)
		{
			for(int i = 0; i < mTarget.size(); i++)
			{
				mean += mTarget.get(i).mean;
			}
			mean /= mTarget.size();
		}
		return mean;
	}
	
	public ShootingPose getPose(){
		return m_pose;
	}
	
	
	public Series() {
		mTarget = new ArrayList<Target>(MINIMUM_CAPACITY_FOR_EMPTY_SERIES);
	}
	public Series(ShootingPose p_pose) {
		mTarget = new ArrayList<Target>(MINIMUM_CAPACITY_FOR_EMPTY_SERIES);
		m_pose = p_pose;
	}
	
	public Series(int numberOfInitialItems, Random randomGenerator) {
		mTarget = new ArrayList<Target>(numberOfInitialItems);
		m_pose = ShootingPose.values()[randomGenerator.nextInt(4)];
		for(int i = 0;i<numberOfInitialItems;++i) {
			mTarget.add(new Target(randomGenerator));
		}
	}
}
