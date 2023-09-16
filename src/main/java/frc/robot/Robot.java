// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import frc.Constants;
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
    public static ControlPanel controlPanel;
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
    private boolean hoodEnabled = true;

    // Inputs
    XboxController xboxController;

    // hood
    TiltHood hood;

    // LED Manager
    LEDManager ledManager = new LEDManager();

    // Pressure sensor
    AnalogInput pressureSensor;
    double chargePSI;

    //Compressor
    private boolean compressorEnabled = false;
    //Compressor compressor = new Compressor(0, PneumaticsModuleType.REVPH);

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
        xboxController = new XboxController(Constants.XBOX_CONTROLLER_PORT);
        stick1 = new XboxController(Constants.XBOX_CONTROLLER_PORT);
        drive.driveInit();
        // simpleWidget = Shuffleboard.getTab("Tab").add("Title", "value");
        controlPanel = new ControlPanel(Constants.CONTROL_PANEL_PORT);

        hood = new TiltHood(Constants.TILT_MOTOR_ID, Constants.SHOOTER_SOLENOID_ID_1, Constants.SHOOTER_SOLENOID_ID_2, Constants.RESERVE_SOLENOID_ID);
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
        stick1 = new XboxController(Constants.XBOX_CONTROLLER_PORT);
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
                if ((xboxController.getButton(4) || controlPanel.shoot()) && Constants.SHOOTER_ENABLED /*&& chargePSI >= 60 TODO*/) {
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
                else if(xboxController.getButton(3) || stick1.getButton(3) || controlPanel.safetySwitch()) {
                    prefiringStrobe = !prefiringStrobe;
                    Timer.delay(0.5);
                }
                //Pre-fire
                else if(xboxController.getButton(2) || stick1.getButton(2) || controlPanel.safetySwitch()) {
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
                    ledManager.Rainbow3();
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
