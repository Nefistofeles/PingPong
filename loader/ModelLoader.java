package loader;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL33;

import dataStructure.MeshData;

public class ModelLoader {
	
	private static final int FLOAT_SIZE = 4 ;
	private static final int INTEGER_SIZE = 4 ;
	
	private List<Integer> vaos ;
	private List<Integer> vbos ;
	private List<Integer> fbos ;

	public ModelLoader() {
		vaos = new ArrayList<Integer>();
		vbos = new ArrayList<Integer>();
		fbos = new ArrayList<Integer>();
	}
	public MeshData loadMesh(float[] vertices, float[] textureCoords,int[] indices) {
		int vaoID = createVAO();
		bindIndices(indices);
		int vboID1 = storeDataInFloatVBO(0, 2, vertices);
		int vboID2 = storeDataInFloatVBO(1, 2, textureCoords);
		GL30.glBindVertexArray(0) ;
		int[] vboIDs = new int[2] ;
		vboIDs[0] = vboID1 ;
		vboIDs[1] = vboID2 ;
		return new MeshData(vaoID,vboIDs,indices.length) ;
	}
	public MeshData loadMesh(float[] vertices, float[] textureCoords,float[] normals, int[] indices) {
		int vaoID = createVAO();
		int vboID4 = bindIndices(indices);
		int vboID1 = storeDataInFloatVBO(0, 2, vertices);
		int vboID2 = storeDataInFloatVBO(1, 2, textureCoords);
		int vboID3 = storeDataInFloatVBO(2, 3, normals);
		GL30.glBindVertexArray(0);
		int[] vboIDs = new int[4] ;
		vboIDs[0] = vboID1 ;
		vboIDs[1] = vboID2 ;
		vboIDs[2] = vboID3 ;
		vboIDs[3] = vboID4 ;
		return new MeshData(vaoID,vboIDs,indices.length) ;
	}
	public MeshData loadMesh(float[] vertices) {
		int vaoID = createVAO();
		int vboID1 = storeDataInFloatVBO(0, 2, vertices);
		GL30.glBindVertexArray(0);
		int[] vboIDs = new int[1] ;
		vboIDs[0] = vboID1 ;
		return new MeshData(vaoID,vboIDs,vertices.length / 2) ;
	}
	public MeshData loadMesh(float[] vertices, int[] indices) {
		int vaoID = createVAO();
		int vboID2 = bindIndices(indices);
		int vboID1 = storeDataInFloatVBO(0, 2, vertices);
		GL30.glBindVertexArray(0);
		int[] vboIDs = new int[2] ;
		vboIDs[0] = vboID1 ;
		vboIDs[1] = vboID2 ;
		return new MeshData(vaoID,vboIDs,indices.length) ;
	}
	public MeshData loadMesh(float[] vertices, float[] textureCoords) {
		int vaoID = createVAO();
		int vboID1 = storeDataInFloatVBO(0, 2, vertices);
		int vboID2 = storeDataInFloatVBO(1, 2, textureCoords);
		GL30.glBindVertexArray(0);
		int[] vboIDs = new int[2] ;
		vboIDs[0] = vboID1 ;
		vboIDs[1] = vboID2 ;
		return new MeshData(vaoID,vboIDs, vertices.length/ 2) ;
	}

	private int createVAO() {
		int vao =GL30.glGenVertexArrays() ;
		vaos.add(vao) ;
		GL30.glBindVertexArray(vao);
		return vao ;
	}
	//***********************************
	public int createVAO(int vao) {
		vao =GL30.glGenVertexArrays() ;
		vaos.add(vao) ;
		GL30.glBindVertexArray(vao);
		return vao ;
	}
	
