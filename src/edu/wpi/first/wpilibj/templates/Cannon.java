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
 * Currently we do not know how it will work so this is all we have at the moment
 */
public class Cannon {
    CSolenoids solShootShirt = new CSolenoids(Var.chanSolUpTShirt, Var.chanSolDownTShirt);
    CSolenoids solChargeTank = new CSolenoids(Var.chanSolUpChargeShirt, Var.chanSolUpChargeShirt);
    CSolenoids solChargeTank2 = new CSolenoids(Var.chanSolUpChargeShirt2, Var.chanSolDownChargeShirt2);
    CButton bShootShirt = new CButton();
    CButton bReleaseAir = new CButton();
    
    public void run(Joystick joy)
    {        
        bShootShirt.run(joy, Var.buttonShootShirt);
        bReleaseAir.run(joy, Var.buttonReleaseAir);
        
        if(bShootShirt.get())
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
