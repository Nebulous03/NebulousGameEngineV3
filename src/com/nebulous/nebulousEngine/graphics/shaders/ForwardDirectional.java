package com.nebulous.nebulousEngine.graphics.shaders;

import com.nebulous.nebulousEngine.core.RenderEngine;
import com.nebulous.nebulousEngine.core.logic.math.Matrix4f;
import com.nebulous.nebulousEngine.graphics.Material;
import com.nebulous.nebulousEngine.graphics.Transform;
import com.nebulous.nebulousEngine.graphics.lights.BaseEmitter;
import com.nebulous.nebulousEngine.graphics.lights.DirectionalEmitter;

/**
 * Created by Nebulous on 7/7/2016.
 */
public class ForwardDirectional extends Shader{

    private static final ForwardDirectional instance = new ForwardDirectional();

    public static ForwardDirectional getInstance(){
        return instance;
    }

    public ForwardDirectional(){
        super();

        addVertexShaderFromFile("forward-directional.vs");
        addFragmentShaderFromFile("forward-directional.fs");
        setAttribLocation("position", 0);
        setAttribLocation("texCoord", 1);
        setAttribLocation("normal", 2);
        compile();

        addUniform("model");
        addUniform("mvp");
        addUniform("specularIntensity");
        addUniform("specularPower");
        addUniform("eyePos");

        addUniform("directionalLight.base.color");
        addUniform("directionalLight.base.intensity");
        addUniform("directionalLight.direction");
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
        setUniformDirectionalLight("directionalLight", (DirectionalEmitter) engine.getActiveLight());
    }

    public void setUniformBaseLight(String uniformName, BaseEmitter baseLight)
    {
        setUniform(uniformName + ".color", baseLight.getColor());
        setUniformf(uniformName + ".intensity", baseLight.getIntensity());
    }

    public void setUniformDirectionalLight(String uniformName, DirectionalEmitter directionalLight)
    {
        setUniformBaseLight(uniformName + ".base", directionalLight);
        setUniform(uniformName + ".direction", directionalLight.getDirection());
    }

}
