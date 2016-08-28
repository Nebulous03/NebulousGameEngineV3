package com.nebulous.nebulousEngine.graphics.lights;

import com.nebulous.nebulousEngine.core.RenderEngine;
import com.nebulous.nebulousEngine.core.components.GameComponent;
import com.nebulous.nebulousEngine.core.logic.math.Vector3f;
import com.nebulous.nebulousEngine.graphics.shaders.Shader;

public class BaseEmitter extends GameComponent{
	
	private Vector3f color;
	private float intensity;
	private Shader shader;
	
	public BaseEmitter(Vector3f color, float intensity) {
		this.color = color;
		this.intensity = intensity;
	}
	
	@Override
	public void init() {}

	@Override
	public void input(double delta) {}
	
	@Override
	public void update(double delta) {}

	@Override
	public void render(Shader shader, RenderEngine engine) {}

	public void setShader(Shader shader){
		this.shader = shader;
	}

    public Shader getShader(){
        return shader;
    }

    @Override
    public void addToRenderEngine(RenderEngine engine){
        engine.addLight(this);
    }

	public Vector3f getColor() {
		return color;
	}

	public void setColor(Vector3f color) {
		this.color = color;
	}

	public float getIntensity() {
		return intensity;
	}

	public void setIntensity(float intensity) {
		this.intensity = intensity;
	}

}
