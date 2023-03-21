// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ConeStick;

public class StickFloor extends CommandBase {
  /** Creates a new StickFloor. */
  private ConeStick coneStick;
  public StickFloor(ConeStick c) {
    coneStick = c;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(c);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    coneStick.setCoast();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    coneStick.moveToFloor();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    coneStick.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return coneStick.atFloorPosition();
  }
}
