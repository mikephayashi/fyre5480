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
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.networktables.NetworkTable;
import frc.robot.RobotMap;
import frc.robot.commands.Driving.DriveManuallyCommand;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  // Drive Train
  //Spark m_left = new Spark(RobotMap.driveSubsystemPorts.leftMotor.getValue());
  //Spark m_right = new Spark(RobotMap.driveSubsystemPorts.rightMotor.getValue());
  //DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
   // Ultrasonic sensor
  public AnalogInput ultraLeft = new AnalogInput(RobotMap.ultraLeft_analog_in);
  public AnalogInput ultraRight = new AnalogInput(RobotMap.ultraRight_analog_in);
  //Gyro
  
  public Gyro gyro_sensor = new AnalogGyro(RobotMap.gyro_port);
  public double angle = gyro_sensor.getAngle(); // get current heading

  public DriveSubsystem(){
    
  }
  

  //Manual Drive
  // public void manualDrive(double move, double turn) {
  //   m_drive.arcadeDrive(move, turn);
  // }

  //Simple Networktableprogram
  NetworkTableEntry xEntry;
  NetworkTableEntry yEntry;
  public void RobotInit(){
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable table = inst.getTable("datatable");
    xEntry = table.getEntry("X");
    yEntry = table.getEntry("Y");
  }
  public void setTableValue(){
    xEntry.setDouble(1);
    yEntry.setDouble(1);
  }

  //TableEntryListenerExample
  public void run(){
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable table = inst.getTable("datatable");
    NetworkTableEntry yEntry = table.getEntry("Y");
    inst.startClientTeam(5480);
    //Continue on FRC
  }
   

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveManuallyCommand());

  }
}

