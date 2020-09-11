package utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.WindowAdapter;

import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {

	
	private static int WIDTH = 800 ;
	private static int HEIGHT = 600 ;
	private static int FPS = 120 ;
	
	private static float delta ;
	private long lastTime ;
	private long now ;
	
	public DisplayManager() {
		delta = 0 ;
		lastTime = 0 ;
		now = 0 ;
		//System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		System.setProperty("org.lwjgl.opengl.Display.enableHighDPI", "true");
	}

	public void createDisplay() {
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize() ;
		WIDTH = (int) d.getWidth() ;
		HEIGHT = (int) d.getHeight() ;
		ContextAttribs attribs = new ContextAttribs(3,3).withForwardCompatible(true).withProfileCore(true) ;
		PixelFormat pixel = new PixelFormat();
		
		pixel.withDepthBits(24) ;
		pixel.withStencilBits(8) ;
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH , HEIGHT));
			Display.create(pixel, attribs);
		//	Display.create(new PixelFormat(0, 16, 1));
			Display.setTitle("Ping Pong");
			Display.setFullscreen(false);
			Display.setVSyncEnabled(true);
			Display.setResizable(false);
			
			
		}catch(Exception e) {
			e.printStackTrace(); 
			System.exit(-1);
		}
		GL11.glViewport(0, 0, WIDTH , HEIGHT);
		lastTime = getTime();
		
	}

	public void updateDisplay() {
		Display.sync(FPS);
		Display.update();
		
		now = getTime();
		delta = (now - lastTime) / 1000f ;
		lastTime = now ;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_N)) {
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE );
		}else if(Keyboard.isKeyDown(Keyboard.KEY_M)) {
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL );
		}else if(Keyboard.isKeyDown(Keyboard.KEY_B)) {
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_POINT );
		}
	
		
		if(Display.wasResized()) {
			GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		}

	}
	
	public static float getFrameTime() {
		return delta ;
	}
	
	public void closeDisplay() {
		Display.destroy();
	}
	
	public long getTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution() ;
	}

}
