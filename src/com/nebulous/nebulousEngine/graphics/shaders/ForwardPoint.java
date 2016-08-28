package com.nebulous.nebulousEngine.graphics.shaders;

import com.nebulous.nebulousEngine.core.RenderEngine;
import com.nebulous.nebulousEngine.core.logic.math.Matrix4f;
import com.nebulous.nebulousEngine.graphics.Material;
import com.nebulous.nebulousEngine.graphics.Transform;
import com.nebulous.nebulousEngine.graphics.lights.BaseEmitter;
import com.nebulous.nebulousEngine.graphics.lights.PointEmitter;

public class ForwardPoint extends Shader{

    private static final ForwardPoint instance = new ForwardPoint();

    public static ForwardPoint getInstance(){
        return instance;
    }

    public ForwardPoint(){
        super();

        addVertexShaderFromFile("forward-point.vs");
        addFragmentShaderFromFile("forward-point.fs");
        setAttribLocation("position", 0);
        setAttribLocation("texCoord", 1);
        setAttribLocation("normal", 2);
        compile();

        addUniform("model");
        addUniform("mvp");
        addUniform("specularIntensity");
        addUniform("specularPower");
        addUniform("eyePos");

        addUniform("pointLight.base.color");
        addUniform("pointLight.base.intensity");
        addUniform("pointLight.atten.constant");
        addUniform("pointLight.atten.linear");
        addUniform("pointLight.atten.exponent");
        addUniform("pointLight.position");
        addUniform("pointLight.range");

    }

    public void updateUniforms(Transform transform, Material material, RenderEngine engine){
        Matrix4f worldMatrix = transform.getTransformation();
        Matrix4f projectedMatrix = engine.getMainCamera().getProjection().mul(worldMatrix);
        material.getTexture("diffuse").bind(); //MAYBE BROKEN

        setUniform("model", worldMatrix);
        setUniform("mvp", projectedMatrix);
        setUniformf("specularIntensity", material.getFloat("specularIntensity"));
        setUniformf("specularPower", material.getFloat("specularPower"));
        setUniform("eyePos", engine.getMainCamera().getTransform().getTransformedPos());
        setUniformPointLight("pointLight", (PointEmitter) engine.getActiveLight());
    }

    public void setUniformBaseLight(String uniformName, BaseEmitter baseLight)
    {
        setUniform(uniformName + ".color", baseLight.getColor());
        setUniformf(uniformName + ".intensity", baseLight.getIntensity());
    }

    public void setUniformPointLight(String uniformName, PointEmitter pointLight)
    {
        setUniformBaseLight(uniformName + ".base", pointLight);
        setUniformf(uniformName + ".atten.constant", pointLight.getAttenuation().getConstant());
        setUniformf(uniformName + ".atten.linear", pointLight.getAttenuation().getLinear());
        setUniformf(uniformName + ".atten.exponent", pointLight.getAttenuation().getExponent());
        setUniform(uniformName + ".position", pointLight.getTransform().getPos());
        setUniformf(uniformName + ".range", pointLight.getRange());
    }

}
