#version 400

in vec2 position ;
uniform vec2 lightPosition ;

uniform vec2 screenSize ;

out vec2 out_lightPosition ;


void main() {

	gl_Position =  vec4(position * screenSize + lightPosition,0, 1.0);
	out_lightPosition = lightPosition ;

}
