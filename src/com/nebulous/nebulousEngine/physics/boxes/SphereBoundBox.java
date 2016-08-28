package com.nebulous.nebulousEngine.physics.boxes;

import com.nebulous.nebulousEngine.core.logic.math.Vector3f;
import com.nebulous.nebulousEngine.physics.Intersection;

public class SphereBoundBox {
	
	private Vector3f center;
	private float radius;

	public SphereBoundBox(Vector3f center, float radius) {
		this.center = center;
		this.radius = radius;
	}
	
	public Intersection intersecting(SphereBoundBox other){
		float radiusDistance = radius + other.getRadius();
		float centerDistance = (other.getCenter().sub(center)).length();
		float distance = other.getCenter().sub(center).length();
		if(centerDistance < radiusDistance){
			return new Intersection(true, distance);
		} else {
			return new Intersection(false, distance);
		}
	}

	public Vector3f getCenter() {
		return center;
	}

	public void setCenter(Vector3f center) {
		this.center = center;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

}
