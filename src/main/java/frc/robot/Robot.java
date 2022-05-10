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


    /**
     * This method is run when the robot is first started up and should be used for any
     * initialization code.
     */

    //Control
    public static boolean driveEnabled = true;
    private boolean shooterEnabled = true;
    private boolean hoodEnabled = true;

    XboxController xboxController;
    ControlPanel controlPanel;

    Hood hood;
    int[] canMotorIds;
    int positionIndex;
    double percentOutput;


    @Override
    public void robotInit() {

        stick1 = new XboxController(0);
        
        // removed this "}" here
        //simpleWidget = Shuffleboard.getTab("Tab").add("Title", "value");
        xboxController = new XboxController(0);
        controlPanel = new ControlPanel(0);

        //TODO set motor IDs, position index, and percent output, the current values are PLACEHOLDERS
        canMotorIds = new int[]{0, 1, 3};
        positionIndex = 1;
        percentOutput = 100;
        hood = new Hood(canMotorIds, positionIndex, percentOutput);
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

        //Drive
        drive.Teleop();

        

        //Shooter
        if((xboxController.getButton(0) || controlPanel.shoot()) && shooterEnabled) { 
            Shooter.fireShot();
        }
        else {
            Shooter.resetShooter();
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
