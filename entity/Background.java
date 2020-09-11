package entity;

import org.jbox2d.common.Vec2;

import dataStructure.Color;
import dataStructure.MeshData;
import dataStructure.Texture;
import main.Creator;
import utils.Matrix4;

public class Background extends Entity{

	public Background(MeshData mesh,Color color,Texture texture, Vec2 position, float rotation, Vec2 scale,
			float worldPosition) {
		super(mesh,color, texture, position, rotation, scale, worldPosition);
		
	}

	@Override
	public void behaviour() {
		// TODO Auto-generated method stub
		
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
