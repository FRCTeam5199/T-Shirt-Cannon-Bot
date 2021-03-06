package frc.LED;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;

public class LEDManager {
    static AddressableLED LEDRGB  = new AddressableLED(1);
    static AddressableLEDBuffer LEDBUFFER = new AddressableLEDBuffer(500);
    Timer timer = new Timer();
    boolean swap = false;

    public void Init() {
        timer.start();
        LEDRGB.setLength(LEDBUFFER.getLength());
        LEDRGB.setData(LEDBUFFER);
    }

    public void test() {
        int[][] Rainer = new int[][]{{255,0,0},{245,167,12}, {255,248,9}, {0,255,0},{0,255,255}, {0,0,255}, {128,0,255}, {255,0,140}};
        for (int Rainbow = 0; Rainbow < LEDBUFFER.getLength();Rainbow++){
            LEDBUFFER.setRGB(Rainbow, Rainer[Rainbow%8][0], Rainer[Rainbow%8][1], Rainer[Rainbow%8][2]);
        }
    }

    public void DOLPHIN(){
        for (int blue = 0; blue < LEDBUFFER.getLength(); blue++) {
            LEDBUFFER.setRGB(blue ,25, 19, 210);
        }
        for (int grey = 0; grey < LEDBUFFER.getLength(); grey++){
            LEDBUFFER.setRGB(grey, 139,139,141);
        }
    }

    
    public void capoLEDMode() {
        //Each second, alternates between black and yellow & yellow and black LEDs
        if (timer.get() >= 1) {
            for (int i = 0; i < LEDBUFFER.getLength(); i++) {
                int color = ((i%2 == 0) ^ (swap))? 0 : 255; 
                LEDBUFFER.setRGB(i, color, color, 0);
            }
            swap = !swap;
            timer.reset();
        }
    }
}