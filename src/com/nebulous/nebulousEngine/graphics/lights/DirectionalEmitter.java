package com.nebulous.nebulousEngine.graphics.lights;

import com.nebulous.nebulousEngine.core.logic.math.Vector3f;
import com.nebulous.nebulousEngine.graphics.shaders.ForwardDirectional;

public class DirectionalEmitter extends BaseEmitter{

	public DirectionalEmitter(Vector3f color, float intensity){
		super(color, intensity);
        this.setShader(ForwardDirectional.getInstance());
	}
	
	public Vector3f getDirection(){
		return getTransform().getTransformedRot().getForward();
	}
	
}
