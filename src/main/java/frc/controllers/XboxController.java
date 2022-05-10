package frc.controllers;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;

public class XboxController {
    public Joystick stick1;

    public XboxController(int n) {
        stick1 = new Joystick(n);
    }

    public double getLXAxis() {
        return (stick1.getRawAxis(0) > 0.05 ? stick1.getRawAxis(0) : 0);
    }

    public double getLYAxis() {
        return -(stick1.getRawAxis(1) > 0.05 ? stick1.getRawAxis(1) : 0);
    }

    public double getRXAxis() {
        return (stick1.getRawAxis(4) > 0.05 ? stick1.getRawAxis(4) : 0);
    }

    public double getRYAxis() {
        return -(stick1.getRawAxis(5) > 0.05 ? stick1.getRawAxis(5) : 0);
    }

    public double getRTrigger() {
        return (stick1.getRawAxis(3) > 0.05 ? stick1.getRawAxis(3) : 0);
    }

    public double getLTrigger() {
        return (stick1.getRawAxis(2) > 0.05 ? stick1.getRawAxis(2) : 0);
    }

    public boolean getButton(int n) {
        return stick1.getRawButton(n);
    }



    public void setLRumble(double n) {
        stick1.setRumble(RumbleType.kLeftRumble, n);
    }

    public void setRRumble(double n) {
        stick1.setRumble(RumbleType.kRightRumble, n);
    }
}
