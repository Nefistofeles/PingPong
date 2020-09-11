package gameEntity;

import static org.lwjgl.opengl.GL11.*;

import java.util.List;

import org.jbox2d.common.Vec2;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import dataStructure.MeshData;
import drawer.Draw;
import loader.Loader;
import renderer.EnableOpenGL;
import renderer.Renderer;
import utils.Coordinates;

public class LightRenderer {

	private LightShader shader ;
	private Draw draw ;
	private MeshData mesh ;
	private Vec2 screenSize ;
	
	public LightRenderer(Draw draw, Loader loader) {
		this.mesh = loader.getModelLoader().loadMesh(Coordinates.Vertex, Coordinates.indices) ;
		this.draw = draw ;
		screenSize = new Vec2(Display.getWidth(), Display.getHeight()) ;
		shader = new LightShader();
		
		shader.start();
		shader.loadScreenSize(screenSize);
		shader.stop();


	}
	public void renderLight(List<Light> lights) {
		EnableOpenGL.disableDepthTestWithMask(true);
		glStencilOp(GL_KEEP, GL_KEEP, GL_KEEP);
		glStencilFunc(GL_EQUAL, 0, 1);
		glColorMask(true, true, true, true);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_ONE, GL_ONE);
		
		shader.start();
		draw.activate(mesh, 1);
		for(int i = 0 ; i< lights.size() ; i++) {
			shader.loadLight(lights.get(i));
			draw.renderTriangleOptimize(mesh.getVertexCount());
			
		}
		draw.destroy(1);
		shader.stop();
		
		glDisable(GL_BLEND);
		EnableOpenGL.disableDepthTestWithMask(false);
	

		
	}
	public void updateScreenSize(Vec2 screenSize) {
		this.screenSize = screenSize ;
		shader.start();
		shader.loadScreenSize(screenSize);
		shader.stop();
	}
	public void cleanUp() {
		shader.cleanUp();
	}
}
