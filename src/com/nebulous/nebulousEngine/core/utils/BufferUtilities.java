package com.nebulous.nebulousEngine.core.utils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import com.nebulous.nebulousEngine.core.logic.math.Matrix4f;
import org.lwjgl.BufferUtils;

import com.nebulous.nebulousEngine.graphics.primatives.Vertex;

public class BufferUtilities {
	
	public static FloatBuffer createFloatBuffer(int size){
		return BufferUtils.createFloatBuffer(size);
	}
	
	public static IntBuffer createIntBuffer(int size){
		return BufferUtils.createIntBuffer(size);
	}
	
	public static IntBuffer createFlippedBuffer(int... values){
		IntBuffer buffer = createIntBuffer(values.length);
		buffer.put(values);
		buffer.flip();
		return buffer;
	}

	public static FloatBuffer createFlippedBuffer(Vertex[] verticies){
		FloatBuffer buffer = createFloatBuffer(verticies.length * Vertex.SIZE);
		for(int i = 0; i < verticies.length; i++){
			buffer.put(verticies[i].getPos().getX());
			buffer.put(verticies[i].getPos().getY());
			buffer.put(verticies[i].getPos().getZ());
			buffer.put(verticies[i].getTexCoord().getX());
			buffer.put(verticies[i].getTexCoord().getY());
			buffer.put(verticies[i].getNormal().getX());
			buffer.put(verticies[i].getNormal().getY());
			buffer.put(verticies[i].getNormal().getZ());
		}
		buffer.flip();
		return buffer;
	}
	
	public static FloatBuffer createFlippedBuffer(Matrix4f value){
		FloatBuffer buffer = createFloatBuffer(4 * 4);
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				buffer.put(value.get(i, j));
		buffer.flip();
		return buffer;
	}
	
	public static String[] removeEmptyStrings(String[] data){
		ArrayList<String> result = new ArrayList<String>();
		for(int i = 0; i < data.length; i++) // MIGHT BE BROKEN
			if(!data[i].equals(""))
				result.add(data[i]);
		String[] res = new String[result.size()];
		result.toArray(res);
		return res;
	}

	public static int[] toIntArray(Integer[] data) {
		int[] result = new int[data.length];
		for(int i = 0; i < data.length; i++)
			result[i] = data[i].intValue();
		return result;
	}

}
