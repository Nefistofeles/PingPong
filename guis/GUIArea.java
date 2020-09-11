package guis;

import org.jbox2d.common.Vec2;

import dataStructure.Color;
import dataStructure.Texture;

public class GUIArea extends GUI {

	public GUIArea(Texture texture, Color color, Vec2 position, float rotation, Vec2 scale, float worldPosition) {
		super(texture, color, position, rotation, scale,worldPosition);
		// TODO Auto-generated constructor stub
	}
	public GUIArea(Texture texture, Vec2 position, float rotation, Vec2 scale,float worldPosition) {
		super(texture, position, rotation, scale,worldPosition);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void isIntersect(boolean isIntersect) {
		// TODO Auto-generated method stub
		
	}



}
