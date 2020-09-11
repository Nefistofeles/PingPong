#version 400
/*
Distance Constant Linear Quadratic
7 			1.0 	0.7 	1.8
13 			1.0 	0.35 	0.44
20 			1.0 	0.22 	0.20
32 			1.0 	0.14 	0.07
50 			1.0 	0.09 	0.032
65 			1.0 	0.07 	0.017
100 		1.0 	0.045 	0.0075
160 		1.0 	0.027 	0.0028
200 		1.0 	0.022 	0.0019
325 		1.0 	0.014 	0.0007
600 		1.0 	0.007 	0.0002
3250 		1.0 	0.0014 	0.000007
 * */
const float constant = 1 ;
const float linear = 0.014 ;
const float quadratic = 0.0007 ;


in vec2 out_lightPosition ;

uniform vec3 lightColor ;
out vec4 out_Color ;

void main(void){

	float distance = length(out_lightPosition - gl_FragCoord.xy);
	//float attenuation = 1.0 / distance * 3;
	float attenuation = 1 / (constant + (distance * linear) + (distance * quadratic * quadratic)) ;
	vec4 color = vec4(attenuation, attenuation, attenuation, pow(attenuation,3)) * vec4(lightColor, 1);

	out_Color = color ;

}

