package com.nebulous.nebulousEngine.core;

import com.nebulous.nebulousEngine.core.utils.Console;
import com.nebulous.nebulousEngine.game.objects.GameObject;

public abstract class Game {

	public GameObject root;
	
	public abstract void init();
	public abstract void update(double delta);
	public abstract void render(RenderEngine engine);

	public void initGame(){
		getRootObject().init();
		this.init();
	}

	public void inputGame(double delta){
		getRootObject().input(delta);
	}

	public void updateGame(double delta){
		getRootObject().update(delta);
		this.update(delta);
	}

	public void renderGame(RenderEngine engine){
		engine.render(root);
		this.render(engine);
	}
	
	public void add(GameObject object){
		getRootObject().addChild(object);
		Console.println("Object added to game: " + object.getClass().getSimpleName() 
				+ " '" + object.getTag()
				+ "' at (" + object.getTransform().getPos().getX() + ", "
				+ object.getTransform().getPos().getY() + ", "
				+ object.getTransform().getPos().getZ() + ") "
				);
	}
	
	private GameObject getRootObject() {
		if(root == null)
			root = new GameObject();
		return root;
	}
}
