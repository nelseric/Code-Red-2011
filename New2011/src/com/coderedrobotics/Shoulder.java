/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderedrobotics;

import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author Developer
 */
public class Shoulder {

    private Solenoid upSolenoid, downSelenoid;
    private boolean state;

    public Shoulder(Solenoid up, Solenoid down) {
        upSolenoid = up;
        downSelenoid = down;
        state = false;
    }

    public boolean isUp() {
        return state;
    }

    public void up() {
        state = true;
    }

    public void down() {
        state = false;
    }

    public void toggle() {
        state = !state;
    }

    public void step() {
        upSolenoid.set(state);
        downSelenoid.set(!state);
    }
}
