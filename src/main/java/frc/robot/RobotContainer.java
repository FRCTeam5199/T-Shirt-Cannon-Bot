// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.drive.Tonkerdrive;
import frc.robot.subsystems.TiltHood;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController xboxController = new CommandXboxController(Constants.XBOX_CONTROLLER_PORT);
  public static final TiltHood tiltHood = new TiltHood();
  public static final Tonkerdrive tonkerDrive = new Tonkerdrive();
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    InstantCommand tiltHoodLow = new InstantCommand(() -> tiltHood.setToAngle(Constants.ANGLE_POSITIONS[0]));
    InstantCommand tiltHoodMedium = new InstantCommand(() -> tiltHood.setToAngle(Constants.ANGLE_POSITIONS[1]));
    InstantCommand tiltHoodHigh = new InstantCommand(() -> tiltHood.setToAngle(Constants.ANGLE_POSITIONS[2]));

    SequentialCommandGroup fireShot = new SequentialCommandGroup(
      new InstantCommand(() -> tiltHood.openReserve()),
      new WaitCommand(.5),
      new InstantCommand(() -> tiltHood.fireShot()),
      new WaitCommand(.5),
      new InstantCommand(() -> tiltHood.closeAll())
    );

    xboxController.povLeft().onTrue(tiltHoodLow);
    xboxController.povDown().onTrue(tiltHoodMedium);
    xboxController.povRight().onTrue(tiltHoodHigh);

    xboxController.a().onTrue(fireShot);
  }
}
