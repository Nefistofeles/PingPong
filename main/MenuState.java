package main;

import org.jbox2d.common.Vec2;
import org.lwjgl.input.Mouse;

import dataStructure.Color;
import renderer.Renderer;
import textCreator.Text;
import textCreator.button.Button;
import utils.DisplayManager;
import utils.Maths;

public class MenuState extends StateManager {
	
	private Creator creator ;
	private Renderer renderer ;
	private Button start ;
	private Button exit ;
	private Text text ;
	private Text name  ;
	private float time ;
	private boolean isCreated ;
	
	public MenuState(Creator creator,Renderer renderer) {
		time = 0 ;
		isCreated = false ;
		this.creator = creator ;
		this.renderer = renderer ;
	}

	@Override
	public void init() {
		start = creator.createButton("START",2,true, "block",new Vec2(Renderer.orthographics.getX(),Renderer.orthographics.getWidth() + 70),
				0, new Vec2(8,8), 2, 1f) ;
		exit = creator.createButton("EXIT",2,true, "block",new Vec2(Renderer.orthographics.getX(),Renderer.orthographics.getWidth() + 50),
				0, new Vec2(8,8), 2, 1f) ;
		text = creator.createText("Tarkan Sarıtaş", 3, new Vec2(Renderer.orthographics.getX(),Renderer.orthographics.getWidth() + 12), 0, new Vec2(8,8), false) ;
		name = creator.createText("BRICKS", 3, new Vec2(Renderer.orthographics.getX()/2, -Renderer.orthographics.getWidth()/15), 0, new Vec2(15,15), true) ;
		
		System.out.println("MenuState init");
		
	}
	
	@Override
	public void update() {
		if(start.isIntersect() && Mouse.isButtonDown(0)) {
			StateManager.setState(State.GameState);
		}
		if(exit.isIntersect() && Mouse.isButtonDown(0)) {
			Main.setClose(true);
		}
		
		time += DisplayManager.getFrameTime() ;
		if(time > 0.5f) {
			time = 0 ;
			name.getColor().setColor((float)Math.random(),(float)Math.random(),(float)Math.random(),1);
			isCreated = false ;
		}
		
	}
	@Override
	public void cleanUp() {
		renderer.getEntityRenderer().getEntities().clear();
		renderer.getGuiRenderer().getGuis().clear();
		renderer.getGuiRenderer().getTexts().clear();
		
		
	}

	

}
