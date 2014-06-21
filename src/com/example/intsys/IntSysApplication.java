/**
 * 
 */
package com.example.intsys;

import com.example.intsys.data.DataSingleton;

import android.app.Application;

/**
 * @author NoNoid
 *
 */
public class IntSysApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		DataSingleton.initInstance();
	}

}
