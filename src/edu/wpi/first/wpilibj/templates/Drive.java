//<<<<<<< HEAD
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

/*
 * This class is responsible for driving the t-shirt cannon.
 * It also has a setspeed function which
 * sets the speed of the motors to the disired speed.
 */
         
public class Drive {
    RampingJaguar motorRight = new RampingJaguar(Var.chanVicDriveRight);
    RampingJaguar motorLeft = new RampingJaguar(Var.chanVicDriveLeft);
    
    public void run(Joystick joy)
    {
        double y = joy.getY() * Math.abs(joy.getY());
        double x = -joy.getX() * Math.abs(joy.getX());
        
        if( Math.abs(y) + Math.abs(x) > 0)
        {
            motorRight.ramp(-y+x);
            motorLeft.ramp(y+x);
        }

        else
        {
            motorRight.ramp(0);
            motorLeft.ramp(0);
        }
    } 
    
    public void setSpeed(double motorRightSpeed, double motorLeftSpeed)
    {
        motorRight.ramp(motorRightSpeed);
        motorLeft.ramp(motorLeftSpeed);
    }
}