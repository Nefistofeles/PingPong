#version 400

in vec2 out_textureCoords ;

uniform sampler2D textureSampler ;
uniform vec3 color ;

out vec4 out_Color ;



void main(void){

	out_Color = vec4(color , texture(textureSampler, out_textureCoords).a) ;
}

/*const float width = 0.40 ;	//0.5	//0.51
const float edge = 0.15 ;	//0.1	//0.2

const float boderWidth = 0.0 ;
const float borderEdge = 0.9 ;

const vec2 offset = vec2(0.000,0.000) ;

const vec3 outlineColor = vec3(0,0,0) ;*/

/*float distance = 1 - texture(textureSampler, out_textureCoords).a ;
float alpha = 1 - smoothstep(width, width+edge,distance) ;

float distance2 = 1 - texture(textureSampler, out_textureCoords+offset).a ;
float alpha2 = 1 - smoothstep(boderWidth, boderWidth+borderEdge,distance2) ;

float allalpha = alpha + (1 - alpha ) * alpha2 ;

vec3 allcolor = mix(outlineColor , color , alpha / allalpha) ;

out_Color = vec4(allcolor , allalpha) ;*/
