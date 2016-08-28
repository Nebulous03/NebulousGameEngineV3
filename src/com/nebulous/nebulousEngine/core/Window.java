package com.nebulous.nebulousEngine.core;

import com.nebulous.nebulousEngine.core.logic.math.Vector2f;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import com.nebulous.nebulousEngine.core.utils.Console;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Window {

	private static long window;
	private static int width, height;
	
	private static boolean fullscreen;
	
	public static void createWindow(int width, int height, String title) {
		setWidth(width);
		setHeight(height);
		createWindow(title);
	}
	
	public static void createWindow(String title) {
		Console.println("GLFW initializing...");
		if (glfwInit() != 1) {
			System.err.println("GLFW failed to initialize!");
			System.exit(1);
		}

		window = glfwCreateWindow(width, height, title, fullscreen ? glfwGetPrimaryMonitor() : 0, 0);

		if (window == 0) {
			throw new IllegalStateException("Failed to create window.");
		}

		if (!fullscreen) {
			GLFWVidMode monitor = glfwGetVideoMode(glfwGetPrimaryMonitor());
			glfwSetWindowPos(window, (monitor.width() - width) / 2, (monitor.height() - height) / 2);

			glfwShowWindow(window);
			Console.println("GLFW window initialized in normal mode...");
		}

		glfwMakeContextCurrent(window);

		GL.createCapabilities();
		Console.println("GLFW context initialized...");

		glEnable(GL_TEXTURE_2D);
		Console.println("OpenGL textures enabled...");
	}
	
	public static void setCallbacks() {
		glfwSetErrorCallback(new GLFWErrorCallback() {

			@Override
			public void invoke(int error, long description) {
				throw new IllegalStateException(GLFWErrorCallback.getDescription(description));
			}

		});
	}

	public static boolean shouldClose() {
		return glfwWindowShouldClose(window) != 0;
	}

	public static void render() {
		glfwSwapBuffers(window);
		glfwPollEvents();
	}

	public static int getWidth() {
		return width;
	}

	public static void setWidth(int width) {
		Window.width = width;
	}

	public static int getHeight() {
		return height;
	}

	public static void setHeight(int height) {
		Window.height = height;
	}

	public static boolean isFullscreen() {
		return fullscreen;
	}

	public static void setFullscreen(boolean fullscreen) {
		Window.fullscreen = fullscreen;
	}

	public static long getWindow() {
		return window;
	}

	public static Vector2f getCenter(){
        return new Vector2f(getWidth()/2, getHeight()/2);
    }
}
