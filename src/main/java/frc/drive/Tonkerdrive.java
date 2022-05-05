import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.controllers.XboxController;

import frc.robot.Robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.drive.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import java.util.*;




public class Tonkerdrive {


    public static CANSparkMax left1;
    public static CANSparkMax left2;
    public static CANSparkMax right1;
    public static CANSparkMax right2;
    public static DifferentialDrive dwivue;
    public static final int left1DeviceID = 1;
    public static final int left2DeviceID = 2;
    public static final int right1DeviceID = 3;
    public static final int right2DeviceID = 4;

    public void driveInit() {
        left1 = new CANSparkMax(left1DeviceID, MotorType.kBrushed);
        left2 = new CANSparkMax(left2DeviceID, MotorType.kBrushed);
        right1 = new CANSparkMax(right1DeviceID, MotorType.kBrushed);
        right2 = new CANSparkMax(right2DeviceID, MotorType.kBrushed);

        left2.follow(left1);
        right2.follow(right1);

        dwivue = new DifferentialDrive(left1, right1);

        left1.restoreFactoryDefaults();

        right1.restoreFactoryDefaults();

        public static void teleopPeriod(){
            dwivue.tankDrive(stick1.getLXAxis(), stick1.getLYAxis());
        }
    }
}
