/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Climbing.ClimbingCommand;

/**
 * Add your docs here.
 */
public class PIDz extends PIDSubsystem {
  /**
   * Add your docs here.
   */
  
  //Constants
  double ENCODER_TARGET_VALUE = 0.0;

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
  Spark ClimbingMotorController = new Spark(RobotMap.climbing_motor_port);
  //Solenoid
  // public Solenoid pidz_solenoid = new Solenoid(RobotMap.climbing_solenoid);

  

  public PIDz() {
    // Intert a subsystem name and PID values here
    // The constructor passes a name for the subsystem and the P, I and D constants that are used when computing the motor output
    super("PIDz", 1, 2, 3);
    // Use these to get going:
    // setSetpoint() - Sets where the PID controller should move the system
    // to
    // enable() - Enables the PID controller.
		setAbsoluteTolerance(0.05);
		getPIDController().setContinuous(false);
  }

  public void solenod_break(){
    // pidz_solenoid.set(true);
  }

  public void climb(double speed){
    ClimbingMotorController.set(speed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ClimbingCommand());

  }

  @Override
  protected double returnPIDInput() {
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;

    // return climbing_Encoder.getAverageVoltage(); // returns the sensor value that is providing the feedback for the system
    return 0.0;
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
    

    // ClimbingMotorController.pidWrite(output); // this is where the computed output value fromthe PIDController is applied to the motor
    // climbing_Encoder.reset();
    // if (ENCODER_TARGET_VALUE < distance){
    //   ClimbingMotorController.set(0.5);
    // } else {
    //   ClimbingMotorController.set(0);
    // }

    // ClimbingMotorController.set(Relay.Value.kOn);
    // ClimbingMotorController.set(Relay.Value.kForward);
  }
}
