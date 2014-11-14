/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RebuildBot.Listeners;

import RebuildBot.Input.Attack3Joystick.Button;

/**
 * Interface describing actions that a ButtonListener must react to.
 */
public interface ButtonListener {
    
 /**
    * This method will be called when the button is released (up).
    * 
    * @param button the button that was pushed
    */
    public void buttonUp(Button button);
        
    /**
     * This method will be called when the button is pressed (down).
     * 
     * @param button the button that was pushed
     */
    public void buttonDown(Button button);
}
