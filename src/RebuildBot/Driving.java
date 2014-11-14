/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RebuildBot;
import edu.wpi.first.wpilibj.Jaguar;
/**
 *
 * @author ros_feshaffer
 */
public class Driving {
        private int port1= 8;//not real port
        private int port2= 4;//not real port
        private Jaguar cats1= new Jaguar(port1);
        private Jaguar cats2= new Jaguar(port2); 
        private double x;
        private double y;
        private double z;
        
    public Driving(){
        
    }
        
    //motors
    //forward
    public void forward(double speed)
    {
        cats1.set(speed);
        cats2.set(speed);
    }
    
    
    //backwards 
    public void backwards(double speed) //will be negative
    {
        cats1.set(speed);
        cats2.set(speed);
    }
    
    public void updateX(double thisX){
       x = thisX; 
    }
    public void updateY(double thisY){
      y = thisY;  
    }
   public void updateZ(double thisZ){
        z = thisZ;
    }
    
    //turn left
    //turn right
    
}
