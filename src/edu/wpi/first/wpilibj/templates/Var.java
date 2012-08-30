/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Fauzi
 */

/*
 * This class has all the variables that are used the set the 
 * channels, slots or speed parameters for any object in the t-shirt cannon.
 * We have a seperate class just so we can change the channel more easily rather
 * than seaching through the class itself. It currently has all variables set 
 * to 0 because we do not know the channels at this moment
 */
public class Var {
    // TODO: Set all of these numbers to corresponding channels, slot, buttons etc...
    
    // PWM
    static final int chanVicShooter = 0;
    static final int slotVicShooter = 0;
    static final int chanVicDriveRight = 0;
    static final int slotVicDriveRight = 0;
    static final int chanVicDriveLeft = 0;
    static final int slotVicDriveLeft = 0;
    static final int chanSolUpTShirt = 0;
    static final int chanSolDownTShirt = 0;
    static final int chanSolUpChargeShirt = 0;
    static final int chanSolDownChargeShirt = 0;
    static final int chanSolUpChargeShirt2 = 0;
    static final int chanSolDownChargeShirt2 = 0;
    
    // Joysticks and buttons
    static final int buttonRecord = 0;
    static final int buttonReplay = 0;
    static final int buttonShootShirt = 0;
    static final int buttonReleaseAir = 0;
    static final int buttonAimUp = 0;
    static final int buttonAimDown = 0;
    static final int buttonClearList = 0;
    static final int buttonPauseRecord = 0;
    
    // Other
    static final double jagurRampSpeed = 0.1;
    static final int chanJoyDrive = 0;
    static final double turretSpeed = 0.1;
}
