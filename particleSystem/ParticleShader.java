package particleSystem;

import org.jbox2d.common.Vec2;

import dataStructure.Color;
import shaderProgram.ShaderProgram;
import utils.Matrix4;

public class ParticleShader extends ShaderProgram{
	
	private static final String VERTEX_SHADER = "/particleSystem/particleVertexShader.vert" ;
	private static final String FRAGMENT_SHADER = "/particleSystem/particleFragmentShader.frag" ;

	private int location_projectionMatrix,
				location_worldPosition,
				location_numberOfRows,
				location_numberOfColumn
		;
	
	public ParticleShader() {
		super(VERTEX_SHADER, FRAGMENT_SHADER);
	}

	@Override
	protected void bindAttribute() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "transformationMatrix");
		super.bindAttribute(5, "offsets");
		super.bindAttribute(6, "blendFactor");
	}

	@Override
	protected void getUniformLocation() {
		location_projectionMatrix = super.getUniformLocation("projectionMatrix") ;
		location_worldPosition = super.getUniformLocation("worldPosition") ;
		location_numberOfRows = super.getUniformLocation("numberOfRows") ;
		location_numberOfColumn = super.getUniformLocation("numberOfColumn") ;
	}

	public void loadProjectionMatrix(Matrix4 matrix) {
		super.loadMatrix4(location_projectionMatrix, matrix);
	}

	public void loadWorldPosition(float worldposition) {
		super.loadFloat(location_worldPosition, worldposition);
	}

	public void loadTextureAtlas(float numberOfRows, float numberOfColumn) {
		super.loadFloat(location_numberOfRows, numberOfRows);
		super.loadFloat(location_numberOfColumn, numberOfColumn);
	}
}
