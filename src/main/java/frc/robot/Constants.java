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

  // XBOX Buttons
  public static final int XBOX_A_BUTTON = 1;
  public static final int XBOX_B_BUTTON = 2;
  public static final int XBOX_X_BUTTON = 3;
  public static final int XBOX_Y_BUTTON = 4;

  public static final int XBOX_R_BUMPER = 5;
  public static final int XBOX_L_BUMPER = 6;


  // Drive motors
  public static final int FRONT_LEFT = 11;
  public static final int FRONT_RIGHT = 2;
  public static final int BACK_LEFT = 1;
  public static final int BACK_RIGHT = 7;

  // Arm motor
  public static final int ARM = 5;

  // Claw Motor
  public static final int CLAW = 4;
}