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
        private static final int PORT1= 1;
        private static final int PORT2= 2;
        private Jaguar cats1= new Jaguar(PORT1);//right reversed
        private Jaguar cats2= new Jaguar(PORT2);//left
        public double x;
        public double y;
        public double z;
        
    public Driving(){
        
    }
        
    //motors
    //setting speed to include the z value and tolerence
    public void recalcvelocity()
    {
        double newY = ((int)(y*z)*100)/100.0;
        double newX = ((int)(x*z)*100)/100.0;
        
        cats1.set(Math.min((Math.max(-1, newY+newX)),1));
        cats2.set(Math.min((Math.max(-1, newY-newX)),1));
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
    //assuming that one motor is flipped
    //turn left
   public void turnLeft(double speed){
       cats1.set(-speed);     
   }
    //turn right
   public void turnRight (double speed) {
       cats2.set (speed);        
   }
   public void stop () {
       cats1.set (0);
       cats2.set (0);        
   }
    
}
