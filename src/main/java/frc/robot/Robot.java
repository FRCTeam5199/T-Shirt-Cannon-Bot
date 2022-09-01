// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.LED.LEDManager;
//import edu.wpi.first.wpilibj.XboxController;
import frc.controllers.ControlPanel;
import frc.controllers.XboxController;
import frc.drive.Tonkerdrive;
import frc.hood.TiltHood;

/**
 * The VM is configured to automatically run this class, and to call the methods
 * corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    public static XboxController stick1;
    public static Tonkerdrive drive = new Tonkerdrive();

    /**
     * This method is run when the robot is first started up and should be used for
     * any
     * initialization code.
     */

    // Control
    public static boolean driveEnabled = true;
    private boolean shooterEnabled = true;
    private boolean hoodEnabled = true;

    // Inputs
    XboxController xboxController;
    ControlPanel controlPanel;

    // hood
    // TODO set motor IDs; the current values are PLACEHOLDERS
    TiltHood hood;
    static final int tiltMotorID = 0;
    static final int shooterSolenoidID = 1;
    static final int reserveSolenoidID = 2;

    // LED Manager
    LEDManager ledManager = new LEDManager();

    // Pressure sensor
    AnalogInput pressureSensor;
    double chargePSI;

    @Override
    public void robotInit() {
        stick1 = new XboxController(0);
        drive.driveInit();
        // removed this "}" here
        // simpleWidget = Shuffleboard.getTab("Tab").add("Title", "value");
        xboxController = new XboxController(0);
        controlPanel = new ControlPanel(0);

        hood = new TiltHood(tiltMotorID, shooterSolenoidID, reserveSolenoidID);

        ledManager.test();

        // TODO ensure with electrical team that the pressure sensor is plugged into
        // analog port 0 on the rio
        pressureSensor = new AnalogInput(0);
    }

    @Override
    public void robotPeriodic() {
    }

    @Override
    public void autonomousInit() {
    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
        stick1 = new XboxController(0);
        hood.resetShooter();
    }

    @Override
    public void teleopPeriodic() {

        if (controlPanel.button4()) {
            ledManager.capoLEDMode();
        }

        if (controlPanel.safetySwitch()) {
            // Drive
            drive.Teleop();

            // Shooter
            // getVoltage returns a voltage between 0 and 5v
            // The REV website states that with this model of sensor 5v = 200 psi
            // TODO Currently firing at 110 PSI, check with shooter group that this is what
            // they want
            chargePSI = pressureSensor.getVoltage() * 40;
            if ((xboxController.getButton(0) || controlPanel.shoot()) && shooterEnabled && chargePSI >= 110) {
                hood.closeReserve();
                hood.fireShot();
            } else {
                hood.resetShooter();
                if (chargePSI < 110) {
                    hood.openReserve();
                } else {
                    hood.closeReserve();
                }
            }

            // TODO Hood
            // note: control panel and button values have not been mapped, these might not
            // be the intended buttons
            // note: The hood.moveTo positions might not be correct
            if ((xboxController.getButton(2) || controlPanel.button1()) && hoodEnabled) {
                hood.setToDefault(0);
            } else if ((xboxController.getButton(3) || controlPanel.button2()) && hoodEnabled) {
                hood.setToDefault(1);
            } else if ((xboxController.getButton(4) || controlPanel.button3()) && hoodEnabled) {
                hood.setToDefault(2);
            }
        }
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void testInit() {
    }

    @Override
    public void testPeriodic() {
    }
}
