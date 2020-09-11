package postProcessing;

import org.jbox2d.common.Vec2;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import dataStructure.MeshData;
import drawer.Draw;
import loader.FBO;
import loader.Loader;
import renderer.EnableOpenGL;
import renderer.Renderer;
import utils.Coordinates;
import utils.DisplayManager;
import utils.Matrix4;

public class PostProcessing {

	private PostProcessingShader shader;
	private final MeshData mesh;
	private Draw draw;
	private Matrix4 transformation;
	private FBO fbo;
	private int effectNumber ;
	private float time ;

	public PostProcessing(Loader loader, Draw draw) {
		this.draw = draw;
		fbo = new FBO(Display.getWidth(), Display.getHeight(), FBO.DEPTH_RENDER_BUFFER);
		shader = new PostProcessingShader();
		mesh = loader.getModelLoader().loadMesh(Coordinates.Vertex, Coordinates.TextureCoords, Coordinates.indices);

		shader.start();
		shader.loadProjectionMatrix(Renderer.projectionMatrix);
		shader.loadPostProcessing(false, false, false, DisplayManager.getFrameTime());
		shader.stop();

		transformation = Matrix4.createTransformationMatrix(new Vec2(0, 0), 0, new Vec2(0, 0));
		
		effectNumber = 0 ;
		time = 0 ;

	}

	public void render(Vec2 position, float rotation, Vec2 scale) {
		transformation = Matrix4.updateTransformationMatrix(transformation, position, rotation, scale);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		shader.start();
		GL30.glBindVertexArray(mesh.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		time += DisplayManager.getFrameTime() ;
		if(time > 1 && Keyboard.isKeyDown(Keyboard.KEY_1)) {
			time = 0 ;
			effectNumber++ ;
		
		}
		if(effectNumber >= 7) effectNumber = 0 ;
		
		shader.loadKernelEffectNumber(effectNumber);
		draw.activateTexture(fbo.getColorTexture());
		shader.loadTransformationMatrix(transformation);
		draw.renderTriangleOptimize(mesh.getVertexCount());

		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
		shader.stop();
		
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}

	

	public void cleanUp() {
		fbo.cleanUp();
	}
	public FBO getFbo() {
		return fbo;
	}
	
}
