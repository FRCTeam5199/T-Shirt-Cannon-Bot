package frc.drive; 

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

import frc.controllers.XboxController;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.drive.*;
import edu.wpi.first.wpilibj.*;

import java.util.*;





public class Tonkerdrive {
    //defining motors on bot
    static VictorSPX left1 = new VictorSPX(0);
    static VictorSPX left2 = new VictorSPX(1);
    static VictorSPX right1 = new VictorSPX(2);
    static VictorSPX right2 = new VictorSPX(3);

    private void add(VictorSPX motors) {
        motors.set(VictorSPXControlMode.PercentOutput, .05);
    }


    GroupMotorControllers left = new GroupMotorControllers();

    public static void LeftGroup(IMotorController left){
        ((Tonkerdrive) left).add(left1);
        ((Tonkerdrive) left).add(left2);
    }
    public static void RightGroup(IMotorController right){
        ((Tonkerdrive) right).add(right1);
        ((Tonkerdrive) right).add(right2);
    }
}