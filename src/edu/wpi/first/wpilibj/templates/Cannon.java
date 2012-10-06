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
    CButton btChargeTmLower = new CButton(); 
    CButton btChargeTmHigher = new CButton(); 
    CButton btEnableKickBack = new CButton();
    Timer tSolTurretOff = new Timer();
    Timer tSolChargeTurret = new Timer();
    boolean isCharging = false;
    int iChargeFactor = 4;
    String sChargeTmLeft;
    String sChargeTm;
    CPrintDriver printDriver = new CPrintDriver();

    public void run(Joystick joy, Drive driver)
    {        
        btShootShirt.run(joy.getRawButton(Var.buttonShootShirt));
        btAimUp.run(joy.getRawButton(Var.buttonAimUp));
        btAimDown.run(joy.getRawButton(Var.buttonAimDown));
        btChargeTurret.run(joy.getRawButton(Var.buttonChargeShirt));
        btChargeTmLower.run(joy.getRawButton(Var.buttonChrgTmLower));
        btChargeTmHigher.run(joy.getRawButton(Var.buttonChrgTmHigher));
        btEnableKickBack.run(joy.getRawButton(Var.chanJoyKickBack));
        
        if(btAimUp.gotPressed())
            solMoveTurret.turnOn();

        else if(btAimDown.gotPressed())
            solMoveTurret.turnOff();
        
        if(btChargeTurret.gotPressed() && Var.bShooting == false)   // Charges turret 
        {
            isCharging = true;
            solFeedTurret.turnOn();
            tSolChargeTurret.start();
        }
        
        //*DriverStation.getInstance().getAnalogIn(1);
       
        if(btChargeTmLower.gotPressed() && (iChargeFactor - 1) > 0) // Increases charge time
            iChargeFactor--;
        
        if(btChargeTmHigher.gotPressed())   // Decreases charge time
            iChargeFactor++;
         
        sChargeTm = Integer.toString(iChargeFactor);
        sChargeTmLeft = Double.toString(tSolChargeTurret.get());

        printDriver.print(2, "Charge Factor: " + sChargeTm);
        printDriver.print(3, "Charge time Remaining: " + sChargeTmLeft);
        
        if(btEnableKickBack.getSwitch())
            printDriver.print(4, "Kickback Status: Enabled");
        
        else if(!btEnableKickBack.getSwitch())
            printDriver.print(4, "Kickback Status: Disabled");
        
//        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser2, 1, "Charge Factor: " + sChargeTm);
//        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser3, 1, "Charge time Remaining: " + sChargeTmLeft);
//        DriverStationLCD.getInstance().updateLCD();
		
        if(tSolChargeTurret.get() > iChargeFactor)  // Chargin turret for specified amount of time
        {
            isCharging = false;
            solFeedTurret.turnOff();
            tSolChargeTurret.stop();
            tSolChargeTurret.reset();
        }
	
        if(btShootShirt.gotPressed() && isCharging == false)    // Shoots shirt
        {
            if(btEnableKickBack.getSwitch())    // Creates fake kickback
                driver.setSpeed(Var.kickBackSpeed, -Var.kickBackSpeed);
            
            Var.bShooting = true;
            solShootShirt.turnOn();
            tSolTurretOff.start();
        }
		
        if(tSolTurretOff.get() > 0.25 && btEnableKickBack.getSwitch())  // Sets speed back to 0
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
