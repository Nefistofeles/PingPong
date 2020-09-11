#version 400

in vec2 out_textureCoords ;

out vec4 out_Color ;

uniform vec4 color ;
uniform sampler2D textureSampler ;

void main(void){

	vec4 out_colour =(texture(textureSampler, out_textureCoords) * color) ;

	if(out_colour.a < 0.2){
		discard ;
	}
	out_Color = out_colour;
}
/*vec3 ambientLighting(){
	float ambientStrength = 0.4;
	vec3 ambient = ambientStrength * lightColor;
	return ambient ;
}

vec3 diffuseLighting(vec2 normalNormalize, vec2 lightDir){
	float diff = max(dot(normalNormalize, lightDir), 0.0);
	vec3 diffuse = diff * lightColor;
	return diffuse ;
}
float pointLightAttenuation(vec2 lightVector){
	float distance = length(lightVector) ;
	float attenuationFactor = constant + (linear * distance) + (quadratic * distance * distance) ;
	return attenuationFactor ;
}
*/
/*	vec2 lightDir = normalize(lightVector) ;
	vec2 surfaceNormalNormalize = normalize(surfaceNormal) ;
 *
 * 	float attenuation =pointLightAttenuation(lightVector) ;
	vec3 diffuse = diffuseLighting(surfaceNormalNormalize, lightDir) / attenuation ;
	vec3 ambient = ambientLighting();*/
/*
const float shine = 32.0 ;
const vec3 ambient = vec3(0.05, 0.05, 0.05) ;
const vec3 diffuse = vec3(0.4, 0.4, 0.4) ;
const vec3 specular = vec3(0.5, 0.5, 0.5) ;
const float constant = 1;
const float linear =0.007;
const float quadratic =0.0002 ;


7	1.0	0.7		1.8
13	1.0	0.35	0.44
20	1.0	0.22	0.20
32	1.0	0.14	0.07
50	1.0	0.09	0.032
65	1.0	0.07	0.017
100	1.0	0.045	0.0075
160	1.0	0.027	0.0028
200	1.0	0.022	0.0019
325	1.0	0.014	0.0007
600	1.0	0.007	0.0002
3250	1.0	0.0014	0.000007*/
/*	float distance = 0.1;
	float attenuation = 1.0 / distance;
	vec4 att_color = vec4(attenuation, attenuation, attenuation, pow(attenuation, 3)) * vec4(lightColor, 1);
	float diffuseFactor = max(dot(surfaceNormalNormalize, lightDir ), 0.2);

	vec3 diffuse = lightColor * diffuseFactor ;
	diffuse *= attenuation ;
*/
