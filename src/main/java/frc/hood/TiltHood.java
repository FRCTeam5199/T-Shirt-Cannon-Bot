package frc.hood;

import com.ctre.phoenix.*;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.Constants;

public class TiltHood {
    // hood/tilt motor for angle
    VictorSPX tiltMotor;
    int angleIndex = 2; //default to 60 degrees
    
    // solenoids for shooting t-shirts
    public static DoubleSolenoid shooterSolenoid;
    public static Solenoid reserveSolenoid;

    //compressor
    public static Compressor compressor = new Compressor(Constants.COMPRESSOR_ID, PneumaticsModuleType.REVPH);
    


    // constructor for the solenoids & tilt motor, with provided IDs
    // TODO Find the device IDs (not so important at the moment)
    public TiltHood(int tiltMotorID, int shooterSolenoidID1, int shooterSolenoidID2, int reserveSolenoidID){
        // initialize tilt motor & shooters; link them to device IDs
        tiltMotor = new VictorSPX(tiltMotorID);
        this.setToAngle(Constants.ANGLE_POSITIONS[angleIndex]);

        shooterSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, shooterSolenoidID1, shooterSolenoidID2);
        reserveSolenoid = new Solenoid(PneumaticsModuleType.REVPH, reserveSolenoidID);

        // set speed of motor to 0.5 (50%)
        if (tiltMotor.configPeakOutputForward(0.5).equals(ErrorCode.OK)
                && tiltMotor.configPeakOutputReverse(0.5).equals(ErrorCode.OK)) {
            System.out.println("hood motors good to go");
        } else {
            System.out.println("this robot does not vibe with the victor motors");
        }
    }

    // i don't know if victor controllers work like this, but it's worth a shot
    public void setToAngle(double angle) {
        tiltMotor.set(ControlMode.Position, angle * (4096.0 / 360.0)); // convert from angle to the
                                                                         // 4096-units-per-rotation
    }

    public double getAnglePosition() {
        return tiltMotor.getSelectedSensorPosition() * (360.0 / 4096.0); // and vice versa here when reading from the
                                                                           // controller
    }

    // and for moving between the set ANGLE_POSITIONS, we have these 2 functions
    public void setToDefault(int space){
        angleIndex = space;
        this.setToAngle(Constants.ANGLE_POSITIONS[space]);
    }

    public void moveDefault(int spaces){
        if( (angleIndex + spaces > -1) && (angleIndex + spaces < Constants.ANGLE_POSITIONS.length) ){ // make sure angle moved to exists, and isn't out of bounds
            angleIndex += spaces;
            this.setToAngle(Constants.ANGLE_POSITIONS[angleIndex]);

        } else { System.out.println("cannot move anymore, don't break me :("); } // console indication that this crap don't work
    }
    
    // functions from previous shooter class
    public void fireShot() {
        System.out.println("firing");
        reserveSolenoid.set(true); //inverted
        shooterSolenoid.set(Value.kForward);
    } 

    public void resetShooter() {
        //System.out.println("reset shooter");
        shooterSolenoid.set(Value.kReverse);
    }

    public void openReserve() {
        shooterSolenoid.set(Value.kReverse);
        reserveSolenoid.set(false); //inverted
    }

    public void closeReserve() {
        reserveSolenoid.set(true); //inverted
    }

    public void closeAll() {
        shooterSolenoid.set(Value.kReverse);
        reserveSolenoid.set(true); //inverted
    }
}
