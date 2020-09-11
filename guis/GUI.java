package guis;

import org.jbox2d.common.Vec2;

import dataStructure.Color;
import dataStructure.MeshData;
import dataStructure.Texture;
import utils.Matrix4;

public abstract class GUI {

	protected Vec2 position ;
	protected float rotation ;
	protected Vec2 scale ;
	protected float worldPosition ;
	protected Matrix4 transformationMatrix ;
	protected Texture texture ;
	protected Color color ;
	protected boolean isIntersect ;
	
	public GUI(Texture texture, Color color, Vec2 position, float rotation, Vec2 scale, float worldPosition) {
		
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		transformationMatrix = Matrix4.createTransformationMatrix(position, rotation, scale) ;
		this.texture = texture ;
		this.color = color ;
		isIntersect = false ;
		this.worldPosition = worldPosition ;
		
	}
	public GUI(Texture texture, Vec2 position, float rotation, Vec2 scale, float worldPosition) {
	 
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		transformationMatrix = Matrix4.createTransformationMatrix(position, rotation, scale) ;
		this.texture = texture ;
		color = new Color(1,1,0,1) ;
		isIntersect = false ;
		this.worldPosition = worldPosition ;
	}
	public GUI(Color color , Vec2 position, float rotation, Vec2 scale, float worldPosition) {
		
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		transformationMatrix = Matrix4.createTransformationMatrix(position, rotation, scale) ;
		this.color = color ;
		color = new Color(1,1,0,1) ;
		isIntersect = false ;
		texture = null ;
		this.worldPosition = worldPosition ;
	}
	public Matrix4 getTransformationMatrix() {
		transformationMatrix = Matrix4.updateTransformationMatrix(transformationMatrix,position, rotation, scale) ;
		return transformationMatrix ; 
	}
	public void update() {
		
	}
	public abstract void isIntersect(boolean isIntersect);

	public boolean isIntersect() {
		return isIntersect ;
	}
	public void setPosition(float x, float y) {
		this.position.set(x, y) ;
	}
	public void setScale(float x, float y) {
		this.scale.set(x, y) ;
	}
	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public Vec2 getPosition() {
		return position;
	}

	public Vec2 getScale() {
		return scale;
	}

	public float getWorldPosition() {
		return worldPosition;
	}

	public void setWorldPosition(float worldPosition) {
		this.worldPosition = worldPosition;
	}

	public Texture getTexture() {
		return texture;
	}
	public Color getColor() {
		return color;
	}


}
