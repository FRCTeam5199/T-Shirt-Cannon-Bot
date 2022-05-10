// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.SimpleWidget;
//import edu.wpi.first.wpilibj.XboxController;
import frc.Shooter.Shooter;
import frc.controllers.ControlPanel;
import frc.controllers.XboxController;
import frc.drive.Tonkerdrive;


import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;



import frc.robot.Robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.drive.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import java.util.*;
/**
 * The VM is configured to automatically run this class, and to call the methods corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    public static XboxController stick1;


    /**
     * This method is run when the robot is first started up and should be used for any
     * initialization code.
     */

    //Control
    private boolean driveEnabled = true;
    private boolean shooterEnabled = true;
    private boolean hoodEnabled = true;

    XboxController xboxController;
    ControlPanel controlPanel;

    private SimpleWidget simpleWidget;

    @Override
    public void robotInit() {

        stick1 = new XboxController(0);
        
        // removed this "}" here
        //simpleWidget = Shuffleboard.getTab("Tab").add("Title", "value");
        xboxController = new XboxController(0);
        controlPanel = new ControlPanel(0);
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
        

        

        //Shooter
        if((xboxController.getButton(0) || controlPanel.shoot()) && shooterEnabled) { 
            Shooter.fireShot();
        }
        else {
            Shooter.resetShooter();
        } 

        //TODO Hood 
        //note: control panel button 1 might not be the intended button
        if((xboxController.getButton(2) || controlPanel.button1()) && hoodEnabled) { 
            
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
