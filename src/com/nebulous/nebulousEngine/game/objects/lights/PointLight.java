package com.nebulous.nebulousEngine.game.objects.lights;

import java.awt.Color;

import com.nebulous.nebulousEngine.core.logic.math.Vector3f;
import com.nebulous.nebulousEngine.game.objects.GameObject;
import com.nebulous.nebulousEngine.graphics.Attenuation;
import com.nebulous.nebulousEngine.graphics.lights.PointEmitter;

public class PointLight extends GameObject{
	
	private PointEmitter light;

	public PointLight(Vector3f color, float intensity) {
		this.setTag("unnamed_pointLight");
		light = new PointEmitter(color, intensity);
		addComponent(light);
	}
	
	public PointLight(Color color, float intensity) {
		this.setTag("unnamed_pointLight");
		Vector3f colorFloat = new Vector3f((float)(color.getRed()) / 255f, (float)(color.getGreen()) / 255f, (float)(color.getBlue()) / 255f);
		light = new PointEmitter(colorFloat, intensity);
		addComponent(light);
	}
	
	public PointLight(int color, float intensity) {
		this.setTag("unnamed_pointLight");
		Color intColor = new Color(color);
		Vector3f colorFloat = new Vector3f((float)(intColor.getRed()) / 255f, (float)(intColor.getGreen()) / 255f, (float)(intColor.getBlue()) / 255f);
		light = new PointEmitter(colorFloat, intensity);
		addComponent(light);
	}
	
	public Attenuation getAttenuation() {
		return light.getAttenuation();
	}

	public void setAttenuation(Attenuation atten) {
		light.setAttenuation(atten);
	}
	
	public void setAttenuation(float constant, float liner, float exponent) {
		light.setAttenuation(new Attenuation(constant, liner, exponent));
	}

	public float getRange() {
		return light.getRange();
	}

	public void setRange(float range) {
		light.setRange(range);
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
