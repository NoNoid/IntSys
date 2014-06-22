/**
 * 
 */
package com.example.intsys.data;

import java.util.Date;
import java.util.Random;

/**
 * @author NoNoid
 *
 */
public class DataSingleton
{
  private static DataSingleton instance = null;
  private Session mCurrentSession = null;
  private SessionHistory mMockUpSessionHistory = null;

  public static void initInstance()
  {
    if (instance == null)
    {
      // Create the instance
      instance = new DataSingleton();
    }
  }
 
  public static DataSingleton getInstance()
  {
    // Return the instance
    return instance;
  }
   
  private DataSingleton()
  {
    // Constructor hidden because this is a singleton
	  Random randomGenerator = new Random();
	  mMockUpSessionHistory = new SessionHistory(10, randomGenerator);
  }
  
  public Session getCurrentSession() {
	  return mCurrentSession;
  }
  
  public boolean checkIfCurrentSessionExists() {
	  return mCurrentSession == null ? false : true;
  }
  
  
  public Session createNewCurrentSession(String ShooterName, String location, Date date) {
	  Session tempSession = mCurrentSession;
	  mCurrentSession = new Session(ShooterName,location);
	  return tempSession;
  }
  
  public Session EndCurrentSession() {
	  Session tempSession = mCurrentSession;
	  mCurrentSession = null;
	  return tempSession;
  }
  
  public SessionHistory getSessionHistory() {
	return mMockUpSessionHistory;
	  
  }
}
