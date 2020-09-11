package postProcessing;

import shaderProgram.ShaderProgram;
import utils.Matrix4;

public class PostProcessingShader extends ShaderProgram{
	
	private static final String VERTEX_SHADER = "/postProcessing/postProcessingVertexShader.vert" ;
	private static final String FRAGMENT_SHADER = "/postProcessing/postProcessingFragmentShader.frag" ;
	
	private int location_projectionMatrix ;
	private int location_transformationMatrix ;
	private int location_keyset ;
	private int location_chaos ;
	private int location_confuse ;
	private int location_shake ;
	private int location_time ;
	

	public PostProcessingShader() {
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
		location_keyset = super.getUniformLocation("keyset") ;
		location_chaos = super.getUniformLocation("chaos") ;
		location_confuse = super.getUniformLocation("confuse") ;
		location_shake = super.getUniformLocation("shake") ;
		location_time = super.getUniformLocation("time") ;
	}
	public void loadProjectionMatrix(Matrix4 matrix) {
		super.loadMatrix4(location_projectionMatrix, matrix);
	}
	public void loadTransformationMatrix(Matrix4 matrix) {
		super.loadMatrix4(location_transformationMatrix, matrix);
	}
	public void loadKernelEffectNumber(int effect) {
		super.loadInt(location_keyset, effect);
	}
	public void loadPostProcessing(boolean chaos, boolean confuse, boolean shake, float time) {
		super.loadBoolean(location_chaos, chaos);
		super.loadBoolean(location_confuse, confuse);
		super.loadBoolean(location_shake, shake);
		super.loadFloat(location_time, time);
	}
}
