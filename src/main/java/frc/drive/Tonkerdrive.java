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





public class Tonkerdrive{

    //defining motors on bot
    left1 = new VictorSPX(0);
    left2 = new VictorSPX(1);
    right1 = new VictorSPX(2);
    right2 = new VictorSPX(3);

    left2.follow(left1);
    right2.follow(right1);

    cooldiff = new DifferentialDrive(left1, right1);


}