package com.nebulous.nebulousEngine.game;

import com.nebulous.nebulousEngine.core.CoreEngine;

public class Main {

    private static int width = 640;
    private static int height = 480;

    public static void main(String[] args) {
        CoreEngine engine = new CoreEngine(new TestGame(), 60.0);
        engine.createWindow("Nebulous 3D Game Engine V0.9 pre-alpha", width, height);
        engine.start();
    }
}
