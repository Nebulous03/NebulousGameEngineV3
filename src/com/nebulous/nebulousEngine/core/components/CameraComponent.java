package com.nebulous.nebulousEngine.core.components;

import com.nebulous.nebulousEngine.core.RenderEngine;
import com.nebulous.nebulousEngine.core.Window;
import com.nebulous.nebulousEngine.core.logic.Input;
import com.nebulous.nebulousEngine.core.logic.math.Matrix4f;
import com.nebulous.nebulousEngine.core.logic.math.Vector2f;
import com.nebulous.nebulousEngine.core.logic.math.Vector3f;
import com.nebulous.nebulousEngine.graphics.shaders.Shader;

public class CameraComponent extends GameComponent{

    private static float zNear = 0.1f;
    private static float zFar = 1000f;
    private static float aspect = 0;
    private static float fov = 90f;
	
	private Matrix4f projection;

	public CameraComponent() {
		this((float)Math.toRadians(80.0f), (float)Window.getWidth()/(float)Window.getHeight(), 0.01f, 1000);
	}

	public CameraComponent(float fov, float aspect, float zNear, float zFar){
        this.projection = new Matrix4f().initPerspective(fov, aspect, zNear, zFar);
	}

    public Matrix4f getProjection(){
        Matrix4f cameraRotationMatrix = getTransform().getTransformedRot().conjugate().toRotationMatrix();
        Vector3f cameraPos = getTransform().getTransformedPos().mul(-1);
        Matrix4f cameraTranslationMatrix = new Matrix4f().initTranslation(cameraPos.getX(),cameraPos.getY(), cameraPos.getZ());
        return projection.mul(cameraRotationMatrix.mul(cameraTranslationMatrix));
    }
    
    @Override
    public void addToRenderEngine(RenderEngine engine){
        engine.addCamera(this);
    }

	boolean mouseLocked = false;
	Vector2f centerPosition = new Vector2f(Window.getWidth()/2, Window.getHeight()/2);
	float sensitivity = 0.5f;
	

	@Override
	public void init() {}
	
	@Override
	public void update(double delta) {}

	@Override
	public void render(Shader shader, RenderEngine engine) {}
	
	@Override
	public void input(double delta){				//TODO: Change dis plz.
		float moveAmmount = (float)(10 * delta);
		
		if(Input.isKeyHeld(Input.KEY_W))
			move(getTransform().getRot().getForward(), moveAmmount);
		if(Input.isKeyHeld(Input.KEY_A))
			move(getTransform().getRot().getLeft(), moveAmmount);
		if(Input.isKeyHeld(Input.KEY_S))
			move(getTransform().getRot().getForward(), -moveAmmount);
		if(Input.isKeyHeld(Input.KEY_D))
			move(getTransform().getRot().getRight(), moveAmmount);
		
		if(Input.isKeyPressed(Input.KEY_ESCAPE))
		{
			Input.grabCursor(true);
			mouseLocked = false;
		}
		if(Input.isMouseButtonClicked(0))
		{
			Input.setMousePosition(centerPosition.getX(), centerPosition.getY());
			Input.grabCursor(false);
			mouseLocked = true;
		}
		
		if(mouseLocked)
		{
			Vector2f deltaPos = Input.getMousePosition().sub(centerPosition);
			
			boolean rotY = deltaPos.getX() != 0;
			boolean rotX = deltaPos.getY() != 0;
			
			if(rotY)
				getTransform().rotate(new Vector3f(0, 1, 0), deltaPos.getX() * sensitivity);
			if(rotX)
				getTransform().rotate(getTransform().getRot().getRight(), deltaPos.getY() * sensitivity);
			if(rotY || rotX)
				Input.setMousePosition(Window.getWidth()/2, Window.getHeight()/2);
		}
	}
	
	public void move(Vector3f dir, float amount){
		getTransform().setPos(getTransform().getPos().add(dir.mul(amount)));
	}

	public static Vector3f getXaxis() {
		return new Vector3f(1, 0, 0);
	}
	
	public static Vector3f getYaxis() {
		return new Vector3f(0, 1, 0);
	}
	
	public static Vector3f getZaxis() {
		return new Vector3f(0, 0, 1);
	}

    public static float getzNear() {
        return zNear;
    }

    public static void setzNear(float zNear) {
        CameraComponent.zNear = zNear;
    }

    public static float getzFar() {
        return zFar;
    }

    public static void setzFar(float zFar) {
        CameraComponent.zFar = zFar;
    }

    public static float getAspect() {
        return aspect;
    }

    public static void setAspect(float aspect) {
        CameraComponent.aspect = aspect;
    }

    public static float getFov() {
        return fov;
    }

    public static void setFov(float fov) {
        CameraComponent.fov = fov;
    }

}
