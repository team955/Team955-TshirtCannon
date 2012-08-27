/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * This class will hopefully be responsible fore recording the robots 
 * movements and "replaying" them.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.*;

/**
 *
 * @author Fauzi
 */
public class CRecord {
    
    Timer timer;
    LinkedListDouble iTimerList = new LinkedListDouble();
    LinkedListDouble iXList = new LinkedListDouble();
    LinkedListDouble iYList = new LinkedListDouble();
    double iXBefore = 0;
    double iYBefore = 0;
    CButton bRecord = new CButton();
    CButton bReplay = new CButton();
    
    public void run(Joystick joy, Cannon cannon, Drive driver)
    {
        bRecord.run(joy.getRawButton(Var.buttonRecord));
        bReplay.run(joy.getRawButton(Var.buttonReplay));
        
        if(bRecord.gotPressed())
        {
            
        }
    }
}
