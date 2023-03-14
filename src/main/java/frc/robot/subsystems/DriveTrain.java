// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
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
  // Accelerometer accel = new ADXL362(SPI.Port.kMXP, Accelerometer.Range.k8G);

  private final double multiplier = 0.75;
  private final double balanceMult = 0.0085;

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

  public void arcadeDriveJoystick(double speed, double curve, boolean fullPower) {
    double leftSpeed, rightSpeed;

    if (fullPower) {
    leftSpeed = (speed + curve);
     rightSpeed = (speed - curve) ;
    }
    else {
       leftSpeed = (speed + curve) * multiplier;
     rightSpeed = (speed - curve) * multiplier;
    }

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

  public void fullSpeed() {
    fL.set(1);
    fR.set(1);
    bL.set(1);
    bR.set(1);

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

    if (isBalanced())
      error = 0;

    double driveSpeed = -balanceMult * error;
    System.out.println(driveSpeed);

    tankDrive(driveSpeed, driveSpeed);
  }

  public boolean isBalanced() {
    double robotAngle = gyro.getAngle();
    double error = GYRO_RESTING - robotAngle;

    return error > -8 && error < 8;
  }

  public boolean chargeStationUnbalanced() {
    double robotAngle = gyro.getAngle();
    double error = GYRO_RESTING - robotAngle;

    // check if tilting forward
    boolean tiltingForward = error > 11 && error < 16;
    boolean tiltingBackward = error < -11 && error > -16;

    return tiltingForward || tiltingBackward;
  }

  public void setToBrake() {
    fL.setIdleMode(IdleMode.kBrake);
    fR.setIdleMode(IdleMode.kBrake);
    bL.setIdleMode(IdleMode.kBrake);
    bR.setIdleMode(IdleMode.kBrake);
  }

  public void setToCoast() {
    fL.setIdleMode(IdleMode.kCoast);
    fR.setIdleMode(IdleMode.kCoast);
    bL.setIdleMode(IdleMode.kCoast);
    bR.setIdleMode(IdleMode.kCoast);
  }
}
