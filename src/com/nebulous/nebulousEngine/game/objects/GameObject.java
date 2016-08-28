package com.nebulous.nebulousEngine.game.objects;

import com.nebulous.nebulousEngine.core.RenderEngine;
import com.nebulous.nebulousEngine.core.components.GameComponent;
import com.nebulous.nebulousEngine.core.components.RenderComponent;
import com.nebulous.nebulousEngine.core.logic.math.Quaternion;
import com.nebulous.nebulousEngine.core.logic.math.Vector3f;
import com.nebulous.nebulousEngine.graphics.Material;
import com.nebulous.nebulousEngine.graphics.Transform;
import com.nebulous.nebulousEngine.graphics.primatives.Mesh;
import com.nebulous.nebulousEngine.graphics.shaders.Shader;

import java.util.ArrayList;

public class GameObject {

    private ArrayList<GameObject> children;
    private ArrayList<GameComponent> components;
    private String tag;

    private Transform transform;

    public GameObject(){
    	if(tag == null) this.setTag("unnamed_object");
        this.children = new ArrayList<GameObject>();
        this.components = new ArrayList<GameComponent>();
        this.transform = new Transform();
    }
    
    public GameObject(Mesh mesh, Material material){
    	if(tag == null) this.setTag("unnamed_object");
        this.children = new ArrayList<GameObject>();
        this.components = new ArrayList<GameComponent>();
        this.transform = new Transform();
        addComponent(new RenderComponent(mesh, material));
    }

    public void addChild(GameObject object){
        children.add(object);
        object.getTransform().setParent(transform);
    }

    public GameObject addComponent(GameComponent component){
        components.add(component);
        component.setParent(this);
        return this;
    }

    public void init(){
        for(GameComponent component: components)
            component.init();
        for(GameObject child: children)
            child.init();
    }

    public void input(double delta){
        for(GameComponent component: components)
            component.input(delta);
        for(GameObject child: children)
            child.input(delta);
    }

    public void update(double delta){
    	transform.update();
        for(GameComponent component: components)
            component.update(delta);
        for(GameObject child: children)
            child.update(delta);
    }

    public void render(Shader shader, RenderEngine engine){
        for(GameComponent component: components)
            component.render(shader, engine);
        for(GameObject child: children)
            child.render(shader, engine);
    }

    public void addToRenderEngine(RenderEngine engine){
        for(GameComponent component: components)
            component.addToRenderEngine(engine);
        for(GameObject child: children)
            child.addToRenderEngine(engine);
    }
    
    public void setPosition(Vector3f pos){
		getTransform().setPos(pos);
	}
    
    public Vector3f getPostion(){
    	return getTransform().getPos();
    }
	
	public void setPosition(float x, float y, float z){
		getTransform().getPos().set(x, y, z);
	}
	
	public void setRotation(Vector3f axis, float angle){
		getTransform().setRot(new Quaternion(axis, angle));
	}
	
	public void setRotation(float rx, float ry, float rz, float angle){
		getTransform().setRot(new Quaternion(new Vector3f(rx, ry, rz), angle));
	}
	
    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public ArrayList<GameObject> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<GameObject> children) {
        this.children = children;
    }

    public ArrayList<GameComponent> getComponents() {
        return components;
    }

    public void setComponents(ArrayList<GameComponent> components) {
        this.components = components;
    }

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
