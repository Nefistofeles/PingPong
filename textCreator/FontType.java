package textCreator;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.jbox2d.common.Vec2;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

import dataStructure.MeshData;

import loader.Loader;
import utils.Coordinates;
import utils.Maths;

public class FontType {
	
	private FontTypeLoader fontLoader ;
	private Loader loader ;

	public FontType(String fileName, Loader loader, int padding) {
		this.loader = loader ;
		fontLoader = new FontTypeLoader(padding);
		fontLoader.loadFont(fileName);
		
	}
	
	private List<Line> createSkeletonWord(Text text) {

		char[] chars = text.getTextString().toCharArray();
		List<Line> lines = new ArrayList<Line>();
		Line currentLine = new Line(text.getMaxLineSize());
		Word currentWord = new Word();
		for(char c : chars) {
			
			Character character = fontLoader.getCharacters().get((int) c ) ;
			currentWord.addCharacter(character);
			
			if((int) c == 32) {

				boolean isadded = currentLine.tryAddToWord(currentWord ) ;
				if(!isadded) {
					lines.add(currentLine) ;
					currentLine = new Line(text.getMaxLineSize()) ;
					currentLine.tryAddToWord(currentWord ) ;
				}
				currentWord = new Word();
			}
		}
		boolean isadded = currentLine.tryAddToWord(currentWord ) ;
		if(!isadded) {
			lines.add(currentLine) ;
			currentLine = new Line(text.getMaxLineSize()) ;
			currentLine.tryAddToWord(currentWord) ;
		}
		lines.add(currentLine) ;
		text.setLines(lines);
		return lines;

	}
	//correction texture to normal opengl coordinates system
	public MeshData createText(Text text) {
		List<Float> vertices = new ArrayList<Float>();
		List<Float> textureCoords = new ArrayList<Float>();

		List<Line> lines = createSkeletonWord(text) ;
		
		Character correction = lines.get(0).getWords().get(0).getCharacters().get(0) ;
		
		double curserX = 0.5f - correction.getOffsetx() ;	
		double curserY = 0.5f - correction.getOffsety() ;
		
		for(Line line : lines) {
			if (text.isCentered()) {
				curserX += (line.getMaxLength() - line.getCurrentLineLength()) / 2;
			}
			for(Word word : line.getWords()) {
				for(Character c : word.getCharacters()) {
					meshAndTextureLoad(curserX,curserY,c, vertices, textureCoords);
					curserX += c.getXadvance() ;
				}
			}
			curserX = 0.5f - correction.getOffsetx() ;
			curserY += Maths.LINE_HEIGHT ;
		}
		float[] verticesArray = Maths.listToArray(vertices) ;
		float[] textureCoordsArray = Maths.listToArray(textureCoords) ;
		
	//	MeshData mesh = loader.getModelLoader().loadMesh(verticesArray, textureCoordsArray); 
		text.setLineSize(lines.size());
		return loader.getModelLoader().createMeshData(verticesArray, textureCoordsArray) ;
		
		
	//	return mesh ;
	}

	public void updateMesh(MeshData mesh, float[] vertices, float[] textureCoords) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(vertices.length) ;
		loader.getModelLoader().updateVBO(mesh.getVboIDs()[0], vertices, buffer, GL15.GL_DYNAMIC_DRAW);
		loader.getModelLoader().updateVBO(mesh.getVboIDs()[1], textureCoords, buffer, GL15.GL_DYNAMIC_DRAW);
		buffer.clear();
		
	}

	private void meshAndTextureLoad(double curserX , double curserY, Character character, List<Float> vertices, List<Float> textureCoords) {
		
		addVerticesCharacterConvert(curserX, curserY, character, vertices);
		Maths.addMeshAttachment(textureCoords, character.getTextureX(),character.getTextureY(),
				character.getTextureWidth() + character.getTextureX(), character.getTextureHeight()+character.getTextureY());
	
	}

	private void addVerticesCharacterConvert(double curserX, double curserY, Character character, List<Float> vertices) {
		
		double x = curserX + (character.getOffsetx());
		double y = curserY + (character.getOffsety());
		double width = x + (character.getWidth());
		double height = y + (character.getHeight());

		double xCorrection = (2 * x) - 1;
		double yCorrection = (-2 * y) + 1;
		double widthCorrection = (2 * width) - 1;
		double heightCorrection = (-2 * height) + 1;

		Maths.addMeshAttachment(vertices, xCorrection , yCorrection , widthCorrection , heightCorrection );
		
	}

}
