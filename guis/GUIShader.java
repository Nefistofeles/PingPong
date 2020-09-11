package guis;

import dataStructure.Color;
import shaderProgram.ShaderProgram;
import utils.Matrix4;

public class GUIShader extends ShaderProgram{
	
	private static final String VERTEX_SHADER = "/guis/guiVertexShader.vert" ;
	private static final String FRAGMENT_SHADER = "/guis/guiFragmentShader.frag" ;
	
	private int location_projectionMatrix ;
	private int location_transformationMatrix ;
	private int location_worldPosition ;
	private int location_color ;

	public GUIShader() {
		super(VERTEX_SHADER, FRAGMENT_SHADER);
	}

	@Override
	protected void bindAttribute() {
		super.bindAttribute(0, "position");
	}

	@Override
	protected void getUniformLocation() {
		location_projectionMatrix = super.getUniformLocation("projectionMatrix") ;
		location_transformationMatrix = super.getUniformLocation("transformationMatrix") ;
		location_worldPosition = super.getUniformLocation("worldPosition") ;
		location_color = super.getUniformLocation("color") ;
	}
	public void loadProjectionMatrix(Matrix4 matrix) {
		super.loadMatrix4(location_projectionMatrix, matrix);
	}
	public void loadTransformationMatrix(Matrix4 matrix) {
		super.loadMatrix4(location_transformationMatrix, matrix);
	}

	public void loadWorldPosition(float worldPosition) {
		super.loadFloat(location_worldPosition, worldPosition);
	}
	public void loadColor(Color color) {
		super.loadVector3f(location_color, color.r, color.g, color.b);
	}
}
