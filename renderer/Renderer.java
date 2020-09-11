
package renderer;

import org.lwjgl.opengl.GL11;

import drawer.Draw;
import entity.MasterEntityRenderer;
import gameEntity.MasterLightRenderer;
import gameEntity.MouseOrtho;
import guis.MasterGUIRenderer;
import loader.Loader;
import main.IRenderer;
import particleSystem.MasterParticleRenderer;
import utils.Matrix4;
import utils.Orthographics;

public class Renderer implements IRenderer{

	public static final Orthographics orthographics = new Orthographics(80,60);
	public static final Matrix4 projectionMatrix = orthographics.getProjectionMatrix();
	
	private MasterEntityRenderer entityRenderer ;
	private MasterParticleRenderer particleRenderer ;
	private MasterGUIRenderer guiRenderer ;
	private MasterLightRenderer lightRenderer ;
	
	public Renderer(Draw draw, Loader loader,MouseOrtho mouse) {
		entityRenderer = new MasterEntityRenderer(draw) ;
		particleRenderer = new MasterParticleRenderer(draw, loader);
		guiRenderer = new MasterGUIRenderer(loader, draw, mouse);
		lightRenderer = new MasterLightRenderer(draw, loader) ;
	}
	
	@Override
	public void init() {
		EnableOpenGL.culling(true);
		EnableOpenGL.blendFunc(false);
		EnableOpenGL.enableDepthTest(true);
		EnableOpenGL.enableStencilTest(true);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 1);
		entityRenderer.init();	
		particleRenderer.init();
		guiRenderer.init();
		lightRenderer.init();
	}

	@Override
	public void update() {
		
		entityRenderer.update();
		particleRenderer.update();
		guiRenderer.update();
		lightRenderer.update();
		
	}
	@Override
	public void render() {
		prepare();
		
		entityRenderer.render();
		particleRenderer.render();
		guiRenderer.render();
		lightRenderer.render();
		
	}
	@Override
	public void cleanUp() {
		entityRenderer.cleanUp();
		particleRenderer.cleanUp();
		guiRenderer.cleanUp();
		lightRenderer.cleanUp();
	}
	private void prepare() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT );
		//GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT );
		

	}
	public MasterEntityRenderer getEntityRenderer() {
		return entityRenderer;
	}

	public MasterParticleRenderer getParticleRenderer() {
		return particleRenderer;
	}


	public MasterGUIRenderer getGuiRenderer() {
		return guiRenderer;
	}

	public MasterLightRenderer getLightRenderer() {
		return lightRenderer;
	}
	
	
	
}
