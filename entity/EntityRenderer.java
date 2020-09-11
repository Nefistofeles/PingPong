package entity;

import static org.lwjgl.opengl.GL11.GL_ALWAYS;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_EQUAL;
import static org.lwjgl.opengl.GL11.GL_KEEP;
import static org.lwjgl.opengl.GL11.GL_ONE;
import static org.lwjgl.opengl.GL11.GL_REPLACE;
import static org.lwjgl.opengl.GL11.GL_STENCIL_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glColorMask;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glStencilFunc;
import static org.lwjgl.opengl.GL11.glStencilOp;
import static org.lwjgl.opengl.GL20.glUseProgram;

import java.util.List;
import java.util.Map;


import dataStructure.Texture;
import drawer.Draw;
import gameEntity.Light;
import renderer.Renderer;

public class EntityRenderer {

	private EntityShader shader ;
	private Draw draw ;
	public EntityRenderer(Draw draw) {
		this.draw = draw ;
		shader = new EntityShader();
		shader.start();
		shader.loadProjectionMatrix(Renderer.projectionMatrix);
		shader.stop();
	}
	
	public void render(Map<Texture, List<Entity>> entities) {

		shader.start();
		//shader.loadLight(light);
		for(Texture texture : entities.keySet()) {
			List<Entity> entitylist = entities.get(texture) ;
			draw.activateTexture(texture);
			for(Entity e : entitylist) {
				if(e.getColor() != null) {
					shader.loadColor(e.getColor());
				}
				draw.activate(e.getMesh(), 2);
				shader.loadTransformationMatrix(e.getTransformationMatrix());
				shader.loadWorldPosition(e.getWorldPosition());
				draw.renderTriangleOptimize(e.getMesh().getVertexCount());
				draw.destroy(2);
			}
		}

		shader.stop();
	}
	
	public void cleanUp() {
		shader.cleanUp();
	}
}
