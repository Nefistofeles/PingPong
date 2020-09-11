#version 330

in vec2 position ;
in mat4 transformationMatrix ;
in vec4 offsets ;
in float blendFactor ;

out vec2 out_textureCoords1 ;
out vec2 out_textureCoords2 ;
out float interpolate ;

uniform mat4 projectionMatrix ;

uniform float worldPosition ;

uniform float numberOfRows ;
uniform float numberOfColumn ;

void main() {

	gl_Position = projectionMatrix * transformationMatrix * vec4(position,worldPosition, 1.0);
	vec2 textureCoords = position/2 + vec2(0.5,0.5) ;
	textureCoords.y = 1 - textureCoords.y ;
	textureCoords /= numberOfRows ;

	out_textureCoords1 = textureCoords + offsets.xy;
	out_textureCoords2 = textureCoords + offsets.zw;
	interpolate = blendFactor ;
}
