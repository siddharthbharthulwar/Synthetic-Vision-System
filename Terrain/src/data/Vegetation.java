package data;

import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import textures.ModelTexture;

public class Vegetation {
	
	public normPoint position;
	public float scaleFactor;
	public float height;
	
	public Vegetation(normPoint position, float scaleFactor) {
		
		this.position = position;
		this.scaleFactor = scaleFactor;
		
	}
	
	public Entity toEntity(Loader loader) {
		RawModel vegetationModel = OBJLoader.loadObjModel("tree", loader);
		TexturedModel staticTreeModel = new TexturedModel(vegetationModel, new ModelTexture(loader.loadTexture("water")));
		Entity tree = new Entity(staticTreeModel, new Vector3f(this.position.getX(), -1 * this.height, -1 * this.position.getY()), 0, 0, 0, this.scaleFactor);
		return tree;
	}
}
