#include <common.h>

int main(int argc, char* argv[]){
    if(SDL_Init(SDL_INIT_NOPARACHUTE) && SDL_Init(SDL_INIT_EVERYTHING) !=0){
        SDL_Log("Unable to initialize SDL: %s", SDL_GetError());
        return -1;
    }
    else{
        SDL_Log("SDL Initialized");
    }


    //creating window
    SDL_Window* window = SDL_CreateWindow(
        "test",
        SDL_WINDOWPOS_CENTERED,
        SDL_WINDOWPOS_CENTERED,
        640,
        480,
        SDL_WINDOW_OPENGL
    );

    //Generate an opengl context and map it to window instance
    SDL_GLContext glContext = SDL_GL_CreateContext(window);

    SDL_GL_SwapWindow(window);

    SDL_GL_DeleteContext(glContext);
    SDL_DestroyWindow(window);

    
    return 0;
}