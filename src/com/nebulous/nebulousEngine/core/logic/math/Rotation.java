package com.nebulous.nebulousEngine.core.logic.math;

public class Rotation extends Quaternion{
	
	public static final Vector3f xAxis = new Vector3f(1,0,0);
	public static final Vector3f yAxis = new Vector3f(0,1,0);
	public static final Vector3f zAxis = new Vector3f(0,0,1);

	public Rotation(Vector3f axis, float angle) {
		super(axis, angle);
	}
	
	public Rotation(float x, float y, float z, float w){
		super(x, y, z, w);
	}

}
