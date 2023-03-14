// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;

public final class Autos {
  /** Example static factory for an autonomous command. */

  public static CommandBase coneCommunity(Arm arm, Claw claw, DriveTrain driveTrain) {
    return Commands.sequence(
        new Close(claw),
        new ArmFloat(arm),
        new Open(claw),
        Commands.parallel(
            new ArcadeDriveAmount(driveTrain, -0.5, 0).withTimeout(1.5),
            new Close(claw),
            new ArmInside(arm)));
  }

  public static CommandBase coneBalance(Arm arm, Claw claw, DriveTrain driveTrain) {
    return Commands.sequence(
        new Close(claw),
        new ArmFloat(arm),
        new Open(claw),
        Commands.parallel(
            new Close(claw),
            new ArmInside(arm)),
        new ArcadeDriveAmount(driveTrain, -0.625, 0).withTimeout(1.5),
        new BalanceDrive(driveTrain));
  }

  public static CommandBase BlueconeBalance(Arm arm, Claw claw, DriveTrain driveTrain) {
    return Commands.sequence(
        new Close(claw),
        new ArmFloat(arm),
        new Open(claw),
        Commands.parallel(
            new Close(claw),
            new ArmInside(arm)),
        //new ArcadeDriveAmount(driveTrain, -0.5, 0).withTimeout(2),
        new ArcadeDriveAmount(driveTrain, -0.7, 0).withTimeout(1.5),

        new BalanceDrive(driveTrain));
  }

  private Autos() {
    throw new UnsupportedOperationException("This is a utility class!");
  }
}
