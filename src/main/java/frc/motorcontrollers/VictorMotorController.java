package frc.motorcontrollers;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.misc.PID;

public class VictorMotorController extends AbstractMotorController{
    VictorSPX victor;

    public VictorMotorController(int ID){
        victor = new VictorSPX(ID);
    }

    @Override
    public AbstractMotorController setInvert(boolean invert) {
        victor.setInverted(invert);
        return this;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void resetEncoder() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public AbstractMotorController setPid(PID pid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void moveAtVelocity(double amount) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void moveAtPosition(double pos) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void moveAtVoltage(double voltIn) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public AbstractMotorController setBrake(boolean brake) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getRotations() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getSpeed() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getVoltage() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getCurrent() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public AbstractMotorController setCurrentLimit(int limit) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AbstractMotorController setCurrentLimit(int stallLimit, int freeLimit) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AbstractMotorController setOpenLoopRampRate(double timeToMax) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getSuggestedFix() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isFailed() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getMaxRPM() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setRealFactorFromMotorRPM(double r2rf, double t2tf) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void moveAtPercent(double percent) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public AbstractMotorController unfollow() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AbstractMotorController follow(AbstractMotorController leader, boolean invert) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public double getMotorTemperature() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getID() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setOutPutRange(double min, double max) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setPercent(double percent) {
        victor.set(ControlMode.PercentOutput, percent);
        
    }

    public void setPosition(double position){
        victor.set(ControlMode.Position, position);
    }
    
}
