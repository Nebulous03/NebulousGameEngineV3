package com.nebulous.nebulousEngine.core.logic;

import java.nio.DoubleBuffer;
import java.util.ArrayList;

import com.nebulous.nebulousEngine.core.Window;
import com.nebulous.nebulousEngine.core.logic.math.Vector2f;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

public class Input {
	 
    public static final Integer KEY_UNKNOWN = -1;
    public static final Integer KEY_SPACE = 32;
    public static final Integer KEY_APOSTROPHE = 39;
    public static final Integer KEY_COMMA = 44;
    public static final Integer KEY_MINUS = 45;
    public static final Integer KEY_PERIOD = 46;
    public static final Integer KEY_SLASH = 47;
    public static final Integer KEY_0 = 48;
    public static final Integer KEY_1 = 49;
    public static final Integer KEY_2 = 50;
    public static final Integer KEY_3 = 51;
    public static final Integer KEY_4 = 52;
    public static final Integer KEY_5 = 53;
    public static final Integer KEY_6 = 54;
    public static final Integer KEY_7 = 55;
    public static final Integer KEY_8 = 56;
    public static final Integer KEY_9 = 57;
    public static final Integer KEY_SEMICOLON = 59;
    public static final Integer KEY_EQUAL = 61;
    public static final Integer KEY_A = 65;
    public static final Integer KEY_B = 66;
    public static final Integer KEY_C = 67;
    public static final Integer KEY_D = 68;
    public static final Integer KEY_E = 69;
    public static final Integer KEY_F = 70;
    public static final Integer KEY_G = 71;
    public static final Integer KEY_H = 72;
    public static final Integer KEY_I = 73;
    public static final Integer KEY_J = 74;
    public static final Integer KEY_K = 75;
    public static final Integer KEY_L = 76;
    public static final Integer KEY_M = 77;
    public static final Integer KEY_N = 78;
    public static final Integer KEY_O = 79;
    public static final Integer KEY_P = 80;
    public static final Integer KEY_Q = 81;
    public static final Integer KEY_R = 82;
    public static final Integer KEY_S = 83;
    public static final Integer KEY_T = 84;
    public static final Integer KEY_U = 85;
    public static final Integer KEY_V = 86;
    public static final Integer KEY_W = 87;
    public static final Integer KEY_X = 88;
    public static final Integer KEY_Y = 89;
    public static final Integer KEY_Z = 90;
    public static final Integer KEY_LEFT_BRACKET = 91;
    public static final Integer KEY_BACKSLASH = 92;
    public static final Integer KEY_RIGHT_BRACKET = 93;
    public static final Integer KEY_GRAVE_ACCENT = 96;
    public static final Integer KEY_WORLD_1 = 161;
    public static final Integer KEY_WORLD_2 = 162;
    public static final Integer KEY_ESCAPE = 256;
    public static final Integer KEY_ENTER = 257;
    public static final Integer KEY_TAB = 258;
    public static final Integer KEY_BACKSPACE = 259;
    public static final Integer KEY_INSERT = 260;
    public static final Integer KEY_DELETE = 261;
    public static final Integer KEY_RIGHT = 262;
    public static final Integer KEY_LEFT = 263;
    public static final Integer KEY_DOWN = 264;
    public static final Integer KEY_UP = 265;
    public static final Integer KEY_PAGE_UP = 266;
    public static final Integer KEY_PAGE_DOWN = 267;
    public static final Integer KEY_HOME = 268;
    public static final Integer KEY_END = 269;
    public static final Integer KEY_CAPS_LOCK = 280;
    public static final Integer KEY_SCROLL_LOCK = 281;
    public static final Integer KEY_NUM_LOCK = 282;
    public static final Integer KEY_PRInteger_SCREEN = 283;
    public static final Integer KEY_PAUSE = 284;
    public static final Integer KEY_F1 = 290;
    public static final Integer KEY_F2 = 291;
    public static final Integer KEY_F3 = 292;
    public static final Integer KEY_F4 = 293;
    public static final Integer KEY_F5 = 294;
    public static final Integer KEY_F6 = 295;
    public static final Integer KEY_F7 = 296;
    public static final Integer KEY_F8 = 297;
    public static final Integer KEY_F9 = 298;
    public static final Integer KEY_F10 = 299;
    public static final Integer KEY_F11 = 300;
    public static final Integer KEY_F12 = 301;
    public static final Integer KEY_F13 = 302;
    public static final Integer KEY_F14 = 303;
    public static final Integer KEY_F15 = 304;
    public static final Integer KEY_F16 = 305;
    public static final Integer KEY_F17 = 306;
    public static final Integer KEY_F18 = 307;
    public static final Integer KEY_F19 = 308;
    public static final Integer KEY_F20 = 309;
    public static final Integer KEY_F21 = 310;
    public static final Integer KEY_F22 = 311;
    public static final Integer KEY_F23 = 312;
    public static final Integer KEY_F24 = 313;
    public static final Integer KEY_F25 = 314;
    public static final Integer KEY_KP_0 = 320;
    public static final Integer KEY_KP_1 = 321;
    public static final Integer KEY_KP_2 = 322;
    public static final Integer KEY_KP_3 = 323;
    public static final Integer KEY_KP_4 = 324;
    public static final Integer KEY_KP_5 = 325;
    public static final Integer KEY_KP_6 = 326;
    public static final Integer KEY_KP_7 = 327;
    public static final Integer KEY_KP_8 = 328;
    public static final Integer KEY_KP_9 = 329;
    public static final Integer KEY_KP_DECIMAL = 330;
    public static final Integer KEY_KP_DIVIDE = 331;
    public static final Integer KEY_KP_MULTIPLY = 332;
    public static final Integer KEY_KP_SUBTRACT = 333;
    public static final Integer KEY_KP_ADD = 334;
    public static final Integer KEY_KP_ENTER = 335;
    public static final Integer KEY_KP_EQUAL = 336;
    public static final Integer KEY_LEFT_SHIFT = 340;
    public static final Integer KEY_LEFT_CONTROL = 341;
    public static final Integer KEY_LEFT_ALT = 342;
    public static final Integer KEY_LEFT_SUPER = 343;
    public static final Integer KEY_RIGHT_SHIFT = 344;
    public static final Integer KEY_RIGHT_CONTROL = 345;
    public static final Integer KEY_RIGHT_ALT = 346;
    public static final Integer KEY_RIGHT_SUPER = 347;
    public static final Integer KEY_MENU = 348;
    public static final Integer KEY_LAST = 348;
    public static final Integer MOD_SHIFT = 1;
    public static final Integer MOD_CONTROL = 2;
    public static final Integer MOD_ALT = 4;
    public static final Integer MOD_SUPER = 8;
    public static final Integer MOUSE_BUTTON_1 = 0;
    public static final Integer MOUSE_BUTTON_2 = 1;
    public static final Integer MOUSE_BUTTON_3 = 2;
    public static final Integer MOUSE_BUTTON_4 = 3;
    public static final Integer MOUSE_BUTTON_5 = 4;
    public static final Integer MOUSE_BUTTON_6 = 5;
    public static final Integer MOUSE_BUTTON_7 = 6;
    public static final Integer MOUSE_BUTTON_8 = 7;
    public static final Integer MOUSE_BUTTON_LAST = 7;
    public static final Integer MOUSE_BUTTON_LEFT = 0;
    public static final Integer MOUSE_BUTTON_RIGHT = 1;
    public static final Integer MOUSE_BUTTON_MIDDLE = 2;
 
