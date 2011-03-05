/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderedrobotics;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author Developer
 */
public class Gamepad extends Joystick {

    public Gamepad(int port) {
        super(port);
    }

    public double getLeftX() {
        return this.getRawAxis(1);
    }

    public double getLeftY() {
        return this.getRawAxis(2);
    }

    public double getRightX() {
        return this.getRawAxis(4);
    }

    public double getRightY() {
        return -this.getRawAxis(5);
    }

    public double getTriggerAxis() {
        return this.getRawAxis(3);
    }

    public double getDpadX() {
        return this.getRawAxis(6);
    }

    public double getDpadY() {
        return this.getRawAxis(7);
    }

    public boolean getAButton() {
        return this.getRawButton(1);
    }

    public boolean getBButton() {
        return this.getRawButton(2);
    }

    public boolean getXButton() {
        return this.getRawButton(3);
    }

    public boolean getYButton() {
        return this.getRawButton(4);
    }

    public boolean getBackButton() {
        return this.getRawButton(7);
    }

    public boolean getStartButton() {
        return this.getRawButton(8);
    }

    public boolean getLeftAnalogStickButton() {
        return this.getRawButton(9);
    }

    public boolean getRightAnalogStickButton() {
        return this.getRawButton(10);
    }

    public boolean getLeftBumper() {
        return this.getRawButton(5);
    }

    public boolean getRightBumper() {
        return this.getRawButton(6);
    }
}
