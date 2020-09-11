package gameEntity;

import org.jbox2d.common.Vec2;

import shaderProgram.ShaderProgram;
import utils.Matrix4;

public class LightShader extends ShaderProgram{
	
	private static final String VERTEX_SHADER = "/gameEntity/lightVertexShader.vert" ;
	private static final String FRAGMENT_SHADER = "/gameEntity/lightFragmentShader.frag" ;
	

	private int location_lightColor ;
	private int location_lightPosition ;
	private int location_screenSize ;
	
	public LightShader() {
		super(VERTEX_SHADER, FRAGMENT_SHADER);
	}

	@Override
	protected void bindAttribute() {
		super.bindAttribute(0, "position");
		
	}

	@Override
	protected void getUniformLocation() {
		
		location_lightColor = super.getUniformLocation("lightColor") ;
		location_lightPosition = super.getUniformLocation("lightPosition") ;
		location_screenSize = super.getUniformLocation("screenSize") ;
	}
	public void loadLight(Light light) {
		super.loadVector3f(location_lightColor, light.getColor().r, light.getColor().g,light.getColor().b);
		super.loadVector2f(location_lightPosition, light.getPosition());
	}

	public void loadScreenSize(Vec2 screenSize) {
		super.loadVector2f(location_screenSize, screenSize);
	}

}
