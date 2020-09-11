package world;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;

import dataStructure.Color;
import entity.Entity;
import entity.EntityType;
import main.Creator;
import renderer.Renderer;
import utils.Coordinates;
import utils.Maths;

public class WorldCreator {

	private int[][] tiles ;
	
	private Creator creator ;
	private int width ;
	private int height ;
	
	public WorldCreator(Creator creator) {
		this.creator = creator ;
	}
	
	public void loadEntities() {
		Vec2 scale = new Vec2(-Renderer.orthographics.getX() / width , -Renderer.orthographics.getWidth() / width ) ;
		Vec2 startPos = new Vec2( -Renderer.orthographics.getX() - (width-0.5f)*scale.x*2 ,
				-Renderer.orthographics.getWidth() - (height-0.5f)*scale.y*2) ;
		for(int j = height-1 ; j >= 0 ; j--) {
			for(int i = 0 ; i < tiles[0].length ; i++) {
				if(tiles[j][i] == 1) {
					Entity entity = creator.createEntity(EntityType.Brick, new Color(1.0f, 0.5f, 0.0f,1), "block",
							new Vec2(startPos.x + i * scale.x * 2,startPos.y + j * scale.y*2),
							0, scale, -2) ;
					creator.createPhysicsBody(entity, BodyType.STATIC, 1, Coordinates.getVertexVector(entity.getScale()),
							0.2f, false);
				}
				if(tiles[j][i] == 2) {
					Entity entity = creator.createEntity(EntityType.Brick, new Color(0.2f, 0.6f, 1.0f,1), "block_solid",
							new Vec2(startPos.x + i * scale.x * 2,startPos.y + j * scale.y*2),
							0, scale, -2) ;
					creator.createPhysicsBody(entity, BodyType.STATIC, 1, Coordinates.getVertexVector(entity.getScale()),
							0.2f, false);
				}
			}
		}
		
	}
	public void loadFile(String fileName) {
		BufferedReader reader = null ; 
		String line ;
		try {
			InputStream in = Class.class.getResourceAsStream("/res/" + fileName + ".txt") ;
			reader = new BufferedReader(new InputStreamReader(in)) ;
			line = reader.readLine() ;
			String[] parser = line.split(" ") ;
			width = Integer.parseInt(parser[0]) ;
			height = Integer.parseInt(parser[1]) ;
			tiles = new int[height][width];
			int j = height-1 ;
			while((line = reader.readLine()) != null) {
				parser = line.split(" ") ;
				for(int i = 0 ; i < width ; i++) {
					tiles[j][i] = Integer.parseInt(parser[i]) ;
				}
				j-- ;
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(fileName + " is not read");
			System.exit(-1);
		}
		
		for(int j = 0 ; j < tiles.length ; j++) {
			for(int i = 0 ; i < tiles[0].length ; i++) {
				System.out.print(tiles[j][i]);
			}
			System.out.println();
		}
	}
}
