package frc.drive;

import frc.controllers.XboxController;

import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Tonkerdrive {

    public static VictorSPX left1;
    public static VictorSPX left2;
    public static VictorSPX right1;
    public static VictorSPX right2;
    public static final int left1DeviceID = 1;
    public static final int left2DeviceID = 2;
    public static final int right1DeviceID = 3;
    public static final int right2DeviceID = 4;
    private double turnFactor = 0.3;
    private double voltageMult = 9.2;
    public boolean isInverted = false;
    private XboxController joystick = new XboxController(0);
    public Robot driveable = new Robot();

    public void driveInit() {

        Robot.driveEnabled = true;
        left1 = new VictorSPX(left1DeviceID);
        left2 = new VictorSPX(left2DeviceID);
        right1 = new VictorSPX(right1DeviceID);
        right2 = new VictorSPX(right2DeviceID);
        
        left2.follow(left1);
        right2.follow(right1);

        right1.setInverted(isInverted);

    }
    
    //Speed at 50% of max
    public double maxSpeedPercent = 0.5;

    public void Teleop() {
        left1.set(ControlMode.PercentOutput, ((joystick.getLXAxis() + (joystick.getRYAxis() * turnFactor)) * voltageMult) * maxSpeedPercent);
        right1.set(ControlMode.PercentOutput, ((joystick.getLXAxis() - (joystick.getRYAxis() * turnFactor)) * voltageMult) * maxSpeedPercent);
    }
}
