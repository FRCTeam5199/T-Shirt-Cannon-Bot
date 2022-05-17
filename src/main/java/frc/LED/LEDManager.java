package frc.LED;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class LEDManager {
    AddressableLED LEDRGB  = new AddressableLED(1);
    AddressableLEDBuffer LEDBUFFER = new AddressableLEDBuffer(500);

    public void Init() {
        LEDRGB.setLength(LEDBUFFER.getLength());
        LEDRGB.setData(LEDBUFFER);

    }

}