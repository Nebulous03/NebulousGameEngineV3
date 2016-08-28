package com.nebulous.nebulousEngine.graphics.lights;

import com.nebulous.nebulousEngine.core.logic.math.Vector3f;
import com.nebulous.nebulousEngine.graphics.Attenuation;
import com.nebulous.nebulousEngine.graphics.shaders.ForwardSpot;

public class SpotEmitter extends PointEmitter{

	private float cutoff;

    public SpotEmitter(Vector3f color, float intensity, float cutoff){
        super(color, intensity);
        this.cutoff = cutoff;
        setShader(ForwardSpot.getInstance());
    }
	
	public SpotEmitter(Vector3f color, float intensity, Attenuation atten, Vector3f direction, float cutoff){
		super(color, intensity, atten);
		this.cutoff = cutoff;
        setShader(ForwardSpot.getInstance());
	}
	
	public Vector3f getDirection(){
		return getTransform().getTransformedRot().getForward();
	}
	
	public float getCutoff(){
		return cutoff;
	}
	
	public void setCutoff(float cutoff){
		this.cutoff = cutoff;
	}

}
