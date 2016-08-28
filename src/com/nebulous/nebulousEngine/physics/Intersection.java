package com.nebulous.nebulousEngine.physics;

public class Intersection {
	
	private boolean intersecting;
	private float distance;

	public Intersection(boolean intersecting, float distance) {
		this.intersecting = intersecting;
		this.distance = distance;
	}

	public boolean isIntersecting() {
		return intersecting;
	}

	public void setIntersecting(boolean intersecting) {
		this.intersecting = intersecting;
	}

	public float getDistance() {
		return distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

}
