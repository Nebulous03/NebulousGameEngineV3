package main;

import java.awt.Color;

import com.nebulous.nebulousEngine.core.Game;
import com.nebulous.nebulousEngine.core.RenderEngine;
import com.nebulous.nebulousEngine.core.logic.math.Vector3f;
import com.nebulous.nebulousEngine.core.utils.Console;
import com.nebulous.nebulousEngine.game.objects.GameObject;
import com.nebulous.nebulousEngine.game.objects.camera.Camera;
import com.nebulous.nebulousEngine.game.objects.lights.PointLight;
import com.nebulous.nebulousEngine.graphics.Material;
import com.nebulous.nebulousEngine.graphics.Texture;
import com.nebulous.nebulousEngine.graphics.primatives.Mesh;

public class TestGameV2 extends Game{

	@Override
	public void init() {
		Material test = new Material();
		test.addTexture("diffuse", new Texture("nullTexture.png"));
		test.addFloat(Material.SPECULAR_INENSITY, 2f);
		test.addFloat("specularPower", 8);
		
		GameObject monkey = new GameObject(new Mesh("monkeyV5.obj"), test);
		monkey.setTag("monkey");
		
		GameObject camera = new Camera();
        camera.setTag("camera");
        
        PointLight point = new PointLight(Color.WHITE, 1f);
        point.setPosition(0, 2, 0);
        point.setAttenuation(0,0,0.1f);
		
        add(camera);
		add(monkey);
		add(point);
	}

	@Override
	public void update(double delta) {
		
	}

	public Vector3f ambientLight = new Vector3f(0.1f,0.1f,0.1f);
	
	@Override
	public void render(RenderEngine engine) {
		if(engine.getAmbientLight() != ambientLight){
			Console.println("Set ambient");
			engine.setAmbientLight(ambientLight);
		}
	}

}
