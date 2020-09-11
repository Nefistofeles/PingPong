package entity;

import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;

import dataStructure.Color;
import dataStructure.MeshData;
import dataStructure.Texture;
import main.Creator;
import utils.Matrix4;

public abstract class Entity {

	protected boolean isAlive ;
	protected MeshData mesh ;
	protected Vec2 position ;
	protected float rotation ;
	protected Vec2 scale ;
	protected float worldPosition ;
	protected Texture texture ;
	protected Color color ; 
	protected Matrix4 transformation ;
	protected Body body ;
	protected Shape shape ;
	protected Fixture fixture ;

	public Entity(MeshData mesh, Texture texture, Vec2 position, float rotation, Vec2 scale,float worldPosition) {
		this.worldPosition = worldPosition ;
		this.mesh = mesh ;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.texture = texture;
		color = null ;
		transformation = Matrix4.createTransformationMatrix(position, rotation, scale); 
		isAlive = true ;
	}
	public Entity(MeshData mesh,Color color, Texture texture, Vec2 position, float rotation, Vec2 scale,float worldPosition) {
		this.worldPosition = worldPosition ;
		this.mesh = mesh ;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.texture = texture;
		this.color = color ;
		transformation = Matrix4.createTransformationMatrix(position, rotation, scale); 
		isAlive = true ;
	}
	public abstract void behaviour() ;
	public abstract void animation() ;
	public abstract void dead(Creator creator);
	
	
	public Matrix4 getTransformationMatrix() {
		if(body == null) {
			transformation = Matrix4.updateTransformationMatrix(transformation, position, rotation, scale) ;
		}else {
			transformation = Matrix4.updateTransformationMatrix(transformation, body.getPosition(), body.getAngle(), scale) ;
		}
		return transformation ;
	}

	public void setPhysics(Body body, Shape shape, Fixture fixture) {
		this.body = body ;
		this.shape = shape ;
		this.fixture = fixture ;
	}
	
	public Vec2 getPosition() {
		if(body != null)
			return body.getPosition() ;
		return position;
	}

	public void setPosition(Vec2 position) {
		this.position = position;
	}

	public float getRotation() {
		if(body != null)
			return body.getAngle() ;
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public Vec2 getScale() {
		return scale;
	}

	public void setScale(Vec2 scale) {
		this.scale = scale;
	}

	public MeshData getMesh() {
		return mesh;
	}

	public Texture getTexture() {
		return texture;
	}


	public float getWorldPosition() {
		return worldPosition;
	}

	public void setWorldPosition(float worldPosition) {
		this.worldPosition = worldPosition;
	}

	public Body getBody() {
		return body;
	}

	public Shape getShape() {
		return shape;
	}

	public Fixture getFixture() {
		return fixture;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public Color getColor() {
		return color;
	}
	
	
	
	
	
	
	
}
