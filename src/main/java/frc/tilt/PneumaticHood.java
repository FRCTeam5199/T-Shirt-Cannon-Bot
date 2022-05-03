package frc.tilt;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class PneumaticHood extends Compressor {
    private double movementSpeed = 0.2;
    public PneumaticHood(int module, PneumaticsModuleType moduleType) { super(module, moduleType); }
    public PneumaticHood(int module, PneumaticsModuleType moduleType, double speed) { super(module, moduleType); movementSpeed = speed; }
    
    public void move(double units){
        
    }
    public void moveTo(double position){

    }
}