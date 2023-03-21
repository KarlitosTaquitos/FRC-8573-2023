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

public class ConeStick extends SubsystemBase {
  private CANSparkMax Cone_Stick = new CANSparkMax(Constants.CONE_STICK, MotorType.kBrushless);
  private RelativeEncoder encoder = Cone_Stick.getEncoder();

  private final double FLOOR_POS = 4;
  private final double FLOAT_POS = 2.5;
  private final double STARTING_POS = 0;

  /** Creates a new ConeStick. */
  public ConeStick() {
    encoder.setPosition(0);
    Cone_Stick.setIdleMode(IdleMode.kBrake);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public boolean atFloorPosition() {
    return encoder.getPosition() >= FLOOR_POS;
  }

  public boolean atFloatPosition() {
    return encoder.getPosition() >= FLOAT_POS - 0.05 && encoder.getPosition() < FLOAT_POS + 0.05;
  }

  public boolean atInitialPosition() {
    return encoder.getPosition() <= STARTING_POS + 1;
  }

  public void moveInside() {
    Cone_Stick.set(-0.4);
  }

  public void moveToFloor() {
    Cone_Stick.set(0.3);
  }

  public void moveToFloat() {
    double position = encoder.getPosition();

    if (position < FLOAT_POS) {
      Cone_Stick.set(0.25);
    } else {
      Cone_Stick.set(-0.25);
    }
  }

  public void setSpeed(double speed) {
    if (speed > 0)
      speed *= 0.075;
    Cone_Stick.set(speed);
  }

  public void setBrake() {
    Cone_Stick.setIdleMode(IdleMode.kBrake);
  }

  public void setCoast() {
    Cone_Stick.setIdleMode(IdleMode.kCoast);
  }

  public void stop() {
    Cone_Stick.set(0);
  }

  public void floatTension() {
    Cone_Stick.set(0.075);
  }
}
