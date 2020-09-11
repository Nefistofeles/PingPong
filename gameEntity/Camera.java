package gameEntity;

import org.jbox2d.common.Vec2;
import org.jbox2d.common.Vec3;
import org.lwjgl.input.Keyboard;

import utils.Maths;
import utils.Matrix4;

public class Camera {

	private Vec3 position ;
	private float pitch, yaw ;
	private Matrix4 viewMatrix ;
	
	public Camera() {
		position = new Vec3(0,0,0) ;
		pitch = 0 ;
		yaw = 0 ;
		viewMatrix = Maths.createViewMatrix(this) ;
	}
	public Matrix4 getViewMatrix() {
		viewMatrix =  Maths.updateViewMatrix(viewMatrix, this) ;
		return viewMatrix ;
	}
	
	public void updateCamera() {
		
	/*	Vector3f targetPosition = entity.getBody().getPosition() ;
		Vector3f v = Vector3f.sub(targetPosition, position, null) ;
		v.normalise() ;
		
		position.x += v.x * velocity.x * DisplayManager.getFrameTime();
		position.y += v.y * velocity.y * DisplayManager.getFrameTime();*/
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			position.x -= 5 ;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.x += 5 ;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.y -= 5 ;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			position.y += 5 ;
		}
	}

	public Vec3 getPosition() {
		return position;
	}
	public void setPosition(Vec2 position) {
		this.position.x = position.x ;
		this.position.y = position.y ;
	}
	public void setPosition(Vec3 position) {
		this.position = position;
	}
	public void setPosition(float x , float y) {
		this.position.x = x ;
		this.position.y = y ;
	}
	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}
	
	
}
