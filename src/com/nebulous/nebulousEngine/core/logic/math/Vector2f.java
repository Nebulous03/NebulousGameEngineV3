package com.nebulous.nebulousEngine.core.logic.math;

public class Vector2f {
	
	private float x;
	private float y;

	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float length(){
		return (float)Math.sqrt(x * x + y * y);
	}
	
	public float dot(Vector2f vec2f){
		return x * vec2f.getX() + y * vec2f.getY();
	}
	
	public Vector2f normalize(){
		float length = length();
		
		x /= length;
		y /= length;
		
		return this;
	}

    public float cross(Vector2f other){
        return x * other.getY() - y * other.getX();
    }
	
	public Vector2f rotate(float angle){
		double rad = Math.toRadians(angle);
		double sin = Math.sin(rad);
		double cos = Math.cos(rad);
		
		return new Vector2f((float)(x * cos - y * sin), (float)(x * sin + y * cos));
	}

	public Vector2f lerp(Vector2f dest, float lerpFactor){
		return dest.sub(this).mul(lerpFactor).add(this);
	}

	public Vector2f add(Vector2f vec2f){
		return new Vector2f(x + vec2f.getX(), y + vec2f.getY());
	}
	
	public Vector2f add(float value){
		return new Vector2f(x + value, y + value);
	}
	
	public Vector2f sub(Vector2f vec2f){
		return new Vector2f(x - vec2f.getX(), y - vec2f.getY());
	}
	
	public Vector2f sub(float value){
		return new Vector2f(x - value, y - value);
	}
	
	public Vector2f mul(Vector2f vec2f){
		return new Vector2f(x * vec2f.getX(), y * vec2f.getY());
	}
	
	public Vector2f mul(float value){
		return new Vector2f(x * value, y * value);
	}
	
	public Vector2f dev(Vector2f vec2f){
		return new Vector2f(x / vec2f.getX(), y / vec2f.getY());
	}
	
	public Vector2f dev(float value){
		return new Vector2f(x / value, y / value);
	}
	
	public float max(){
		return Math.max(x, y);
	}
	
	public float min(){
		return Math.min(x, y);
	}
	
	public String toString(){
		return "(" + x + ", " + y + ")";
	}
	
	public Vector2f set(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	public Vector2f set(Vector2f vec2) {
		this.x = vec2.getX();
		this.y = vec2.getY();
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
	
	public boolean equals(Vector2f other) {
		return x == other.getX() && y == other.getY();
	}

}
