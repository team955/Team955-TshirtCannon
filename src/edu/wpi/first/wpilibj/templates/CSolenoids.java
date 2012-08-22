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

/*
 * Purpose of this class is so that it can simply the job of making solenoids
 * and having to manualy set them indivdually on and off. So instead I just made
 * a Solenoid class with functions that do it for us :D
 */
public class CSolenoids 
{

    private Solenoid solUp;
    private Solenoid solDown;
    
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
