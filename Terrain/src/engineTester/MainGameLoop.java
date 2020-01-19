package engineTester;
 
import models.RawModel;
import models.TexturedModel;

import java.io.File;
import java.io.IOException;
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
import guidance.guidingBox;
import guis.GuiRenderer;
import guis.GuiTexture;

import toolbox.arrayUtils;
import util.FileUtil;
 
public class MainGameLoop {
 
    public static void main(String[] args) throws IOException {
 
        DisplayManager.createDisplay(800, 800);
        Loader loader = new Loader();
        TextMaster.init(loader);

        FontType font = new FontType(loader.loadTexture("segoeUI"), new File("res/segoeUI.fnt"));
        GUIText text = new GUIText("Synthetic Vision System", 1, font, new Vector2f(0, 0), 0.5f, true);
        text.setColour(1, 1, 1);
        
        List<Point> pointList = new ArrayList<Point>();
        List<Point> pointList2 = new ArrayList<Point>();
        List<Building> buildings = FileUtil.loadBuildingsFromJSON("res/data.json");
        
        System.out.println(" ==== Building List read is: " + buildings);
        System.out.println(" ==== Building List Size is: " + buildings.size());
        System.out.println(" ==== Building List Class is: " + buildings.getClass());
        
        
        List<Building> buildingList = null;

        for(Object obj:buildings) {
        	// System.out.println("  ==== Building class for OBJ is: " + obj.getClass());
        	 
        	// System.out.println(" ==== Building List 2 is: " + buildingList);
        	 
        	 buildingList = (ArrayList<Building>) obj;

        	 
        }
        
        List<guidingBox> guideList = new ArrayList<guidingBox>();
        
        
        guidingBox guide1 = new guidingBox(500, new Vector3f(500, 2400, 1000), 200, 50);
        guidingBox guide2 = new guidingBox(500, new Vector3f(500, 2400, 3000), 200, 50);
        guidingBox guide3 = new guidingBox(500, new Vector3f(500, 2400, 5000), 200, 50);
        guidingBox guide4 = new guidingBox(500, new Vector3f(500, 2400, 7000), 200, 50);
        guidingBox guide5 = new guidingBox(500, new Vector3f(500, 2400, 9000), 200, 50);
        guidingBox guide6 = new guidingBox(500, new Vector3f(500, 2400, 11000), 200, 50);
        guidingBox guide7 = new guidingBox(500, new Vector3f(500, 2400, 13000), 200, 50);
        guidingBox guide8 = new guidingBox(500, new Vector3f(500, 2400, 15000), 200, 50);

        

        guideList.add(guide1);
        guideList.add(guide2);
        guideList.add(guide3);
        guideList.add(guide4);
        guideList.add(guide5);
        guideList.add(guide6);
        guideList.add(guide7);
        guideList.add(guide8);
        
        
        
        float[] textureCoords = {
        		1, 1	
		};
        
        float[] normals = {
                
                1, 0, 0,
                1, 0, 0,
                1, 0, 0,
                1, 0, 0,
                0, 0, 1,
                0, 0, 1,
                0, 0, 1,
                0, 0, 1,
               - 1, 0, 0,
                -1, 0, 0,
                -1, 0, 0,
               - 1, 0, 0,
                0, 0, 1,
                0, 0, 1,
                0, 0, 1,
                0, 0, 1,
               - 1, 0, 0,
                -1, 0, 0,
                -1, 0, 0,
                -1, 0, 0,
                0, 0, 1,
                0, 0, 1,
                0, 0, 1,
                0, 0, 1,
                0, 1, 0, 
                0, 1, 0,
                0, 1, 0,
                0, 1, 0
               
           
        };
       
        
        
        Runway r = new Runway(new Vector2f(12000, -3000), new Vector2f(7800,-5000), new Vector2f(7900, -6500), new Vector2f(12200, -3500), 50);
        
        
        List<TexturedModel> staticModels = new ArrayList<TexturedModel>();
        List<Entity> entities = new ArrayList<Entity>();
        
        for (Building building: buildingList) {
        	
        	RawModel model = loader.loadToVAO(building.floatVertProcess(), textureCoords, normals, building.generateIndices());
        	TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("white")));
        	ModelTexture texture = staticModel.getTexture();
            texture.setShineDamper(1000);
            texture.setReflectivity(0.5f);
            
            staticModels.add(staticModel);
            //System.out.println(building.getCorners().size());
            
            
            //System.out.println(arrayToString(building.generateIndices()));
           // System.out.println(building.generateIndices().length);
            
        	
        }
        
        for (guidingBox guide: guideList) {
        	RawModel model = loader.loadToVAO(guide.generateVertices(), textureCoords, normals, guide.generateIndices());
        	TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("white")));
        	ModelTexture texture = staticModel.getTexture();
            texture.setShineDamper(1000);
            texture.setReflectivity(0.5f);
            
            staticModels.add(staticModel);
        }
        
        
        
        /*
        RawModel model = loader.loadToVAO(r.genVertices(), textureCoords, normals, r.genIndices());
        TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("white")));
        staticModels.add(staticModel);
*/
        
        
        for (int j = 0; j < buildingList.size() + guideList.size(); j++) {
        	entities.add(new Entity(staticModels.get(j), new Vector3f(5550, -10, -4000), 0, 0, 0, 4));
        	
        }
        /*
        Entity entity = new Entity(staticModels.get(0), new Vector3f(1525,-40,-1000),0,0,0,1);
        Entity entity2 = new Entity(staticModels.get(1), new Vector3f(2151, -40, -1921), 0, 0, 0, 1);
        Entity entity3 = new Entity(staticModels.get(2), new Vector3f(400, -400, 400), 0, 0, 0, 1);
        //Entity runway = new Entity(staticModels.get(2), new Vector3f(0, 0, 0), 0, 0, 0, 1);
        
        entities.add(entity);
        entities.add(entity2);
        entities.add(entity3);
        //entities.add(runway);
        */
        Camera camera = new Camera(1000);
        

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
        Terrain terrain2 = new Terrain(-1, -1, loader, texturePack, blendMap, "heightmap");
        Terrain terrain3 = new Terrain(-1, 0, loader, texturePack, blendMap, "heightmap");
        Terrain terrain4 = new Terrain(0, 0, loader, texturePack, blendMap, "heightmap");

         
     
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