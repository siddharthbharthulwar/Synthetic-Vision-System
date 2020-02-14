package entities;
 
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
 
public class Camera {
     
    private Vector3f position = new Vector3f(0,0,0);
    private float pitch;
    private float yaw;
    private float roll = 20;
    private int speed;
     
    public Camera(int s){
    	this.speed = s;
    }
     
    public void move(){
    	
    	
    	//SLOW COMMANDS
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
        
        if (Keyboard.isKeyDown(Keyboard.KEY_T)) {
        	this.pitch -= 0.2f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
        	this.pitch += 0.2f;
        }
        
        if (Keyboard.isKeyDown(Keyboard.KEY_Z)) {
        	this.yaw -= 0.2f;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_C)) {
        	this.yaw += 0.2f;
        }
        
        
        
        //FAST COMMANDS
        
        if(Keyboard.isKeyDown(Keyboard.KEY_I)){
            position.z-=this.speed * 0.1f;
            
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_L)){
            position.x+=this.speed * 0.1f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_J)){
            position.x-=this.speed * 0.1f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_K)){
            position.z+=this.speed * 0.1f;
        }

    }

 
    public Vector3f getPosition() {
        return position;
    }
 
    public float getPitch() {
        return pitch;
    }
 
    public float getYaw() {
        return yaw;
    }
 
    public float getRoll() {
        return roll;
    }
     
     
 
}