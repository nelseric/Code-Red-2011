/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.coderedrobotics;

import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author austin
 */
public class Minibot {
    private Solenoid launcher;
    public Minibot(Solenoid s) {
        launcher = s;
    }
    public void deploy() {
        launcher.set(true);
    }

}
