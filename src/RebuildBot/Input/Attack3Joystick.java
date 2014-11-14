/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package RebuildBot.Input;

import RebuildBot.Listeners.ButtonListener;
import RebuildBot.Listeners.JoystickListener;
import java.util.Hashtable;
import java.util.Vector;

/**
 * Represents a Logitech Attack3 joystick. This is the joystick that came with
 * the 2012 KOP. We extend the Joystick class that comes with the FIRST Java library
 * because we still need the functions it provides to detect buttons and joystick
 * axis positions.
 * 
 * @since 2013
 */
public class Attack3Joystick extends edu.wpi.first.wpilibj.Joystick{
    
    // This stores which listeners are listening to which buttons. 
    private Hashtable buttonListeners;
    
    // This stores the threads for each button
    private Thread[] threads;
    
        // This stores which listeners are listening to the joystick
    private Vector joystickListeners;
    
    // This stores the previous state of each button
    private Hashtable state;
    
    /**
     * This is a list of all the buttons on the joystick. 
     */
    public static final Button[] BUTTONS = new Button[] {
        Button.TRIGGER,  Button.BUTTON_2,  Button.BUTTON_3, Button.BUTTON_4,
        Button.BUTTON_5, Button.BUTTON_6,  Button.BUTTON_7, Button.BUTTON_8,
        Button.BUTTON_9, Button.BUTTON_10, Button.BUTTON_11
    };
    
    /**
     * Creates a new instance of the Logitech Attack3 joystick. This is the
     * joystick that can with the 2012 KOP.
     * 
     * @param port the port that this joystick is plugged in to
     */
    public Attack3Joystick(int port) {
        super(port);
        threads = new Thread[BUTTONS.length];
        joystickListeners = new Vector();
        buttonListeners = new Hashtable();
        state = new Hashtable();
    }
        
    /**
     * Repeatedly call this method to check for a button press and notify the
     * listeners for those buttons.
     */
    public void listen() {
        // Were need to look at all the buttons on the joystick...
        for (int i = 0; i < BUTTONS.length; i++) {
            final Button button = BUTTONS[i];
            
            // Is the button pressed down right now?
            final Boolean isPressed = Boolean.valueOf(getRawButton(button.getButtonNumber()));
            
            final Boolean wasPressed;
            // Was the button pressed last time we checked?
            if(((Boolean)state.get(button)) == null){
                wasPressed = Boolean.valueOf(!isPressed.booleanValue());
            }
            else{
                wasPressed = ((Boolean)state.get(button));
            }
            
            //if (threads[button.getButtonNumber() - 1] == null) {
            //    threads[button.getButtonNumber() - 1] = new Thread() {
            //        public void run(){
                        notifyButtonListeners(button, isPressed.booleanValue(), wasPressed.booleanValue());
            //        }
            //    };
            //}
            
            //if (threads[button.getButtonNumber() - 1] != null) {
            //    threads[button.getButtonNumber() - 1].start();
            //}
            
            // Save the new state of the button
            state.put(button, isPressed);
        }
        
        notifyJoystickListeners();
        
    } 
    
    /**
     * When you want to have code respond to a button press, create a new
     * {@link ButtonListener} class that responds to the button being pushed
     * down, and released.
     * 
     * Example:
     * addButtonListener(Button b, new ButtonListener() {
     *   public void buttonUp() {
     *     // Now, what do we do when the button is released?
     *   }
     * 
     *   public void buttonDown() {
     *     // Now, what do we do when the button is pushed?
     *   }
     * });
     * 
     * @param button the Button to listen to
     * @param listener the "listener" that responds to the button being pressed or released
     */
    public void addButtonListener(Button button, ButtonListener listener) {
        if (!buttonListeners.contains(button)) {
            buttonListeners.put(button, new Vector());
        }
        
        ((Vector)(buttonListeners.get(button))).addElement(listener);
        
        System.out.println("Attached Button Listener");
    }
    public void addButtonListener(Button[] buttons, ButtonListener listener){
        for(int i = 0; i < buttons.length; i++){
            addButtonListener(buttons[i], listener);
            //System.out.println("Attached Button Listener");
        }
    }
            