	public int emptyVBO(int floatCount, int drawType) {
		int vbo = GL15.glGenBuffers() ;
		vbos.add(vbo) ;
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, floatCount * Float.BYTES, drawType);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		return vbo ;
	}
	public int addNormalAttribute(int vao, int vbo,float[] data, int index, int size, int length, int offset, int drawType) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length) ;
		buffer.put(data) ;
		buffer.flip() ;
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, drawType);
		GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, length * Float.BYTES, offset* Float.BYTES);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		
		return vbo ;
	}
	public void updateVBO(int vbo, float[] data, FloatBuffer buffer, int drawType) {
		buffer.clear() ;
		buffer.put(data) ;
		buffer.flip() ;
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer.capacity() * Float.BYTES, drawType);
		GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, buffer);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	//************************************
	//instances rendering
	public void addInstancedAttribute(int vao, int vbo, int attribute, int datasize, int datalength, int offset) {
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL30.glBindVertexArray(vao);
		GL20.glVertexAttribPointer(attribute, datasize, GL11.GL_FLOAT, false, datalength * Float.BYTES, offset* Float.BYTES);
		GL33.glVertexAttribDivisor(attribute, 1);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		GL30.glBindVertexArray(0);
	}
	/*public int emptyVBO(int floatCount) {
		int vbo = GL15.glGenBuffers() ;
		vbos.add(vbo) ;
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, floatCount * Float.BYTES, GL15.GL_STREAM_DRAW);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		return vbo ;
	}


	public void updateVBO(int vbo, float[] data, FloatBuffer buffer) {
		buffer.clear() ;
		buffer.put(data) ;
		buffer.flip() ;
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer.capacity() * Float.BYTES, GL15.GL_STREAM_DRAW);
		GL15.glBufferSubData(GL15.GL_ARRAY_BUFFER, 0, buffer);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}*/
	
	//**********************************
	public MeshData createMeshData(float[] vertices, float[] textureCoords) {
		int vao = 0 ;
		int[] vbos = new int[2] ;
		vao = createVAO(vao) ; 
		int vboVertices = emptyVBO(2 * 1, GL15.GL_DYNAMIC_DRAW);
		addNormalAttribute(vao, vboVertices,vertices, 0, 2, 2, 0, GL15.GL_DYNAMIC_DRAW) ;
		int vboTextureCoords = emptyVBO(2 * 1, GL15.GL_DYNAMIC_DRAW);
		addNormalAttribute(vao, vboTextureCoords,textureCoords, 1, 2, 2, 0, GL15.GL_DYNAMIC_DRAW) ;
		vbos[0] = vboVertices ; 
		vbos[1] = vboTextureCoords ; 
		GL30.glBindVertexArray(0);
		return new MeshData(vao,vbos, vertices.length/2) ;
	}

	private int storeDataInFloatVBO(int index, int size, float[] data) {
		int vbo = GL15.glGenBuffers() ;
		vbos.add(vbo) ;
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		FloatBuffer buffer = storeFloatBuffer(data) ;
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, size* FLOAT_SIZE ,0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		return vbo ;
	}
	private void storeDataInIntVBO(int index, int size, int[] data) {
		int vbo = GL15.glGenBuffers() ;
		vbos.add(vbo) ;
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo);
		IntBuffer buffer = storeIntBuffer(data) ;
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(index, size, GL11.GL_INT, false, size * INTEGER_SIZE ,0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}

	private int bindIndices(int[] indices) {
		int vbo = GL15.glGenBuffers() ;
		vbos.add(vbo) ;
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vbo);
		IntBuffer buffer = storeIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		return vbo ;
	}
	private FloatBuffer storeFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length) ;
		buffer.put(data) ;
		buffer.flip();
		return buffer ;
	}
	private IntBuffer storeIntBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length) ;
		buffer.put(data) ;
		buffer.flip();
		return buffer ;
	}
	public void cleanUp() {
		for(int vao : vaos) {
			GL30.glDeleteVertexArrays(vao);
		}
		for(int vbo : vbos) {
			GL15.glDeleteBuffers(vbo);
		}
		for(int fbo : fbos) {
			GL15.glDeleteBuffers(fbo);
		}
	}
}
