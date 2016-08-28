package com.nebulous.nebulousEngine.core;

import com.nebulous.nebulousEngine.core.components.CameraComponent;
import com.nebulous.nebulousEngine.core.logic.math.Vector3f;
import com.nebulous.nebulousEngine.core.utils.Console;
import com.nebulous.nebulousEngine.game.objects.GameObject;
import com.nebulous.nebulousEngine.graphics.lights.BaseEmitter;
import com.nebulous.nebulousEngine.graphics.shaders.*;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL30.GL_FRAMEBUFFER_SRGB;
import static org.lwjgl.opengl.GL32.GL_DEPTH_CLAMP;

public class RenderEngine {

    private CameraComponent mainCamera;

    private Vector3f ambientLight;
    private ArrayList<BaseEmitter> lights;
    private BaseEmitter activeLight;

    public RenderEngine(){
        lights = new ArrayList<BaseEmitter>();

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);

        glEnable(GL_DEPTH_CLAMP);

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_FRAMEBUFFER_SRGB);

        ambientLight = new Vector3f(0.01f,0.01f,0.01f);
    }

    public Vector3f getAmbientLight(){
        return ambientLight;
    }

    public void addLight(BaseEmitter light){
        this.lights.add(light);
    }
    
    public void addCamera(CameraComponent camera){
    	mainCamera = camera;
    }

    public BaseEmitter getActiveLight(){
        return activeLight;
    }

    public void render(GameObject object){
        clearScreen();
        lights.clear();

        object.addToRenderEngine(this);
        Shader forwardAmbient = ForwardAmbient.getInstance();
    	object.render(forwardAmbient, this);

        glEnable(GL_BLEND);

        glBlendFunc(GL_ONE, GL_ONE);
        glDepthMask(true);
        glDepthFunc(GL_EQUAL);

        for(BaseEmitter light : lights) {
            activeLight = light;
            object.render(light.getShader(), this);
        }

        glDepthFunc(GL_LESS);
        glDepthMask(true);
        glDisable(GL_BLEND);
    }

    private static void clearScreen(){
        //TODO: Stencil Buffer
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void setClearColor(Vector3f color){
        glClearColor(color.getX(), color.getY(), color.getZ(), 1.0f);
    }

    public static void setTextures(boolean enabled){
        if(enabled)
            glEnable(GL_TEXTURE_2D);
        else
            glDisable(GL_TEXTURE_2D);
    }

    public static void unbindTextures(){
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public static void printGLStats(){
        Console.printMOTD(

        " OPENGL : " + glGetString(GL_VERSION) + "\n" +
        " GRAPHICS : " + glGetString(GL_RENDERER) + "\n" +
        " VENDORS : " + glGetString(GL_VENDOR) + "\n" +
        " OPERATING SYSTEM : " + System.getProperty("os.name") + "\n" +
        " JAVA VERSION : " + System.getProperty("java.version") + "\n" +
        " CURRENT DIRECTORY : \n" +
        " " + System.getProperty("user.dir")

        );
    }

    public CameraComponent getMainCamera() {
        return mainCamera;
    }

    public void setMainCamera(CameraComponent mainCamera) {
        this.mainCamera = mainCamera;
    }

	public ArrayList<BaseEmitter> getLights() {
		return lights;
	}

	public void setAmbientLight(Vector3f ambientLight) {
		this.ambientLight = ambientLight;
	}

}
