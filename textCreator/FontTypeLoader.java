package textCreator; 
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.opengl.Display;

import utils.Maths;


public class FontTypeLoader {

	private double aspectRatio ;
	private int ownPadding ;
	private Map<Integer, Character> characters ;
	private Map<String, String> parseLine ;
	private int imageScaleWH;
	private int padding ;
	private int lineHeight ;
	private String textureName ;
	private BufferedReader reader ;
	private int charsCount ;
	private double verticalPerPixelSize ; 
	private double horizontalPerPixelSize ; 
	
	public FontTypeLoader(int ownPadding) {
		this.ownPadding = ownPadding ;
		aspectRatio = (double) Display.getWidth() / (double) Display.getHeight() ;
		System.out.println("aspect " + aspectRatio);
		characters = new HashMap<Integer, Character>();
		imageScaleWH = 0 ;
		padding = 0 ;
		lineHeight = 0 ;
		parseLine = new HashMap<String, String>();
		reader = null ;
	}
	
	public void loadFont(String name) {
		
		try {
			InputStream in = Class.class.getResourceAsStream("/res/" + name + ".fnt") ;
			reader = new BufferedReader(new InputStreamReader(in));
			
			loadCharacterData();
			
			reader.close();
		}catch(Exception e) {
			e.printStackTrace(); 
			System.out.println(name + " is not readed");
			System.exit(-1);
		}
		
	}
	private void loadCharacterData() throws Exception {
		parser() ;
		
		String[] paddingData = parseLine.get("padding").split(",") ;
		padding = Integer.parseInt(paddingData[0]) ;
		
		parser() ;
		lineHeight = Integer.parseInt(parseLine.get("lineHeight")) ;
		imageScaleWH = Integer.parseInt(parseLine.get("scaleW")) ;
		
		int lineHeightPixels = lineHeight - 2 * padding;
		verticalPerPixelSize = Maths.LINE_HEIGHT / (double) lineHeightPixels;
		horizontalPerPixelSize = verticalPerPixelSize / aspectRatio;
		
		parser() ;
		textureName = parseLine.get("file") ;
		parser();
		charsCount = Integer.parseInt(parseLine.get("count")) ;
		System.out.println("line height " + lineHeight + " " + imageScaleWH);
		
		while(parser()) {
			Character c = loadCharacter();
			if(c != null) {
				characters.put(c.getId(), c) ;
			}
		}
		
		
	}
	private Character loadCharacter() {
		int id = integerParser(parseLine.get("id") );
		
		int width = integerParser(parseLine.get("width")) - (2*padding - (2 * ownPadding));
		int height = integerParser(parseLine.get("height")) - (2*padding - (2 * ownPadding));
		double quadWidth = width * (double)horizontalPerPixelSize;
		double quadHeight = height * (double)verticalPerPixelSize;
		
		double x = ((double) integerParser(parseLine.get("x")) + (padding - ownPadding)) / imageScaleWH;
		double y = ((double) integerParser(parseLine.get("y")) + (padding - ownPadding)) / imageScaleWH;
		
		double xTexSize = (double) width / imageScaleWH;
		double yTexSize = (double) height / imageScaleWH;

		double xoffset = (integerParser(parseLine.get("xoffset")) + (padding - ownPadding)) * (double)horizontalPerPixelSize;
		double yoffset = (integerParser(parseLine.get("yoffset")) + (padding - ownPadding)) * (double)verticalPerPixelSize;
		double xadvance = (integerParser(parseLine.get("xadvance")) -2*padding) *(double)horizontalPerPixelSize;
		
		return new Character(id,quadWidth,quadHeight,x,y,xTexSize,yTexSize,xoffset,yoffset,xadvance);
	}
	private int integerParser(String s ) {
		return Integer.parseInt(s) ;
	}
	private float floatParser(String s) {
		return Float.parseFloat(s) ;
	}
	private boolean parser() throws Exception{
		parseLine.clear();
		String line = reader.readLine() ;
		if(line == null) {
			return false ;
		}
			
			String[] parser = line.split(" ") ;
			for(int i = 0 ; i < parser.length ; i++) {
				if(parser[i].contains("=")) {
					String[] parser2 = parser[i].split("=") ;
					parseLine.put(parser2[0] , parser2[1]) ;
				}
			}
		return true ;
	}

	public Map<Integer, Character> getCharacters() {
		return characters;
	}
	
}