    /**
     * Let the listeners for a button know that is was pressed. If isPressed == wasPressed,
     * the listeners will not be notified, since the button did not change since
     * last time
     * 
     * @param button the button that was pressed
     * @param isPressed whether or down the button is now pressed.
     * @param wasPressed whether or not the button was already pressed.
     */
    private void notifyButtonListeners(Button button, boolean isPressed, boolean wasPressed ) {
        Vector listeners = ((Vector)buttonListeners.get(button));
        
        //check to see if listeners is null meaning that there is not listeneer for that button
        if(listeners == null)
            return;
        
        // If the button is pressed right now, and is was not already pressed,
        // let the listeners know that the button is now pressed (down)
        if (isPressed && !wasPressed) {
            for (int i = 0;i<listeners.size();i++){
                ButtonListener bl = (ButtonListener)listeners.elementAt(i);
                bl.buttonDown(button);
            }
        }
        // If the button is not pressed right now, and is was already pressed,
        // let the listeners know that the button is now pressed released (up)
        else if (!isPressed && wasPressed) {
            for (int i = 0;i<listeners.size();i++){
                ButtonListener bl = (ButtonListener)listeners.elementAt(i);
                bl.buttonUp(button);
            }
        }
    }
    
    /**
     * When you want to have code respond to joystick movement, create a new
     * {@link JoystickListener} class that responds to the joystick moving in
     * any direction
     * 
     * Example:
     * addJoystickListener(new JoystickListener() {
     *   public void joystickMoved(double x, double y, double z) {
     *     // Now, what do we do when the button is released?
     *   }
     * });
     * 
     * @param listener the "listener" that responds to the joystick moving
     */
    public void addJoystickListener(JoystickListener listener){
        if (listener == null){
           System.out.println("Listener Nonexistant");
           return;
        }
        
        joystickListeners.addElement(listener);
        System.out.println("Attached Joystick Listener");
    }
    
    /**
     * Let the listeners for the joystick know that is was moved.
     */
    private void notifyJoystickListeners(){
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        z = (-z+1)/2;
        
        for(int i = 0; i < joystickListeners.size(); i ++){
            JoystickListener joystickListener = (JoystickListener)joystickListeners.elementAt(i);
            if ( joystickListener == null){
                System.out.println("No Listener at Index "+ i);
            }
            joystickListener.joystickMoved(x, y, z);
        }
    }
    
    /**
     * Type safe enumeration class representing all the buttons on the Joystick.
     * 
     * You cannot "create" new buttons on the joystick, hence why the constructor
     * is private. To use a button when adding a listener, use Button.TRIGGER or
     * Button.BUTTON_2
     */
    public static class Button {
        
        public static final Button BUTTON_1 = new Button(1);
        public static final Button BUTTON_2 = new Button(2);
        public static final Button BUTTON_3 = new Button(3);
        public static final Button BUTTON_4 = new Button(4);
        public static final Button BUTTON_5 = new Button(5);
        public static final Button BUTTON_6 = new Button(6);
        public static final Button BUTTON_7 = new Button(7);
        public static final Button BUTTON_8 = new Button(8);
        public static final Button BUTTON_9 = new Button(9);
        public static final Button BUTTON_10 = new Button(10);
        public static final Button BUTTON_11 = new Button(11);
        
        /**
         * This is an alias for button #1 on the joystick
         */
        public static final Button TRIGGER = BUTTON_1;
        
        private int buttonNumber;
        
        private Button(int buttonNumber) {
            this.buttonNumber = buttonNumber;
        }
        
        public int getButtonNumber() {
            return buttonNumber;
        }
        
        public String toString() {
            return "Button_" + buttonNumber;
        }
    } 
}