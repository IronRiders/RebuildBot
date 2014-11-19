/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RebuildBot.Listeners;

import RebuildBot.Driving;

/**
 *
 * @author ros_maremash
 */
public class MovementListener implements JoystickListener{
    Driving driving;
    public MovementListener (Driving driving1){
        this.driving= driving1;
    }
    public void joystickMoved(double x, double y, double z){
        driving.updateX(x);
        driving.updateY(y);
        driving.updateZ(z);
        driving.recalcvelocity();
    }
}
