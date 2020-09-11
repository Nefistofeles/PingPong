package entity;

import dataStructure.Color;
import gameEntity.Light;
import shaderProgram.ShaderProgram;
import utils.Matrix4;

public class EntityShader extends ShaderProgram{
	
	private static final String VERTEX_SHADER = "/entity/entityVertexShader.vert" ;
	private static final String FRAGMENT_SHADER = "/entity/entityFragmentShader.frag" ;

	private int location_projectionMatrix ;
	private int location_transformationMatrix ;
	private int location_color ;
	private int location_worldPosition ;

	
	public EntityShader() {
		super(VERTEX_SHADER, FRAGMENT_SHADER);
		
	}

	@Override
	protected void bindAttribute() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
	}

	@Override
	protected void getUniformLocation() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix") ;
		location_projectionMatrix = super.getUniformLocation("projectionMatrix") ;
		location_color = super.getUniformLocation("color") ;
		location_worldPosition = super.getUniformLocation("worldPosition") ;

	}
	
	
	public void loadProjectionMatrix(Matrix4 matrix) {
		super.loadMatrix4(location_projectionMatrix, matrix);
	}
	public void loadTransformationMatrix(Matrix4 matrix) {
		super.loadMatrix4(location_transformationMatrix, matrix);
	}
	public void loadWorldPosition(float pos) {
		super.loadFloat(location_worldPosition, pos);
	}
	public void loadColor(Color color) {
		super.loadVector4f(location_color, color.r, color.g, color.b, color.alpha);
	}

}
