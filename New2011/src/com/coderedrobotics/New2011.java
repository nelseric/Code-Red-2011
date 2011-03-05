/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package com.coderedrobotics;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class New2011 extends IterativeRobot {

    Compressor compressor;
    Gamepad armGamepad, driverGamepad;
    Shoulder shoulder;
    Claw claw;
    Encoder elbowEncoder;
    CANJaguar clawJaguar, elbowJaguar;
    DigitalInput open, closed;
    PIDController elbow;
    Drive drive;
    Autonomous autoDrive;

    public void robotInit() {
        compressor = new Compressor(
                Wiring.COMPRESSOR_PRESSURE_IN, Wiring.COMPRESSOR_OUT);
        compressor.start();
        driverGamepad = new Gamepad(1);
        armGamepad = new Gamepad(2);

        elbowEncoder = new Encoder(
                Wiring.ELBOW_ENCODER_A, Wiring.ELBOW_ENCODER_B, true);
        elbowEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        elbowEncoder.setDistancePerPulse(.2);

        try {
            clawJaguar = new CANJaguar(Wiring.CLAW_JAGUAR);
            elbowJaguar = new CANJaguar(Wiring.ELBOW_JAGUAR);

            drive = new Drive(new CANJaguar(Wiring.DRIVE_L_A),
                    new CANJaguar(Wiring.DRIVE_L_B),
                    new CANJaguar(Wiring.DRIVE_R_A),
                    new CANJaguar(Wiring.DRIVE_R_B));
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
        elbow = new PIDController(0, 0, 0, elbowEncoder, elbowJaguar);
        open = new DigitalInput(Wiring.CLAW_SWITCH_OPEN);
        closed = new DigitalInput(Wiring.CLAW_SWITCH_CLOSE);
        shoulder = new Shoulder(new Solenoid(Wiring.SHOULDER_UP),
                new Solenoid(Wiring.SHOULDER_DOWN));
        shoulder.down();
        claw = new Claw(clawJaguar, open, closed);
    }

    public void autonomousInit() {
        autoDrive = new Autonomous(drive,
                new LineTracker(drive,
                    new DigitalInput(Wiring.LEFT_LINE_SENSOR_PORT),
                    new DigitalInput(Wiring.MIDDLE_LINE_SENSOR_PORT),
                    new DigitalInput(Wiring.RIGHT_LINE_SENSOR_PORT)),
                shoulder, claw, elbowJaguar, elbowEncoder);
    }

    public void autonomousPeriodic() {
        autoDrive.step();

        
        
    }

    public void teleopInit() {
        elbowEncoder.start();
        elbowEncoder.reset();
        elbow.enable();
    }
    /**
     * This function is called periodically during operator control
     */
    boolean shoulderToggling = false;

    public void teleopPeriodic() {
        System.out.println(elbow.getError());
        if (armGamepad.getLeftBumper()) {
            if (!shoulderToggling) {
                shoulder.toggle();
                shoulderToggling = true;
            }
        } else {
            shoulderToggling = false;
        }

        try {

            drive.setLeft(
                    driverGamepad.getLeftY());
            drive.setRight(
                    driverGamepad.getRightY());

            //System.out.println("Left: "+driverGamepad.getLeftY()+"\tRight: "+driverGamepad.getRightY());
            //System.out.println("Left X: "+drive.getLeft()+"\tRight X: "+drive.getRight());

            double elbowIn = armGamepad.getRightY();
            if (!armGamepad.getRightBumper()) {
                elbowIn = elbowIn / 2;
            }
            elbowJaguar.setX(elbowIn);

            claw.set(armGamepad.getLeftY());
            claw.step();

        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }

        shoulder.step();
    }
}
