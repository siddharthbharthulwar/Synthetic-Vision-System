package entities;
 
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;
 
public class Camera {
     
    private Vector3f position = new Vector3f(0,0,0);
    private float pitch;
    private float yaw;
    private float roll;
    private int speed;
     
    public Camera(int s){
    	this.speed = s;
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