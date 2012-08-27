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
 * This class is responsible for driving the t-shirt cannon
 * it currently has a recording ability in the run function that
 * has not been tested yet. It also has a setspeed function which
 * sets the speed of the motors to the disired speed.
 */
         
public class Drive {
    Timer timer;
    LinkedListDouble iTimerList = new LinkedListDouble();
    LinkedListDouble iXList = new LinkedListDouble();
    LinkedListDouble iYList = new LinkedListDouble();
    double iXBefore = 0;
    double iYBefore = 0;
    CButton bRecord = new CButton();
    CButton bReplay = new CButton();
    rampingJaguar motorRight = new rampingJaguar(Var.slotVicDriveRight,Var.chanVicDriveRight);
    rampingJaguar motorLeft = new rampingJaguar(Var.slotVicDriveLeft,Var.chanVicDriveLeft);
    
    public void run(Joystick joy)
    {
        double y = joy.getY() * Math.abs(joy.getY());
        double x = -joy.getX() * Math.abs(joy.getX());
        bRecord.run(joy.getRawButton(Var.buttonRecord));
        bReplay.run(joy.getRawButton(Var.buttonReplay));

        if(bRecord.gotPressed())
        {            
            if(joy.getX() != iXBefore && joy.getY() != iYBefore)
            {
                iXBefore = joy.getX();
                iYBefore = joy.getY();
                iTimerList.add(timer.get());
                timer.stop();
                timer.reset();
                timer.start();
                iXList.add(joy.getX());
                iYList.add(joy.getY());
            }
        }
        
        else if(bReplay.gotPressed())
        {
            for(int i = iXList.size(); i >= 0; --i)
            {
                y = iYList.get(i) * Math.abs(iYList.get(i));
                x = iXList.get(i) * Math.abs(iXList.get(i));
                timer.reset();
                timer.start();
                while(timer.get() <= iTimerList.get(i) )
                {
                    setSpeed(-(-y+x), -(y+x));
                }
            }
        }
        
        else
        {
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
    } 
    
    public void setSpeed(double motorRightSpeed, double motorLeftSpeed)
    {
        motorRight.set(motorRightSpeed);
        motorLeft.set(motorLeftSpeed);
    }
}