package frc.drive;


import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.controllers.XboxController;

import frc.robot.Robot;


import edu.wpi.first.wpilibj.drive.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import java.util.*;

public class Tonkerdrive {

    public static CANSparkMax left1;
    public static CANSparkMax left2;
    public static CANSparkMax right1;
    public static CANSparkMax right2;
    public static DifferentialDrive drive;
    public static final int left1DeviceID = 1;
    public static final int left2DeviceID = 2;
    public static final int right1DeviceID = 3;
    public static final int right2DeviceID = 4;
    private double voltageMult = 9.2;
    private double turnFactor = 0.3;
    public boolean isInvert = false;
    private XboxController joystick = new XboxController(0);
    public void driveInit() {
        left1 = new CANSparkMax(left1DeviceID, MotorType.kBrushed);
        left2 = new CANSparkMax(left2DeviceID, MotorType.kBrushed);
        right1 = new CANSparkMax(right1DeviceID, MotorType.kBrushed);
        right2 = new CANSparkMax(right2DeviceID, MotorType.kBrushed);

        left2.follow(left1);
        right2.follow(right1);

        left1.setInverted(true);
        right1.setInverted(false);

        left1.setInverted(isInvert);
        right1.setInverted(!isInvert);
    }

   public void  updateTeleOp(){
        left1.setVoltage((joystick.getLYAxis() + (joystick.getRXAxis()*turnFactor)) * voltageMult);
        right1.setVoltage((joystick.getLYAxis() - (joystick.getRXAxis()*turnFactor)) * voltageMult);
   }
}
