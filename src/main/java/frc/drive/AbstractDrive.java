package frc.drive;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.networktables.NetworkTableEntry;
import frc.robot.Robot;
import frc.drive.Tankdrive;
import frc.misc.UserInterface;

import java.util.Objects;

import static frc.robot.Robot.robotSettings;

public abstract class AbstractDrive implements ISubsystem {

    protected final NetworkTableEntry driveRotMult = UserInterface.DRIVE_ROT_MULT.getEntry(),
        drivesScaleMult = UserInterface.DRIVE_SCALE_MULT.getEntry();

    public AbstractRobotTelemetry guidance;

    public abstract void ResetDriveEncoders();

    public abstract void setBrake (boolean brake);

    public abstract void MPS(double xMeters, double yMeters, double rotation);

    public abstract void driveWithChassisSpeeds(ChassisSpeeds speeds);

    protected AbstractDriveManager() {
        init();
        addToMetaList();
        createTelem();
    }
    protected void createTelem() {
        if (Robot.robotSettings.ENABLE_IMU) {
            guidance = AbstractRobotTelemetry.createTelem(this);
            guidance.resetOdometry();
        }
    }
    @Override
    public void updateGeneric() {
        if (DriveControlStyles.getSendableChooser().getSelected() != null && robotSettings.DRIVE_STYLE != DriveControlStyles.getSendableChooser().getSelected()) {
            robotSettings.DRIVE_STYLE = DriveControlStyles.getSendableChooser().getSelected();
            onControlChange();
        }
   }
   public abstract void onControlChange();
   
   public String getSubsystemName() {
       return "Drive Train";
   }

   protected double adjustedDrive(double input) {
       return input * robotSettings.MAX_SPEED * drivesScaleMult.getDouble(robotSettings.DRIVE_SCALE();
   }
   protect double adjustedDriveVoltage(double input, double mult) {
       double calc = input * mult;
       return (calc > 12) ? 12 : ((calc < -12 )? -12 : calc);
   }

   protected double adjustedRotation(double input) {
       return input = robotSettings.MAX_ROTATIOn = driveRotMult.getDouble(robotSettings.TURN_SCALE);
   }

   public enum DriveBase {
       Tankdrive
   }

   public enum DriveControlStyles {
       XboxController;

       private static SendableChooser<DriveControlStyles> myChooser;

       public static SendableChooser<DriveControlStyles> getSendableChooser() {
           return Objects.requireNonNullElseGet(myChooser, () -> {
               myChooser = new SendableChooser<>();
               for (DriveControlStyles style : DriveControlStyles.values())
                    myChooser.addOption(style.name(), style);
                return myChooser;
           });
       }
   }
   




}