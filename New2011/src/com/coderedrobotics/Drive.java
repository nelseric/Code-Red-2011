/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coderedrobotics;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.can.CANTimeoutException;

/**
 *
 * @author Developer
 */
public class Drive {

    CANJaguar leftA;
    CANJaguar leftB;
    CANJaguar rightA;
    CANJaguar rightB;

    public Drive(CANJaguar leftA, CANJaguar leftB,
            CANJaguar rightA, CANJaguar rightB) {
        this.leftA = leftA;
        this.leftB = leftB;
        this.rightA = rightA;
        this.rightB = rightB;
    }

    public void setLeft(double magnitude) throws CANTimeoutException {
        leftA.setX(magnitude);
        leftB.setX(magnitude);
    }

    public void setRight(double magnitude) throws CANTimeoutException {
        rightA.setX(magnitude);
        rightB.setX(magnitude);
    }

    public String getLeft(){
        try{
        return "A: "+leftA.getX()+"\tB: "+leftB.getX();
        } catch (CANTimeoutException cante) {

        }
        return "error";
    }

    public String getRight(){
        try{
        return "A: "+rightA.getX()+"\tB: "+rightB.getX();
        } catch (CANTimeoutException cante) {

        }
        return "error";
    }
    public void setVector(double magnitude, double direction) {
        
        double left = magnitude + (direction * 2);
        double right = magnitude - (direction * 2);

        if (right > 1) {
            right = 1;
            left = left/right;
        }
        if (right < -1) {
            right = -1;
            left = left/Math.abs(right);
        }
        if (left > 1) {
            left = 1;
            right = right/left;
        }
        if (left < -1) {
            left = -1;
            right = right/Math.abs(left);
        }

        try {
            setLeft(left);
            setRight(right);
        } catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }
    }
}
