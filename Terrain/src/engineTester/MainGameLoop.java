package engineTester;
 
import models.RawModel;
import models.TexturedModel;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
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
import guis.GuiRenderer;
import guis.GuiTexture;
 
public class MainGameLoop {
 
    public static void main(String[] args) {
 
        DisplayManager.createDisplay(1200, 800);
        Loader loader = new Loader();

        
         
        RawModel model = OBJLoader.loadObjModel("cube", loader);
        TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("white")));
        ModelTexture texture = staticModel.getTexture();
        texture.setShineDamper(110);
        texture.setReflectivity(5);
         
        Entity entity = new Entity(staticModel, new Vector3f(100,-6,-50),0,0,0,1);
        Entity entity2 = new Entity(staticModel, new Vector3f(100,-6,-100),0,0,0,1);

        Light light = new Light(new Vector3f(2000,5000,2323), new Vector3f(1,1,1));
        
        
        //******************************TERRAIN TEXTURE******************
        
        
        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("green"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("green"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("grass"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("white"));
        
        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendmap"));
        
        
        
        
        //*********************************END TERRAIN TEXTURE
        
        /*
        List<GuiTexture> guis = new ArrayList<GuiTexture>();
        GuiTexture gui = new GuiTexture(loader.loadTexture("green"), new Vector2f(0.75f, 0.75f), new Vector2f(0.15f, 0.25f));
        guis.add(gui);
        
        GuiRenderer guiRenderer = new GuiRenderer(loader);
        */
        
        
        Terrain terrain = new Terrain(0, -1, loader, texturePack, blendMap, "heightmap");

         
        Camera camera = new Camera(280);
         
        MasterRenderer renderer = new MasterRenderer();
        while(!Display.isCloseRequested()){
        	
            camera.move();
            
            renderer.processTerrain(terrain);
            
          
            renderer.processEntity(entity);
            renderer.processEntity(entity2);
            renderer.render(light, camera);
            //guiRenderer.render(guis);
            DisplayManager.updateDisplay();
        }
        //guiRenderer.cleanUp();
        renderer.cleanUp();
        
        loader.cleanUp();
        DisplayManager.closeDisplay();
 
    }
 
}