package frc.controllers;

import edu.wpi.first.wpilibj.Joystick;

public class ControlPanel {
    private final Joystick panel;

    public ControlPanel(int n) {
        panel = new Joystick(n);
    }

    public boolean shoot(){
        return panel.getRawButton(0);
    }

}