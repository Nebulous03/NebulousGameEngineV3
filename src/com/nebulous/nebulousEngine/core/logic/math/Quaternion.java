package com.nebulous.nebulousEngine.core.logic.math;

public class Quaternion {

	private float x;
	private float y;
	private float z;
	private float w;
	
	
	public Quaternion(){
		this(0,0,0,1);
	}
	
	public Quaternion(float x, float y, float z, float w){
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public Quaternion(Vector3f axis, float angle){
		float sinHalfAngle = (float)Math.sin(Math.toRadians(angle / 2));
        float cosHalfAngle = (float)Math.cos(Math.toRadians(angle / 2));

        this.x = axis.getX() * sinHalfAngle;
        this.y = axis.getY() * sinHalfAngle;
        this.z = axis.getZ() * sinHalfAngle;
        this.w = cosHalfAngle;
	}
	
	public float length(){
		return (float)Math.sqrt(x * x + y * y + z * z + w * w);
	}
	
	public Quaternion initRotation(Vector3f axis, float angle){
		float sinHalfAngle = (float)Math.sin(Math.toRadians(angle / 2));
        float cosHalfAngle = (float)Math.cos(Math.toRadians(angle / 2));

        this.x = axis.getX() * sinHalfAngle;
        this.y = axis.getY() * sinHalfAngle;
        this.z = axis.getZ() * sinHalfAngle;
        this.w = cosHalfAngle;
        
        return this;
	}
	
	public Quaternion normalized(){
		float length = length();
		
		x /= length;
		y /= length;
		z /= length;
		w /= length;
		
		return this;
	}
	
	public Quaternion conjugate(){
		return new Quaternion(-x, -y, -z, w);
	}
	
	public Quaternion mul(float r){
		return new Quaternion(x * r, y * r, z * r, w * r);
	}
	
	public Quaternion mul(Quaternion quat){
		
		float w_ = w * quat.getW() - x * quat.getX() - y * quat.getY() - z * quat.getZ();
		float x_ = x * quat.getW() + w * quat.getX() + y * quat.getZ() - z * quat.getY();
		float y_ = y * quat.getW() + w * quat.getY() + z * quat.getX() - x * quat.getZ();
		float z_ = z * quat.getW() + w * quat.getZ() + x * quat.getY() - y * quat.getX();
		
		return new Quaternion(x_, y_, z_, w_);
	}
	
	public Quaternion mul(Vector3f vec3f){
		
		float w_ = -x * vec3f.getX() - y * vec3f.getY() - z * vec3f.getZ();
		float x_ =  w * vec3f.getX() + y * vec3f.getZ() - z * vec3f.getY();
		float y_ =  w * vec3f.getY() + z * vec3f.getX() - x * vec3f.getZ();
		float z_ =  w * vec3f.getZ() + x * vec3f.getY() - y * vec3f.getX();
		
		return new Quaternion(x_, y_, z_, w_);
	}
	
	public Matrix4f toRotationMatrix(){
		Vector3f forward =  new Vector3f(2.0f * (x*z - w*y), 2.0f * (y*z + w*x), 1.0f - 2.0f * (x*x + y*y));
		Vector3f up = new Vector3f(2.0f * (x*y + w*z), 1.0f - 2.0f * (x*x + z*z), 2.0f * (y*z - w*x));
		Vector3f right = new Vector3f(1.0f - 2.0f * (y*y + z*z), 2.0f * (x*y - w*z), 2.0f * (x*z + w*y));

		return new Matrix4f().initRotation(forward, up, right);
	}

	public Vector3f getForward() {
		return new Vector3f(0, 0, 1).rotate(this);
	}

	public Vector3f getBack() {
		return new Vector3f(0, 0, -1).rotate(this);
	}

	public Vector3f getUp() {
		return new Vector3f(0, 1, 0).rotate(this);
	}

	public Vector3f getDown() {
		return new Vector3f(0, -1, 0).rotate(this);
	}

	public Vector3f getRight() {
		return new Vector3f(1, 0, 0).rotate(this);
	}

	public Vector3f getLeft() {
		return new Vector3f(-1, 0, 0).rotate(this);
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

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}
	
	public Quaternion set(float x, float y, float z, float w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		return this;
	}
	
	public Quaternion set(Quaternion quat) {
		this.x = quat.getX();
		this.y = quat.getY();
		this.z = quat.getZ();
		this.w = quat.getW();
		return this;
	}
	
	public boolean equals(Quaternion other) {
		return x == other.getX() && y == other.getY() && z == other.getZ() && w == other.getW();
	}
	
}
