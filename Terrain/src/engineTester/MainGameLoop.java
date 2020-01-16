package engineTester;
 
import models.RawModel;
import models.TexturedModel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;
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
import entities.Runway;
import fontMeshCreator.FontType;
import fontMeshCreator.GUIText;
import fontRendering.TextMaster;
import guis.GuiRenderer;
import guis.GuiTexture;
 
public class MainGameLoop {
 
    public static void main(String[] args) {
 
        DisplayManager.createDisplay(800, 800);
        Loader loader = new Loader();
        TextMaster.init(loader);

        FontType font = new FontType(loader.loadTexture("segoeUI"), new File("res/segoeUI.fnt"));
        GUIText text = new GUIText("Synthetic Vision System", 1, font, new Vector2f(0, 0), 0.5f, true);
        text.setColour(1, 1, 1);
        
        List<Point> pointList = new ArrayList<Point>();
        List<Point> pointList2 = new ArrayList<Point>();
        List<Building> buildings = new ArrayList<Building>();
        
        
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
       
        pointList2.add(new Point(0, 0));
        pointList2.add(new Point(115, 10));
        pointList2.add(new Point(210, 51));
        pointList2.add(new Point(115,111));
        pointList2.add(new Point(19,212));
        pointList2.add(new Point(10, 110));
        pointList2.add(new Point(-11, 51));
	
        
        pointList.add(new Point(-1, 5));
        pointList.add(new Point(0, 10));
        pointList.add(new Point(9,22));
        pointList.add(new Point(15,11));
        pointList.add(new Point(20, 5));
        pointList.add(new Point(15, 0));
        pointList.add(new Point(0, 0));
        
        Building b = new Building(-15, pointList);
        Building c = new Building(-19, pointList2);

        buildings.add(b);
        buildings.add(c);
        
        Runway r = new Runway(new Vector2f(12000, -3000), new Vector2f(7800,-5000), new Vector2f(7900, -6500), new Vector2f(12200, -3500), 50);
        
        
        List<TexturedModel> staticModels = new ArrayList<TexturedModel>();
        List<Entity> entities = new ArrayList<Entity>();
        
        for (Building building: buildings) {
        	
        	RawModel model = loader.loadToVAO(building.floatVertProcess(), textureCoords, normals, building.generateIndices());
        	TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("white")));
        	ModelTexture texture = staticModel.getTexture();
            texture.setShineDamper(1000);
            texture.setReflectivity(0.5f);
            
            staticModels.add(staticModel);
            
        	
        }
        
        RawModel model = loader.loadToVAO(r.genVertices(), textureCoords, normals, r.genIndices());
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("white")));
        staticModels.add(staticModel);

        Entity entity = new Entity(staticModels.get(0), new Vector3f(1525,-7,-1000),90,0,0,1);
        Entity entity2 = new Entity(staticModels.get(1), new Vector3f(2151, -7, -1921), 90, 0, 0, 1);
        Entity runway = new Entity(staticModels.get(2), new Vector3f(0, 0, 0), 0, 0, 0, 1);
        
        entities.add(entity);
        entities.add(entity2);
        entities.add(runway);
        
        Camera camera = new Camera(100);
        

        Light light = new Light(new Vector3f(camera.getPosition().x,10500,9000), new Vector3f(1,1,1), 1900);
        
        
        //******************************TERRAIN TEXTURE******************
        
        
        TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("green"));
        TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("water"));
        TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("grass"));
        TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("white"));
        
        TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture, rTexture, gTexture, bTexture);
        TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendmap"));
        
        
        
        
        //*********************************END TERRAIN TEXTURE
        
        
        List<GuiTexture> g22 = new ArrayList<GuiTexture>();
        GuiTexture gui = new GuiTexture(loader.loadTexture("2-2"), new Vector2f(0.75f, 0.75f), new Vector2f(0.1f, 0.03f));
        g22.add(gui);
        
        List<GuiTexture> g13 = new ArrayList<GuiTexture>();
        GuiTexture gui2 = new GuiTexture(loader.loadTexture("1-3"), new Vector2f(0.75f, 0.75f), new Vector2f(0.1f, 0.03f));
        g13.add(gui2);
        
        List<GuiTexture> g04 = new ArrayList<GuiTexture>();
        GuiTexture gui3 = new GuiTexture(loader.loadTexture("0-4"), new Vector2f(0.75f, 0.75f), new Vector2f(0.1f, 0.03f));
        g04.add(gui3);
        
        List<GuiTexture> g31 = new ArrayList<GuiTexture>();
        GuiTexture gui4 = new GuiTexture(loader.loadTexture("3-1"), new Vector2f(0.75f, 0.75f), new Vector2f(0.1f, 0.03f));
        g31.add(gui4);
        
        List<GuiTexture> g40 = new ArrayList<GuiTexture>();
        GuiTexture g5 = new GuiTexture(loader.loadTexture("4-0"), new Vector2f(0.75f, 0.75f), new Vector2f(0.1f, 0.03f));
        g40.add(g5);
        
        
        
        GuiRenderer guiRenderer = new GuiRenderer(loader);
        
        
        
        Terrain terrain = new Terrain(0, -1, loader, texturePack, blendMap, "heightmap");

         
     
        MasterRenderer renderer = new MasterRenderer();
        
        while(!Display.isCloseRequested()){
        	
            camera.move();
            light.move();
            renderer.processTerrain(terrain);
            
            for (Entity e: entities) {
            	renderer.processEntity(e);
            }
            
            
            
            renderer.render(light, camera);
            if (camera.getPosition().y > 100) {
                guiRenderer.render(g40);

            }
            else if (camera.getPosition().y > 75) {
            	guiRenderer.render(g31);
            }
            else if (camera.getPosition().y > 50) {
            	guiRenderer.render(g22);
            }
            else if (camera.getPosition().y > 25) {
            	guiRenderer.render(g13);
            }
            else {
            	guiRenderer.render(g04);
            }
            text.setTextString("1");
            TextMaster.render();
            
            
            
            DisplayManager.updateDisplay();
            
        }
        
        
        //***********CLEAN UP *************
        
        TextMaster.cleanUp();
        guiRenderer.cleanUp();
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
 
    }
 
}