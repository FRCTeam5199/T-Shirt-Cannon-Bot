package frc.controllers;

import edu.wpi.first.wpilibj.Joystick;

public class ControlPanel {
    private final Joystick panel;

    public ControlPanel(int n) {
        panel = new Joystick(n);
    }

    public boolean shoot(){
        //TODO was 0
        return panel.getRawButton(5);
    }

    public boolean button1 () {
        return panel.getRawButton(1);
    }

    public boolean button2 () {
        return panel.getRawButton(2);
    }

    public boolean button3 () {
        return panel.getRawButton(3);
    }

    //added by johnathon
    public boolean button4 () {return panel.getRawButton(4);}

     //added by Ethan
     //TODO map both of these to the control panel
     public boolean safetySwitch () {return panel.getRawButton(5);}

     public boolean killSwitch () {return panel.getRawButton(6);}
}