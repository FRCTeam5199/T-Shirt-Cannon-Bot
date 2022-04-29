package frc.motors;
 
import frc.misc.PID;
import frc.misc.UserInterface;
import frc.motors.followers.AbstractFollowerMotorController;
import frc.robot.Robot;
import frc.robot.Main;

import java.util.ArrayList;

import static frc.robot.Robot.robotSettings


public abstract class AbstractMotorController {
    public static final ArrayList<AbstractMotorController> motorList = new ArrayList<>();


    public Double sensorToRealDistanceFactor;
    protected Boolean FailureFlag = false;
    protected String potentialfix;
    protected Boolean isOverheated;

    public abstract String getname();

    public abstract void resetencoder();

    public abstract AbstractMotorController setPID(PID pid);

    public abstract void moveatvelocity(double amount);

    public abstract void moveatposition(double position);

    public abstract void moveatvoltage(double voltsin);

    public abstract AbstractMotorController setbrake(boolean brake);

    public abstract double getrotates();

    public abstract double getspeed();

    public abstract double getvoltage();

    public abstract AbstractMotorController setcurrent(int CLimit);

    public abstract AbstractMotorController setopenloopRamprate(double timetomax);

    public abstract String getsuggestedfix();

    public abstract boolean isfailed();

    public static void resetallmotors() {

        for(AbstractMotorController motor : motorList) {
        motor.moveatpercent(0);
        }
    }

    public abstract void moveatpercent(double percent);

    protected AbstractMotorController(){
        motorList.add(this);

    }

    public AbstractMotorController follow(AbstractMotorController leader) {
        return follow(leader, false);


    }

    public abstract AbstractMotorController follow(AbstractMotorController leader, boolean invert);

    public void setSensortoRealDistanceFactor(double s2rf) {
        sensorToRealDistanceFactor = s2rf;

    }





    protected boolean isTemperatureAcceptable() {
        if(Robot.robotSettings.ENABLE_OVERHEAT_DETECTION) {
            if(getMotorTemperature() >= Robot.robotSettings.OVERHEAT_THRESHOLD){
                if(!isOverheated) {
                    UserInterface.smartDashboardPutBoolean("OVERHEAT" + getID, false);
                    if (robotSettings.ENABLE_MEMES)
                        Main.pipepline.sendAlarm(Alarms.Overheat, true);
                    isOverheated = true;

                }
            }
            else if(isOverheated && getMotorTemperature() < Robot.robotSettings.OVERHEAT_THRESHOLD - 5){
                if (robotSettings.ENABLE_MEMES)
                    Main.pipeline.sendAlarm(Alarms.Overheat, false);  
                isOverheated = false;
                UserInterface.smartDashboardPutBoolean("OVERHEAT" + getID(), true);
            }
            return !isOverheated;

        }else {
            return true;
        }   
    }

    public abstract double getMotorTemperature();

    public abstract int getID();

    public abstract int getMaxRPM();

    public enum SupportedMotors{
        VICTOR(18730), SERVO;

        public final int Max_SPEED_RPM;

        SupportedMotors(int speed) {
            MAX_SPEED_RPM = speed;
        }

        SupportedMotors() {
            MAX_SPEED_RPM = 0;
        }
    }
}





