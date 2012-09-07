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
    //CSolenoids solChargeTank = new CSolenoids(Var.chanSolUpChargeShirt, Var.chanSolUpChargeShirt);
    //CSolenoids solChargeTank2 = new CSolenoids(Var.chanSolUpChargeShirt2, Var.chanSolDownChargeShirt2);
    CButton btShootShirt = new CButton();
    CButton btAimUp = new CButton();
    CButton btAimDown = new CButton(); 
    CSolenoids mShooter = new CSolenoids(Var.chanSolUpTShirt, Var.chanSolDownTShirt);
    
    public void run(Joystick joy)
    {        
        btShootShirt.run(joy.getRawButton(Var.buttonShootShirt));
        btAimUp.run(joy.getRawButton(Var.buttonAimUp));
        btAimDown.run(joy.getRawButton(Var.buttonAimDown));
        
        while( btAimUp.isHeld() || btAimDown.isHeld())
        {
            if(btAimUp.isHeld())
                mShooter.turnOn();
        
            else if(btAimDown.isHeld())
                mShooter.turnOff();
        }
  
        mShooter.turnOff();
        
        if(btShootShirt.gotPressed())
        {
            //solChargeTank.turnOn();
            //solChargeTank2.turnOn();
            solShootShirt.turnOn();
            btShootShirt.set(false);
        }
        
        //solChargeTank.turnOff();
        //solChargeTank2.turnOff();
        solShootShirt.turnOff();
    }
}
