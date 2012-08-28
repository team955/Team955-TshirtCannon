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
    rampingJaguar motorRight = new rampingJaguar(Var.slotVicDriveRight,Var.chanVicDriveRight);
    rampingJaguar motorLeft = new rampingJaguar(Var.slotVicDriveLeft,Var.chanVicDriveLeft);
    
    public void run(Joystick joy)
    {
        double y = joy.getY() * Math.abs(joy.getY());
        double x = -joy.getX() * Math.abs(joy.getX());
        
        if( Math.abs(y) + Math.abs(x) > 0)
        {
            motorRight.set(-y+x);
            motorLeft.set(y+x);
        }

        else
        {
            motorRight.set(0);
            motorLeft.set(0);
        }
    } 
    
    public void setSpeed(double motorRightSpeed, double motorLeftSpeed)
    {
        motorRight.set(motorRightSpeed);
        motorLeft.set(motorLeftSpeed);
    }
}