package frc.misc;

import frc.robot.Robot;
import frc.selfdiagnostic.ISimpleIssue;

public interface ISubsystem {
    

    void init();

    SubsystemStatus = getSubsystemStatus();

    void updateTeleop();

    void updateGeneric();

    void initTeleop();

    
}
