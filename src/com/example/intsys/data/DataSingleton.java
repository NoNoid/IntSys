/**
 * 
 */
package com.example.intsys.data;

import java.util.Date;
import java.util.Random;

import com.example.intsys.data.Session.SessionType;

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
  
  
  public Session createNewCurrentSession(String title, String ShooterName, String location,SessionType type , Date date) {
	  Session tempSession = mCurrentSession;
	  mCurrentSession = new Session(title, ShooterName,location,type);
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
