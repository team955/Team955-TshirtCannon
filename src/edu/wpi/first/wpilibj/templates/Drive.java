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
    Victor motorRight = new Victor(Var.chanVicDriveRight);
    Victor motorLeft = new Victor(Var.chanVicDriveLeft);
    CButton butInvert = new CButton();
    public void run(Joystick joy)
    {
		butInvert.run(joy.getRawButton(Var.buttonInvert));
		
        double y = -joy.getY() * Math.abs(joy.getY());
        double x = -joy.getX() * Math.abs(joy.getX());
		
		System.out.println("x:" + x + " - y:" + y);
        if(Math.abs(y) + Math.abs(x) > 0.1)
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