package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Light {

	public Vector3f position;
	private Vector3f colour;
	private int speed;
	
	public Light(Vector3f position, Vector3f colour, int speed) {
		this.position = position;
		this.colour = colour;
		this.speed = speed;
	}
	public Vector3f getPosition() {
		return position;
	}
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	public Vector3f getColour() {
		return colour;
	}
	public void setColour(Vector3f colour) {
		this.colour = colour;
	}
	
	
	
    public void move(){

            if(Keyboard.isKeyDown(Keyboard.KEY_W)){
                position.z-=this.speed * 0.01f;
                
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_D)){
                position.x+=this.speed * 0.01f;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_A)){
                position.x-=this.speed * 0.01f;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_S)){
                position.z+=this.speed * 0.01f;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_R)){
                position.y+=this.speed * 0.01f;
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_F)){
                position.y-=this.speed* 0.01f;
            }
            

    }
	
}
