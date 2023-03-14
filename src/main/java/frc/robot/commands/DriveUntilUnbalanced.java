// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveUntilUnbalanced extends CommandBase {
  DriveTrain dt;
  double speed, curve;

  /** Creates a new DriveUntilUnbalanced. */
  public DriveUntilUnbalanced(DriveTrain d, double s, double c) {
    // Use addRequirements() here to declare subsystem dependencies.
    dt = d;
    speed = s;
    curve = c;
    addRequirements(dt);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    dt.stop();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    dt.arcadeDrive(speed, curve);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //return !dt.isBalanced();
     return dt.chargeStationUnbalanced();
  }
}
