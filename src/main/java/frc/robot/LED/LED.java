package frc.robot.LED;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;

public class LED extends SubsystemBase{
    CommandXboxController xboxController;
    static AddressableLED LEDRGB;
    static AddressableLEDBuffer LEDBUFFER;
    Timer timer = new Timer();
    boolean swap = false;
    int firstPixelHue = 0;

    public LED() {
        xboxController = new CommandXboxController(Constants.XBOX_CONTROLLER_PORT);
        LEDRGB = new AddressableLED(Constants.LED_PORT);
        LEDBUFFER = new AddressableLEDBuffer(Constants.LED_LENGTH);

        init();
    }

    public void init() {
        timer.start();
        //LEDRGB.setLength(LEDBUFFER.getLength());
        //LEDRGB.setData(LEDBUFFER);
        //LEDRGB.start();
    }

    public Command test() {
        return this.runOnce(() -> {
            for (var i = 0; i < LEDBUFFER.getLength(); i++) {
                LEDBUFFER.setRGB(i, 255, 0, 0);
            }
            LEDRGB.setLength(LEDBUFFER.getLength());
            LEDRGB.setData(LEDBUFFER);
            LEDRGB.start();
        });
    }

    public Command yellow() {
        return this.runOnce(() -> {
            for(var i = 0; i < LEDBUFFER.getLength(); i++) {
                LEDBUFFER.setRGB(i, 255, 255, 0);
            }
            LEDRGB.setLength(LEDBUFFER.getLength());
            LEDRGB.setData(LEDBUFFER);
            LEDRGB.start();
        });
    }

    public Command Rainbow() {
        return this.runOnce(() -> {
            int[][] Rainer = new int[][]{{255, 0, 0}, {245, 167, 12}, {255, 248, 9}, {0, 255, 0}, {0, 255, 255}, {0, 0, 255}, {128, 0, 255}, {255, 0, 140}};
            for (int Rainbow = 0; Rainbow < LEDBUFFER.getLength(); Rainbow++) {
                LEDBUFFER.setRGB(Rainbow, Rainer[Rainbow % 8][0], Rainer[Rainbow % 8][1], Rainer[Rainbow % 8][2]);
                //LEDRGB.setData(LEDBUFFER);
                LEDRGB.setLength(LEDBUFFER.getLength());
                LEDRGB.setData(LEDBUFFER);
                LEDRGB.start();
            }
        //LEDRGB.setData(LEDBUFFER);
        });
    }

    public Command Rainbow2() {
        return this.runOnce(() -> {
            for (var i = 0; i < LEDBUFFER.getLength(); i++) {
                final var hue = (firstPixelHue + (i * 180 / LEDBUFFER.getLength())) % 180;
                LEDBUFFER.setHSV(i, hue, 255, 128);
            }
            firstPixelHue += 3;
            firstPixelHue %= 180;
        });
    }

    public Command Rainbow3() {
        return this.runOnce(() -> {
            Rainbow2();
            LEDRGB.setData(LEDBUFFER);
        });
    }

    public Command DOLPHIN() {
        return this.runOnce(() -> {
            for (int blue = 0; blue < LEDBUFFER.getLength(); blue++) {
                LEDBUFFER.setRGB(blue, 25, 19, 210);
            }
            for (int grey = 0; grey < LEDBUFFER.getLength(); grey++) {
                LEDBUFFER.setRGB(grey, 139, 139, 141);
                //LEDRGB.setData(LEDBUFFER);
            }
            //LEDRGB.setData(LEDBUFFER);
        });
    }


    public Command capoLEDMode() {
        return this.runOnce(() -> {
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
        });
    }
}