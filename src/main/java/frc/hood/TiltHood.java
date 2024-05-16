package frc.hood;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.Constants;
import frc.misc.ISubsystem;

public class TiltHood implements ISubsystem {
    // hood/tilt motor for angle
    VictorSPX motor;
    int angleIndex = 2; //default to 60 degrees
    
    // solenoids for shooting t-shirts
    public static Solenoid reserveSolenoid = new Solenoid(PneumaticsModuleType.REVPH, Constants.RESERVE_SOLENOID_ID);
    ShuffleboardTab tab = Shuffleboard.getTab("Tilt Hood");
    public static DigitalOutput outputs = new DigitalOutput(9);
    //compressor
    public static Compressor compressor = new Compressor(Constants.COMPRESSOR_ID, PneumaticsModuleType.REVPH);

    // constructor for the solenoids & tilt motor, with provided IDs
    // TODO Find the device IDs (not so important at the moment)
   

    // i don't know if victor controllers work like this, but it's worth a shot
    public TiltHood() {
        motor = new VictorSPX(Constants.TILT_MOTOR_ID);
    }
    


    public void setMotorSpeed(double speed) {
        motor.set(ControlMode.PercentOutput, speed);
    }
    
    // functions from previous shooter class
    public void fireShot() {
        System.out.println("firing");
        outputs.set(true);
        //reserveSolenoid.set(); //inverted
    } 

    public void resetShooter() {
        //System.out.println("rest shooter");
        outputs.set(false);
    }

    public void openReserve() {
        outputs.set(true);
        reserveSolenoid.set(true); //inverted
    }

    public void closeReserve() {
        outputs.set(false);
        reserveSolenoid.set(false); //inverted
    }

    public void closeAll() {
        outputs.set(false);
        reserveSolenoid.set(true); //inverted
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
