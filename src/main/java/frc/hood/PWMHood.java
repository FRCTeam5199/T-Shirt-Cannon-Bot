package frc.hood;

import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

public class PWMHood extends PWMVictorSPX {
    private double movementSpeed = 0.2;

    public PWMHood(int channel, double speed) { super(channel); movementSpeed=speed; } // constructor for custom motor speed
    public PWMHood(int channel) { super(channel); } // constructor for default motor speed

    public void move(double units){
        
    }
    public void moveTo(double position){

    }

}