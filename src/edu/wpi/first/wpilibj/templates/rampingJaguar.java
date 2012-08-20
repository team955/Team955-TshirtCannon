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
 * This class extends the Jaguar class. Its purpose is so that the motors
 * would not just shoot off at the desired speed and damage the motor but 
 * instead increase at a safe rate.
 */
public class rampingJaguar extends Jaguar {
    
    double current;
    double max = .1;
    double value; 
        public rampingJaguar (int slot, int channel ) {
            super(slot,channel);
        }
        
        public void ramp(double target) {
            current = get();
            if (target > (current + max)) {
                value  = current + max;
            }
            else if (target < (current - max)) {
                value = current - max;
            }
            else {
                value = target;
            }
            set(value);
        }
}
