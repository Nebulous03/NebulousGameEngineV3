package com.nebulous.nebulousEngine.core.components;

import com.nebulous.nebulousEngine.core.RenderEngine;
import com.nebulous.nebulousEngine.game.objects.GameObject;
import com.nebulous.nebulousEngine.graphics.Transform;
import com.nebulous.nebulousEngine.graphics.shaders.Shader;

public abstract class GameComponent {
	
	private GameObject parent;

    public abstract void init();
    public abstract void input(double delta);
    public abstract void update(double delta);
    public abstract void render(Shader shader, RenderEngine engine);

    public void addToRenderEngine(RenderEngine engine){}
    
    public Transform getTransform(){
    	return parent.getTransform();
    }
    
	public GameObject getParent() {
		return parent;
	}
	public void setParent(GameObject parent) {
		this.parent = parent;
	}

}
