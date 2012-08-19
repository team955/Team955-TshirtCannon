/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.*;

/**
 *
 * @author Fauzi
 */
public class CSolenoids 
{

    Solenoid solUp;
    Solenoid solDown;
    
    public CSolenoids(int solUpChannel, int solDownChannel)
    {
        solUp = new Solenoid(solUpChannel);
        solDown = new Solenoid(solDownChannel);
        solUp.set(false);
        solDown.set(true);
    }
    
    public void turnOn()
    {
        solUp.set(true);
        solDown.set(false);
    }
    
    public void turnOff()
    {
        solUp.set(false);
        solDown.set(true); 
    }
    
}
