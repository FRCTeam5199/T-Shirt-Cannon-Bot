package frc.tilt;

import com.ctre.phoenix.*;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

import java.util.*;

public class Tilt{
    List<VictorSPX> motors = new ArrayList<VictorSPX>();
    
    public Tilt(int[] deviceNumbers, double nMultiplier) { // constructor for custom motor output + multiple motors
        for (int index = 0; index < deviceNumbers.length; index++) {
            motors.add(new VictorSPX(deviceNumbers[index]));

            if (index > 0) { // link all other motors to first motor in list
                motors.get(index).follow(motors.get(0)); 
            }
        }
        
        setMotorSpeed(nMultiplier); // set new output of motors
    } 

    public Tilt(int[] deviceNumbers) { // constructor for default motor output 0.1
        this(deviceNumbers, 0.1);
    } 
    


    private void setMotorSpeed(double nMultiplier){ // set motor speed from multiplier -> returns error codes (0 is the okay and good code that means it works)
        
        if( !(motors.get(0).configPeakOutputForward(nMultiplier).equals(ErrorCode.OK) && motors.get(0).configPeakOutputReverse(nMultiplier).equals(ErrorCode.OK)) ){
            System.out.println("this robot does not vibe with the victor motors");
        } else {
            System.out.println("hood motors good to go");
        }
    }

    public VictorSPX leadMotor(){ // the "master" motor is just the first one on the list
        return motors.get(0);
    }



    public void setToAngle(double angle){
        leadMotor().set(ControlMode.Position, angle * (4096.0 / 360.0)); // convert from angle to the 4096-units-per-rotation
    }

    public double getPosition() {
        return leadMotor().getSelectedSensorPosition() * (360.0  / 4096.0); // and vice versa here
    }
}