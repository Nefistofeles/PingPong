package textCreator.button;

import java.util.List;

import org.jbox2d.common.Vec2;

import dataStructure.Color;
import dataStructure.MeshData;
import dataStructure.Texture;
import guis.GUI;
import guis.GUIArea;
import loader.Loader;
import textCreator.Character;
import textCreator.Line;
import textCreator.Text;
import utils.Coordinates;
import utils.Maths;

public class Button extends GUI{
	
	private Text text ;
	private GUIArea gui ;
	private float padding ;
	
	public Button(Text text,float padding, Texture texture, Color color, Vec2 position, float rotation, Vec2 scale, float worldPosition ) {
		super(texture, color, position, rotation, scale,worldPosition);
		this.padding = padding ;
		this.text = text ;
		gui = new GUIArea(texture,color, position, rotation, scale,worldPosition) ;
		setTextSize();
	}
	public Button(Text text,float padding, Texture texture, Vec2 position, float rotation, Vec2 scale, float worldPosition) {
		super(texture, position, rotation, scale,worldPosition);
		this.padding = padding ;
		this.text = text ;
		gui = new GUIArea(texture, position, rotation, scale,worldPosition) ;
		setTextSize();
	}
	private void setTextSize() {
		Character c = text.getLines().get(0).getWords().get(0).getCharacters().get(0); 
		float x = (float) (0.5f + (c.getOffsetx())) ;
		float y = (float) (0.5f + (c.getOffsety())) ;
		x -= padding/2 ;
		float width = (float) (text.getScale().x *2 * text.getMaxLineSize());
		float height = (float) (text.getScale().y * Maths.LINE_HEIGHT * text.getLineSize()) ;
		setPosition(text.getPosition().x + x , text.getPosition().y + y);
		setScale(width, height);
	}

	public Text getText() {
		return text;
	}
	@Override
	public void isIntersect(boolean isIntersect) {
		this.isIntersect = isIntersect ;
		if(isIntersect) {
			getColor().setColor(1, 0, 0, 1);
			text.getColor().setColor(0, 0, 1, 1);
		}else {
			getColor().setColor(0, 1, 1, 1);
			text.getColor().setColor(0, 0, 0, 1);
		}
		
	}


	

	
	
	
	
	
	
	
	
	
}
