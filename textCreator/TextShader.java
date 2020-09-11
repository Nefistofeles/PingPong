package textCreator;

import dataStructure.Color;
import shaderProgram.ShaderProgram;
import utils.Matrix4;

public class TextShader extends ShaderProgram{
	
	private static final String VERTEX_SHADER = "/textCreator/textVertexShader.vert" ;
	private static final String FRAGMENT_SHADER = "/textCreator/textFragmentShader.frag" ;
	
	private int location_projectionMatrix ;
	private int location_transformationMatrix ;
	private int location_color ;


	public TextShader() {
		super(VERTEX_SHADER, FRAGMENT_SHADER);
	}

	@Override
	protected void bindAttribute() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
	}

	@Override
	protected void getUniformLocation() {
		location_projectionMatrix = super.getUniformLocation("projectionMatrix") ; 
		location_transformationMatrix = super.getUniformLocation("transformationMatrix") ; 
		location_color = super.getUniformLocation("color") ;
	}
	
	public void loadProjectionMatrix(Matrix4 matrix) {
		super.loadMatrix4(location_projectionMatrix, matrix);
	}
	public void loadTransformation(Matrix4 matrix) {
		super.loadMatrix4(location_transformationMatrix, matrix);
	}

	public void loadColor(Color color) {
		super.loadVector3f(location_color, color.r, color.g, color.b);

	}
}
