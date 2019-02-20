/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Lift.ManualLiftCommand;
import edu.wpi.first.wpilibj.DigitalInput;
/**
 * Add your docs here.
 */
public class LiftSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.


  //Motors
   public Spark LiftMotorController = new Spark(RobotMap.lift_motor_port);

  //Limit Switch
  public DigitalInput limitSwitch = new DigitalInput(RobotMap.manipulator_switch_port);
  public Boolean limitSwitch_value = limitSwitch.get();

  public LiftSubsystem(){

  }

  //Manual Lift
  public void lift(double stick){
     LiftMotorController.set(stick);
  }

  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ManualLiftCommand());
  }
}
