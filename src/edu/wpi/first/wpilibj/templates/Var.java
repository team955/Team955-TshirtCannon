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
    static final int chanVicDriveRight = 9;
    static final int chanVicDriveLeft = 8;
    static final int chanSolShootUpTShirt = 1;
    static final int chanSolShootDownTShirt = 2;
	static final int chanTurretMoveUpTShirt = 3;
    static final int chanTurretMoveDownTShirt = 4;
    static final int chanSolUpChargeShirt = 0;
    static final int chanSolDownChargeShirt = 0;
    
    // Joysticks and buttons
    //static final int buttonRecord = 1;
    //static final int buttonReplay = 3;
    static final int buttonShootShirt = 4;
    static final int buttonAimUp = 1;
    static final int buttonAimDown = 3;
    static final int buttonChargeShirt = 8;
    //static final int buttonClearList = 4;
    //static final int buttonPauseRecord = 2;
    
    // Other
    static final double jagurRampSpeed = 0.1;
    static final int chanJoyDrive = 3;
    static final double turretSpeed = 0.1;
}
