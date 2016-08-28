package com.nebulous.nebulousEngine.graphics.shaders;

import com.nebulous.nebulousEngine.core.RenderEngine;
import com.nebulous.nebulousEngine.core.logic.math.Matrix4f;
import com.nebulous.nebulousEngine.graphics.Material;
import com.nebulous.nebulousEngine.graphics.Transform;
import com.nebulous.nebulousEngine.graphics.lights.BaseEmitter;
import com.nebulous.nebulousEngine.graphics.lights.PointEmitter;
import com.nebulous.nebulousEngine.graphics.lights.SpotEmitter;

/**
 * Created by Nebulous on 7/7/2016.
 */
public class ForwardSpot extends Shader{

    private static final ForwardSpot instance = new ForwardSpot();

    public static ForwardSpot getInstance(){
        return instance;
    }

    public ForwardSpot(){
        super();

        addVertexShaderFromFile("forward-spot.vs");
        addFragmentShaderFromFile("forward-spot.fs");
        setAttribLocation("position", 0);
        setAttribLocation("texCoord", 1);
        setAttribLocation("normal", 2);
        compile();

        addUniform("model");
        addUniform("mvp");
        addUniform("specularIntensity");
        addUniform("specularPower");
        addUniform("eyePos");

        addUniform("spotLight.pointLight.base.color");
        addUniform("spotLight.pointLight.base.intensity");
        addUniform("spotLight.pointLight.atten.constant");
        addUniform("spotLight.pointLight.atten.linear");
        addUniform("spotLight.pointLight.atten.exponent");
        addUniform("spotLight.pointLight.position");
        addUniform("spotLight.pointLight.range");
        addUniform("spotLight.direction");
        addUniform("spotLight.cutoff");

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
        setUniformSpotLight("spotLight", (SpotEmitter) engine.getActiveLight());
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
        setUniform(uniformName + ".position", pointLight.getTransform().getTransformedPos());
        setUniformf(uniformName + ".range", pointLight.getRange());
    }

    public void setUniformSpotLight(String uniformName, SpotEmitter spotLight)
    {
        setUniformPointLight(uniformName + ".pointLight", spotLight);
        setUniform(uniformName + ".direction", spotLight.getDirection());
        setUniformf(uniformName + ".cutoff", spotLight.getCutoff());
    }

}
