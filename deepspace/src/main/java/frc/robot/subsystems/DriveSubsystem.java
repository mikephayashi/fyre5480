/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import frc.robot.RobotMap;
import frc.robot.commands.Driving.DriveManuallyCommand;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  // Drive Train
  Spark m_left = new Spark(RobotMap.driveSubsystemPorts.leftMotor.getValue());
  Spark m_right = new Spark(RobotMap.driveSubsystemPorts.rightMotor.getValue());
  DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
  // Encoders
  public Encoder wheelChassis_Encoder = new Encoder(RobotMap.wheel_encoder_port_one, RobotMap.wheel_encoder_port_two, false, Encoder.EncodingType.k4X);
  public int count = wheelChassis_Encoder.get(); 
  public double raw_distance = wheelChassis_Encoder.getRaw(); 
  public double distance = wheelChassis_Encoder.getDistance(); 
  public double period = wheelChassis_Encoder.getPeriod(); 
  public double rate = wheelChassis_Encoder.getRate(); 
  public boolean direction = wheelChassis_Encoder.getDirection(); 
  public boolean stopped = wheelChassis_Encoder.getStopped(); 
  // Ultrasonic sensor
   // usually (1,1)... creates the ultra object and assigns ultra to be an ultrasonic sensor which uses DigitalOutput 1 for the echo pulse and DigitalInput 1 for the trigger pulse
  public Ultrasonic ultraLeft = new Ultrasonic(RobotMap.ultraLeft_digital_out,RobotMap.ultraLeft_digital_in);
  public Ultrasonic ultraRight = new Ultrasonic(RobotMap.ultraRight_digital_out,RobotMap.ultraRight_digital_in);
  // reads the range on the ultrasonic sensor return range
  public double rangeLeft = ultraLeft.getRangeInches(); 
  public double rangeRight= ultraRight.getRangeInches();
  //Gyro
  public Gyro gyro_sensor = new AnalogGyro(RobotMap.gyro_port);
  public double angle = gyro_sensor.getAngle(); // get current heading

  public DriveSubsystem(){
    ultraLeft.setAutomaticMode(true); // turns on automatic mode
    ultraRight.setAutomaticMode(true);
  }
  

  //Manual Drive
  public void manualDrive(double move, double turn) {
    m_drive.arcadeDrive(move, turn);
  }

  // //Simple Networktableprogram
  // NetworkTableEntry xEntry;
  // NetworkTableEntry yEntry;
  // public void RobotInit(){
  //   NetworkTableInstance inst = NetworkTableInstance.getDefault();
  //   NetworkTable table = inst.getTable("datatable");
  //   xEntry = table.getEntry("X");
  //   yEntry = table.getEntry("Y");
  // }
  // public void setTableValue(){
  //   xEntry.setDouble(1);
  //   yEntry.setDouble(1);
  // }

  // //TableEntryListenerExample
  // public void run(){
  //   NetworkTableInstance inst = NetworkTableInstance.getDefault();
  //   NetworkTable table = inst.getTable("datatable");
  //   NetworkTableEntry yEntry = table.getEntry("Y");
  //   inst.startClientTeam(5480);
  //   //Continue on FRC
  // }
   

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveManuallyCommand());

  }
}

