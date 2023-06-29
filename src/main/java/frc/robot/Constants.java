// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  // Controller Axes
  public static final int XBOX_LEFT_Y = 1;
  public static final int XBOX_LEFT_X = 0;

  public static final int XBOX_RIGHT_Y = 5;

  public static final int FLIGHT_Y = 1;
  public static final int FLIGHT_X = 0;

  public static final int PS4_LEFT_Y = 1;
  public static final int PS4_RIGHT_X = 4;

  // XBOX Buttons
  public static final int XBOX_A_BUTTON = 1;
  public static final int XBOX_B_BUTTON = 2;
  public static final int XBOX_X_BUTTON = 3;
  public static final int XBOX_Y_BUTTON = 4;

  public static final int XBOX_R_BUMPER = 5;
  public static final int XBOX_L_BUMPER = 6;

  //PS4 Buttons
  public static final int PS4_R_BUMPER = 6;
  public static final int PS4_L_BUMPER = 5;
  public static final int PS4_SQUARE = 3;
  public static final int PS4_X = 1;

  // Flight Stick Buttons
  public static final int FLIGHT_BUTTON_11 = 11;

  // Drive motors
  public static final int FRONT_LEFT = 11;
  public static final int FRONT_RIGHT = 2;
  public static final int BACK_LEFT = 1;
  public static final int BACK_RIGHT = 7;

  // Arm motor
  public static final int ARM = 5;

  // Claw Motor
  public static final int CLAW = 4;

  // Gyro Motor
  public static final int CONE_STICK = 10;

  // Auto Codes
  public static final String CONE_COMMUNITY = "cone-community";
  public static final String CONE_BALANCE = "cone-balance";
  public static final String BLUE_CONE_BALANCE = "blueConeBalance";
  public static final String NODRIVECONE = "cone_no_drive";

  //led
  public static final int led1 = 1;
  public static final int led2 = 2;

  //ConeStick DPAD Values
  public static final int up = 0;
  public static final int right = 90;
  public static final int down = 180;
  public static final int left = 270;
}
