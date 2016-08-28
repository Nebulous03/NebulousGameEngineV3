package com.nebulous.nebulousEngine.game.objects.lights;

import java.awt.Color;

import com.nebulous.nebulousEngine.core.logic.math.Vector3f;
import com.nebulous.nebulousEngine.game.objects.GameObject;
import com.nebulous.nebulousEngine.graphics.lights.DirectionalEmitter;

public class DirectionalLight extends GameObject{
	
	private DirectionalEmitter light;

	public DirectionalLight(Vector3f color, float intensity) {
		this.setTag("unnamed_directionLight");
		light = new DirectionalEmitter(color, intensity);
		addComponent(light);
	}
	
	public DirectionalLight(Color color, float intensity) {
		this.setTag("unnamed_directionLight");
		Vector3f colorFloat = new Vector3f((float)(color.getRed()) / 255f, (float)(color.getGreen()) / 255f, (float)(color.getBlue()) / 255f);
		light = new DirectionalEmitter(colorFloat, intensity);
		addComponent(light);
	}
	
	public DirectionalLight(int color, float intensity) {
		this.setTag("unnamed_directionLight");
		Color intColor = new Color(color);
		Vector3f colorFloat = new Vector3f((float)(intColor.getRed()) / 255f, (float)(intColor.getGreen()) / 255f, (float)(intColor.getBlue()) / 255f);
		light = new DirectionalEmitter(colorFloat, intensity);
		addComponent(light);
	}
	
	public void setColor(Vector3f color){
		light.setColor(color);
	}
	
	public void setColor(float red, float green, float blue){
		light.setColor(new Vector3f(red, green, blue));
	}
	
	public void setIntensity(float intensity){
		light.setIntensity(intensity);
	}

}
