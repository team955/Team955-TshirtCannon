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
 * This class is responsible for the "cannon" part of the t-shirt cannon.
 * It shoots shirt and raises or lowers the shirt shooter
 */

public class Cannon {
    CSolenoids solMoveTurret = new CSolenoids(Var.chanTurretMoveUpTShirt, Var.chanTurretMoveDownTShirt);
    CSolenoids solShootShirt = new CSolenoids(Var.chanSolShootUpTShirt, Var.chanSolShootDownTShirt);
    CSolenoids solFeedTurret = new CSolenoids(Var.chanSolUpChargeShirt, Var.chanSolDownChargeShirt);
    CButton btChargeTurret = new CButton();
    CButton btShootShirt = new CButton();
    CButton btAimUp = new CButton();
    CButton btAimDown = new CButton(); 
    Timer tSolTurretOff = new Timer();
    Timer tSolChargeTurret = new Timer();
    boolean isCharging = false;
  
    public void run(Joystick joy, Drive driver)
    {        
        btShootShirt.run(joy.getRawButton(Var.buttonShootShirt));
        btAimUp.run(joy.getRawButton(Var.buttonAimUp));
        btAimDown.run(joy.getRawButton(Var.buttonAimDown));
        btChargeTurret.run(joy.getRawButton(Var.buttonChargeShirt));
        
        
        if(btAimUp.gotPressed())
            solMoveTurret.turnOn();

        else if(btAimDown.gotPressed())
            solMoveTurret.turnOff();
        
        if(btChargeTurret.gotPressed() && Var.bShooting == false)
        {
            isCharging = true;
            solFeedTurret.turnOn();
            tSolChargeTurret.start();
        }
        
        if(tSolChargeTurret.get() > 4*DriverStation.getInstance().getAnalogIn(1))
        {
            isCharging = false;
            solFeedTurret.turnOff();
            tSolChargeTurret.stop();
            tSolChargeTurret.reset();
        }
	
        if(btShootShirt.gotPressed() && isCharging == false)
        {
            Var.bShooting = true;
            driver.setSpeed(Var.kickBackSpeed, -Var.kickBackSpeed);
            solShootShirt.turnOn();
            tSolTurretOff.start();
        }
		
        if(tSolTurretOff.get() > 0.25)
        {
            driver.setSpeed(0,0);
			
			if(tSolTurretOff.get() > 1)
			{	
				Var.bShooting = false;
				solShootShirt.turnOff();
				tSolTurretOff.stop();
				tSolTurretOff.reset();
			}
		}
    }
}
