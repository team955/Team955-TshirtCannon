/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.*;



/**
 *
 * @author ryan
 */
public class Drive {
   rampingJaguar leftMotor;
   rampingJaguar rightMotor;
    public Drive () {
     rightMotor = new rampingJaguar(0,0);
     leftMotor = new rampingJaguar(0,0);
    }
    
    public void arcade (Joystick joy){
        leftMotor.ramp(joy.getY()- joy.getX());
        rightMotor.ramp(joy.getX()+joy.getY());
        
    }
    
}