#version 400

in vec2 position ;

uniform mat4 transformationMatrix ;
uniform mat4 projectionMatrix ;
uniform float worldPosition ;

out vec2 out_textureCoords ;

void main(void) {

	gl_Position = projectionMatrix * transformationMatrix* vec4(position,worldPosition, 1.0);

	out_textureCoords = vec2((position.x+1.0)/2.0, 1 - (position.y+1.0)/2.0);
}
