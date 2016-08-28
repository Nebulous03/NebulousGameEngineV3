package com.nebulous.nebulousEngine.game.objects.camera;

import com.nebulous.nebulousEngine.core.components.CameraComponent;
import com.nebulous.nebulousEngine.game.objects.GameObject;

public class Camera extends GameObject{

	public Camera() {
		this.setTag("unnamed_camera");
		this.addComponent(new CameraComponent());
	}

}
