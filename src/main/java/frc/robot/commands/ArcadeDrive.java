// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class ArcadeDrive extends CommandBase {
  private DriveTrain dt;
  private Joystick p1;

  /** Creates a new ArcadeDrive. */
  public ArcadeDrive(DriveTrain driveTrain, Joystick stick) {
    dt = driveTrain;
    p1 = stick;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double yAxis = p1.getRawAxis(Constants.PS4_LEFT_Y);
    double xAxis = p1.getRawAxis(Constants.PS4_RIGHT_X);

    xAxis *= 0.75;

    if (yAxis < 0.05 && yAxis > -0.05)
      yAxis = 0;
    if (xAxis < 0.1 && xAxis > -0.1)
      xAxis = 0;

    boolean reverse = p1.getRawButton(Constants.PS4_L_BUMPER);

    // dt.arcadeDriveJoystick(-yAxis, xAxis, fullPower);
    if (!reverse) {
      yAxis *= -1;
    }
    dt.arcadeDriveJoystick(yAxis, xAxis, false);
  }

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
