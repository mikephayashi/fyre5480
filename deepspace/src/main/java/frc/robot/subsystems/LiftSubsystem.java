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
import frc.robot.commands.Lift.UpLiftCommand;
import frc.robot.commands.Lift.DownLiftCommand;

/**
 * Add your docs here.
 */
public class LiftSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //Encoders
  //Encoder lift_Encoder = new Encoder(RobotMap.lift_encoder_port_one, RobotMap.lift_encoder_port_two, false, Encoder.EncodingType.k4X);


public LiftSubsystem(){

}

/* public void liftEncoder(){
    
  int count = lift_Encoder.get();
  double raw_distance = lift_Encoder.getRaw();
  double distance = lift_Encoder.getDistance();
  double period = lift_Encoder.getPeriod();
  double rate = lift_Encoder.getRate();
  boolean direction = lift_Encoder.getDirection();
  boolean stopped = lift_Encoder.getStopped();
} */

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new UpLiftCommand());
  }
}
