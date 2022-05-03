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
        WARNING_TAB = Shuffleboard.getTab("Warning");


    public static final ShuffleboardLayout PDP_SETTINGS = ROBOT_TAB.getLayout("PDP", BuiltInLayouts.kList).withProperties(Map.of("Label Position", "Left")).withSize(2, 3),


    
    public static final SimpleWidget DRIVE_ROT_MULT = DRIVE_TAB.add("Rotation Factor", robotSettings.TURNSCALE),
            DRIVE_SCALE_MULT = DRIVE_TAB.add("Speed Factor", robotSetting.DRIVE_SCALE),
            DRIVE_SPEED_RPM = DRIVE_TAB.add("Drivebase RPM", 0).withWidget(BuiltInWidgets.kGraph),
            DRIVE_COAST = DRIVE_TAB.add("Coast", false).withWidget(BuiltInWidgets.kGraph),

    GET_RANDOM_FIX = ROBOT_TAB.add("get random fix", false).withWidget(BuiltInWidgets.kToggleButton);

    public static ComplexWidget MUSIC_SELECTOR, PDP_DISPLAY,
        DRIVE_STYLE_CHOOSER = DRIVE_TAB.add("Drive", AbstractDrive.DriveControlStyles.getSendableChooser()).withWidget(BuiltInWidgets.kComboBoxChooser);
}

