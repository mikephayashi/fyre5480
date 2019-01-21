/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.DriveManuallyCommand;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //Drive Train
  Spark m_frontLeft = new Spark(RobotMap.leftMaster);
  Spark m_rearLeft = new Spark(RobotMap.leftSlave);
  SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);

  Spark m_frontRight = new Spark(RobotMap.rightMaster);
  Spark m_rearRight = new Spark(RobotMap.rightSlave);
  SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);

  DifferentialDrive drive = new DifferentialDrive(m_left, m_right);

  //Encoders
  Encoder sampleEncoder = new Encoder(RobotMap.encoder_port_one, RobotMap.encoder_port_two, false, Encoder.EncodingType.k4X);

  //Sensors
  public Ultrasonic ultra = new Ultrasonic(RobotMap.ultrasonic_digital_out,RobotMap.ultrasonic_digital_in); // usually (1,1)... creates the ultra object and assigns ultra to be an ultrasonic sensor which uses DigitalOutput 1 for the echo pulse and DigitalInput 1 for the trigger pulse

  public DriveSubsystem(){

  }

  public void manualDrive(double move, double turn){
    drive.arcadeDrive(move, turn);
  }

  public void driveEncoder(){
    
    /*int count = sampleEncoder.get();
    double raw_distance = sampleEncoder.getRaw();
    double distance = sampleEncoder.getDistance();
    double period = sampleEncoder.getPeriod();
    double rate = sampleEncoder.getRate();
    boolean direction = sampleEncoder.getDirection();
    boolean stopped = sampleEncoder.getStopped();*/
  }

  public void ultraSonicDistanceSensor(){
    double range = ultra.getRangeInches(); // reads the range on the ultrasonic sensor
  }

  


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveManuallyCommand());

  }
}
