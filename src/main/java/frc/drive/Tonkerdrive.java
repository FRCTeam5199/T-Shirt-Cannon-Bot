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
    public static VictorSPX tilt;
    public static final int left1DeviceID = 1;
    public static final int left2DeviceID = 2;
    public static final int right1DeviceID = 3;
    public static final int right2DeviceID = 4;
    public static final int tiltID = 7;
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
        tilt = new VictorSPX(tiltID);
        
        left2.follow(left1);
        right2.follow(right1);

        right1.setInverted(isInverted);

    }
    
    //Speed at 50% of max
    public double maxSpeedPercent = 0.20;

    public void Teleop() {
        
        //Drive
        left1.set(ControlMode.PercentOutput, ((joystick.getLYAxis() + (joystick.getRXAxis() * turnFactor)) * voltageMult) * maxSpeedPercent);
        right1.set(ControlMode.PercentOutput, ((-joystick.getLYAxis() + (joystick.getRXAxis() * turnFactor)) * voltageMult) * maxSpeedPercent);
        
        /*
        if(joystick.getButton(1)) {
            System.out.println("hello");
            tilt.set(ControlMode.Position, 30 * (4096.0 / 360.0));
        }
        if(joystick.getButton(2)) {
            tilt.set(ControlMode.Position, 45 * (4096.0 / 360.0));
        }
        if(joystick.getButton(3)) {
            tilt.set(ControlMode.Position, 60 * (4096.0 / 360.0));
        }
        */

        //Tilt
        //System.out.println("d-pad-up is " + joystick.getDPadUp());
        if(joystick.getDPadUp()) {
            tilt.setInverted(false);
            tilt.set(ControlMode.PercentOutput, 0.35);
        }
        else if(joystick.getDPadDown()) {
            tilt.setInverted(true);
            tilt.set(ControlMode.PercentOutput, 0.35);
        }
        else {
            tilt.set(ControlMode.PercentOutput, 0);
        }
    }
}
