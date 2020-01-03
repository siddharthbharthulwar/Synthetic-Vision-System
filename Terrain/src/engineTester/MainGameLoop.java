package engineTester;
 
import models.RawModel;
import models.TexturedModel;
 
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
 
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;
import entities.Camera;
import entities.Entity;
 
public class MainGameLoop {
 
    public static void main(String[] args) {
 
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);
        
         
        RawModel model = OBJLoader.loadObjModel("stall", loader);
         
        TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("stallTexture")));
         
        Entity entity = new Entity(staticModel, new Vector3f(0,0,-50),0,0,0,1);
         
        Camera camera = new Camera();
         
        while(!Display.isCloseRequested()){
            entity.increaseRotation(2, 1, 2);
            camera.move();
            renderer.prepare();
            shader.start();
            shader.loadViewMatrix(camera);
            renderer.render(entity,shader);
            shader.stop();
            DisplayManager.updateDisplay();
        }
 
        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
 
    }
 
}