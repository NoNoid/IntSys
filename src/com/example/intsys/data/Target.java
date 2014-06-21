/**
 * 
 */
package com.example.intsys.data;

import java.util.Random;

/**
 * @author NoNoid
 *
 */
public class Target {
	public float windage;
	public float elevation;
	public float mean;
	public float max;
	
	public Target(final float windage, final float elevation, final float mean, final float max) {
		this.windage = windage;
		this.elevation = elevation;
		this.mean = mean;
		this.max = max;
	}
	
	public Target(Random randomGenerator) {
		windage = randomGenerator.nextFloat();
		elevation = randomGenerator.nextFloat();
		mean = randomGenerator.nextFloat();
		max = randomGenerator.nextFloat();
	}
}
