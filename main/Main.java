package main;

import org.lwjgl.opengl.Display;

import utils.DisplayManager;

public class Main {

	private static boolean isClose = false ;
	
	public static void main(String[] args) {
		DisplayManager display = new DisplayManager();
		display.createDisplay(); 
		Game game = new Game();
		game.init();
		
		while(!Display.isCloseRequested() && !isClose) {
			game.update();
			game.render();
			
			display.updateDisplay();
		}
		game.cleanUp();
		display.closeDisplay();
	}

	public static boolean isClose() {
		return isClose;
	}

	public static void setClose(boolean isClose) {
		Main.isClose = isClose;
	}
	
	
}
