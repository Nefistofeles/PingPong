#version 330

in vec2 position ;
in vec2 textureCoords ;

out vec2 out_textureCoords ;

uniform mat4 projectionMatrix ;
uniform mat4 transformationMatrix ;

uniform bool  chaos;
uniform bool  confuse;
uniform bool  shake;
uniform float time;

void main(void) {
	gl_Position = projectionMatrix * transformationMatrix * vec4(position,9, 1.0);
	out_textureCoords = vec2(textureCoords.x , 1-textureCoords.y) ;

    if (shake){
        float strength = 0.01;
        gl_Position.x += cos(time * 10) * strength;
        gl_Position.y += cos(time * 15) * strength;
    }
}
/*  if(chaos){
        float strength = 0.3;
        vec2 pos = vec2(out_textureCoords.x + sin(time) * strength, out_textureCoords.y + cos(time) * strength);
        out_textureCoords = pos;
    }else if(confuse){
    	out_textureCoords = vec2(1 - out_textureCoords.x, 1- out_textureCoords.y);
    }else{
    	out_textureCoords = vec2(out_textureCoords.x , out_textureCoords.y);
    }*/
