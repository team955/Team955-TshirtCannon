/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * This class will hopefully be responsible for recording the robots 
 * movements and "replaying" them.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.*;
//test
/**
 *
 * @author Fauzi
 */
public class CRecord {

    Timer trRecord = new Timer();
    Timer trReplay = new Timer();
    LinkedListDouble dLsTimerDrive = new LinkedListDouble();
    LinkedListDouble dLsTimerAiming = new LinkedListDouble();
    LinkedListDouble dLsX = new LinkedListDouble();
    LinkedListDouble dLsY = new LinkedListDouble();
    LinkedListBoolean dLsAiming = new LinkedListBoolean();
    boolean bAimUpBefore = false;
    boolean bAimDownBefore = false;
    boolean bRecord = false;
    double iXBefore = 0;
    double iYBefore = 0;
    CButton btRecord = new CButton();
    CButton btReplay = new CButton();
    CButton btClear = new CButton();
   
    public void run(Joystick joy, Cannon cannon, Drive driver)
    {
        btRecord.run(joy.getRawButton(Var.buttonRecord));
        btReplay.run(joy.getRawButton(Var.buttonReplay));
        btClear.run(joy.getRawButton(Var.buttonClearList));
        
        if(btClear.gotPressed())
                clearAll();
        
        if(btRecord.getSwitch())
        {   
            System.out.println("Record");
            trRecord.start();

            if(joy.getX() != iXBefore && joy.getY() != iYBefore)
            {
                iXBefore = joy.getX();
                iYBefore = joy.getY();
                dLsTimerDrive.add(trRecord.get());
                dLsX.add(joy.getX());
                dLsY.add(joy.getY());
            }

            if(cannon.btAimUp.gotPressed() != bAimUpBefore)
            {
                    dLsTimerAiming.add(trRecord.get());
                    bAimUpBefore = !bAimUpBefore;
                    dLsAiming.add(bAimUpBefore);
            }
        }
		
        else if(btReplay.getSwitch())
        {
            System.out.println("Replaying");
            double x, y;
            
            trRecord.stop();
            trReplay.start();
            
            if(trReplay.get() < trRecord.get())
            {
                System.out.println("Working");
                if(trReplay.get() >= dLsTimerDrive.getNext(false))
                {
                    dLsTimerDrive.getNext(true);
                    y = dLsY.getNext(true) * Math.abs(dLsY.getNext(true));
                    x = -dLsX.getNext(true) * Math.abs(dLsX.getNext(true)); 
                    driver.setSpeed((-y+x), (y+x));
                }

                if(trReplay.get() >= dLsTimerAiming.getNext(false))
                {
                    dLsTimerAiming.getNext(true);
                    cannon.solMoveTurret.set(dLsAiming.getNext(true));
                }
            }
			
            else
            {
                    driver.setSpeed(0,0);
                    trReplay.stop();
                    trReplay.reset();
            }
        }
    }
    
    private void clearAll() // To clear the memory so you can record something else
    {
        trRecord.stop();
        trRecord.reset();
        trReplay.stop();
        trReplay.reset();
        dLsTimerDrive.deleteAll();
        dLsTimerAiming.deleteAll();
        dLsX.deleteAll();
        dLsY.deleteAll();
        dLsAiming.deleteAll();
        bAimUpBefore = false;
        bAimDownBefore = false;
        bRecord = false;
        iXBefore = 0;
        iYBefore = 0;
    }
}
