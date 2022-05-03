package frc.drive; 

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.IMotorController;

import frc.controllers.XboxController;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.kinematics.ChassisSpeeds;

import java.util.*;





public class Tonkerdrive {
    //defining motors on bot
    private static VictorSPX left1;
    private static VictorSPX left2;
    private static VictorSPX right1;
    private static VictorSPX right2;

    //Too dumb to think of a better way to combine motors
    public class LeftGroupMotorController {
        List<IMotorController> left = new ArrayList<IMotorController>();
    }
    public class RightGroupMotorController {
        List<IMotorController> right = new ArrayList<IMotorController>();
    }

    public static void combineL(IMotorController left){
        ((List<IMotorController>) left).add(left1);
        ((List<IMotorController>) left).add(left2);
    }

    public static void combineR (IMotorController right){
        ((List<IMotorController>) right).add(right1);
        ((List<IMotorController>) right).add(right2);
    }

}
