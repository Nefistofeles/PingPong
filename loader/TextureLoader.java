package loader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/*import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL30;*/
import org.lwjgl.opengl.* ;

import dataStructure.TextureData;
import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

public class TextureLoader {

	private List<Integer> textures ;
	public static final int DEFAULT_BIAS = 0x01; 
	
	public TextureLoader() {
		textures = new ArrayList<Integer>();
	}
	public int loadTexture(String name,int wrapping, int nearest, float bias ) {
		TextureData data = textureDecomposition(name) ;
		int textureID = GL11.glGenTextures() ;
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, data.getWidth(), data.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, data.getBuffer());

		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, wrapping);
	    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, wrapping);
	    
	    if(nearest != 0) {
	        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, nearest);
	        GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, nearest);
	        
	    }
	    if(bias == DEFAULT_BIAS) {
	    	 GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, -1.4f);
	    	 
	    }else {
	    	GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, bias);
	    }
	   
        GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        
        textures.add(textureID);
        return textureID ;
		
	}
	public int loadTextureForFont(String name) {
		TextureData data = textureDecomposition(name) ;
		int textureID = GL11.glGenTextures() ;
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, data.getWidth(), data.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, data.getBuffer());

		GL11.glTexParameteri(GL11.GL_TEXTURE_2D	, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D	, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, -0);
		
		GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        
        textures.add(textureID);
        return textureID ;
	}

	private TextureData textureDecomposition(String name) {
		TextureData data = null ;
		try {
			InputStream in = Class.class.getResourceAsStream("/res/" + name + ".png") ;
			PNGDecoder decoder = new PNGDecoder(in);
			int width = decoder.getWidth() ;
			int height = decoder.getHeight() ;
			ByteBuffer buffer = ByteBuffer.allocateDirect(4 * width * height);
			decoder.decode(buffer, width * 4, Format.RGBA);
			buffer.flip();
			data = new TextureData(width, height, buffer) ;
			in.close();
			
		}catch(Exception e) {
			System.out.println(name + " texture is not founded");
			System.exit(-1);
		}
		return data ;
	}
	
	public void cleanUp() {
		for(Integer t : textures) {
			GL11.glDeleteTextures(t);
		}
	}
}
