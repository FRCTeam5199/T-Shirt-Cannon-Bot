package frc.drive;

import frc.Constants;
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

    private XboxController joystick = new XboxController(Constants.XBOX_CONTROLLER_PORT);
    public Robot driveable = new Robot();

    public void driveInit() {

        Robot.driveEnabled = true;
        left1 = new VictorSPX(Constants.LEFT_1_DEVICE_ID);
        left2 = new VictorSPX(Constants.LEFT_2_DEVICE_ID);
        right1 = new VictorSPX(Constants.RIGHT_1_DEVICE_ID);
        right2 = new VictorSPX(Constants.RIGHT_2_DEVICE_ID);
        tilt = new VictorSPX(Constants.TILT_ID);
        
        left2.follow(left1);
        right2.follow(right1);

        right1.setInverted(Constants.IS_INVERTED);

    }
    
    //Speed at 50% of max

    public void Teleop() {
        
        //Drive
        left1.set(ControlMode.PercentOutput, ((joystick.getLYAxis() + (joystick.getRXAxis() * Constants.TURN_FACTOR)) * Constants.VOLTAGE_MULT) * Constants.MAX_SPEED_PERCENT);
        right1.set(ControlMode.PercentOutput, ((-joystick.getLYAxis() + (joystick.getRXAxis() * Constants.TURN_FACTOR)) * Constants.VOLTAGE_MULT) * Constants.MAX_SPEED_PERCENT);

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
