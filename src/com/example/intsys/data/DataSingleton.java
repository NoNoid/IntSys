/**
 * 
 */
package com.example.intsys.data;

/**
 * @author NoNoid
 *
 */
public class DataSingleton
{
  private static DataSingleton instance = null;
   
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
  }
}
