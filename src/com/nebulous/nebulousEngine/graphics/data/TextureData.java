package com.nebulous.nebulousEngine.graphics.data;

import static org.lwjgl.opengl.GL15.*;

public class TextureData {
	
	private int id;
	private int index;

	public TextureData(int id) {
		this.id = id;
	}
	
	@Override
	protected void finalize(){
		try {
			super.finalize();
			glDeleteBuffers(id);
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

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getId() {
		return id;
	}

}
