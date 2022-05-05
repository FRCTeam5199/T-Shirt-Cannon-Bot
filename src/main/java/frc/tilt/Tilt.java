package frc.tilt;

import java.util.*;

import com.ctre.phoenix.*;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;


public class Tilt {
    List<VictorSPX> motors = new ArrayList<VictorSPX>();
    
    // constructor for custom motor output + multiple motors
    public Tilt ( int[] deviceNumbers, double nMultiplier ) {
        for (int index = 0; index < deviceNumbers.length; index++) {
            motors.add(new VictorSPX(deviceNumbers[index]));

            if (index > 0) { // link all other motors to first motor in list
                motors.get(index).follow(motors.get(0)); 
            }
        }
        
        setMotorSpeed(nMultiplier); // set new output of motors
    } 

    // constructor for default motor output 0.5 (50%)
    public Tilt ( int[] deviceNumbers ) {
        this(deviceNumbers, 0.5);
    } 
    
    

    // the lead motor is just the first item of the motor ArrayList that every other motor is linked to, but I wanted a simple name for it,
    // instead of "motors.get(0)"
    public VictorSPX leadMotor () {
        return motors.get(0);
    }

    // just in case we need to set a different gear in the middle of the performance
    public void setMotorSpeed ( double nMultiplier ) {

        // set motor speed from multiplier -> returns error codes (0 is the okay and good code that means it works)
        if( !(motors.get(0).configPeakOutputForward(nMultiplier).equals(ErrorCode.OK) && motors.get(0).configPeakOutputReverse(nMultiplier).equals(ErrorCode.OK)) ){
            System.out.println("this robot does not vibe with the victor motors");
        } else {
            System.out.println("hood motors good to go");
        }
    }
   
    // and 2 more if motors are australian (turning opposite way of what we want to be)
    public void reverseMotor ( int index ) {
        motors.get(index).setInverted( !motors.get(index).getInverted() ); // toggle state of motor (normal -> reverse, reverse -> normal)
    }
    public void reverseMotor ( int[] indexes ) {
        for ( int n = 0; n < indexes.length; n++ ) { // reverseMotor, but for each index motor in the list
            reverseMotor( indexes[n] );
        }
    }



    // i don't know if victor controllers work like this, but it's worth a shot
    public void setToAngle ( double angle ){ 
        leadMotor().set(ControlMode.Position, angle * (4096.0 / 360.0)); // convert from angle to the 4096-units-per-rotation
    }
    public double getPosition () { 
        return leadMotor().getSelectedSensorPosition() * (360.0  / 4096.0); // and vice versa here when reading from the controller
    }
}