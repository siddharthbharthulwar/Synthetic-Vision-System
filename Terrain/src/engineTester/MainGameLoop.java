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
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import entities.Camera;
import entities.Entity;
import entities.Light;
 
public class MainGameLoop {
 
    public static void main(String[] args) {
 
        DisplayManager.createDisplay(1200, 800);
        Loader loader = new Loader();

        
         
        RawModel model = OBJLoader.loadObjModel("dragon", loader);
        TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("white")));
        ModelTexture texture = staticModel.getTexture();
        texture.setShineDamper(100);
        texture.setReflectivity(1);
         
        Entity entity = new Entity(staticModel, new Vector3f(100,0,-10),0,0,0,1);
        Light light = new Light(new Vector3f(500,500,500), new Vector3f(1,1,1));
        
        
        //******************************TERRAIN TEXTURE******************
        
        
        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("green"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("green"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("grass"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("white"));
        
        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendmap"));
        
        
        
        
        //*********************************END TERRAIN TEXTURE
        
        
        
        Terrain terrain = new Terrain(0, -1, loader, texturePack, blendMap, "heightmap");

         
        Camera camera = new Camera(1000);
         
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