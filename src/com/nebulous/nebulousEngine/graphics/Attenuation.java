package com.nebulous.nebulousEngine.graphics;

public class Attenuation {
	
	private float constant;
	private float liner;
	private float exponent;

	public Attenuation(float constant, float liner, float exponent) {
		this.constant = constant;
		this.liner = liner;
		this.exponent = exponent;
	}

	public float getConstant() {
		return constant;
	}

	public void setConstant(float constant) {
		this.constant = constant;
	}

	public float getLinear() {
		return liner;
	}

	public void setLiner(float liner) {
		this.liner = liner;
	}

	public float getExponent() {
		return exponent;
	}

	public void setExponent(float exponent) {
		this.exponent = exponent;
	}

}
