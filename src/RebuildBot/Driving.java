//specification of where this class is within the project.
package RebuildBot;
//import for the Jaguar class, so the object can be used.
import edu.wpi.first.wpilibj.Jaguar;
/**
 *
 * @author ros_feshaffer
 */
public class Driving {
        //these variables cannot be changed, they are constants.
        //They are the ports that the motors are connected to.
        private static final int PORT1= 1;
        private static final int PORT2= 2;
        //Jaguar motors:
        private Jaguar catsRight= new Jaguar(PORT1);//right reversed
        private Jaguar catsLeft= new Jaguar(PORT2);//left
        //x, y, z values that will be updated froom joystick values.
        public double x;
        public double y;
        public double z;
        
    public Driving(){
       //TO-DO: initialize the values of x, y, and z as zero. why should we do this? 
    }
    
    //this is the function that makes the robot move
    //setting speed to include the z value and tolerence
    public void recalcvelocity()
    {
        double newY = ((int)(y*z)*100)/100.0;
        double newX = ((int)(x*z)*100)/100.0;
        
        /*TO-DO: apply the setSpeedCatsRight() and setSpeedCatsLeft() methods to
        the statements below to account for the reversed motor*/
        catsRight.set(Math.min((Math.max(-1, newY+newX)),1));
        catsLeft.set(Math.min((Math.max(-1, newY-newX)),1));
    }
    
    /*the update methods below update the x, y, and z value stored in driving, 
    this is the only way other mehtods can change x, y, and z from outside the 
    driving object because the variables are private to this class. 
    These methods are called setters. */
    public void updateX(double thisX){
        x = thisX; 
    }
    public void updateY(double thisY){
        y = thisY;  
    }
    public void updateZ(double thisZ){
        z = thisZ;
    }

   //stops both of the motors
   //used in autonomus
   public void stop () {
       catsRight.set (0);
       catsLeft.set (0);        
   }
   
   //TO-DO: complete this recalc velocity class to be used in autonomus
   public void recalcVelocityAuto(double speed){
       //how do you make both of the motors turn at the same speed in the same direction?
   }
   
   //assuming that one motor is flipped
   //turn left
   public void setSpeedCatsRight(double speed){
       catsRight.set(-speed);     
   }
    //turn right
   public void setSpeedCatsLeft (double speed) {
       catsLeft.set(speed);        
   }
}
