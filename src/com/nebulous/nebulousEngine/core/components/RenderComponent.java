package com.nebulous.nebulousEngine.core.components;

import com.nebulous.nebulousEngine.core.RenderEngine;
import com.nebulous.nebulousEngine.core.components.GameComponent;
import com.nebulous.nebulousEngine.graphics.Material;
import com.nebulous.nebulousEngine.graphics.primatives.Mesh;
import com.nebulous.nebulousEngine.graphics.shaders.Shader;

public class RenderComponent extends GameComponent{

    private Mesh mesh;
    private Material material;

    public RenderComponent(Mesh mesh, Material material){
        this.mesh = mesh;
        this.material = material;
    }

    @Override
    public void init() {}
    
    @Override
	public void input(double delta) {}

    @Override
    public void update(double delta) {}

    @Override
    public void render(Shader shader, RenderEngine engine) {
        shader.bind();
        shader.updateUniforms(getTransform(), material, engine);
        mesh.draw();
        
    }
}
