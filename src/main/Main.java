package main;

import com.nebulous.nebulousEngine.core.CoreEngine;

public class Main {

	public static void main(String[] args) {
		CoreEngine engine = new CoreEngine(new TestGameV2(), 60.0);
		engine.createWindow("TestGameV2", 640, 480);
		engine.start();
	}

}
