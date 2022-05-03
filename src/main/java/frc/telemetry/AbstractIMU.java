package frc.telemetry;

import frc.misc.ISubsystem;
import frc.misc.SubsystemStatus;
import frc.misc.utilFunctions;


public abstract class AbstractIMU implements ISubsystem {
    protected double startYaw;
    protected double[] why = new double [3];
    protected double[] startwhy = new double[3];

    public abstract void resetOdometry();
    
    public static AbstractIMU createIMU(SupportedIMU imuType) throws IllegalArgumentException {
        switch(imuType) {
            case PIGEON:
                return new WrappedPigeon();

            default:
                throw new IllegalArgumentException("connot make a" + imuType.name());
        }
    }
    
    protected AbstractIMU() {
        init();
        addToMetaList();
    }
    
    @Override
    public SubsystemStatus getSubsystemStatus() {
        return Yaw() != 0 ? SubsystemStatus.NOMINAL: SubsystemStatus.FAILED;
    }
    public double Yaw() {
        updateGeneric();
        return why[0];
    }

    @Override
    public void updateTeleop(){
        updateGeneric();
    }

    @Override
    public void updateGeneric(){
        IMUNonOpIssue.handleIssue(this, getSubsystemName(), why[0] != 0);
    }

    @Override
    public String getSubsystemName() {
        return "IMU";
    }

    public double yawWrap() {
        return UtilFunction.mathematicalMod(relativeYaw() + 180, 360) - 180;
    }
    public double relativeYaw() {
        return(Yaw() - startYaw);
    }
    public enum SupportedIMU {
        PIGEON
    }

}

    

