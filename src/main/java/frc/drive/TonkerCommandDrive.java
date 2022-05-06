package frc.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.controllers.XboxController;
import frc.drive.Tonkerdrive;

public class TonkerCommandDrive extends Command {
    private XboxController;
    public TonkerCommandDrive (XboxController controller) {
        requires(drive.Tonkerdrive);
        XboxController = controller;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

    }

}