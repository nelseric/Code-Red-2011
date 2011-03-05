/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.coderedrobotics;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author Developer
 */
public class Elbow {
    PIDSource encoder;
    SpeedController motor;
    PIDController pid;
    
    public Elbow(PIDSource encoder, SpeedController motor, double kP, double kI, double kD){
        this.encoder = encoder;
        this.motor = motor;
        pid = new PIDController(kP, kI, kD, encoder, motor);
    }

}