    private static Integer KEY_CODES = 512;
    private static Integer MOUSE_CODES = 6;
 
    private static ArrayList<Integer> CURRENT_KEYS = new ArrayList<Integer>();
    private static ArrayList<Integer> DOWN_KEYS = new ArrayList<Integer>();
    private static ArrayList<Integer> UP_KEYS = new ArrayList<Integer>();
 
    private static ArrayList<Integer> CURRENT_MOUSE = new ArrayList<Integer>();
    private static ArrayList<Integer> DOWN_MOUSE = new ArrayList<Integer>();
    private static ArrayList<Integer> UP_MOUSE = new ArrayList<Integer>();
 
    public static void update() {
    	long window = Window.getWindow();
        UP_MOUSE.clear();
        for (int i = 0; i < MOUSE_CODES; i++)
            if (!isMouseButtonHeld(window, i) && CURRENT_MOUSE.contains(i))
                UP_MOUSE.add(i);
 
        DOWN_MOUSE.clear();
 
        for (int i = 0; i < MOUSE_CODES; i++)
            if (isMouseButtonHeld(window, i) && !CURRENT_MOUSE.contains(i))
                DOWN_MOUSE.add(i);
 
        CURRENT_MOUSE.clear();
 
        for (int i = 0; i < MOUSE_CODES; i++)
            if (isMouseButtonHeld(window, i))
                CURRENT_MOUSE.add(i);
 
        UP_KEYS.clear();
        for (int i = 0; i < KEY_CODES; i++)
            if (!isKeyHeld(window, i) && CURRENT_KEYS.contains(i))
                UP_KEYS.add(i);
 
        DOWN_KEYS.clear();
 
        for (int i = 0; i < KEY_CODES; i++)
            if (isKeyHeld(window, i) && !CURRENT_KEYS.contains(i))
                DOWN_KEYS.add(i);
 
        CURRENT_KEYS.clear();
 
        for (int i = 0; i < KEY_CODES; i++)
            if (isKeyHeld(window, i))
                CURRENT_KEYS.add(i);
    }
 
