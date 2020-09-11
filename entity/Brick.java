package entity;

import org.jbox2d.common.Vec2;

import dataStructure.Color;
import dataStructure.MeshData;
import dataStructure.Texture;
import main.Creator;
import particleSystem.Particle;
import utils.Maths;

public class Brick extends Entity {

	public Brick(MeshData mesh,Color color, Texture texture, Vec2 position, float rotation, Vec2 scale, float worldPosition) {
		super(mesh,color,texture, position, rotation, scale, worldPosition);
		// TODO Auto-generated constructor stub
	}

	public Brick(MeshData mesh,Texture texture, Vec2 position, float rotation, Vec2 scale, float worldPosition) {
		super(mesh, texture, position, rotation, scale, worldPosition);
		
	}

	@Override
	public void behaviour() {
		
		
	}

	@Override
	public void animation() {
		
	}


	@Override
	public void dead(Creator creator) {
		for(int i = 0 ; i <15 ; i++) {
			Particle p = creator.createParticle("particledeneme", position.clone() ,
					0, new Vec2(10,10), worldPosition ,1/30f, 1) ;
			//p.setVelocity(new Vec2(0, -Maths.random(0, 0.80f)));
			p.setVelocity(new Vec2(0, -0.20f * i));
			System.out.println(p.getVelocity());
		}

		
		
	}

}
