package com.nebulous.nebulousEngine.physics;

import com.nebulous.nebulousEngine.core.logic.math.Vector3f;

public class PhysicsObject {
	
	private Vector3f position;
	private Vector3f velocity;
	
	public PhysicsObject(Vector3f position, Vector3f velocity) {
		this.position = position;
		this.velocity = velocity;
	}
	
	public void integrate(double delta){
		position.add(velocity.mul((float)delta));
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector3f velocity) {
		this.velocity = velocity;
	}

}
