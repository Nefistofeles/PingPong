#version 400

in vec2 out_textureCoords;

uniform sampler2D textureSampler ;
uniform vec3 color ;

out vec4 out_Color ;

void main(void){
	out_Color = vec4(color,1) ;
}
