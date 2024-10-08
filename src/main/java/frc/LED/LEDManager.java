package frc.LED;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import frc.Constants;
import frc.controllers.ControlPanel;
import frc.controllers.XboxController;


public class LEDManager {
    XboxController xboxController = new XboxController(Constants.XBOX_CONTROLLER_PORT);
    ControlPanel panelofcontrol = new ControlPanel(Constants.PANEL_OF_CONTROL_PORT);
    ControlPanel controlPanel;
    static AddressableLED LEDRGB = new AddressableLED(Constants.LED_PORT);
    static AddressableLEDBuffer LEDBUFFER = new AddressableLEDBuffer(Constants.LED_LENGTH);
    //static AddressableLEDBuffer FRONTLEDBUFFER = new AddressableLEDBuffer(94);

    Timer timer = new Timer();
    boolean swap = false;
    int firstPixelHue = 0;

    public void Init() {
        timer.start();
        //LEDRGB.setLength(LEDBUFFER.getLength());
        //LEDRGB.setData(LEDBUFFER);
        //LEDRGB.start();
    }

    public void test() {
        for(var i = 0; i < LEDBUFFER.getLength(); i++) {
            LEDBUFFER.setRGB(i, 255, 0, 0);
        }
        LEDRGB.setLength(LEDBUFFER.getLength());
        LEDRGB.setData(LEDBUFFER);
        LEDRGB.start();
    }

    public void yellow() {
        for(var i = 0; i < LEDBUFFER.getLength(); i++) {
            LEDBUFFER.setRGB(i, 255, 255, 0);
        }
        LEDRGB.setLength(LEDBUFFER.getLength());
        LEDRGB.setData(LEDBUFFER);
        LEDRGB.start();

  
    }

    public void Rainbow() {
        int[][] Rainer = new int[][]{{255, 0, 0}, {245, 167, 12}, {255, 248, 9}, {0, 255, 0}, {0, 255, 255}, {0, 0, 255}, {128, 0, 255}, {255, 0, 140}};
        for (int Rainbow = 0; Rainbow < LEDBUFFER.getLength(); Rainbow++) {
            LEDBUFFER.setRGB(Rainbow, Rainer[Rainbow % 8][0], Rainer[Rainbow % 8][1], Rainer[Rainbow % 8][2]);
            //LEDRGB.setData(LEDBUFFER);
            LEDRGB.setLength(LEDBUFFER.getLength());
            LEDRGB.setData(LEDBUFFER);
            LEDRGB.start();
        }
        //LEDRGB.setData(LEDBUFFER);
    }

    public void Rainbow2() {
        for(var i = 0; i < LEDBUFFER.getLength(); i++) {
            final var hue = (firstPixelHue + (i * 180 / LEDBUFFER.getLength())) % 180;
            LEDBUFFER.setHSV(i, hue, 255, 128);
        }
        firstPixelHue += 3;
        firstPixelHue %= 180;
    }

    public void Rainbow3() {
        Rainbow2();
        LEDRGB.setData(LEDBUFFER);
    }

    public void DOLPHIN() {
        for (int blue = 0; blue < LEDBUFFER.getLength(); blue++) {
            LEDBUFFER.setRGB(blue, 25, 19, 210);
        }
        for (int grey = 0; grey < LEDBUFFER.getLength(); grey++) {
            LEDBUFFER.setRGB(grey, 139, 139, 141);
            //LEDRGB.setData(LEDBUFFER);
        }
        //LEDRGB.setData(LEDBUFFER);
    }


    public void capoLEDMode() {
        //Each second, alternates between black and yellow & yellow and black LEDs
        if (timer.get() >= 1) {
            for (int i = 0; i < LEDBUFFER.getLength(); i++) {
                int color = ((i % 2 == 0) ^ (swap)) ? 0 : 255;
                LEDBUFFER.setRGB(i, color, color, 0);
            }
            swap = !swap;
            timer.reset();
            LEDRGB.setLength(LEDBUFFER.getLength());
            LEDRGB.setData(LEDBUFFER);
            LEDRGB.start();
            //LEDRGB.setData(LEDBUFFER);
        }
        //LEDRGB.setData(LEDBUFFER);
    }

    public void safetymode() throws InterruptedException {
            int safetylight = 0;
            while(controlPanel.safetySwitch()) {
                LEDBUFFER.setRGB(safetylight, 255, 164, 0);
                LEDBUFFER.setRGB(safetylight, 0, 0, 0);
                if(xboxController.getButton(4) || panelofcontrol.shoot()){
                    LEDBUFFER.setRGB(safetylight, 255, 164, 0);
                    wait(3000);
                }
            }
    }

}