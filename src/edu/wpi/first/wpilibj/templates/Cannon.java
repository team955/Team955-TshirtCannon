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
    CSolenoids solTshirt = new CSolenoids(Var.chanSolUpTShirt, Var.chanSolDownTShirt);
    CSolenoids solTshirt2 = new CSolenoids(Var.chanSolUpTShirt2, Var.chanSolDownTShirt2);
    CSolenoids solChargeShirt = new CSolenoids(Var.chanSolUpChargeShirt, Var.chanSolDownChargeShirt);
    CButton bShootShirt = new CButton();
    CButton bReleaseAir = new CButton();
    
    public void run(Joystick joy)
    {        
        bShootShirt.run(joy, Var.buttonShootShirt);
        bReleaseAir.run(joy, Var.buttonReleaseAir);
        
        if(bShootShirt.get())
        {
            solChargeShirt.turnOn();
            solTshirt2.turnOn();
            solTshirt.turnOn();
        }
        
        if(bReleaseAir.get())
        {
            solTshirt.turnOff();
            solTshirt2.turnOff();
            solChargeShirt.turnOff();
        }
    }
}
