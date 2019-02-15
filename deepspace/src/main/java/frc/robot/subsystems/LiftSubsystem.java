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
import frc.robot.commands.Lift.ManualLiftUpCommand;

/**
 * Add your docs here.
 */
public class LiftSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //Constants
  public int CURRENT_LEVEL = 1;

  //Encoders
  // public Encoder lift_Encoder = new Encoder(RobotMap.lift_encoder_port_one, RobotMap.lift_encoder_port_two, false, Encoder.EncodingType.k4X);
  // public int count = lift_Encoder.get();
  // public double raw_distance = lift_Encoder.getRaw();
  // public double distance = lift_Encoder.getDistance();
  // public double period = lift_Encoder.getRate();
  // public double rate = lift_Encoder.getRate();
  // public boolean direction = lift_Encoder.getDirection();
  // public boolean stopped = lift_Encoder.getStopped();
  //Motors
  //  public Spark LiftMotorController = new Spark(RobotMap.lift_motor_port);
  //Ultrasonic
  // public AnalogInput ultraLift = new AnalogInput(RobotMap.ultraLift_analog_in);
  // public double range = ultraLift.getAverageVoltage();

  public LiftSubsystem(){

  }

  //Lift Down Level
  public void liftDown(double level){
    // lift_Encoder.reset();
    // if (count != level){
    //   lift_solenoid.set(false);
    //   LiftMotorController.set(0.5);
    // } else {
    //   LiftMotorController.set(0);
    //   lift_solenoid.set(true);
    // }
    
  }

  //Lift Up Level
  public void liftUp(double level){
    // lift_Encoder.reset();
    // if (count != level){
    //   lift_solenoid.set(false);
    //   LiftMotorController.set(0.5);
    // } else {
    //   LiftMotorController.set(0);
    //   lift_solenoid.set(true);
    // }
  }

  //Manual Lift
  public void lift(double stick){
    //  LiftMotorController.set(stick);
  }
  
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ManualLiftUpCommand());
  }
}
