// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.XboxController;
import frc.Shooter.Shooter;
import frc.controllers.XboxController;
/**
 * The VM is configured to automatically run this class, and to call the methods corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

    /**
     * This method is run when the robot is first started up and should be used for any
     * initialization code.
     */

    //Control
    private boolean driveEnabled = true;
    private boolean shooterEnabled = true;
    private boolean hoodEnabled = true;

    XboxController xboxController;

    @Override
    public void robotInit() {}
    
    
    @Override
    public void robotPeriodic() {}
    
    
    @Override
    public void autonomousInit() {}
    
    
    @Override
    public void autonomousPeriodic() {}
    
    
    @Override
    public void teleopInit() {
        xboxController = new XboxController(0);
    }
    
    
    @Override
    public void teleopPeriodic() {
        //TODO map buttons
        
        //Shooter
        if(xboxController.getButton(1) && shooterEnabled) { 
            Shooter.fireShot();
        }
        else {
            Shooter.resetShooter();
        } 

        //Hood
        if(xboxController.getButton(2) && hoodEnabled) { 
            
        }
        else {
            
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
