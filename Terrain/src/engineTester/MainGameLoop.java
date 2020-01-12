package engineTester;
 
import models.RawModel;
import models.TexturedModel;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import data.Building;
import data.Point;
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

        
        List<Point> pointList = new ArrayList<Point>();
        pointList.add(new Point(0, 0));
        pointList.add(new Point(15, 0));
        pointList.add(new Point(20, 5));
        pointList.add(new Point(15,11));
        pointList.add(new Point(9,22));
        pointList.add(new Point(0, 10));
        pointList.add(new Point(-1, 5));

        
        
        Building b = new Building(-15, pointList);
        float[] vertices = b.floatVertProcess();
        
        /*
        int[] indices = {
        		
        		1, 0, 3,
        		0, 2, 3,
        		3, 2, 5,
        		2, 4, 5,
        		5, 4, 7,
        		3, 6, 7,
        		7, 6, 9,
        		6, 8, 9
        		
        };
		*/
        
        int[] indices = b.generateIndices();
        
        float[] vertices2 = {			
				0.5f, -0.5f, 0.5f,
				-0.5f, -0.5f, 0.5f,
				-0.5f, -0.5f, -0.5f,
				0.5f, -0.5f, -0.5f,
				0.5f, 0.5f, 0.5f,
				-0.5f, 0.5f, 0.5f,
				-0.5f, 0.5f, -0.5f,
				0.5f, 0.5f, -0.5f
				
		};
        float[] textureCoords = {
				
				0,0,
				0,1,
				1,1,
				1,0,			
				

				
		};
        
        float[] normals = {
                
                0.2f, 0.3f, 0.4f,
                0.5f, 0.6f, 0.8f,
                1, 1, 1,
                1,1,1
            
           
        };
       
        RawModel model = loader.loadToVAO(vertices, textureCoords, normals, indices);
        //RawModel model = OBJLoader.loadObjModel("dragon", loader);
        TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("white")));
        
        ModelTexture texture = staticModel.getTexture();
        texture.setShineDamper(1000);
        texture.setReflectivity(0.5f);
        
        Entity entity = new Entity(staticModel, new Vector3f(1525,-10,-1000),90,0,0,7);
        
        

        Light light = new Light(new Vector3f(11500,11500,-11500), new Vector3f(1,1,1));
        
        
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

         
        Camera camera = new Camera(1125);
         
        MasterRenderer renderer = new MasterRenderer();
        while(!Display.isCloseRequested()){
        	
            camera.move();
            
            renderer.processTerrain(terrain);
            
          
            renderer.processEntity(entity);
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