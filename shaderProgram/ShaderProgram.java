package shaderProgram;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.FloatBuffer;

import org.jbox2d.common.Vec2;
import org.jbox2d.common.Vec3;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import utils.Matrix4;

public abstract class ShaderProgram {
	
	private int vertexShaderID ;
	private int fragmentShaderID ;
	private int programID ;
	
	private FloatBuffer matrixBuffer ;

	public ShaderProgram(String vertexFile, String fragmentFile) {
		matrixBuffer = BufferUtils.createFloatBuffer(16) ;
		vertexShaderID = loadShader(vertexFile, GL20.GL_VERTEX_SHADER) ;
		fragmentShaderID = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER) ;
		programID = GL20.glCreateProgram() ;
		GL20.glAttachShader(programID, vertexShaderID);
		GL20.glAttachShader(programID, fragmentShaderID);
		bindAttribute();
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
		getUniformLocation();
		
	}
	
	
	protected void loadVector2f(int location, Vec2 data) {
		GL20.glUniform2f(location, data.x, data.y);
		
	}
	protected void loadBoolean(int location, boolean value) {
	
		int x = 0 ;
		if(value) {
			x = 1 ;
		}
		GL20.glUniform1i(location, x);
	}
	protected void loadVector2f(int location, float x, float y) {
		GL20.glUniform2f(location, x,y);
	}
	protected void loadVector3f(int location, Vec3 data) {
		GL20.glUniform3f(location, data.x, data.y, data.z);
	}
	
	protected void loadVector4f(int location, float x , float y ,float z ,float w) {
		GL20.glUniform4f(location, x,y,z,w);
	}
	protected void loadInt(int location, int data) {
		GL20.glUniform1i(location, data);
	}
	protected void loadFloat(int location, float data) {
		GL20.glUniform1f(location, data);
	}
	protected void loadVector3f(int location, float x ,float y ,float z) {
		GL20.glUniform3f(location, x, y, z);
	}
	
	protected void loadMatrix4(int location , Matrix4 matrix) {
		matrix.store(matrixBuffer) ;
		matrixBuffer.flip() ;
		GL20.glUniformMatrix4(location, false, matrixBuffer);
	}
	protected void bindAttribute(int index, String name) {
		GL20.glBindAttribLocation(programID, index, name);
	}
	protected int getUniformLocation(String name) {
		return GL20.glGetUniformLocation(programID, name);
	}
	
	public void start() {
		GL20.glUseProgram(programID);
	}
	public void stop() {
		GL20.glUseProgram(0);
	}
	public void cleanUp() {
		GL20.glDetachShader(programID, vertexShaderID);
		GL20.glDetachShader(programID, fragmentShaderID);
		GL20.glDeleteShader(vertexShaderID);
		GL20.glDeleteShader(fragmentShaderID);
		GL20.glDeleteProgram(programID);
	}
	
	protected abstract void bindAttribute();
	protected abstract void getUniformLocation();
	
	@SuppressWarnings("deprecation")
	private int loadShader(String name, int type) {
		BufferedReader reader = null ;
		try {
			InputStream in = Class.class.getResourceAsStream(name) ;
			reader = new BufferedReader(new InputStreamReader(in)) ;
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println(name + " is not loaded");
			System.exit(-1);
		}
		StringBuilder builder = new StringBuilder();
		try {
			String line ;
			while((line = reader.readLine()) != null) {
				builder.append(line).append("\n");
				
			}
			reader.close();
		}catch(Exception e) {
			e.printStackTrace(); 
		}
		
		int shaderID = GL20.glCreateShader(type); 
		GL20.glShaderSource(shaderID, builder);
		GL20.glCompileShader(shaderID);
		if(GL20.glGetShader(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println(GL20.glGetShaderInfoLog(shaderID, 500));
			System.out.println(name + " is not compiled");
			System.exit(-1);
		}
		return shaderID ;
	}

}
