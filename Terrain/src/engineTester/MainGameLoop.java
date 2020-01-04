package engineTester;
 
import models.RawModel;
import models.TexturedModel;
 
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
 
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import renderEngine.EntityRenderer;
import shaders.StaticShader;
import terrain.Terrain;
import textures.ModelTexture;
import entities.Camera;
import entities.Entity;
import entities.Light;
 
public class MainGameLoop {
 
    public static void main(String[] args) {
 
        DisplayManager.createDisplay(1000, 1000);
        Loader loader = new Loader();

        
         
        RawModel model = OBJLoader.loadObjModel("dragon", loader);
        TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("white")));
        ModelTexture texture = staticModel.getTexture();
        texture.setShineDamper(100);
        texture.setReflectivity(1);
         
        Entity entity = new Entity(staticModel, new Vector3f(0,0,-10),0,0,0,1);
        Light light = new Light(new Vector3f(500,500,500), new Vector3f(1,1,1));
        
        
        Terrain terrain = new Terrain(0,0, loader, new ModelTexture(loader.loadTexture("green")));

         
        Camera camera = new Camera(100);
         
        MasterRenderer renderer = new MasterRenderer();
        while(!Display.isCloseRequested()){
            entity.increaseRotation(0, 0, 0);
            camera.move();
            
            renderer.processTerrain(terrain);
            
          
            renderer.processEntity(entity);
            renderer.render(light, camera);

            DisplayManager.updateDisplay();
        }
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
 
    }
 
}