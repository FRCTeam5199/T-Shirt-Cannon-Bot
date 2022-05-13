package frc.LED;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class LEDManager {
    AddressableLED strip1 = new AddressableLED(1);
    AddressableLED strip2 = new AddressableLED(1);
    AddressableLED strip3 = new AddressableLED(1);
    AddressableLED strip4 = new AddressableLED(1);

    public void LEDModeCapoRally() {
        strip1.setLength(500);
        strip2.setLength(500);
        strip3.setLength(500);
        strip4.setLength(500);
    }
}