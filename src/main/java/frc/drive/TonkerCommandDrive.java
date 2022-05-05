package frc.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TonkerCommandDrive extends Command {
    private XboxController;
    public TonkerCommandDrive (XboxController controller) {
        requires(Robot.Tonkerdrive);
        XboxController = controller;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {

    }

}