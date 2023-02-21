// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Arm extends SubsystemBase {
  private CANSparkMax arm = new CANSparkMax(Constants.ARM, MotorType.kBrushless);
  private RelativeEncoder encoder = arm.getEncoder();

  private final double STARTING_POS = 0;
  private final double FLOOR_POS = 60;
  private final double MID_POS = 30;
  private final double FLOAT_POS = 45;

  public int state = 0;

  /** Creates a new Arm. */
  public Arm() {
    arm.setIdleMode(IdleMode.kBrake);
    encoder.setPosition(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void manualArm(double speed) {
    arm.set(powerCurve(speed));
  }

  public double powerCurve(double speed) {
    double position = encoder.getPosition();
    double powerFunction;

    // SPEED +
    if (speed > 0) {
      if (position <= STARTING_POS) {
        powerFunction = 0.5;
      } else if (position > 0 && position <= FLOOR_POS) {
        powerFunction = (-0.015 * position) + 1;
      } else {
        powerFunction = 0.1;
      }
    }
    // SPEED -
    else if (speed < 0) {
      if (position <= STARTING_POS) {
        powerFunction = 0.25;
      } else if (position > 0 && position <= FLOOR_POS) {
        powerFunction = (0.015 * position) + 0.1;
      } else {
        powerFunction = 0.625;
      }
    }
    // SPEED 0
    else {
      if (atFloatPosition()) {
        return maintainFloatPosition();
      } else {
        return 0;
      }
    }

    // VERY SENSITIVE FUNCTIONS DO NOT TOUCH
    /*
     * OVERCOMPLICATED THIS
     * if (speed >= 0) {
     * if (position >= 0) {
     * double powerOutput = -0.2 * Math.log(position + 0.25) + 1;
     * return powerOutput * speed;
     * } else
     * return 0.25;
     * } else {
     * if (position < 67) {
     * double powerOutput = -0.2 * Math.log(-position + 67.25) + 0.9;
     * return powerOutput * speed;
     * } else return -0.25;
     * }
     */
    return powerFunction * speed;
  }

  public boolean atFloatPosition() {
    return encoder.getPosition() >= FLOAT_POS - 7.5 && encoder.getPosition() < FLOAT_POS + 7.5;
  }

  public boolean atFloorPosition() {
    return encoder.getPosition() >= FLOOR_POS;
  }

  public boolean atInitialPosition() {
    return encoder.getPosition() <= STARTING_POS + 10;
  }

  public void moveInside() {
    arm.set(powerCurve(-0.3));
    state = 1;
  }

  public void moveToFloor() {
    arm.set(powerCurve(0.35));
    state = 2;
  }

  public void moveToFloat() {
    double position = encoder.getPosition();

    if (position < FLOAT_POS) {
      arm.set(powerCurve(0.3));
    } else {
      arm.set(powerCurve(-0.35));
    }
  }

  public double maintainFloatPosition() {
    double position = encoder.getPosition();
    double tensionAmount = -0.006 * (position - MID_POS);
    return tensionAmount;
  }

  public void stop() {
    arm.set(0);
  }
}
