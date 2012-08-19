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
public class Drive {
    Timer timer;
    LinkedListDouble iTimerList = new LinkedListDouble();
    LinkedListDouble iXList = new LinkedListDouble();
    LinkedListDouble iYList = new LinkedListDouble();
    double iXBefore = 0;
    double iYBefore = 0;
    boolean lastStateRC;
    boolean curStateRC;
    boolean lastStateRP;
    boolean curStateRP;
    boolean record = false;
    rampingJaguar motorRight = new rampingJaguar(Var.slotVicDriveRight,Var.chanVicDriveRight);
    rampingJaguar motorLeft = new rampingJaguar(Var.slotVicDriveLeft,Var.chanVicDriveLeft);
    
    public void run(Joystick joy)
    {
        double y = joy.getY() * Math.abs(joy.getY());
        double x = -joy.getX() * Math.abs(joy.getX());
        lastStateRC = curStateRC;
        curStateRC = joy.getRawButton(Var.buttonRecord);
        lastStateRP = curStateRP;
        curStateRC = joy.getRawButton(Var.buttonReplay);
       
        if(curStateRC == true && lastStateRC == false)
            record = !record;
       
        if(record)
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
        
        if(curStateRP == true && lastStateRP == false && record == false)
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


//>>>>>>> cb72cd71caf8dc03dd787cbaf75e7ebf2782ab0b
