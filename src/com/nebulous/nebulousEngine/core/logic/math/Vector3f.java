package com.nebulous.nebulousEngine.core.logic.math;

public class Vector3f {

	private float x;
	private float y;
	private float z;

	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float length() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}

	public float dot(Vector3f vec3f) {
		return x * vec3f.getX() + y * vec3f.getY() + z * vec3f.getZ();
	}

	public Vector3f cross(Vector3f vec3f) {
		float x_ = y * vec3f.getZ() - z * vec3f.getY();
		float y_ = z * vec3f.getX() - x * vec3f.getZ();
		float z_ = x * vec3f.getY() - y * vec3f.getX();

		return new Vector3f(x_, y_, z_);
	}

	public Vector3f normalized() {
		float length = length();

		return new Vector3f(x / length, y / length, z / length);
	}

	public Vector3f rotate(Vector3f axis, float angle)
	{
		float sinAngle = (float)Math.sin(Math.toRadians(-angle));
		float cosAngle = (float)Math.cos(Math.toRadians(-angle));

		return this.cross(axis.mul(sinAngle)).add((this.mul(cosAngle)).add(axis.mul(this.dot(axis.mul(1 - cosAngle)))));
	}
	
	public Vector3f rotate(Quaternion rotation) {
		Quaternion conjugate = rotation.conjugate();
		Quaternion w = rotation.mul(this).mul(conjugate);
		return new Vector3f(w.getX(), w.getY(), w.getZ());
	}

	public Vector3f lerp(Vector3f dest, float lerpFactor) {
		return dest.sub(this).mul(lerpFactor).add(this);
	}

	public Vector2f getXY() {
		return new Vector2f(x, y);
	}

	public Vector2f getXZ() {
		return new Vector2f(x, z);
	}

	public Vector2f getYX() {
		return new Vector2f(y, x);
	}

	public Vector2f getYZ() {
		return new Vector2f(y, z);
	}

	public Vector2f getZX() {
		return new Vector2f(z, x);
	}

	public Vector2f getZY() {
		return new Vector2f(z, y);
	}

	public Vector3f add(Vector3f vec3f) {
		return new Vector3f(x + vec3f.getX(), y + vec3f.getY(), z + vec3f.getZ());
	}

	public Vector3f add(float value) {
		return new Vector3f(x + value, y + value, z + value);
	}

	public Vector3f sub(Vector3f vec3f) {
		return new Vector3f(x - vec3f.getX(), y - vec3f.getY(), z - vec3f.getZ());
	}

	public Vector3f sub(float value) {
		return new Vector3f(x - value, y - value, z - value);
	}

	public Vector3f mul(Vector3f vec3f) {
		return new Vector3f(x * vec3f.getX(), y * vec3f.getY(), z * vec3f.getZ());
	}

	public Vector3f mul(float value) {
		return new Vector3f(x * value, y * value, z * value);
	}

	public Vector3f dev(Vector3f vec3f) {
		return new Vector3f(x / vec3f.getX(), y / vec3f.getY(), z / vec3f.getZ());
	}

	public Vector3f dev(float value) {
		return new Vector3f(x / value, y / value, z / value);
	}
	
	public Vector3f min(Vector3f other) {
		Vector3f result = new Vector3f(0,0,0);
		result.x = x < other.x ? x : other.x;
		result.y = y < other.y ? y : other.y;
		result.z = z < other.z ? z : other.z;
        return this;
    }

    public Vector3f max(Vector3f other) {
    	Vector3f result = new Vector3f(0,0,0);
    	result.x = x > other.x ? x : other.x;
    	result.y = y > other.y ? y : other.y;
    	result.z = z > other.z ? z : other.z;
        return this;
    }

	public float max() {
		return Math.max(x, Math.max(y, z));
	}

	public float min() {
		return Math.min(x, Math.min(y, z));
	}

	public Vector3f set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}
	
	public Vector3f set(Vector3f vec3) {
		this.x = vec3.getX();
		this.y = vec3.getY();
		this.z = vec3.getZ();
		return this;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
	public boolean equals(Vector3f other) {
		return x == other.getX() && y == other.getY() && z == other.getZ();
	}

}
