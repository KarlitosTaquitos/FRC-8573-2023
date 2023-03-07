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

public class Claw extends SubsystemBase {
  private CANSparkMax claw = new CANSparkMax(Constants.CLAW, MotorType.kBrushless);
  private RelativeEncoder encoder = claw.getEncoder();
    
  
  /** Creates a new Claw. */
  public Claw() {
    claw.setIdleMode(IdleMode.kBrake);
    encoder.setPosition(-2);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
 
  public void open(){
    claw.set(-.25);
  }

  public void close(){
    claw.set(.3);
  }

  public void tension(){
    claw.set(.05);
  }

  public void stop(){
    claw.set(0);
  }

  public boolean openPosition(){
    return encoder.getPosition() <= -20;
  }
  public boolean closePosition(){
    return encoder.getPosition() >= -2;
  }
}
