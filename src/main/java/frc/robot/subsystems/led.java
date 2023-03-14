// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class led extends SubsystemBase {
private Spark led = new Spark(Constants.led1);
private Spark led2 = new Spark(Constants.led2);

  /** Creates a new led. */
  public led() {

  }

  public void setColor(double value){
    if ((value >= -1.0) && (value <= 1.0)){
      led.set(value);
    }
  }
  public void allianceColor(){
    boolean isRed = NetworkTableInstance.getDefault().getTable("FMSInfo").getEntry("IsRedAlliance").getBoolean(true);
    if(isRed = true){
      led.set(-0.01);
      System.out.println("led RED");
    }
    else{
      led.set(0.19);
      System.out.println("led BLUE") ;
    }
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
