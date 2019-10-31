#include <stdio.h>
#include <stdlib.h>

#include <glut.h>
#include <glfw3.h>
#include <GL/gl.h>


using namespace glm;

glewExperimental = true; // Needed for core profile
if( !glfwInit() )
{
    fprintf( stderr, "Failed to initialize GLFW\n" );
    return -1;
}