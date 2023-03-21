// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.ArmFloat;
import frc.robot.commands.ArmFloatInside;
import frc.robot.commands.ArmFloor;
import frc.robot.commands.ArmInside;
import frc.robot.commands.ArmFloatInside;
import frc.robot.commands.StickFloor;
import frc.robot.commands.StickInside;
import frc.robot.commands.Autos;
import frc.robot.commands.BalanceDrive;
import frc.robot.commands.Close;
import frc.robot.commands.ManualArm;
import frc.robot.commands.Open;
import frc.robot.commands.OpenSmallAmount;
import frc.robot.commands.SetBrake;
import frc.robot.commands.SetCoast;
import frc.robot.commands.StickControl;
import frc.robot.commands.StickFloat;
import frc.robot.commands.TankDrive;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.ConeStick;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  public static final Spark led = null;
  // Joysticks
  private final Joystick player1 = new Joystick(0);
  private final Joystick player2 = new Joystick(1);

  // Subsystems
  private final DriveTrain driveTrain = new DriveTrain();
  private final Arm arm = new Arm();
  private final Claw claw = new Claw();
  //private final ConeStick coneStick = new ConeStick();

  // Commands
  private final ArcadeDrive arcadeDrive = new ArcadeDrive(driveTrain, player2);
  private final TankDrive tankDrive = new TankDrive(driveTrain, player2);
  private final BalanceDrive balanceDrive = new BalanceDrive(driveTrain);

  private final Open openClaw = new Open(claw);
  private final Close closeClaw = new Close(claw);
  private final OpenSmallAmount openSmallAmount = new OpenSmallAmount(claw);

  private final ManualArm manualArm = new ManualArm(player1, arm);

  private final ArmInside armInside = new ArmInside(arm);
  private final ArmFloor armFloor = new ArmFloor(arm);
  private final ArmFloat armFloat = new ArmFloat(arm);

  private final SetCoast setCoast = new SetCoast(driveTrain);
  private final SetBrake setBrake = new SetBrake(driveTrain);

  private final ArmFloatInside armFloatInside = new ArmFloatInside(arm);

  /*private final StickFloor stickFloor = new StickFloor(coneStick);
  private final StickFloat stickFloat = new StickFloat(coneStick);
  private final StickInside stickInside = new StickInside(coneStick);

  private final StickControl stickControl = new StickControl(coneStick, player1);*/

  // Buttons
  private final JoystickButton rightBumper = new JoystickButton(player1, Constants.XBOX_R_BUMPER);
  private final JoystickButton leftBumper = new JoystickButton(player1, Constants.XBOX_L_BUMPER);

  private final JoystickButton xButton = new JoystickButton(player1, Constants.XBOX_X_BUTTON);
  private final JoystickButton yButton = new JoystickButton(player1, Constants.XBOX_Y_BUTTON);
  private final JoystickButton bButton = new JoystickButton(player1, Constants.XBOX_B_BUTTON);

  private final JoystickButton flightButton11 = new JoystickButton(player2, Constants.FLIGHT_BUTTON_11);
  private final JoystickButton flightButton7 = new JoystickButton(player2, 7);
  private final JoystickButton flightButton8 = new JoystickButton(player2, 8);

  //private final JoystickButton leftStick = new JoystickButton(player1, 9);

  private final JoystickButton aButton = new JoystickButton(player1, Constants.XBOX_A_BUTTON);

  private String selectedAuto;
  private final SendableChooser<String> chooser = new SendableChooser<>();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();

    chooser.addOption("Cone and Commmunity", Constants.CONE_COMMUNITY);
    chooser.addOption("BlueBalance", Constants.BLUE_CONE_BALANCE);
    chooser.setDefaultOption("Cone and Balance", Constants.CONE_BALANCE);
    chooser.addOption("cone_no_drive", Constants.NODRIVECONE);
    SmartDashboard.putData("Auto Choices", chooser);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for
   * {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    rightBumper.onTrue(openClaw);
    leftBumper.onTrue(closeClaw.withTimeout(1));

    xButton.toggleOnTrue(armInside);
    yButton.toggleOnTrue(armFloat);
    bButton.toggleOnTrue(armFloor);
    aButton.toggleOnTrue(Commands.parallel(
        armFloatInside,
        openSmallAmount));

    //leftStick.toggleOnTrue(stickFloat);

    flightButton11.toggleOnTrue(balanceDrive);

    flightButton7.whileTrue(setBrake);
    flightButton8.onTrue(setCoast);

    driveTrain.setDefaultCommand(arcadeDrive);
    arm.setDefaultCommand(manualArm);
    //coneStick.setDefaultCommand(stickControl);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    selectedAuto = chooser.getSelected();

    //switch (selectedAuto) {
     // case "cone-community":
      //  return Autos.coneCommunity(arm, claw, driveTrain);
      //case "cone-balance":
       // return Autos.coneBalance(arm, claw, driveTrain);
    //  case "blueConeBalance":
       // return Autos.BlueconeBalance(arm, claw, driveTrain);
     // case "cone_no_drive":
       // return Autos.cone(claw, arm, driveTrain);
     // default:
      //return Autos.coneCommunity(arm, claw, driveTrain);
    //}
    return Autos.coneCommunity(arm, claw, driveTrain);
  }
}