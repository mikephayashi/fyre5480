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

  
  //Drive Motors
  public static int leftMotor = 0;
	public static int rightMotor = 1;
  
  //OI joystick and buttons
  public static int joy_stick_port = 0;
  public static int button_one = 1;
  public static int button_two = 2;
  public static int button_three = 3;
  public static int button_four = 4;
  public static int button_five = 5;
  public static int button_six = 6;
  public static int button_eight = 8;
  public static int button_ten = 10;

  //OI X Box Controller
  public static int xbox_port = 1;

  //Dirve Subystem Wheel Chassis encoder
  public static int wheel_encoder_port_one = 0;
  public static int wheel_encoder_port_two = 0;

  //Drive Subystem Gyro
  public static int gyro_port = 0;

  //Dirve Subystem Ultrasonic sensor
  public static int ultraLeft_analog_in = 3;
  public static int ultraRight_analog_in = 2;
  
  //Drive Subystem color sensor
  public static int color_sensor_port = 0;

  //Lift Subsystem Encoder
  public static int lift_encoder_port_one = 0;
  public static int lift_encoder_port_two = 0;
  
  //Lift Subsystem Motor
  public static int lift_motor_port = 9;

  //Lift Subystem Ultrasonic sensor
  public static int ultraLift_analog_in = 8;


  //Lift subystem solenoid
  public static int lift_solenoid_port = 0;


  //PIDz = climbing
  //Climbing Subsystem Encoder
  public static int climbing_encoder_port_one = 1;
  public static int climbing_encoder_port_two = 2;

  //Climbing Subsystem Motor
  public static int climbing_motor_port = 4;

  //Climbing Solenoid 
  public static int climbing_solenoid = 0;

  //Mainpulator Subsystem Switch
  public static int manipulator_switchFront_port = 0;
  public static int manipulator_switchBack_port = 0;
  //Manipulator Subsystem Compressor
  public static int manipulator_compressor_port = 0;

  //Cargo Subystem Solenoid
  public static int cargo_solenoid_port = 1;

  //Hatch subsystem solenoid
  public static int hatch_solenoid_port = 2;
  
  //Manipulator Subsystem Motor
  public static int rackAndPinionMotorController = 7;

  //Manipulator Subsystem Encoder
  public static int cargo_encoder_port_one = 0;
  public static int cargo_encoder_port_two = 0;
}
  // huhyeaaa
