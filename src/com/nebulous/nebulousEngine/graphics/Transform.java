package com.nebulous.nebulousEngine.graphics;

import com.nebulous.nebulousEngine.core.logic.math.Matrix4f;
import com.nebulous.nebulousEngine.core.logic.math.Position;
import com.nebulous.nebulousEngine.core.logic.math.Quaternion;
import com.nebulous.nebulousEngine.core.logic.math.Rotation;
import com.nebulous.nebulousEngine.core.logic.math.Scale;
import com.nebulous.nebulousEngine.core.logic.math.Vector3f;

public class Transform {
	
	private Transform parent;
	private Matrix4f parentMatrix;
	
	private Vector3f pos;
	private Quaternion rot;
	private Vector3f scale;
	
	private Vector3f previousPos;
	private Quaternion previousRot;
	private Vector3f previousScale;

	public Transform() {
		pos = new Position(0,0,0);
		rot = new Rotation(0,0,0,1);
		scale = new Scale(1,1,1);
		parentMatrix = new Matrix4f().initIdentity();
	}

	public Matrix4f getTransformation() {
		Matrix4f translationMatrix = new Matrix4f().initTranslation(pos.getX(), pos.getY(), pos.getZ());
		Matrix4f rotationMatrix = rot.toRotationMatrix();
		Matrix4f scaleMatrix = new Matrix4f().initScale(scale.getX(), scale.getY(), scale.getZ());
		return getParentMatrix().mul(translationMatrix.mul(rotationMatrix.mul(scaleMatrix)));
	}
	
	public void update(){
		if(previousPos != null){
			previousPos = pos;
			previousRot = rot;
			previousScale = scale;
		} else {
			previousPos = new Position(0, 0, 0).set(pos).add(1.0f);
			previousRot = new Rotation(0,0,0,0).set(rot).mul(0.5f);
			previousScale = new Scale(0, 0, 0).set(scale).add(1.0f);
		}
	}
	
	public boolean hasChanged(){
		
		if(parent != null){
			if(parent.hasChanged()){
				return true;
			}
		}
		
		if(!pos.equals(previousPos)) return true;
		if(!rot.equals(previousRot)) return true;
		if(!scale.equals(previousScale)) return true;
		
		return false;
	}
	
	public void rotate(Vector3f axis, float angle){
		rot = new Quaternion(axis, angle).mul(rot).normalized();
	}
	
	public Matrix4f getParentMatrix(){
		if(parent != null)
		if(parent.hasChanged())
		parentMatrix = parent.getTransformation();
		return parentMatrix;
	}
	
	public Vector3f getTransformedPos(){
		return getParentMatrix().transform(pos);
	}
	
	public Quaternion getTransformedRot(){
		Quaternion parentRot = new Quaternion(0,0,0,1);
		if(parent != null)
		parentRot = parent.getTransformedRot();
		return parentRot.mul(rot);
	}
	
	public void setParent(Transform parent){
		this.parent = parent;
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public void setPos(float x, float y, float z) {
		this.pos = new Vector3f(x, y, z);
	}

	public void setTranslation(float x, float y, float z) {
		this.pos = new Vector3f(x, y, z);
	}

	public Quaternion getRot() {
		return rot;
	}

	public void setRot(Quaternion rot) {
		this.rot = rot;
	}

	public void setRotation(float x, float y, float z, float w) {
		this.rot = new Quaternion(x, y, z, w);
	}

	public Vector3f getScale() {
		return scale;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}

	public void setScale(float x, float y, float z) {
		this.scale = new Vector3f(x, y, z);
	}
}
