package RebuildBot.Listeners;

import RebuildBot.Driving;

/**
 *
 * @author ros_maremash
 */
public class MovementListener implements JoystickListener{
    private Driving driving;
    
    public MovementListener (Driving driving1){
        this.driving = driving1;
    }
    
    public void joystickMoved(double x, double y, double z){
        //to make z a nonexistent factor uncomment the staement below:
        // z = 1;
        //updating values within driving
        driving.updateX(x);
        driving.updateY(y);
        driving.updateZ(z);
        //driving/moving according to those values
        driving.recalcvelocity();
    }
}
