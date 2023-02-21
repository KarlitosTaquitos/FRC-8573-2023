// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.ArmFloat;
import frc.robot.commands.ArmFloor;
import frc.robot.commands.ArmInside;
import frc.robot.commands.Autos;
import frc.robot.commands.Close;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.ManualArm;
import frc.robot.commands.Open;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  // Joysticks
  private final Joystick player1 = new Joystick(0);

  // Subsystems
  private final DriveTrain driveTrain = new DriveTrain();
  private final Arm arm = new Arm();
  private final Claw claw = new Claw();
 

  // Commands
  private final ArcadeDrive arcadeDrive = new ArcadeDrive(driveTrain, player1);
  private final TankDrive tankDrive = new TankDrive(driveTrain, player1);
  private final ManualArm manualArm = new ManualArm(player1, arm);

  private final Open openClaw = new Open(claw);
  private final Close closeClaw = new Close(claw);

  private final ArmInside armInside = new ArmInside(arm);
  private final ArmFloor armFloor = new ArmFloor(arm);
  private final ArmFloat armFloat = new ArmFloat(arm);

  // Buttons
  private final JoystickButton rightBumper = new JoystickButton(player1, Constants.XBOX_R_BUMPER);
  private final JoystickButton leftBumper = new JoystickButton(player1, Constants.XBOX_L_BUMPER);

  private final JoystickButton xButton = new JoystickButton(player1, Constants.XBOX_X_BUTTON);
  private final JoystickButton yButton = new JoystickButton(player1, Constants.XBOX_Y_BUTTON);
  private final JoystickButton bButton = new JoystickButton(player1, Constants.XBOX_B_BUTTON);

  


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
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.

    rightBumper.onTrue(openClaw);
    leftBumper.onTrue(closeClaw.withTimeout(1));

    xButton.onTrue(armInside);
    yButton.onTrue(armFloat);
    bButton.onTrue(armFloor);



    driveTrain.setDefaultCommand(arcadeDrive);
    arm.setDefaultCommand(manualArm);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
