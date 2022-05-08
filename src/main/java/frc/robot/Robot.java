// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;



/**
 * The VM is configured to automatically run this class, and to call the methods corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
<<<<<<< Updated upstream
public class Robot extends TimedRobot
{
=======
public class Robot extends TimedRobot {
    public static XboxController stick1;
    public static DifferentialDrive drive; 


>>>>>>> Stashed changes
    /**
     * This method is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {}
    
    
    @Override
    public void robotPeriodic() {}
    
    
    @Override
    public void autonomousInit() {}
    
    
    @Override
    public void autonomousPeriodic() {}
    
    
    @Override
<<<<<<< Updated upstream
    public void teleopInit() {}
    
    
    @Override
    public void teleopPeriodic() {}
    
=======

    public void teleopInit() {
        stick1 = new XboxController(0);
        public static void tele(){
            drive.tankDrive(Robot.stick1.getLXAxis(), Robot.stick1.getLYAxis());
        }
    @Override
    public void teleopPeriodic() {

        //Drive 
        if (Robot.stick1.getLYAxis() > 0) { 
            drive.tankDrive(Robot.stick1.getLYAxis() * .5, Robot.stick1.getLYAxis() * .5);
        }

        if (Robot.stick1.getLYAxis() < 0) {
            drive.tankDrive(Robot.stick1.getLYAxis() * -.5, Robot.stick1.getLYAxis() * -.5);

        }
        
        //Shooter
        if(xboxController.getButton(0) && shooterEnabled) { 
            Shooter.fireShot();
        }
        else {
            Shooter.resetShooter();
        } 

        //TODO Hood
        if(xboxController.getButton(2) && hoodEnabled) { 
            
        }
        else {
            
        } 
    }
>>>>>>> Stashed changes
    
    @Override
    public void disabledInit() {}
    
    
    @Override
    public void disabledPeriodic() {}
    
    
    @Override
    public void testInit() {}
    
    
    @Override
    public void testPeriodic() {}
}
