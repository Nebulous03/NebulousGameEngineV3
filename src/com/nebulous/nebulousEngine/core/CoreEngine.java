package com.nebulous.nebulousEngine.core;

import com.nebulous.nebulousEngine.core.utils.Time;
import org.lwjgl.glfw.GLFW;

import com.nebulous.nebulousEngine.core.logic.Input;
import com.nebulous.nebulousEngine.core.utils.Console;

public class CoreEngine {

	private boolean running;
    private double framerate;
    private int width;
    private int height;
	private Game game;
    private RenderEngine renderEngine;
	
	public CoreEngine(Game game, double framerate){
        Console.printMOTD("NEBULOUS 3D ENGINE V0.9 pre-alpha");
        this.game = game;
        this.framerate = 1.0/framerate;
		this.running = false;
	}

    private void initGraphics(){
        RenderEngine.printGLStats();
        this.renderEngine = new RenderEngine();
    }

    public void createWindow(String title, int width, int height){
        this.width = width;
        this.height = height;
        Window.createWindow(width, height, title);
        initGraphics();
    }
	
	public void start(){
		if(running) return;
		tick();
	}
	
	public void stop(){
		if(!running) return;
		running = false;
	}
	
	private void tick(){
		running = true;
		
		boolean render = false;
		GLFW.glfwSwapInterval(1);							// FRAME CAP LIMITER (SET TO 0 / 1)
		
		int frames = 0;
        double frameCounter = 0;
		
		double lastTime = Time.getTime();
		double unprocessedTime = 0;

        game.initGame();
		
		while(running) {
            double startTime = Time.getTime();
            double pastTime = startTime - lastTime;
			lastTime = startTime;
			unprocessedTime += pastTime;
			frameCounter += pastTime;
			
			while(unprocessedTime > framerate){
				render = true;
				unprocessedTime -= framerate;
				if(Window.shouldClose())
					stop();

				game.inputGame(framerate);
				Input.update();
				
				game.updateGame(framerate);

				if(frameCounter >= 1.0){
					Console.println("FPS: " + frames + " Delta: " + pastTime*1000); //TODO: Fix this
					frameCounter = 0;
					frames = 0;
				}
			}
			
			if(render){
				game.renderGame(renderEngine);
                Window.render();
				frames++;
			}
			
			else{
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}

	}

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}
