package frc.LED;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import frc.controllers.ControlPanel;
import frc.controllers.XboxController;


public class LEDManager {
    XboxController xboxController = new XboxController(0);
    ControlPanel panelofcontrol = new ControlPanel(1);
    ControlPanel controlPanel;
    static AddressableLED LEDRGB = new AddressableLED(0);
    static AddressableLEDBuffer LEDBUFFER = new AddressableLEDBuffer(89);
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

    public void purple() {
        for(var i = 0; i < LEDBUFFER.getLength(); i++) {
            LEDBUFFER.setRGB(i, 138, 43, 226);
        }
        LEDRGB.setLength(LEDBUFFER.getLength());
        LEDRGB.setData(LEDBUFFER);
        LEDRGB.start();
    }

    public void blunwhite(){
        for(var i = 0; i < LEDBUFFER.getLength(); i++){
            if(i%2 == 0){
                LEDBUFFER.setRGB(i, 0, 0, 255);
            }else{
                LEDBUFFER.setRGB(i, 255, 255, 255);
            }
        }
        LEDRGB.setLength(LEDBUFFER.getLength());
        LEDRGB.setData(LEDBUFFER);
        LEDRGB.start();
    }

    public void blunwhite2(){
        int blueFade = 0;
        int otherFade = 0;
        boolean blueFadeIn = false;

        for(var i = 0; i < LEDBUFFER.getLength(); i++){
            if(i%2 == 0){
                LEDBUFFER.setRGB(i, otherFade, otherFade, blueFade);
            }else{
                LEDBUFFER.setRGB(i, 255-otherFade, 255-otherFade, 255-blueFade);
            }
            
            if(blueFadeIn && blueFade < 255) {
                blueFade++;
            }
            else if(blueFadeIn) {
                blueFadeIn = false;
            }
            else if(!blueFadeIn && blueFade < 255) {
                blueFade++;
                otherFade++;
            }
            else if(!blueFadeIn) {
                blueFade--;
            }
            else {
                blueFade--;
                otherFade--;
            }
            //System.out.println("bluefade" + blueFade);
            //System.out.println("otherfade" + otherFade);
            //LEDRGB.setData(LEDBUFFER);
        }
        //LEDRGB.setLength(LEDBUFFER.getLength());
        //LEDRGB.setData(LEDBUFFER);
        //LEDRGB.start();
    }

    public void blunwhite3() {
        blunwhite2();
        LEDRGB.setData(LEDBUFFER);
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