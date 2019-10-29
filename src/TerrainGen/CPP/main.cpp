#include <stdio.h>
#include <stdlib.h>
#include <GL/glew.h>
#include <GL/glfw.h>
#include <glm/glm.hpp>

using namespace glm;

int main(void){
   if (! gflwInit()){
      fprintf(stderr, "Failed to initialize GLFW\n");
      return -1;
   }
}


