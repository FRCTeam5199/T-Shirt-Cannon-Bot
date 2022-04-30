package frc.misc;

import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.ComplexWidget;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.drive.AbstractDrive;
import frc.motors.AbstractMotorController;

import java.util.HashMap;
import java.util.Map;



public class UserInterface {
    public static final ShuffleboardTab SHOOTER_TAB = Shuffleboard.getTab("Shooter"),
        DRIVE_TAB = Shuffleboard.getTab("Drive"),

    MUSICK_TAB = Shuffleboard.getTab("musick"),
        ROBOT_TAB = Shuffleboard.getTab("Danger"),
        WARNING_TAB = Shuffleboard.getTab("Warning"),
        TILT_TAB = Shuffleboard.getTab("Tilt");
}

