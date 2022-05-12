package frc.tilt;

import com.ctre.phoenix.*;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

public class Tilt {
    VictorSPX leaderMotor;
    VictorSPX[] followerMotors;

    // constructor for leader motor IDs + follower motor IDs
    public Tilt(int leaderNumber, int[] followerNumbers, double nMultiplier) {
        leaderMotor = new VictorSPX(leaderNumber);

        followerMotors = new VictorSPX[followerNumbers.length];
        for (int index = 0; index < followerNumbers.length; index++) {
            followerMotors[index] = new VictorSPX(followerNumbers[index]);
        }

        setOutputMultipier(nMultiplier); // set new output of motors
    }

    // constructor for just a list of IDs ()
    public Tilt(int[] deviceNumbers, double nMultiplier) {
        for (int index = 0; index < deviceNumbers.length; index++) {
            VictorSPX newMotor = new VictorSPX(deviceNumbers[index]);

            if (index > 0) { // link all other motors to first motor in list
                newMotor.follow(leaderMotor); 
            } else // and the first motor is set as the lead motor
              leaderMotor = newMotor; 
        }

        setOutputMultipier(nMultiplier); // set new output of motors
    }

    // constructors for default motor output 0.5 (50%)
    public Tilt(int leadNumber, int[] followerNumbers) {
        this(leadNumber, followerNumbers, 0.5);
    }

    public Tilt(int[] deviceNumbers) {
        this(deviceNumbers, 0.5);
    }

    // just in case we need to set a different speed in the middle of the
    // performance
    public void setOutputMultipier(double nMultiplier) {

        // set motor speed from multiplier will hopefully return the OK ErrorCode
        // (ErrorCode.OK is good sign and means the speed was successfuly changed)
        if (leaderMotor.configPeakOutputForward(nMultiplier).equals(ErrorCode.OK)
                && leaderMotor.configPeakOutputReverse(nMultiplier).equals(ErrorCode.OK)) {
            System.out.println("hood motors good to go");
        } else {
            System.out.println("this robot does not vibe with the victor motors");
        }
    }

    // and 2 more functions if motors are turning opposite way of what we want them to
    public void reverseFollowerMotor(int... indexes) {
        for (int n = 0; n < indexes.length; n++) { // reverseMotor, but for each index motor in the list
            reverseFollowerMotor(indexes[n]);
        }
    }

    // i don't know if victor controllers work like this, but it's worth a shot
    public void setToAngle(double angle) {
        leaderMotor.set(ControlMode.Position, angle * (4096.0 / 360.0)); // convert from angle to the
                                                                         // 4096-units-per-rotation
    }

    public double getAnglePosition() {
        return leaderMotor.getSelectedSensorPosition() * (360.0 / 4096.0); // and vice versa here when reading from the
                                                                           // controller
    }
}