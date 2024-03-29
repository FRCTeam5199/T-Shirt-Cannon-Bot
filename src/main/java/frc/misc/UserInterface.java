package frc.misc;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.motorcontrollers.AbstractMotorController;

import java.util.HashMap;
import java.util.Map;


public class UserInterface {
    private static NetworkTableInstance instance = NetworkTableInstance.getDefault();
    public static NetworkTable autoDataTable = instance.getTable("autodata");
    public static NetworkTableEntry autoPath = autoDataTable.getEntry("autoPath");

    private static NetworkTable position = autoDataTable.getSubTable("position");
    public static NetworkTableEntry xPos = position.getEntry("x");
    public static NetworkTableEntry yPos = position.getEntry("y");
    public static NetworkTableEntry enabled = autoDataTable.getEntry("enabled");

    //TABS
    public static final ShuffleboardTab DRIVE_TAB = Shuffleboard.getTab("drive"),
    //PDP_TAB = Shuffleboard.getTab("Lectricity"),
    MUSICK_TAB = Shuffleboard.getTab("musick"),
            ROBOT_TAB = Shuffleboard.getTab("DANGER!"),
            WARNINGS_TAB = Shuffleboard.getTab("Warnings"),
            AUTON_TAB = Shuffleboard.getTab("Auton"),
            CAMERA_TAB = Shuffleboard.getTab("Camera");

    //LAYOUTS
    public static final ShuffleboardLayout DRIVE_PID_LAYOUT = DRIVE_TAB.getLayout("PID", BuiltInLayouts.kList).withProperties(Map.of("Label position", "LEFT")).withSize(2, 3),
            PDP_SETTINGS_LAYOUT = ROBOT_TAB.getLayout("PowerDistribution", BuiltInLayouts.kList).withProperties(Map.of("Label position", "LEFT")).withSize(2, 1);

    //DRIVETRAIN
    public static final SimpleWidget
//            DRIVE_ROT_MULT = DRIVE_TAB.add("Rotation Factor", robotSettings.TURN_SCALE),
//            DRIVE_SCALE_MULT = DRIVE_TAB.add("Speed Factor", robotSettings.DRIVE_SCALE),
//            DRIVE_P = DRIVE_PID_LAYOUT.add("P", robotSettings.DRIVEBASE_PID.getP()),
//            DRIVE_I = DRIVE_PID_LAYOUT.add("I", robotSettings.DRIVEBASE_PID.getI()),
//            DRIVE_D = DRIVE_PID_LAYOUT.add("D", robotSettings.DRIVEBASE_PID.getD()),
//            DRIVE_F = DRIVE_PID_LAYOUT.add("F", robotSettings.DRIVEBASE_PID.getF()),
            DRIVE_CALIBRATE_PID = DRIVE_PID_LAYOUT.add("Tune PID", false).withWidget(BuiltInWidgets.kToggleSwitch),
            DRIVE_COAST = DRIVE_TAB.add("Coast", false).withWidget(BuiltInWidgets.kToggleSwitch),
            DRIVE_RUMBLE_NEAR_MAX = DRIVE_TAB.add("Rumble Near Max", false).withWidget(BuiltInWidgets.kToggleSwitch),
            DRIVE_SPEED_RPM = DRIVE_TAB.add("Drivebase RPM", 0).withWidget(BuiltInWidgets.kGraph),
            ROBOT_LOCATION = DRIVE_TAB.add("Robot Location", "(0, 0)").withWidget(BuiltInWidgets.kTextView),

    //MUSICK
    MUSIC_DISABLE_SONG_TAB = MUSICK_TAB.add("Stop Song", false).withWidget(BuiltInWidgets.kToggleButton),
            MUSIC_FOUND_SONG = MUSICK_TAB.add("Found it", false),
            DELETE_DEPLOY_DIRECTORY = ROBOT_TAB.add("DELETE DEPLOY DIRECTORY", ""),
            PRINT_ROBOT_TOGGLES = ROBOT_TAB.add("Reprint robot toggles", false).withWidget(BuiltInWidgets.kToggleButton),
            PRINT_ROBOT_MAPPINGS = ROBOT_TAB.add("Reprint robot mappings", false).withWidget(BuiltInWidgets.kToggleButton),
            PRINT_ROBOT_NUMBERS = ROBOT_TAB.add("Reprint robot numbers", false).withWidget(BuiltInWidgets.kToggleButton),
            DRIVE_SPEED = DRIVE_TAB.add("Drivebase Speed", 0).withWidget(BuiltInWidgets.kDial).withProperties(Map.of("Min", 0, "Max", 20)),
    //PowerDistribution
    PDP_BROWNOUT_MIN_OVERRIDE = PDP_SETTINGS_LAYOUT.add("Settings Override", false).withWidget(BuiltInWidgets.kToggleSwitch),
            PDP_BROWNOUT_MIN_VAL = PDP_SETTINGS_LAYOUT.add("Minimum Brownout Voltage", 9),
    //DANGER PANEL
    GET_RANDOM_FIX = ROBOT_TAB.add("Get random fix", false).withWidget(BuiltInWidgets.kToggleButton);
    public static final HashMap<AbstractMotorController, SimpleWidget> motorTemperatureMonitors = new HashMap<>();

    //STATIC STUFF
    public static SimpleWidget SHOOTER_RPM;

    //SmartDashboard
    public static void smartDashboardPutNumber(String key, double value) {
        SmartDashboard.putNumber(key, value);
    }

    public static void smartDashboardPutBoolean(String key, boolean value) {
        SmartDashboard.putBoolean(key, value);
    }

    public static void smartDashboardPutString(String key, String value) {
        SmartDashboard.putString(key, value);
    }

    //MISC
    public static void initRobot() {
//        if (robotSettings.ENABLE_CAMERA) {
//            UsbCamera camera = CameraServer.startAutomaticCapture(0);
//            camera.setResolution(640, 480);
//            //UserInterface.SMART_DASHBOARD.add("CameraViewer", camera);
//        }
    }
}