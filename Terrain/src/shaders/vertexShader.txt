#version 150

in vec3 position;
in vec2 textureCoords;

out vec2 pass_textureCoords;

uniform mat4 transformationMatrix;

void main(void){

	gl_Position = vec4(position,1.0);
	pass_textureCoords = textureCoords;
}