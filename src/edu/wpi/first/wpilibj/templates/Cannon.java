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
    CSolenoids solShootShirt = new CSolenoids(Var.chanSolUpTShirt, Var.chanSolDownTShirt);
    CSolenoids solChargeTank = new CSolenoids(Var.chanSolUpChargeShirt, Var.chanSolUpChargeShirt);
    CSolenoids solChargeTank2 = new CSolenoids(Var.chanSolUpChargeShirt2, Var.chanSolDownChargeShirt2);
    CButton bShootShirt = new CButton();
    CButton bReleaseAir = new CButton();
    CButton bAimUp = new CButton();
    CButton bAimDown = new CButton(); 
    Victor mShooter = new Victor(Var.chanVicShooter, Var.slotVicShooter);
    
    public void run(Joystick joy)
    {        
        bShootShirt.run(joy.getRawButton(Var.buttonShootShirt));
        bReleaseAir.run(joy.getRawButton(Var.buttonReleaseAir));
        bAimUp.run(joy.getRawButton(Var.buttonAimUp));
        bAimDown.run(joy.getRawButton(Var.buttonAimDown));
        
        while( bAimUp.isHeld() || bAimDown.isHeld())
        {
            if(bAimUp.isHeld())
                mShooter.set(.1);
        
            else if(bAimDown.isHeld())
                mShooter.set(-.1);
        }
  
        mShooter.set(0);
        
        if(bShootShirt.gotPressed())
        {
            solChargeTank.turnOn();
            solChargeTank2.turnOn();
            solShootShirt.turnOn();
            bShootShirt.set(false);
        }
        
        solChargeTank.turnOff();
        solChargeTank2.turnOff();
        solShootShirt.turnOff();
    }
}
