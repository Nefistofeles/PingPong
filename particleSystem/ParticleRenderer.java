
package particleSystem;

import java.nio.FloatBuffer;
import java.util.List;
import java.util.Map;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL15;

import dataStructure.MeshData;
import dataStructure.Texture;
import drawer.Draw;
import loader.Loader;
import main.Creator;
import renderer.EnableOpenGL;
import renderer.Renderer;
import utils.Coordinates;
import utils.DisplayManager;
import utils.Matrix4;

public class ParticleRenderer {


	private ParticleShader shader;
	private Draw draw;
	private Loader loader;
	
	private final MeshData mesh ; 
	private static final int MAX_INSTANCES = 500;
	private static final int DATA_LENGTH_FLOAT = 21;
	private static final FloatBuffer buffer = BufferUtils.createFloatBuffer(MAX_INSTANCES * DATA_LENGTH_FLOAT);
	private int vbo;
	
	private int pointer;
	
	public ParticleRenderer(Draw draw, Loader loader) {
		this.loader = loader;
		this.draw = draw;
		
		mesh = loader.getModelLoader().loadMesh(Coordinates.ParticleVertex);
		vbo = loader.getModelLoader().emptyVBO(DATA_LENGTH_FLOAT * MAX_INSTANCES, GL15.GL_STREAM_DRAW);
		storeVboData();
		pointer = 0;
	
		
		shader = new ParticleShader();
		shader.start();
		shader.loadProjectionMatrix(Renderer.projectionMatrix);
		shader.stop();
	}

	public void update() {
		
	}

	public void render(Map<Texture, List<Particle>> particles) {
		EnableOpenGL.blendAdditiveFunc(true);
		EnableOpenGL.disableDepthTestWithMask(true);
		
		shader.start();
		for (Texture texture : particles.keySet()) {
			List<Particle> particlelist = particles.get(texture);
			
			draw.activate(mesh.getVaoID(), 6);
			draw.activateTexture(texture);
			shader.loadTextureAtlas(texture.getNumberOfRows(), texture.getNumberOfColumn());
			
			shader.loadWorldPosition(particlelist.get(0).getWorldPosition());

			float[] vboData = new float[particlelist.size() * DATA_LENGTH_FLOAT];
			pointer = 0;
			for (Particle p : particlelist) {
				
				storeMatrix(p.getTransformation(), vboData);
				storeTextureCoords(p, vboData);
				
			}
			loader.getModelLoader().updateVBO(vbo, vboData, buffer, GL15.GL_STREAM_DRAW);
			draw.renderTriangleStripInstance(Creator.getMesh().getVertexCount(), vboData.length);
			
			
			draw.destroy(6);
		}
		shader.stop();
		EnableOpenGL.disableDepthTestWithMask(false);
		EnableOpenGL.blendAdditiveFunc(false);

	}
	private void storeVboData() {
		loader.getModelLoader().addInstancedAttribute(mesh.getVaoID(), vbo, 1, 4, DATA_LENGTH_FLOAT, 0 );
		loader.getModelLoader().addInstancedAttribute(mesh.getVaoID(), vbo, 2, 4, DATA_LENGTH_FLOAT, 4 );
		loader.getModelLoader().addInstancedAttribute(mesh.getVaoID(), vbo, 3, 4, DATA_LENGTH_FLOAT, 8);
		loader.getModelLoader().addInstancedAttribute(mesh.getVaoID(), vbo, 4, 4, DATA_LENGTH_FLOAT, 12);
		loader.getModelLoader().addInstancedAttribute(mesh.getVaoID(), vbo, 5, 4, DATA_LENGTH_FLOAT, 16);
		loader.getModelLoader().addInstancedAttribute(mesh.getVaoID(), vbo, 6, 1, DATA_LENGTH_FLOAT, 20);
	}
	private void storeTextureCoords(Particle particle, float[] data) {
		data[pointer++] = particle.getOffset1().x ;
		data[pointer++] = particle.getOffset1().y ;
		data[pointer++] = particle.getOffset2().x ;
		data[pointer++] = particle.getOffset2().y ;
		data[pointer++] = particle.getInterpolate() ;
	}

	private void storeMatrix(Matrix4 matrix, float[] vboData) {
		vboData[pointer++] = matrix.m00;
		vboData[pointer++] = matrix.m01;
		vboData[pointer++] = matrix.m02;
		vboData[pointer++] = matrix.m03;
		vboData[pointer++] = matrix.m10;
		vboData[pointer++] = matrix.m11;
		vboData[pointer++] = matrix.m12;
		vboData[pointer++] = matrix.m13;
		vboData[pointer++] = matrix.m20;
		vboData[pointer++] = matrix.m21;
		vboData[pointer++] = matrix.m22;
		vboData[pointer++] = matrix.m23;
		vboData[pointer++] = matrix.m30;
		vboData[pointer++] = matrix.m31;
		vboData[pointer++] = matrix.m32;
		vboData[pointer++] = matrix.m33;
	}

	public void cleanUp() {
		buffer.clear(); 
		
		shader.cleanUp();

	}

	public void init() {

	}
}
