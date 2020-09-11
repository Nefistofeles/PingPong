package gameEntity;

import org.jbox2d.common.Vec2;
import org.jbox2d.common.Vec3;

import dataStructure.Color;

public class Light {

	private Vec2 position ;
	private Color color ;
	
	public Light(Vec2 position, Color color) {
		
		this.position = position;
		this.color = color;
	}
	
	public Vec2 getPosition() {
		return position;
	}

	public void setPosition(Vec2 position) {
		this.position = position;
	}
	public void setPosition(float x , float y) {
		this.position.x = x ;
		this.position.y = y ;
	}
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	
	
	
}
