/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderedrobotics;

import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author austin
 */
public class AutoDrive {

    private Drive drive;
    private LineTracker lineTracker;
    private Shoulder shoulder;
    private Claw claw;
    private SpeedController elbow;
    private long startTime;
    private int phase = 0;

    public AutoDrive(Drive drive, LineTracker lineTracker, Shoulder shoulder, Claw claw, SpeedController elbow) {
        this.drive = drive;
        this.lineTracker = lineTracker;
        this.shoulder = shoulder;
        this.claw = claw;
        this.elbow = elbow;
        startTime = System.currentTimeMillis();
    }

    public void step() {
        
    }

    private double currentTime() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }
}
