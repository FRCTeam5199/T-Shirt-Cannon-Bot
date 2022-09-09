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
    //Compressor compressor = new Compressor(0, PneumaticsModuleType.REVPH);

    //pre fireing
    private boolean isPrefiring = false;

    //Safety
    private boolean safetyMode = false;

    @Override
    public void robotInit() {
        System.out.println("initializing");
        xboxController = new XboxController(0);
        stick1 = new XboxController(0);
        drive.driveInit();
        // removed this "}" here
        // simpleWidget = Shuffleboard.getTab("Tab").add("Title", "value");
        controlPanel = new ControlPanel(1);

        hood = new TiltHood(tiltMotorID, shooterSolenoidID1, shooterSolenoidID2, reserveSolenoidID);

        //Enables compressor if the shooter is also
        /*TODO
        if(shooterEnabled) {
            TiltHood.compressor.enableDigital();
        }
        else {
            TiltHood.compressor.disable();
        }
        */

        //ledManager.test(); TODO

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
        System.out.println("initalizing");
        stick1 = new XboxController(0);
        hood.resetShooter();
        ledManager.yellow();
        System.out.println("done initalizing");
    }

    @Override
    public void teleopPeriodic() {
        //System.out.println("tele-op");
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
            //System.out.println("compressor enabled is " + compressorEnabled);
            //TiltHood.compressor.enableDigital();
            //compressor.enableDigital();
            
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
            // TODO Currently firing at 110 PSI, check with shooter group that this is what
            // they want
            if (true/*safetyMode8*//*controlPanel.safetySwitch()TODO*/) {
                warning.start();
                chargePSI = pressureSensor.getVoltage() * 40;
                if ((xboxController.getButton(4) || controlPanel.shoot()) && shooterEnabled /*&& chargePSI >= 60 TODO*/) {
                    isPrefiring = false;
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
                //Pre-fire
                else if(xboxController.getButton(2) || stick1.getButton(2)) {
                    isPrefiring = !isPrefiring;
                    Timer.delay(0.5);
                }
                else if(isPrefiring == true) {
                    hood.openReserve();
                    ledManager.test();
                    System.out.println("pre-firing");
                }
                else {
                    hood.closeAll();
                    ledManager.yellow();
                }

                //Disable compressor if PSI above 120
                if(chargePSI >= 120) {
                    TiltHood.compressor.disable();
                    compressorEnabled = false;
                }
            }

            // TODO Hood
            // note: control panel and button values have not been mapped, these might not
            // be the intended buttons
            // note: The hood.moveTo positions might not be correct

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
