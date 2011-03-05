/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderedrobotics;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 * @author Code Red
 */
public class LineTracker {

    private long startTime;
    private long duration = 3000;
    private Drive drive;
    private boolean b = true;
    private DigitalInput digitalInputLeft, digitalInputMiddle, digitalInputRight;

    public void step() {
        int i = ((digitalInputLeft.get() ? 1 : 0) * 4 + (digitalInputMiddle.get() ? 1 : 0) * 2 + (digitalInputRight.get() ? 1 : 0));
        switch (i) {
            case 7:
                System.out.println("Case 7");
                if (b) {
                    drive.setVector(0, -getLineFollowingSpeed() * 2);
                } else {
                    drive.setVector(0, getLineFollowingSpeed() * 2);
                }
                break;
            case 6:
                b = true;
                drive.setVector(0, -getLineFollowingSpeed() * 2);
                System.out.println("Case 6");
                break;
            case 5:
                System.out.println("Case 5");
                drive.setVector(getLineFollowingSpeed(), 0);
                break;
            case 4:
                System.out.println("Case 4");
                b = true;
                drive.setVector(getLineFollowingSpeed(), -getLineFollowingSpeed());
                break;
            case 3:
                System.out.println("Case 3");
                b = false;
                drive.setVector(0, getLineFollowingSpeed() * 2);
                break;
            case 2:
                System.out.println("Case 2");
                drive.setVector(0, 0);
                break;
            case 1:
                System.out.println("Case 1");
                b = false;
                drive.setVector(getLineFollowingSpeed(), getLineFollowingSpeed());
                break;
            case 0:
                System.out.println("Case 0");
                drive.setVector(-getLineFollowingSpeed() / 2, 0);
                break;
        }
    }

    public LineTracker(Drive controller, DigitalInput l, DigitalInput m, DigitalInput r) {
        startTime = System.currentTimeMillis();
        drive = controller;
        digitalInputLeft = l;
        digitalInputMiddle = m;
        digitalInputRight = r;
    }

    public double getLineFollowingSpeed() {
        if (startTime + duration > System.currentTimeMillis()) {
            return Wiring.LINE_FOLLOWING_SPEED / 1.5;
        }
        return Wiring.LINE_FOLLOWING_SPEED;
    }
}