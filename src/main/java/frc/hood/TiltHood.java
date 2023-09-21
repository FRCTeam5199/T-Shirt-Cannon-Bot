package frc.hood;

import com.ctre.phoenix.*;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.Constants;
import frc.misc.ISubsystem;

public class TiltHood implements ISubsystem {
    // hood/tilt motor for angle
    //VictorSPX oldTiltMotor;
    CANSparkMax tiltMotor;
    SparkMaxPIDController tiltMotorController;
    int angleIndex = 2; //default to 60 degrees
    
    // solenoids for shooting t-shirts
    public static DoubleSolenoid shooterSolenoid;
    public static Solenoid reserveSolenoid;
    ShuffleboardTab tab = Shuffleboard.getTab("Tilt Hood");
    NetworkTableEntry motorRotations = tab.add("Motor rotations", 0).getEntry();

    //compressor
    public static Compressor compressor = new Compressor(Constants.COMPRESSOR_ID, PneumaticsModuleType.REVPH);

    // constructor for the solenoids & tilt motor, with provided IDs
    // TODO Find the device IDs (not so important at the moment)
    public TiltHood(int tiltMotorID, int shooterSolenoidID1, int shooterSolenoidID2, int reserveSolenoidID){
        // initialize tilt motor & shooters; link them to device IDs
//        oldTiltMotor = new VictorSPX(tiltMotorID);

        tiltMotor = new CANSparkMax(tiltMotorID, CANSparkMaxLowLevel.MotorType.kBrushed);
        tiltMotorController = tiltMotor.getPIDController();
        tiltMotorController.setP(Constants.TILT_HOOD_P);
        tiltMotorController.setI(Constants.TILT_HOOD_I);
        tiltMotorController.setD(Constants.TILT_HOOD_D);

        this.setToAngle(Constants.ANGLE_POSITIONS[angleIndex]);

        shooterSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, shooterSolenoidID1, shooterSolenoidID2);
        reserveSolenoid = new Solenoid(PneumaticsModuleType.REVPH, reserveSolenoidID);

        // set speed of motor to 0.5 (50%)
//        if (oldTiltMotor.configPeakOutputForward(0.5).equals(ErrorCode.OK)
//                && oldTiltMotor.configPeakOutputReverse(0.5).equals(ErrorCode.OK)) {
//            System.out.println("hood motors good to go");
//        } else {
//            System.out.println("this robot does not vibe with the victor motors");
//        }
    }

    // i don't know if victor controllers work like this, but it's worth a shot
    public void setToAngle(double angle) {
//        oldTiltMotor.set(ControlMode.Position, angle * (4096.0 / 360.0)); // convert from angle to the
                                                                        // 4096-units-per-rotation
        tiltMotor.getEncoder().setPosition(angle);
    }

    public double getAnglePosition() {
        //return oldTiltMotor.getSelectedSensorPosition() * (360.0 / 4096.0); // and vice versa here when reading from the
                                                                           // controller
        return tiltMotor.getEncoder().getPosition();
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

    public void logMotorRotations() {
        motorRotations.setDouble(tiltMotor.getEncoder().getPosition());
    }

    @Override
    public void init() {

    }

    @Override
    public SubsystemStatus getSubsystemStatus() {
        return null;
    }

    @Override
    public void updateTest() {

    }

    @Override
    public void updateTeleop() {

    }

    @Override
    public void updateAuton() {

    }

    @Override
    public void updateGeneric() {

    }

    @Override
    public void initTest() {

    }

    @Override
    public void initTeleop() {

    }

    @Override
    public void initAuton() {

    }

    @Override
    public void initDisabled() {

    }

    @Override
    public void initGeneric() {

    }

    @Override
    public String getSubsystemName() {
        return null;
    }
}