    public static Boolean isKeyHeld(Long window, Integer key) {
        return GLFW.glfwGetKey(window, key) == GLFW.GLFW_TRUE;
    }
 
    public static Boolean isKeyHeld(Integer key) {
        return GLFW.glfwGetKey(Window.getWindow(), key) == GLFW.GLFW_TRUE;
    }
    
    public static Boolean isKeyPressed(Integer key) {
        return DOWN_KEYS.contains(key);
    }
 
    public static Boolean isKeyReleased(Integer key) {
        return UP_KEYS.contains(key);
    }
 
    public static Boolean isMouseButtonHeld(Long window, Integer button) {
        return GLFW.glfwGetMouseButton(window, button) == GLFW.GLFW_TRUE;
    }
    
    public static Boolean isMouseButtonHeld(Integer button) {
        return GLFW.glfwGetMouseButton(Window.getWindow(), button) == GLFW.GLFW_TRUE;
    }
 
    public static Boolean isMouseButtonClicked(Integer button) {
        return DOWN_MOUSE.contains(button);
    }
 
    public static Boolean isMouseButtonUp(Integer button) {
        return UP_MOUSE.contains(button);
    }
 
    public static Vector2f getMousePosition() {
        DoubleBuffer posX = BufferUtils.createDoubleBuffer(1);
        DoubleBuffer posY = BufferUtils.createDoubleBuffer(1);
        GLFW.glfwGetCursorPos(Window.getWindow(), posX, posY);
        return new Vector2f((float)posX.get(0), (float)posY.get(0));
    }
    
    public static void setMousePosition(float f, float g) {
        GLFW.glfwSetCursorPos(Window.getWindow(), f, g);
    }
    
    public static void grabCursor(boolean grab){
    	if(!grab){
    		GLFW.glfwSetInputMode(Window.getWindow(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);
    	} else {
    		GLFW.glfwSetInputMode(Window.getWindow(), GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_NORMAL);
    	}
    }
 
}
