package loader;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;

public class FBO {

	public static final int NONE = 0x00;
	public static final int DEPTH_TEXTURE = 0x01;
	public static final int DEPTH_RENDER_BUFFER = 0x02;
	
	private final int width ;
	private final int height ;
	
	private int fbo ;
	private int colorTexture ;
	private int depthTexture ;

//	private int colorBuffer ;
	private int depthBuffer ;

	
	public FBO(int width, int height, int depthbuffertype) {
		
		this.width = width;
		this.height = height;
		createFBO(depthbuffertype);
	}
	
	public void bindFrameBuffer() {
		GL30.glBindFramebuffer(GL30.GL_DRAW_FRAMEBUFFER, fbo);
		GL11.glViewport(0, 0, width, height);
	}
	public void unbindFrameBuffer() {
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
		GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
	}

	public void bindToReadTexture() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL30.glBindFramebuffer(GL30.GL_READ_FRAMEBUFFER, fbo);
		GL11.glReadBuffer(GL30.GL_COLOR_ATTACHMENT0);
	}
	private void createFBO(int type) {
		createFrameBuffer();
		createColorTextureAttachment();
		if (type == DEPTH_RENDER_BUFFER) {
			createDepthBufferAttachment();
		} else if (type == DEPTH_TEXTURE) {
			createDepthTextureAttachment();
		}
		unbindFrameBuffer();
		
	}
	
	private void createFrameBuffer() {
		fbo = GL30.glGenFramebuffers() ;
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, fbo);
		//birden fazla atanabilir.
		GL11.glDrawBuffer(GL30.GL_COLOR_ATTACHMENT0);
		
	}
	private void createColorTextureAttachment() {
		colorTexture = GL11.glGenTextures() ;
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, colorTexture);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (ByteBuffer) null);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
		//mipmap kullanýlmadýðý için 0
		GL30.glFramebufferTexture2D(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, colorTexture, 0);
	
	}
	private void createDepthTextureAttachment() {
		depthTexture = GL11.glGenTextures() ;
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, depthTexture);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL14.GL_DEPTH_COMPONENT24, width, height, 0, GL11.GL_DEPTH_COMPONENT, GL11.GL_FLOAT, (ByteBuffer) null);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
		
		GL30.glFramebufferTexture2D(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT, GL11.GL_TEXTURE_2D, depthTexture, 0);
	
	}
	private void createDepthBufferAttachment() {
		depthBuffer = GL30.glGenRenderbuffers() ;
		GL30.glBindRenderbuffer(GL30.GL_RENDERBUFFER, depthBuffer);
		GL30.glRenderbufferStorage(GL30.GL_RENDERBUFFER, GL14.GL_DEPTH_COMPONENT24, width, height);
		GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT, GL30.GL_RENDERBUFFER	, depthBuffer);
	}

	public void cleanUp() {
		GL30.glDeleteFramebuffers(fbo);
		GL11.glDeleteTextures(colorTexture);
		GL11.glDeleteTextures(depthTexture);
		GL30.glDeleteRenderbuffers(depthBuffer);
	//	GL30.glDeleteRenderbuffers(colorBuffer);
	}

	public int getColorTexture() {
		return colorTexture;
	}

	public int getDepthBuffer() {
		return depthBuffer;
	}
	
	
	
}
