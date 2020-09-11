package textCreator;

import java.util.ArrayList;
import java.util.List;


public class Word {

	private List<Character> characters ;
	private double width ;

	public Word() {
		characters = new ArrayList<Character>();
		width = 0;
	}
	public void addCharacter(Character character){
		characters.add(character);
		width += character.getXadvance()  ;
	}
	public List<Character> getCharacters() {
		return characters;
	}
	public double getWidth() {
		return width;
	}
	
	
}
