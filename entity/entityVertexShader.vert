#version 400

in vec2 position ;
in vec2 textureCoords ;

out vec2 out_textureCoords ;

uniform mat4 transformationMatrix ;
uniform mat4 projectionMatrix ;
uniform float worldPosition ;



void main(void) {
	vec4 objectPosition = transformationMatrix * vec4(position,worldPosition, 1.0) ;
	gl_Position = projectionMatrix * objectPosition;

	out_textureCoords = textureCoords ;



}
/*	surfaceNormal = (transformationMatrix * vec4(normals, 0)).xy ;
	lightVector = lightPosition - objectPosition.xy ;*/
