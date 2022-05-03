package frc.tilt;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;

public class PWMHood extends VictorSPX {
    private double movementSpeed = 0.1;

    public PWMHood(int deviceNum, double speed) { super(deviceNum); movementSpeed=speed; } // constructor for custom motor speed
    public PWMHood(int deviceNum) { super(deviceNum); } // constructor for default motor speed

    public void setForward(double units) { super.set(ControlMode.PercentOutput, movementSpeed); } // percent output for moving forward and back, and one for clearing
    public void setBackward(double units) { super.set(ControlMode.PercentOutput, -1 * movementSpeed); }
    public void clear() { super.set(ControlMode.PercentOutput, 0.0); }


}