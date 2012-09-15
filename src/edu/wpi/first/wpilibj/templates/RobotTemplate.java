/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

/*
 * This is the main file where everything actually takes place.
 * For more information ask Ryan, programming captian.
 */

public class RobotTemplate extends IterativeRobot {
    Joystick ps3Joy = new Joystick(1); 
    Drive drive = new Drive();
    Cannon cannon = new Cannon();
    //CRecord record;
    
    /**
    * This function is run when the robot is first started up and should be
    * used for any initialization code.
    */
    public void robotInit() {
        ps3Joy.setAxisChannel(Joystick.AxisType.kX, Var.chanJoyDrive);
    }
    
    public void autonomous() {
        
    }

    /**
     * This function is called once each time the robot enters operator control. False
     */
    public void teleopPeriodic() {
            drive.run(ps3Joy);
            cannon.run(ps3Joy);
            //record.run(ps3Joy, cannon, drive);
    }
}
