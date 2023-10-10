package frc.robot.drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.Command;

public class Tonkerdrive {
    public static VictorSPX left1;
    public static VictorSPX left2;
    public static VictorSPX right1;
    public static VictorSPX right2;

    Tonkerdrive() {
        left1 = new VictorSPX(Constants.LEFT_1_DEVICE_ID);
        left2 = new VictorSPX(Constants.LEFT_2_DEVICE_ID);
        right1 = new VictorSPX(Constants.RIGHT_1_DEVICE_ID);
        right2 = new VictorSPX(Constants.RIGHT_2_DEVICE_ID);
        
        left2.follow(left1);
        right2.follow(right1);

        right1.setInverted(Constants.IS_INVERTED);
    }

    public Command drive(XboxController xboxController) {
    return this.runOnce(() -> {
        left1.set(ControlMode.PercentOutput, ((xboxController.getLeftY() + (xboxController.getRXAxis() * Constants.TURN_FACTOR)) * Constants.VOLTAGE_MULT) * Constants.MAX_SPEED_PERCENT);
        right1.set(ControlMode.PercentOutput, ((-xboxController.getLeftY() + (xboxController.getRXAxis() * Constants.TURN_FACTOR)) * Constants.VOLTAGE_MULT) * Constants.MAX_SPEED_PERCENT);
    });
}
