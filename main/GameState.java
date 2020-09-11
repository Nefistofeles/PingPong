package main;

import java.util.ArrayList;
import java.util.List;

import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyType;
import org.lwjgl.input.Keyboard;

import dataStructure.Color;
import entity.Entity;
import entity.EntityType;
import renderer.Renderer;
import textCreator.Text;
import utils.Coordinates;
import world.WorldCreator;

public class GameState extends StateManager{

	private Creator creator ;
	private WorldCreator worldCreator ;
	private Renderer renderer ;
	private Entity ball ;
	
	private Vec2[] screenShape = {
			new Vec2(Renderer.orthographics.getX(),Renderer.orthographics.getWidth()),
			new Vec2(Renderer.orthographics.getX(),Renderer.orthographics.getHeight()),
			new Vec2(Renderer.orthographics.getY(),Renderer.orthographics.getHeight()),
			new Vec2(Renderer.orthographics.getY(),Renderer.orthographics.getWidth()),
			new Vec2(Renderer.orthographics.getX(),Renderer.orthographics.getWidth())
		};

	public GameState( Creator creator,Renderer renderer, WorldCreator worldCreator) {
		this.creator = creator ;
		this.worldCreator = worldCreator ;
		this.renderer = renderer ;
	}

	@Override
	public void init() {

		creator.getPhysicsCreator().loadScreenShape(screenShape);
	/*	Entity background = creator.createEntity(EntityType.Background,new Color(1,1,1,1), "background", new Vec2(0,0),
				0, new Vec2(-Renderer.orthographics.getX() , Renderer.orthographics.getHeight()), -4) ; 
		*/
		Entity player = creator.createEntity(EntityType.Player,new Color(1,1,1,1), "player", new Vec2(0,-55),
				0, new Vec2(8 ,3), -2) ; 
		creator.createPhysicsBody(player,BodyType.DYNAMIC, 1, Coordinates.getVertexVector(new Vec2(5,3)), 0, true);
		
		
		Shape shape = creator.getPhysicsCreator().loadShape(new Vec2(6,0), 2.5f) ;
		player.getBody().createFixture(shape, 0.2f);
		Shape shape2 = creator.getPhysicsCreator().loadShape(new Vec2(-6,0), 2.5f) ;
		player.getBody().createFixture(shape2, 0.2f);
		
		ball = creator.createEntity(EntityType.Ball,new Color(1,1,1,1), "awesomeface1", new Vec2(0,0),
				0, new Vec2(3,3), -2) ; 
		creator.createPhysicsBody(ball,BodyType.DYNAMIC, 0, ball.getScale().x, 1, false);
		
		
		worldCreator.loadFile("level1");
		worldCreator.loadEntities();
		
	/*	Text text = creator.createText("TARKAN saritas ?", 6, new Vec2(-50,-20), 0, new Vec2(13,13), false) ;
*/
		Text text = creator.createText("Çýkmak için ESC'ye basýnýz", 8, new Vec2(Renderer.orthographics.getX(),Renderer.orthographics.getWidth() + 8), 0, new Vec2(6,6), false) ;
		System.out.println(text.getPosition());
		System.out.println("GameState init");
	}
	@Override
	public void update() {
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			StateManager.setState(State.MenuState);
		}
	
	}
	@Override
	public void cleanUp() {
		renderer.getEntityRenderer().destroyEntityAll();
		renderer.getGuiRenderer().getGuis().clear();
		renderer.getGuiRenderer().getTexts().clear();
		renderer.getParticleRenderer().clear();
	}

	
	
	
}
