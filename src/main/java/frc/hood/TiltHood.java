package frc.hood;

import com.ctre.phoenix.*;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.*;

public class TiltHood {
    // hood/tilt motor for angle
    VictorSPX tiltMotor;
    
    // solenoids for shooting t-shirts
    public static Solenoid shooterSolenoid;
    public static Solenoid reserveSolenoid;

    // constructor for the solenoids & tilt motor, with provided IDs
    // TODO Find the device IDs (not so important at the moment)
    public TiltHood(int tiltMotorID, int shooterSolenoidID, int reserveSolenoidID){
        // initialize tilt motor & shooters; link them to device IDs
        tiltMotor = new VictorSPX(tiltMotorID);

        shooterSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, shooterSolenoidID);
        reserveSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, reserveSolenoidID);

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

    // functions from previous shooter class
    public static void fireShot() {
        reserveSolenoid.set(false);
        shooterSolenoid.set(true);
    } 

    public static void resetShooter() {
        shooterSolenoid.set(false);
    }

    public static void openReserve() {
        shooterSolenoid.set(false);
        reserveSolenoid.set(true);
    }

    public static void closeReserve() {
        reserveSolenoid.set(false);
    }
}
