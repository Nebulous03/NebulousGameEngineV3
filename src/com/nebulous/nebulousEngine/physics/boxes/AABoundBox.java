package com.nebulous.nebulousEngine.physics.boxes;

import com.nebulous.nebulousEngine.core.logic.math.Vector3f;
import com.nebulous.nebulousEngine.physics.Intersection;

public class AABoundBox {
	
	public Vector3f minPoint;
	public Vector3f maxPoint;

	public AABoundBox(Vector3f minPoint, Vector3f maxPoint) {
		this.minPoint = minPoint;
		this.maxPoint = maxPoint;
	}
	
	public AABoundBox(Vector3f startPos, float width, float height, float depth) {
		this.minPoint = startPos;
		this.maxPoint = startPos.add(new Vector3f(width, height, depth));
	}
	
	public Intersection intersecting(AABoundBox other){
		Vector3f distancePositive = other.getMinPoint().sub(maxPoint);
		Vector3f distanceNegative = minPoint.sub(other.getMaxPoint());
		Vector3f distance = distancePositive.max(distanceNegative);
		float maxDistance = distance.max();
		return new Intersection(maxDistance < 0, maxDistance);
	}

	public Vector3f getMinPoint() {
		return minPoint;
	}

	public void setMinPoint(Vector3f minPoint) {
		this.minPoint = minPoint;
	}

	public Vector3f getMaxPoint() {
		return maxPoint;
	}

	public void setMaxPoint(Vector3f maxPoint) {
		this.maxPoint = maxPoint;
	}
}
