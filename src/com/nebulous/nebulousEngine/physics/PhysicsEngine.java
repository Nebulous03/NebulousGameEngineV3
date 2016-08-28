package com.nebulous.nebulousEngine.physics;

import java.util.ArrayList;

public class PhysicsEngine {
	
	private ArrayList<PhysicsObject> objects = new ArrayList<PhysicsObject>();

	public PhysicsEngine() {
		
	}
	
	public void addObject(PhysicsObject object){
		objects.add(object);
	}
	
	public void update(double delta){
		simulate(delta);
	}
	
	public void removeObject(PhysicsObject object){
		objects.remove(object);
	}

	public void simulate(double delta){
		for(int i = 0; i < objects.size(); i++){
			objects.get(i).integrate(delta);
		}
	}

	public ArrayList<PhysicsObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<PhysicsObject> objects) {
		this.objects = objects;
	}
}
