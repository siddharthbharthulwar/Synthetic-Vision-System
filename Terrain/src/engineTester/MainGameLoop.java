package engineTester;
 
import models.RawModel;
import models.TexturedModel;

import java.io.File;
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
import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import fontRendering.TextMaster;
import guis.GuiRenderer;
import guis.GuiTexture;
 
public class MainGameLoop {
 
    public static void main(String[] args) {
 
        DisplayManager.createDisplay(1800, 1000);
        Loader loader = new Loader();
        TextMaster.init(loader);

        FontType font = new FontType(loader.loadTexture("segoeUI"), new File("res/segoeUI.fnt"));
        GUIText text = new GUIText("Synthetic Vision System", 1, font, new Vector2f(0, 0), 0.5f, true);
        text.setColour(1, 1, 1);
        
        List<Point> pointList = new ArrayList<Point>();
      /*  
        pointList.add(new Point(0, 0));
        pointList.add(new Point(15, 0));
        pointList.add(new Point(20, 5));
        pointList.add(new Point(15,11));
        pointList.add(new Point(9,22));
        pointList.add(new Point(0, 10));
        pointList.add(new Point(-1, 5));
	*/
        
        pointList.add(new Point(-1, 5));
        pointList.add(new Point(0, 10));
        pointList.add(new Point(9,22));
        pointList.add(new Point(15,11));
        pointList.add(new Point(20, 5));
        pointList.add(new Point(15, 0));
        pointList.add(new Point(0, 0));
        
        Building b = new Building(-15, pointList);
        float[] vertices = b.floatVertProcess();
        

        
        int[] indices = b.generateIndices();
        
        
        float[] textureCoords = {
        		1, 1	
		};
        
        float[] normals = {
                
                1, 1, 1,
                1, 1, 1,
                1, 1, 1,
                1, 1, 1,
                1, 1, 1,
                1, 1, 1,
                1, 1, 1,
                1, 1, 1,
                1, 1, 1,
                1, 1, 1,
                1, 1, 1,
                1, 1, 1,
                1, 1, 1,
                1, 1, 1,
                1, 1, 1
                
            
           
        };
       
        RawModel model = loader.loadToVAO(vertices, textureCoords, normals, indices);
        TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("white")));
        
        ModelTexture texture = staticModel.getTexture();
        texture.setShineDamper(1000);
        texture.setReflectivity(0.5f);
        
        Entity entity = new Entity(staticModel, new Vector3f(1525,-10,-1000),90,0,0,7);
        
        Camera camera = new Camera(1900);
        

        Light light = new Light(new Vector3f(camera.getPosition().x,10500,9000), new Vector3f(1,1,1), 1900);
        
        
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

         
     
        MasterRenderer renderer = new MasterRenderer();
        while(!Display.isCloseRequested()){
        	
            camera.move();
            light.move();
            renderer.processTerrain(terrain);
            
          
            renderer.processEntity(entity);
            renderer.render(light, camera);
            //guiRenderer.render(guis);
            text.setTextString("1");
            TextMaster.render();
            
            
            
            DisplayManager.updateDisplay();
            
        }
        
        
        //***********CLEAN UP *************
        
        TextMaster.cleanUp();
        //guiRenderer.cleanUp();
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
 
    }
 
}