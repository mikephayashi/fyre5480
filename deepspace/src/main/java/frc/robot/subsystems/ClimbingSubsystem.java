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
import frc.robot.commands.Climbing.ClimbingCommand;
import edu.wpi.first.wpilibj.Spark;

/**
 * Add your docs here.
 */
public class ClimbingSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //Encoders
  public Encoder climbing_Encoder = new Encoder(RobotMap.climbing_encoder_port_one, RobotMap.climbing_encoder_port_two, false, Encoder.EncodingType.k4X);
  public int count = climbing_Encoder.get();
  public double raw_distance = climbing_Encoder.getRaw();
  public double distance = climbing_Encoder.getDistance();
  public double period = climbing_Encoder.getRate();
  public double rate = climbing_Encoder.getRate();
  public boolean direction = climbing_Encoder.getDirection();
  public boolean stopped = climbing_Encoder.getStopped();
  //Motors
  Spark ClimbingMotorController = new Spark(RobotMap.climbing_encoder_port_one);

  //Starts climbing motor and stops when encoder reaches a point
  public void climb(){
    climbing_Encoder.reset();
    if (count < 10){
      ClimbingMotorController.set(0.5);
    } else {
      ClimbingMotorController.set(0);
    }
    
  };

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ClimbingCommand());
  }

}
