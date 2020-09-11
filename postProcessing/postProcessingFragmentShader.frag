#version 330

in vec2 out_textureCoords ;

uniform sampler2D textureSampler ;

out vec4 out_Color ;

const float contrast = 0.5 ;

const float offset = 1.0 / 300.0;

uniform int keyset ;


void main(void) {
	vec2 offsets[9] = vec2[](
			        vec2(-offset,  offset), // top-left
			        vec2( 0.0,    offset), // top-center
			        vec2( offset,  offset), // top-right
			        vec2(-offset,  0.0),   // center-left
			        vec2( 0.0f,    0.0),   // center-center
			        vec2( offset,  0.0),   // center-right
			        vec2(-offset, -offset), // bottom-left
			        vec2( 0.0,   -offset), // bottom-center
			        vec2( offset, -offset)  // bottom-right
			    );
	float kernel[9] ;

	if(keyset == 0){
		out_Color = texture(textureSampler,out_textureCoords );
	}else if(keyset == 1){
		out_Color = texture(textureSampler,out_textureCoords );
		out_Color.rgb = (out_Color.rgb - 0.5) * (1 + contrast) + 0.5 ;
	}else if(keyset == 2){//Inversion
		out_Color = vec4(vec3(1.0 - texture(textureSampler, out_textureCoords)), 1.0);
	}else if(keyset == 3){//Grayscale
		out_Color = texture(textureSampler, out_textureCoords);
		float average = 0.2126 * out_Color.r + 0.7152 * out_Color.g + 0.0722 * out_Color.b;
		out_Color = vec4(average, average, average, 1.0);
	}else if(keyset == 4){
		kernel = float[](
		        -1, -1, -1,
		        -1,  9, -1,
		        -1, -1, -1
		    );
	}else if(keyset == 5){
		kernel = float[](
		        1, 1, 1,
		        1, -8, 1,
		        1, 1, 1
		    );
	}else if(keyset == 6){
		kernel = float[](
		    1.0 / 16, 2.0 / 16, 1.0 / 16,
		    2.0 / 16, 4.0 / 16, 2.0 / 16,
		    1.0 / 16, 2.0 / 16, 1.0 / 16
		);

	}

	if(keyset >= 4){

		 vec3 sampleTex[9];
		    for(int i = 0; i < 9; i++)
		    {
		        sampleTex[i] = vec3(texture(textureSampler, out_textureCoords.st + offsets[i]));
		    }
		    vec3 col = vec3(0.0);
		    for(int i = 0; i < 9; i++)
		        col += sampleTex[i] * kernel[i];

		    out_Color = vec4(col, 1.0);

	}

}

