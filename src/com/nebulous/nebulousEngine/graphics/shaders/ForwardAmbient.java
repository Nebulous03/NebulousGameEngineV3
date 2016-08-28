package com.nebulous.nebulousEngine.graphics.shaders;

import com.nebulous.nebulousEngine.core.RenderEngine;
import com.nebulous.nebulousEngine.core.logic.math.Matrix4f;
import com.nebulous.nebulousEngine.graphics.Material;
import com.nebulous.nebulousEngine.graphics.Transform;

public class ForwardAmbient extends Shader{

    private static final ForwardAmbient instance = new ForwardAmbient();

    public static ForwardAmbient getInstance(){
        return instance;
    }

    public ForwardAmbient(){
        super();
        
        String vertexShaderText = loadShader("forward-ambient.vs");
        String fragmentShaderText = loadShader("forward-ambient.fs");
        addVertexShader(vertexShaderText);
        addFragmentShader(fragmentShaderText);
        
        compile();
        
        addAllAttributes(vertexShaderText);
        addAllAttributes(fragmentShaderText);
        addAllUniforms(vertexShaderText);
        addAllUniforms(fragmentShaderText);
    }

    public void updateUniforms(Transform transform, Material material, RenderEngine engine){
        Matrix4f worldMatrix = transform.getTransformation();
        Matrix4f projectedMatrix = engine.getMainCamera().getProjection().mul(worldMatrix);
        material.getTexture("diffuse").bind(); //MAYBE BROKEN

        setUniform("mvp", projectedMatrix);
        setUniform("intensity", engine.getAmbientLight());
    }
}
