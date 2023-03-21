// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ConeStick;
import frc.robot.subsystems.DriveTrain;

public class StickControl extends CommandBase {
  private ConeStick joustingStick;
  private Joystick p2;

  /** Creates a new ArcadeDrive. */
  public StickControl(ConeStick coneStick, Joystick stick) {
    joustingStick = coneStick;
    p2 = stick;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(coneStick);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double yAxis = p2.getRawAxis(Constants.XBOX_LEFT_Y);

    if (yAxis < 0.05 && yAxis > -0.05)
      yAxis = 0;

    joustingStick.setSpeed(yAxis);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    joustingStick.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
