/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
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
  public Encoder lift_Encoder = new Encoder(RobotMap.lift_encoder_port_one, RobotMap.lift_encoder_port_two, false, Encoder.EncodingType.k4X);
  public int count = lift_Encoder.get();
  public double raw_distance = lift_Encoder.getRaw();
  public double distance = lift_Encoder.getDistance();
  public double period = lift_Encoder.getPeriod();
  public double rate = lift_Encoder.getRate();
  public boolean direction = lift_Encoder.getDirection();
  public boolean stopped = lift_Encoder.getStopped();
  //Motors
  public Spark LiftMotorController = new Spark(RobotMap.lift_motor_port);

  //Lift Down Level
  public void liftDown(){
    lift_Encoder.reset();
    if (count != 0){
      LiftMotorController.set(0.5);
    } else {
      LiftMotorController.set(0);
    }
    
  }

  //Lift Up Level
  public void liftUp(){
    lift_Encoder.reset();
    if (count != 10){
      LiftMotorController.set(0.5);
    } else {
      LiftMotorController.set(0);
    }
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new UpLiftCommand());
  }
}
