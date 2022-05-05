package frc.tilt;

import com.ctre.phoenix.*;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

public class PWMHood{
    VictorSPX motor;
    double multiplier = 0.1;

    public PWMHood(int deviceNumber) { motor = new VictorSPX(deviceNumber); initMotorSpeed(); } // constructor for defualt motor output
    public PWMHood(int deviceNumber, double nMultiplier) { motor = new VictorSPX(deviceNumber); multiplier = nMultiplier; initMotorSpeed(); } // constructor for custom motor speed 
    
    private void initMotorSpeed(){ // set motor speed from multiplier
        if( !(motor.configPeakOutputForward(multiplier).equals(ErrorCode.OK) && motor.configPeakOutputReverse(multiplier).equals(ErrorCode.OK)) ){
            System.out.println("this robot does not vibe with the victor motors");
        } else {
            System.out.println("hood motors good to go");
        }
    }

    public void setToAngle(double angle){
        motor.set(ControlMode.Position, angle * (4096.0 / 360.0)); // convert from angle to the 4096-units-per-rotation
    }

    public double getPosition() {
        return motor.getSelectedSensorPosition() * (360.0  / 4096.0); // and vice versa here
    }

}