/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import frc.robot.RobotMap;
import frc.robot.commands.Driving.DriveManuallyCommand;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.


  // Drive Train
  Spark m_left = new Spark(RobotMap.leftMotor);
  Spark m_right = new Spark(RobotMap.rightMotor);
  DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);

  //Gyro
  public Gyro gyro_sensor = new AnalogGyro(RobotMap.gyro_port);
  public double angle = gyro_sensor.getAngle(); // get current heading
  

  public DriveSubsystem(){
    // colorSensor.setGlobalSampleRate(62500); //default value of 62,500 samples per channel per second (500kS/s total)
    gyro_sensor.calibrate();
    gyro_sensor.reset();
  }
  

  //Manual Drive
  public void manualDrive(double move, double turn) {
    m_drive.arcadeDrive(move, turn);
  }

   

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveManuallyCommand());

  }
}

