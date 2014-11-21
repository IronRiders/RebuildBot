/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package RebuildBot;
//imports:
import edu.wpi.first.wpilibj.IterativeRobot;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    
    //TO-DO: create objects universally.
    /*hint: Look in robotinit. 
    what do you need to: control the robot(what so we have in the code
    to control this thing)? make the robot move? Control the driving functions?*/
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        //TO-DO: initialize variables in robotInit()
        //joystick(s)
        //create constants for ports 1 and/or 2 these ports are for the computer 
        
        //driving
        
        //adding joystick listener(s)
        /*TO-DO: look through Attack3Joystick at the comment descriptions above
        each method, which one should be used to give the Attack3Joystick 
        something new to listen to? which listener do we need to add?*/
    }

     /**
     * This function is called once when autonomous mode is first started
     */
    public void autonomousInit(){
        
    }
    
     /**
     * This function is called once when operator control is first started
     */
    public void teleopInit() {
        System.out.println("The robot has been enabled.");
    }
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        /*TO-Do: tell the joystick to update values, look at Attack3Joustick methods.
        Becasue this method is in the teleopPeriodic() method it gets called 
        /ver and over and over again,and will upsate values effectivley. */
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
