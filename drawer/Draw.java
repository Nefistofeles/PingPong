package drawer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL31;

import dataStructure.MeshData;
import dataStructure.Texture;
import utils.Coordinates;

public class Draw {

	
	public void activate(MeshData mesh, int enableVA) {
		GL30.glBindVertexArray(mesh.getVaoID());
		for(int i = 0 ; i < enableVA ; i++) {
			GL20.glEnableVertexAttribArray(i);
		}

		
	}
	public void activate(int vaoID, int enableVA) {
		GL30.glBindVertexArray(vaoID);
		for(int i = 0 ; i < enableVA ; i++) {
			GL20.glEnableVertexAttribArray(i);
		}

		
	}
	public void renderTriangleStripInstance(int vertexCount, int listSize) {
		GL31.glDrawArraysInstanced(GL11.GL_TRIANGLE_STRIP, 0, vertexCount, listSize);
	}
	public void activateTexture(Texture texture) {
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
	}
	public void activateTexture(int texture) {
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
	}
	public void renderTriangleOptimize(int vertexCount) {
		GL11.glDrawElements(GL11.GL_TRIANGLES, vertexCount, GL11.GL_UNSIGNED_INT, 0);
	}
	public void renderTriangleUnoptimize(int vertexCount) {
		GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, vertexCount);
	}

	public void render(int vertexCount) {
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, vertexCount);
	}
	public void renderPoint(int pointSize) {
		GL11.glPointSize(pointSize);
		
		//GL11.glPointSize(pointSize);
		GL11.glDrawArrays(GL11.GL_POINTS, 0, 1);
		
	}
	public void renderLine() {//not support lineWidth
		GL11.glDrawArrays(GL11.GL_LINES, 0, 2);
	}
	public void renderLineStrip(int vertexCount) {
		GL11.glDrawArrays(GL11.GL_LINE_STRIP, 0, vertexCount);
	}
	public void renderLineLoop(int vertexCount) {
		GL11.glDrawArrays(GL11.GL_LINE_LOOP, 0, vertexCount);
		
	}
	public void renderQuad(int vertexCount) {
		GL11.glDrawArrays(GL11.GL_QUADS, 0, vertexCount);
	}
	public void renderTriangleStrip(int vertexCount) {
		GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, vertexCount);
	}
	public void renderTriangleFan(int vertexCount) {
		GL11.glDrawArrays(GL11.GL_TRIANGLE_FAN, 0, vertexCount);
	}
	public void destroy(int disableVA) {
		for(int i = 0 ; i < disableVA ; i++) {
			GL20.glDisableVertexAttribArray(i);
			
		}
		GL30.glBindVertexArray(0);
	}
	
	
}
