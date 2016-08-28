package com.nebulous.nebulousEngine.physics.boxes;

import com.nebulous.nebulousEngine.core.logic.math.Vector3f;
import com.nebulous.nebulousEngine.physics.Intersection;

public class PlaneBoundBox {

	private Vector3f normal;
	private float distance;
	
	public PlaneBoundBox(Vector3f normal, float distance) {
		this.normal = normal;
		this.distance = distance;
	}
	
	public PlaneBoundBox normalized(){
		float length = normal.length();
		return new PlaneBoundBox(normal.dev(length), distance / length);
	}
	
	public Intersection Intersecting(SphereBoundBox other){
		float distanceFromCenter = Math.abs(normal.dot(other.getCenter()) + distance);
		float distanceFromSphere = distanceFromCenter - other.getRadius();
		return new Intersection(distanceFromCenter < 0, distanceFromSphere);
		
	}

	public Vector3f getNormal() {
		return normal;
	}

	public void setNormal(Vector3f normal) {
		this.normal = normal;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

}
