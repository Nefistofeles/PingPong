package entity;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;

import dataStructure.Color;
import dataStructure.MeshData;
import dataStructure.Texture;
import main.Creator;
import utils.Matrix4;

public class Ball extends Entity {

	public Ball(MeshData mesh,Color color,Texture texture, Vec2 position, float rotation, Vec2 scale,
			float worldPosition) {
		super(mesh,color, texture, position, rotation, scale, worldPosition);
		

		
	}
	
	
	@Override
	public void behaviour() {
			
		
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
