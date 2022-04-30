package frc.drive;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.networktables.NetworkTableEntry;
import frc.robot.Robot;

import java.util.Objects;

import static frc.robot.Robot.robotSettings;

public abstract AbstractDrive implements ISubsystem {

    protected NetworkTableEntry driveRotMult = UserInterface.DRIVE_ROT_MULT.getEntry();
}