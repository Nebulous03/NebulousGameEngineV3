package com.nebulous.nebulousEngine.graphics;

import java.util.HashMap;

import com.nebulous.nebulousEngine.core.logic.math.Vector3f;
import com.nebulous.nebulousEngine.core.utils.nullUtils;

public class Material {
	
	public static final String SPECULAR_INENSITY = "specularIntensity";
	public static final String SPECULAR_POWER = "specularPower";
	
	public static final String TEXTURE_DIFFUSE = "diffuse";
	
	private HashMap<String, Texture> textureHash;
	private HashMap<String, Vector3f> vector3fHash;
	private HashMap<String, Float> floatHash;
	
	public Material(Texture texture) {
		this(texture, new Vector3f(1,1,1));
	}

	public Material(Texture texture, Vector3f color) {
		this(texture, color, 2, 32);
	}
	
	public Material(Texture texture, Vector3f color, float specularIntensity, float specularExponent) {

	}
	
	public Material(){
		textureHash = new HashMap<String, Texture>();
		vector3fHash = new HashMap<String, Vector3f>();
		floatHash = new HashMap<String, Float>();
	}
	
	public void addTexture(String textureType, Texture texture) {
		textureHash.put(textureType, texture);
	}

	public void addVector3f(String name, Vector3f color) {
		vector3fHash.put(name, color);
	}

	public void addFloat(String floatType, float specular) {
		floatHash.put(floatType, specular);
	}
	
	public Texture getTexture(String name){
		Texture result = textureHash.get(name);
		if(result != null) return result; 
		return nullUtils.nullTexture;
	}
	
	public Vector3f getVector3f(String name){
		Vector3f result = vector3fHash.get(name);
		if(result != null) return result;
		return new Vector3f(0, 0, 0);
	}
	
	public float getFloat(String name){
		Float result = floatHash.get(name);
		if(result != null) return result;
		return 0;
	}

}
