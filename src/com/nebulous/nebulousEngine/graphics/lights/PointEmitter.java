package com.nebulous.nebulousEngine.graphics.lights;

import com.nebulous.nebulousEngine.graphics.Attenuation;
import com.nebulous.nebulousEngine.core.logic.math.Vector3f;
import com.nebulous.nebulousEngine.graphics.shaders.ForwardPoint;

public class PointEmitter extends BaseEmitter{
	
	private static final int COLOR_DEPTH = 256;
	
	private Attenuation attenuation;
	private float range;

	public PointEmitter(Vector3f color, float intensity, Attenuation atten) {
		super(color, intensity);
		this.attenuation = atten;
		
		float a = attenuation.getConstant();
		float b = attenuation.getLinear();
		float c = attenuation.getExponent() - COLOR_DEPTH * getIntensity() * getColor().max();
		
		this.range = (float)((-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a));

        setShader(ForwardPoint.getInstance());
	}

    public PointEmitter(Vector3f color, float intensity) {
        super(color, intensity);
        this.attenuation = new Attenuation(0,0,1);
        
        float a = attenuation.getConstant();
		float b = attenuation.getLinear();
		float c = attenuation.getExponent() - COLOR_DEPTH * getIntensity() * getColor().max();
		
		this.range = (float)((-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a));

        setShader(ForwardPoint.getInstance());
    }

	public Attenuation getAttenuation() {
		return attenuation;
	}

	public void setAttenuation(Attenuation atten) {
		this.attenuation = atten;
	}

	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}

}
