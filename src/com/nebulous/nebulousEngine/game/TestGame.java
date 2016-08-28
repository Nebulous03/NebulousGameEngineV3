package com.nebulous.nebulousEngine.game;

import java.awt.Color;

import com.nebulous.nebulousEngine.core.Game;
import com.nebulous.nebulousEngine.core.RenderEngine;
import com.nebulous.nebulousEngine.core.components.PhysicsComponent;
import com.nebulous.nebulousEngine.core.logic.math.Rotation;
import com.nebulous.nebulousEngine.core.logic.math.Vector3f;
import com.nebulous.nebulousEngine.core.utils.Console;
import com.nebulous.nebulousEngine.game.objects.GameObject;
import com.nebulous.nebulousEngine.game.objects.camera.Camera;
import com.nebulous.nebulousEngine.game.objects.lights.DirectionalLight;
import com.nebulous.nebulousEngine.game.objects.lights.PointLight;
import com.nebulous.nebulousEngine.game.objects.lights.SpotLight;
import com.nebulous.nebulousEngine.graphics.*;
import com.nebulous.nebulousEngine.graphics.primatives.Mesh;
import com.nebulous.nebulousEngine.physics.PhysicsEngine;
import com.nebulous.nebulousEngine.physics.PhysicsObject;

public class TestGame extends Game {
	
	public GameObject rotatingCube;
	
    PhysicsEngine physics = new PhysicsEngine(); //TEMP

	@Override
    public void init(){

        Material duffuse = new Material();
        duffuse.addTexture(Material.TEXTURE_DIFFUSE, new Texture("/textures/nullTexture.png"));
        duffuse.addFloat(Material.SPECULAR_INENSITY, 0.2f);
        duffuse.addFloat(Material.SPECULAR_POWER, 8);
        
        Material shiney = new Material();
        shiney.addTexture(Material.TEXTURE_DIFFUSE, new Texture("/textures/nullTexture.png"));
        shiney.addFloat(Material.SPECULAR_INENSITY, 1);
        shiney.addFloat(Material.SPECULAR_POWER, 8);
        
        //Camera
        GameObject camera = new Camera();
        camera.setTag("camera");
        
        //World Objects
        GameObject ground = new GameObject(new Mesh("/models/playgroundV2.obj"), duffuse);
        ground.setTag("ground");
        ground.setPosition(new Vector3f(0,-1,0));
        
        GameObject cube = new GameObject(new Mesh("/models/cubeV2.obj"), shiney);
        cube.setTag("cube");
        cube.setPosition(5, 0, 0);
        
        GameObject sphere = new GameObject(new Mesh("/models/sphereV2.obj"), shiney);
        sphere.setTag("sphere");
        
        GameObject monkey = new GameObject(new Mesh("/models/monkeyV5.obj"), shiney);
        monkey.setTag("monkey");
        monkey.setPosition(-5, 0, 0);
        monkey.setRotation(0,1,0, 180.0f);
        
        rotatingCube = new GameObject(new Mesh("/models/cubeV2.obj"), duffuse);
        rotatingCube.setPosition(-7, 4, 7);

        //Directional Light
		DirectionalLight directionalLight = new DirectionalLight(new Vector3f(1,1,1), 0.3f);
		directionalLight.setPosition(0, 1, 0);
        directionalLight.setRotation(1,0,0, -135.0f);

        //Point Light
        PointLight greenPointLight = new PointLight(Color.GREEN, 1f);
        greenPointLight.setPosition(5, 0.5f, 5);
        
        PointLight redPointLight = new PointLight(Color.RED, 1f);
        redPointLight.setPosition(0, 0.5f, 5);
        
        PointLight bluePointLight = new PointLight(Color.BLUE, 1f);
        bluePointLight.setPosition(-5, 0.5f, 5);

        //Spot Light
        SpotLight sphereSpotLight = new SpotLight(new Vector3f(0,1,0.5f), 0.6f, new Vector3f(0,-1,0), 0.7f);
        sphereSpotLight.setPosition(0, 3, 0);
        sphereSpotLight.setRotation(1,0,0, 90.0f);
        sphereSpotLight.setAttenuation(0, 0, 0.3f);
        sphereSpotLight.setRange(60f);
        
        SpotLight monkeySpotLight = new SpotLight(Color.WHITE, 0.6f, new Vector3f(0,-1,0), 0.7f);
        monkeySpotLight.setPosition(-5, 3, 0);
        monkeySpotLight.setRotation(1,0,0, 90.0f);
        monkeySpotLight.setAttenuation(0, 0, 0.3f);
        monkeySpotLight.setRange(60f);
        
        SpotLight cubeSpotLight = new SpotLight(new Vector3f(1,0.5f,0.5f), 0.8f, new Vector3f(0,-1,0), 0.7f);
        cubeSpotLight.setPosition(5, 3, 0);
        cubeSpotLight.setRotation(1,0,0, 90.0f);
        cubeSpotLight.setAttenuation(0, 0, 0.3f);
        cubeSpotLight.setRange(60f);

        //Add Objects
        add(camera);
        
        add(ground);
        add(cube);
        add(sphere);
        add(monkey);
        
        add(rotatingCube);
        
        add(directionalLight);
        
        add(greenPointLight);
        add(redPointLight);
        add(bluePointLight);
        
        add(cubeSpotLight);
        add(sphereSpotLight);
        add(monkeySpotLight);
        
        //PHYSICS
        physics.addObject(new PhysicsObject(new Vector3f(0,0,0), new Vector3f(1.0f,1.0f,0.0f)));
        physics.addObject(new PhysicsObject(new Vector3f(20f,30f,-9f), new Vector3f(-0.8f,-0.9f,0.7f)));
        for(PhysicsObject object : physics.getObjects()){
        	add(new GameObject(new Mesh("/models/sphereV2.obj"), shiney).addComponent(new PhysicsComponent(physics, object)));
        }
        
    }
    
	int rot = 0;
	
    @Override
    public void update(double delta){
    	physics.update(delta); //TEMP
    	rotatingCube.setRotation(Rotation.yAxis, (float)(rot * 10));
    	rot++;
    	if(rot > 3000){
    		Console.println("RESET");
    		rot = 0;
    	}
    }

	@Override
	public void render(RenderEngine engine) {
		
	}

}
