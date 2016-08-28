package com.nebulous.nebulousEngine.graphics.primatives;

import com.nebulous.nebulousEngine.core.logic.math.Vector2f;
import com.nebulous.nebulousEngine.core.logic.math.Vector3f;

public class Vertex {
	
	public static final int SIZE = 8;
	
	private Vector3f pos;
	private Vector2f texCoord;
	private Vector3f normal;
	
	public Vertex(Vector3f pos) {
		this(pos, new Vector2f(0,0));
	}
	
	public Vertex(Vector3f pos, Vector2f texCoord) {
		this(pos, new Vector2f(0,0), new Vector3f(0,0,0));
	}
	
	public Vertex(Vector3f pos, Vector2f texCoord, Vector3f normal) {
		this.pos = pos;
		this.texCoord = texCoord;
		this.normal = normal;
	}

	public Vector3f getPos() {
		return pos;
	}

	public void setPos(Vector3f pos) {
		this.pos = pos;
	}

	public static int getSize() {
		return SIZE;
	}

	public Vector2f getTexCoord() {
		return texCoord;
	}

	public void setTexCoord(Vector2f texCoord) {
		this.texCoord = texCoord;
	}

	public Vector3f getNormal() {
		return normal;
	}

	public void setNormal(Vector3f normal) {
		this.normal = normal;
	}

}
