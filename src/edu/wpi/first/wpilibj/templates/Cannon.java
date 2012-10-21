/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.*;;

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
    CButton btAimTur = new CButton();
    CButton btChargeTmLower = new CButton(); 
    CButton btChargeTmHigher = new CButton(); 
    CButton btEnableKickBack = new CButton();
    Timer tSolTurretOff = new Timer();
    Timer tSolChargeTurret = new Timer();
    boolean isCharging = false;
    boolean bTurretUp = false;
    int iChargeFactor = 4;
    int iPrint = 0;
    String sChargeTm;
    CPrintDriver printDriver = new CPrintDriver();

    public void run(Joystick joy, Drive driver)
    {        
        btShootShirt.run(joy.getRawButton(Var.buttonShootShirt));
        btAimTur.run(joy.getRawButton(Var.buttonAimDown));
        btChargeTurret.run(joy.getRawButton(Var.buttonChargeShirt));
        btChargeTmLower.run(joy.getRawButton(Var.buttonChrgTmLower));
        btChargeTmHigher.run(joy.getRawButton(Var.buttonChrgTmHigher));
        btEnableKickBack.run(joy.getRawButton(Var.buttonJoyKickBack));
        
        if(btAimTur.gotPressed())
        {
            bTurretUp = !bTurretUp;
            solMoveTurret.set(bTurretUp);
        }
        
        if(btChargeTurret.gotPressed() && Var.bShooting == false)   // Charges turret 
        {
            iPrint = 1;
            isCharging = true;
            solFeedTurret.turnOn();
            tSolChargeTurret.start();
        }
       
        if(btChargeTmLower.gotPressed() && (iChargeFactor - 1) > 0) // Decreases charge time
            iChargeFactor--;
        
        if(btChargeTmHigher.gotPressed())   // Increases charge time
            iChargeFactor++;
        		
        if(tSolChargeTurret.get() > iChargeFactor-1)  // Chargin turret for specified amount of time
        {
            iPrint = 2;
            isCharging = false;
            solFeedTurret.turnOff();
            tSolChargeTurret.stop();
            tSolChargeTurret.reset();
        }
	
        if(btShootShirt.gotPressed() && isCharging == false && solMoveTurret.getUp())    // Shoots shirt
        {
            Var.bShooting = true;
            
            if(btEnableKickBack.getSwitch())    // Creates fake kickback
                driver.setSpeed(Var.kickBackSpeed, -Var.kickBackSpeed);
            
            solShootShirt.turnOn();
            tSolTurretOff.start();
            iPrint = 0;
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
        
        // Printing to Driver Station 
        
        sChargeTm = Integer.toString(iChargeFactor);
        printDriver.print(Var.iChargeFactorLine, "Charge Factor: " + sChargeTm);
        
        if(btEnableKickBack.getSwitch())
            printDriver.print(Var.iKickBackLine, "Kickback: Enabled");
        
        else if(!btEnableKickBack.getSwitch())
            printDriver.print(Var.iKickBackLine, "Kickback: Disabled");
        
        printChargeStatus(iPrint);
    }
    
    private void printChargeStatus(int i)
    {
        if(i == 0)
            printDriver.print(Var.iChargeStatusLine, "Turret Not Charged");
        
        else if(i == 1)
            printDriver.print(Var.iChargeStatusLine, "Charging Turret");
        
        else if(i == 2)
            printDriver.print(Var.iChargeStatusLine, "Turret Charged!!!");
    }
    
    public void set(boolean bUp)
    {
        if(bUp)
            solMoveTurret.turnOn();
        
        else
            solMoveTurret.turnOff();
    }
}
