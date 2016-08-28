package com.nebulous.nebulousEngine.graphics.data;

import static org.lwjgl.opengl.GL15.*;

public class MeshData {
	
	private int vbo;
	private int ibo;
	private int size;
	private int index;

	public MeshData() {
		vbo = glGenBuffers();
		ibo = glGenBuffers();
		index = 1;
	}
	
	@Override
	protected void finalize(){
		try {
			super.finalize();
			glDeleteBuffers(vbo);
			glDeleteBuffers(ibo);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void increaseIndex(){
		index++;
	}
	
	public boolean decreaseIndex(){
		index--;
		return index == 0;
	}

	public int getVbo() {
		return vbo;
	}

	public int getIbo() {
		return ibo;
	}

	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
