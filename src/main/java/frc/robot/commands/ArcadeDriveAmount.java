// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class ArcadeDriveAmount extends CommandBase {
  DriveTrain dt;
  double s, c;

  /** Creates a new ArcadeDriveAmount. */
  public ArcadeDriveAmount(DriveTrain driveTrain, double speed, double curve) {
    // Use addRequirements() here to declare subsystem dependencies.
    dt = driveTrain;
    s = speed;
    c = curve;

    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    dt.arcadeDrive(s, c);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    dt.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
