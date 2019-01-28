/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  /* For example to map the left and right motors, you could define the
  following variables to use with your drivetrain subsystem.
  public static int leftMotor = 1;
  public static int rightMotor = 2;

  If you are using multiple modules, make sure to define both the port
  number and the module. For example you with a rangefinder:
  public static int rangefinderPort = 1;
  public static int rangefinderModule = 1;*/

  //Drive subsystem Drive Motors
  public static int leftMotor = 0;
	public static int rightMotor = 1;
  
  //OI joystick and buttons
  //public static int joy_stick_port = 0;

  //OI X Box Controller
  public static int xbox_port = 1;

  //Dirve Subystem Wheel Chassis encoder
  public static int wheel_encoder_port_one = 0;
  public static int wheel_encoder_port_two = 0;

  //Drive Subystem Gyro
  public static int gyro_port = 0;

  //Dirve Subystem Ultrasonic sensor
  public static int ultrasonic_digital_out = 0;
  public static int ultrasonic_digital_in = 0;

  //Lift Subsystem Encoder
  public static int lift_encoder_port_one = 0;
  public static int lift_encoder_port_two = 0;

  //Climbing Subsystem Encoder
  public static int climbing_encoder_port_one = 0;
  public static int climbing_encoder_port_two = 0;

  //Mainpulator Subsystem Switch
  public static int manipulator_switch_port = 0;

  //Manipulator Subsystem Compressor
  public static int manipulator_compressor_port = 0;

  //Manipulator Subystem Solenoid
  public static int manipulator_solenoid_port = 0;
}
