package main;

import org.jbox2d.common.Vec2;
import org.jbox2d.common.Vec3;
import org.jbox2d.dynamics.World;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import dataStructure.Color;
import dataStructure.Texture;
import dataStructure.TextureType;
import drawer.Draw;
import entity.EntityBehaviour;
import gameEntity.Camera;
import gameEntity.Light;
import gameEntity.MouseOrtho;
import loader.Loader;
import loader.TextureLoader;
import postProcessing.PostProcessing;
import renderer.Renderer;
import utils.DisplayManager;
import utils.Maths;
import world.WorldCreator;

public class Game implements IRenderer{
	
	private Loader loader ;
	private Renderer renderer ;
	private Creator creator ;
	private Draw draw ;
	private WorldCreator worldCreator ;
	private World world ;
	private PostProcessing post ;
	private Light light ;
	private MouseOrtho mouse ;
	private Camera camera ;
	private StateManager gameState ;
	private StateManager menuState ;
	
	private float time ;
	private boolean isChanged ;
	
	public Game() {
		time = 0 ;
		isChanged = false ;
	}
	@Override
	public void init() {
		camera = new Camera();
		draw = new Draw();
		world = new World(Maths.GRAVITY) ;
		loader = new Loader();
		
		mouse = new MouseOrtho(camera);
		renderer = new Renderer(draw, loader,mouse) ;
		renderer.init();
		creator = new Creator(loader, renderer,world) ;
		worldCreator = new WorldCreator(creator);
		renderer.getEntityRenderer().set(creator, world, new EntityBehaviour());
		
		light = new Light(new Vec2(1366,768), new Color(1,1,1,1)) ;
		renderer.getLightRenderer().addLight(light);
		
		creator.addTexture("awesomeface1", TextureType.entity, GL11.GL_LINEAR_MIPMAP_LINEAR,TextureLoader.DEFAULT_BIAS);
		creator.addTexture("block_solid",TextureType.entity,GL11.GL_LINEAR_MIPMAP_LINEAR,TextureLoader.DEFAULT_BIAS);
		creator.addTexture("block",TextureType.entity,GL11.GL_LINEAR_MIPMAP_LINEAR,TextureLoader.DEFAULT_BIAS);
		creator.addTexture("player",TextureType.entity,GL11.GL_LINEAR_MIPMAP_LINEAR,TextureLoader.DEFAULT_BIAS);
		creator.addTexture("background",TextureType.entity,GL11.GL_LINEAR_MIPMAP_LINEAR,TextureLoader.DEFAULT_BIAS);
		Texture texture = creator.addTexture("particleAtlas", TextureType.particle,GL11.GL_LINEAR_MIPMAP_LINEAR,TextureLoader.DEFAULT_BIAS);
		texture.setNumberOfRows(4);
		texture.setNumberOfColumn(4);
		Texture texture2 = creator.addTexture("cosmic", TextureType.particle, GL11.GL_LINEAR_MIPMAP_LINEAR,TextureLoader.DEFAULT_BIAS);
		texture2.setNumberOfRows(4);
		texture2.setNumberOfColumn(4);
		Texture texture3 = creator.addTexture("particleStar", TextureType.particle, GL11.GL_LINEAR_MIPMAP_LINEAR,TextureLoader.DEFAULT_BIAS);
		texture3.setNumberOfRows(1);
		texture3.setNumberOfColumn(1);
		Texture texture5 = creator.addTexture("particledeneme", TextureType.particle, GL11.GL_LINEAR_MIPMAP_LINEAR,-2.4f);
		texture5.setNumberOfRows(8);
		texture5.setNumberOfColumn(8);
		
		post = new PostProcessing(loader, draw);
		
		gameState = new GameState(creator, renderer, worldCreator) ;
		menuState = new MenuState(creator, renderer) ;
		StateManager.setCurrentState(menuState);
	}
	
	@Override
	public void update() {
		light.setPosition(Mouse.getX() , Mouse.getY());
		
		if(!isChanged) {
			light.getColor().setColor((float)Math.random(), (float)Math.random(), (float)Math.random(), 1);
			isChanged = true ;
		}
		
		if(time > 1 && isChanged) {
			time = 0 ;
			isChanged = false ;
		}else if(isChanged){
			time+= DisplayManager.getFrameTime() ; 
		}
		
		world.step(1/20f, 6, 3);
		
		//TODO : 
		if(StateManager.getState() == State.MenuState && StateManager.getCurrentState() != menuState) {
			StateManager.setCurrentState(menuState);
		}else if(StateManager.getState() == State.GameState && StateManager.getCurrentState() != gameState) {
			StateManager.setCurrentState(gameState);
		}
		StateManager.getCurrentState().update();
		
		renderer.update();
	}
	@Override
	public void render() {
		//TODO : 
		renderer.render();

		post.getFbo().bindFrameBuffer();
		renderer.render();
		post.getFbo().unbindFrameBuffer();
		
		post.render(new Vec2(0,0), 0, new Vec2(-Renderer.orthographics.getX(),-Renderer.orthographics.getWidth()));
	
	}
	
	
	@Override
	public void cleanUp() {
		//TODO : 
		menuState.cleanUp();
		gameState.cleanUp();
		post.cleanUp();
		loader.cleanUp();
		renderer.cleanUp();
		System.out.println("all is clear");
	}
}
