package frc.misc;

import frc.robot.Robot;
import frc.diagnostic.ISimpleIssue;

public interface ISubsystem {
    

    void init();

    SubsystemStatus = getSubsystemStatus();

    void updateTeleop();

    void updateGeneric();

    void initTeleop();

    void initDisabled();

    void initGeneric();

    String getSubsystemName();

    default void addtoMetalList() {
        Robot.subsystems.add(this);
    }


}
