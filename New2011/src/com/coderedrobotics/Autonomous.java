/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderedrobotics;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author austin
 */
public class Autonomous {

    private Drive drive;
    private LineTracker lineTracker;
    private Shoulder shoulder;
    private Claw claw;
    private SpeedController elbow;
    private Encoder elbowEncoder;
    private long startTime;
    private int phase = 0;

    public Autonomous(Drive drive, LineTracker lineTracker, Shoulder shoulder, Claw claw, SpeedController elbow, Encoder e) {
        this.drive = drive;
        this.lineTracker = lineTracker;
        this.shoulder = shoulder;
        this.claw = claw;
        this.elbow = elbow;
        startTime = System.currentTimeMillis();
    }
    private double timer = 0;

    public void step() {
        if (!lineTracker.isDone()) {
            lineTracker.step();
            claw.set(0.5);
            shoulder.up();
            if (elbowEncoder.getDistance() < 90) {
                elbow.set(.4125);
            } else {
                elbow.set(0);
            }
        } else {

            claw.set(-.5);
            if (timer == 0) {
                timer = currentTime();
            }
            if (currentTime() - timer < 1) { //change to actual encoder distance
                drive.setVector(-0.2, 0);
            } else {
                drive.setVector(timer, timer);
            }
        }
    }

    private double currentTime() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }
}
