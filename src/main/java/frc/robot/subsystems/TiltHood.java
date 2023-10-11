package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.Command;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Constants;

public class TiltHood extends SubsystemBase {
  CANSparkMax tiltMotor;
  SparkMaxPIDController tiltMotorController;
  int angleIndex = 2; //default to 60 degrees
  
  public static DoubleSolenoid shooterSolenoid;
  public static Solenoid reserveSolenoid;
  ShuffleboardTab tab = Shuffleboard.getTab("Tilt Hood");
  GenericEntry motorRotations = tab.add("Motor rotations", 0).getEntry();

  public static Compressor compressor = new Compressor(Constants.COMPRESSOR_ID, PneumaticsModuleType.REVPH);

  public TiltHood() {
      
    tiltMotor = new CANSparkMax(Constants.TILT_MOTOR_ID, CANSparkMaxLowLevel.MotorType.kBrushed);
    tiltMotorController = tiltMotor.getPIDController();
    tiltMotorController.setP(Constants.TILT_HOOD_P);
    tiltMotorController.setI(Constants.TILT_HOOD_I);
    tiltMotorController.setD(Constants.TILT_HOOD_D);

    this.setToAngle(Constants.ANGLE_POSITIONS[angleIndex]);

    shooterSolenoid = new DoubleSolenoid(PneumaticsModuleType.REVPH, Constants.SHOOTER_SOLENOID_ID_1, Constants.SHOOTER_SOLENOID_ID_2);
    reserveSolenoid = new Solenoid(PneumaticsModuleType.REVPH, Constants.RESERVE_SOLENOID_ID);
}

  public Command setToAngle(double angle) {
    //Coneversion factor previously was 4096 / 360, 4096 units per rotation
      return this.runOnce(() -> tiltMotor.getEncoder().setPosition(angle));
  }

  public double getAnglePosition() {
    //Conversion factor previously was 360 / 4096
    return tiltMotor.getEncoder().getPosition();
  }

  public Command setToDefault(int space) {
    angleIndex = space;
    return this.runOnce(() -> setToAngle(Constants.ANGLE_POSITIONS[angleIndex]));
  }

  public Command moveDefault(int spaces) {
    if ((angleIndex + spaces > -1) && (angleIndex + spaces < Constants.ANGLE_POSITIONS.length) ){
        angleIndex += spaces;
        return this.runOnce(() -> setToAngle(Constants.ANGLE_POSITIONS[angleIndex]));
    } else { return this.runOnce(() -> System.out.println("Can't move to it")); }
  }

    public Command fireShot() { //2
      return this.runOnce(() -> {
        reserveSolenoid.set(true); //inverted
        shooterSolenoid.set(Value.kForward);
      });
  } 

  public Command openReserve() { //1
      return this.runOnce(() -> {
        shooterSolenoid.set(Value.kReverse);
        reserveSolenoid.set(false); //inverted
      });
  }


  public Command closeAll() { //3
      return this.runOnce(() -> {
        shooterSolenoid.set(Value.kReverse);
        reserveSolenoid.set(true); //inverted
      });
  }
}