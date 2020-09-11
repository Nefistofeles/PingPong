#version 330

in vec2 out_textureCoords1 ;
in vec2 out_textureCoords2 ;
in float interpolate ;

uniform sampler2D textureSampler ;

out vec4 out_Color ;



void main(void){

	vec4 colorC1 = texture(textureSampler , out_textureCoords1) ;
	vec4 colorC2 = texture(textureSampler , out_textureCoords2) ;

	vec4 colorControl = mix(colorC1, colorC2, interpolate) ;
	if(colorControl.a < 0.1){
		discard ;
	}
	out_Color = colorControl ;
}
