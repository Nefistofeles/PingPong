package entity;

import org.jbox2d.common.Vec2;
import org.lwjgl.input.Keyboard;

import dataStructure.Color;
import dataStructure.MeshData;
import dataStructure.Texture;
import main.Creator;
import utils.DisplayManager;
import utils.Matrix4;

public class Player extends Entity{
	
	private Vec2 direction ;
	private Vec2 velocity ;

	public Player(MeshData mesh,Color color, Texture texture, Vec2 position, float rotation, Vec2 scale, float worldPosition) {
		super(mesh,color, texture, position, rotation, scale, worldPosition);
		direction = new Vec2(0,0) ;
		velocity = new Vec2(50,50) ;
	}

	public Player(MeshData mesh, Texture texture, Vec2 position, float rotation, Vec2 scale, float worldPosition) {
		super(mesh, texture, position, rotation, scale, worldPosition);
		direction = new Vec2(0,0) ;
	}

	@Override
	public void behaviour() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			direction.y = 1 ;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			direction.y = -1 ;
		}else {
			direction.y = 0 ;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			direction.x = -1 ;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			direction.x = 1 ;
		}else {
			direction.x = 0 ;
		}
		
		body.applyLinearImpulse(new Vec2(velocity.x * direction.x , velocity.y * direction.y), getPosition(), true);
		
	}


	@Override
	public void animation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dead(Creator creator) {
		// TODO Auto-generated method stub
		
	}

}
