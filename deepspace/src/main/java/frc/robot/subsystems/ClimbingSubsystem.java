/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ClimbingCommand;

/**
 * Add your docs here.
 */
public class ClimbingSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //Encoders
  //Encoder climbing_Encoder = new Encoder(RobotMap.climbing_encoder_port_one, RobotMap.climbing_encoder_port_two, false, Encoder.EncodingType.k4X);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ClimbingCommand());
  }

  /* public void climbingEncoder(){
    
    int count = climbing_Encoder.get();
    double raw_distance = climbing_Encoder.getRaw();
    double distance = climbing_Encoder.getDistance();
    double period = climbing_Encoder.getPeriod();
    double rate = climbing_Encoder.getRate();
    boolean direction = climbing_Encoder.getDirection();
    boolean stopped = climbing_Encoder.getStopped();
  } */
}
