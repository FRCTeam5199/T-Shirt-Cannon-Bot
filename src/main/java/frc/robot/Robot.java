// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.LED.LEDManager;
//import edu.wpi.first.wpilibj.XboxController;
import frc.controllers.ControlPanel;
import frc.controllers.XboxController;
import frc.drive.Tonkerdrive;
import frc.hood.TiltHood;
//import com.ctre.phoenix.*;
//import com.ctre.phoenix.motorcontrol.*;
//import com.ctre.phoenix.motorcontrol.can.*;

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

    public class WarningLed implements Runnable{
        private Thread t;
        private String Led;

        WarningLed(String warn){
            Led = warn;
        }
        public void run(){
            try{
                while(controlPanel.safetySwitch()) {
                ledManager.safetymode();
                }
            }catch(IllegalAccessError | InterruptedException error){
                System.out.println(" ");
            }
        }
        public void start(){
            if(t == null){
                t = new Thread(this, Led);
                t.start();
            }
        }
    }

    //Variables

    // Control
    public static boolean driveEnabled = true;
    private boolean shooterEnabled = true;
    private boolean hoodEnabled = true;

    // Inputs
    XboxController xboxController;
    ControlPanel controlPanel;

    // hood
    TiltHood hood;
    static final int tiltMotorID = 7;
    static final int shooterSolenoidID1 = 0;
    static final int shooterSolenoidID2 = 1;
    static final int reserveSolenoidID = 2;

    // LED Manager
    LEDManager ledManager = new LEDManager();

    // Pressure sensor
    AnalogInput pressureSensor;
    double chargePSI;

    //Compressor
    private boolean compressorEnabled = false;

    //pre fireing
    private boolean isPrefiring = false;
    private boolean prefiringStrobe = false;

    //Safety
    private boolean safetyMode = false;

    //Main
    @Override

    //Robot Init
    public void robotInit() {
        System.out.println("initializing");
        xboxController = new XboxController(0);
        stick1 = new XboxController(0);
        drive.driveInit();
        // simpleWidget = Shuffleboard.getTab("Tab").add("Title", "value");
        controlPanel = new ControlPanel(1);

        hood = new TiltHood(tiltMotorID, shooterSolenoidID1, shooterSolenoidID2, reserveSolenoidID);
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

    //Tele-op init
    @Override
    public void teleopInit() {
        System.out.println("initalizing");
        stick1 = new XboxController(0);
        hood.resetShooter();
        ledManager.yellow();
        System.out.println("done initalizing");
    }

    //Tele-op periodic
    @Override
    public void teleopPeriodic() {

        WarningLed warning = new WarningLed("orange");

        //ledManager.capoLEDMode();TODO

        if (/*controlPanel.killSwitch()TODO*/ true) {

            // Drive
            drive.Teleop();
            
            /*TODO
            if(stick1.getButton(6)) {
                safetyMode = !safetyMode;
                System.out.println("safety is " + safetyMode);
                Timer.delay(1);
            }
            */
            
            //Toggle compressor with Left-bumper
            if(xboxController.getButton(5) || stick1.getButton(5)) {
                System.out.println("toggling compressor");
                if(compressorEnabled == true) {
                    TiltHood.compressor.enableDigital();
                }
                else {
                    TiltHood.compressor.disable();
                }
                
                compressorEnabled = !compressorEnabled;
                
                Timer.delay(1);
            }
        


            // Shooter
            // getVoltage returns a voltage between 0 and 5v
            // The REV website states that with this model of sensor 5v = 200 psi
            if (true/*safetyMode8*//*controlPanel.safetySwitch()TODO*/) {
                warning.start();
                chargePSI = pressureSensor.getVoltage() * 40;

                //Fire
                if ((false)) {
                    isPrefiring = false;
                    prefiringStrobe = false;
                    hood.closeReserve();
                    hood.fireShot();
                }
                /*
                } else {
                    hood.resetShooter();
                    //hood.closeReserve();
                    if (chargePSI < 90) {
                        hood.openReserve();
                    } else {
                        hood.closeReserve();
                    }
                }
                */
                //Pre-fire strobe
                else if(false) {
                    prefiringStrobe = !prefiringStrobe;
                    Timer.delay(0.5);
                }
                //Pre-fire
                else if(false) {
                    isPrefiring = !isPrefiring;
                    Timer.delay(0.5);
                }
                else if(isPrefiring == true) {
                    hood.openReserve();
                    if(prefiringStrobe) {
                        ledManager.yellow();
                        Timer.delay(0.15);
                        ledManager.test();
                        Timer.delay(0.15);
                    }
                    else {
                        ledManager.test();
                    }
                    System.out.println("pre-firing");
                }
                else {
                    hood.closeAll();
                    //ledManager.Rainbow3();
                    if(xboxController.getButton(2)) {
                        ledManager.yellow();
                    }
                    if(xboxController.getButton(3)) {
                        ledManager.purple();
                    }
                    if(xboxController.getButton(1)) {
                        ledManager.Rainbow3();
                    }
                    if(xboxController.getButton(4)) {
                        ledManager.blunwhite3();
                    }
                }

                //Disable compressor if PSI above 120
                if(chargePSI >= 120) {
                    TiltHood.compressor.disable();
                    compressorEnabled = false;
                }
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
