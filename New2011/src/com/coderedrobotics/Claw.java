/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderedrobotics;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 *
 * @author Developer
 */
public class Claw {

    private CANJaguar motor;
    private DigitalInput open, close;
    private double speed;

    public Claw(CANJaguar motor, DigitalInput open, DigitalInput close) {
        this.motor = motor;
        this.open = open;
        this.close = close;

    }

    public boolean isOpen() {
        return !open.get();
    }

    public boolean isClosed() {
        return !close.get();
    }

    public void set(double speed) {
        this.speed = speed;
    }

    public void step() {
        double clawIn = speed;
        if (!open.get()) {
            if (clawIn < 0) {
                clawIn = 0;
            }
        } else if (!close.get()) {
            if (clawIn > 0) {
                clawIn = 0;
            }
        }
        try {
            motor.setX(clawIn);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
}
