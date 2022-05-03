package frc.robot.configs;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SerialPort;
import frc.motors.AbstractMotorController;
import frc.telemetry.AbstractIMU;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import frc.drive.AbstractDrive;


public abstract class Tonker {
    public boolean ENABLE_DRIVE = false;
    public AbstractIMU.SupportedIMU imuType = AbstractIMU.SupportedIMU.PIGEON;
    


    
}
