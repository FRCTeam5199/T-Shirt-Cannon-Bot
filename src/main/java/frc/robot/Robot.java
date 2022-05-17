// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.XboxController;
import frc.Shooter.Shooter;
import frc.controllers.ControlPanel;
import frc.controllers.XboxController;
import frc.drive.Tonkerdrive;
import edu.wpi.first.wpilibj.*;
import frc.LED.LEDManager;




import frc.robot.Robot;
import frc.tilt.Hood;



/**
 * The VM is configured to automatically run this class, and to call the methods corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    public static XboxController stick1;
    public static Tonkerdrive drive = new Tonkerdrive();
    public static LEDManager LED = new LEDManager();


    /**
     * This method is run when the robot is first started up and should be used for any
     * initialization code.
     */

    //Control
    public static boolean driveEnabled = true;
    private boolean shooterEnabled = true;
    private boolean hoodEnabled = true;

    //Inputs
    XboxController xboxController;
    ControlPanel controlPanel;

    //hood
    Hood hood;
    int[] canMotorIds;
    int positionIndex;
    double percentOutput;

    //Pressure sensor
    AnalogInput pressureSensor;
    double chargePSI;

    @Override
    public void robotInit() {
        stick1 = new XboxController(0);
        drive.driveInit();
        LEDManager.Init();
        // removed this "}" here
        //simpleWidget = Shuffleboard.getTab("Tab").add("Title", "value");
        xboxController = new XboxController(0);
        controlPanel = new ControlPanel(0);

        LEDManager.test();
        //TODO set motor IDs, position index, and percent output, the current values are PLACEHOLDERS
        canMotorIds = new int[]{0, 1, 3};
        positionIndex = 1;
        percentOutput = 100;
        hood = new Hood(canMotorIds, percentOutput, positionIndex);

        //TODO ensure with electrical team that the pressure sensor is plugged into analog port 0 on the rio
        pressureSensor = new AnalogInput(0);
    }
    

    
    @Override
    public void robotPeriodic() {}
    
    
    @Override
    public void autonomousInit() {}
    
    
    @Override
    public void autonomousPeriodic() {}
    
    
    @Override
    public void teleopInit() {
        stick1 = new XboxController(0);
        Shooter.resetShooter();
    }

    @Override
    public void teleopPeriodic() {
        //LED's
        if (controlPanel.button4()){
            LED.DOLPHIN();
        }
        //Drive
        drive.Teleop();


        //Shooter
        //getVoltage returns a voltage between 0 and 5v
        //The REV website states that with this model of sensor 5v = 200 psi
        //TODO Currently firing at 110 PSI, check with shooter group that this is what they want
        chargePSI = pressureSensor.getVoltage() * 40;
        if((xboxController.getButton(0) || controlPanel.shoot()) && shooterEnabled && chargePSI >= 110) { 
            Shooter.closeReserve();
            Shooter.fireShot();
        }
        else {
            Shooter.resetShooter();
            if(chargePSI < 110) {
                Shooter.openReserve();
            }
            else {
                Shooter.closeReserve();
            }
        }      

        //TODO Hood 
        //note: control panel and button values have not been mapped, these might not be the intended buttons
        //note: The hood.moveTo positions might not be correct
        if((xboxController.getButton(2) || controlPanel.button1()) && hoodEnabled) { 
            hood.moveTo(0);
        }
        else if ((xboxController.getButton(3) || controlPanel.button2()) && hoodEnabled) {
            hood.moveTo(1);
        }
        else if ((xboxController.getButton(4) || controlPanel.button3()) && hoodEnabled) {
            hood.moveTo(2);
        }
    }
    
    @Override
    public void disabledInit() {}
    
    
    @Override
    public void disabledPeriodic() {}
    
    
    @Override
    public void testInit() {}
    
    
    @Override
    public void testPeriodic() {}
}
