// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXL362;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  private CANSparkMax fL = new CANSparkMax(Constants.FRONT_LEFT, MotorType.kBrushless);
  private CANSparkMax fR = new CANSparkMax(Constants.FRONT_RIGHT, MotorType.kBrushless);
  private CANSparkMax bL = new CANSparkMax(Constants.BACK_LEFT, MotorType.kBrushless);
  private CANSparkMax bR = new CANSparkMax(Constants.BACK_RIGHT, MotorType.kBrushless);

  ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  Accelerometer accel = new ADXL362(SPI.Port.kMXP, Accelerometer.Range.k8G);

  private final double multiplier = 0.75;
  private final double balanceMult = 0.01;

  private double GYRO_RESTING;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    fR.setInverted(true);
    bR.setInverted(true);

    GYRO_RESTING = gyro.getAngle();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void arcadeDrive(double speed, double curve) {
    double leftSpeed = (speed + curve) * multiplier;
    double rightSpeed = (speed - curve) * multiplier;
    fL.set(leftSpeed);
    fR.set(rightSpeed);
    bL.set(leftSpeed);
    fR.set(rightSpeed);
  }

  public void tankDrive(double leftspeed, double rightspeed) {
    fL.set(leftspeed);
    fR.set(rightspeed);
    bL.set(leftspeed);
    bR.set(rightspeed);
  }

  public void stop() {
    fL.set(0);
    fR.set(0);
    bL.set(0);
    bR.set(0);
  }

  public void balance() {
    double robotAngle = gyro.getAngle();
    double error = GYRO_RESTING - robotAngle;

    if (error < 1 && error > -1)
      error = 0;

    double driveSpeed = -balanceMult * error;

    tankDrive(driveSpeed, driveSpeed);
  }
}
