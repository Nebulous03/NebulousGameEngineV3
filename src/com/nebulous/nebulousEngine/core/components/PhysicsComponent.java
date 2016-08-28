package com.nebulous.nebulousEngine.core.components;

import com.nebulous.nebulousEngine.core.RenderEngine;
import com.nebulous.nebulousEngine.graphics.shaders.Shader;
import com.nebulous.nebulousEngine.physics.PhysicsEngine;
import com.nebulous.nebulousEngine.physics.PhysicsObject;

public class PhysicsComponent extends GameComponent{

	private PhysicsEngine engine;
	private PhysicsObject physicsObject;
	
	public PhysicsComponent(PhysicsEngine engine, PhysicsObject object) {
		this.engine = engine;
		this.physicsObject = object;
	}

	@Override
	public void init() {}

	@Override
	public void update(double delta) {
		getTransform().setPos(physicsObject.getPosition());
	}

	@Override
	public void render(Shader shader, RenderEngine engine) {}

	@Override
	public void input(double delta) {}

	public PhysicsEngine getEngine() {
		return engine;
	}

	public void setEngine(PhysicsEngine engine) {
		this.engine = engine;
	}

	public PhysicsObject getPhysicsObject() {
		return physicsObject;
	}

	public void setPhysicsObject(PhysicsObject physicsObject) {
		this.physicsObject = physicsObject;
	}

}
