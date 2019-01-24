/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.RobotMap;
import frc.robot.commands.DriveManuallyCommand;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //Drive Train
  Spark m_left = new Spark(RobotMap.leftMotor);
	Spark m_right = new Spark(RobotMap.rightMotor);
  DifferentialDrive m_drive = new DifferentialDrive(m_left,m_right);

  //Encoders
  Encoder wheeelChassis_Encoder = new Encoder(RobotMap.wheel_encoder_port_one, RobotMap.wheel_encoder_port_two, false, Encoder.EncodingType.k4X);

  //Sensors
  public Ultrasonic ultra = new Ultrasonic(RobotMap.ultrasonic_digital_out,RobotMap.ultrasonic_digital_in); // usually (1,1)... creates the ultra object and assigns ultra to be an ultrasonic sensor which uses DigitalOutput 1 for the echo pulse and DigitalInput 1 for the trigger pulse

  public DriveSubsystem(){

  }

  public void manualDrive(double move, double turn){
    m_drive.arcadeDrive(move, turn);
  }

  public void driveEncoder(){
    
    /*int count = wheeelChassis_Encoder.get();
    double raw_distance = wheeelChassis_Encoder.getRaw();
    double distance = wheeelChassis_Encoder.getDistance();
    double period = wheeelChassis_Encoder.getPeriod();
    double rate = wheeelChassis_Encoder.getRate();
    boolean direction = wheeelChassis_Encoder.getDirection();
    boolean stopped = wheeelChassis_Encoder.getStopped();*/
  }

  public double ultraSonicDistanceSensor(){
    double range = ultra.getRangeInches(); // reads the range on the ultrasonic sensor
    return range;
  }

  


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveManuallyCommand());

  }
}
