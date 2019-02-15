/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Manipulator.OpenHatchCommand;

/**
 * Add your docs here.
 */
public class ManipulatorSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //Constants
  double CARGO_ENCODER_TARGET_RETRACTED = 0.0;
  double CARGO_ENCODER_TARGET_EXTENDED = 0.0;

  //Switches
  // public DigitalInput limitSwitchFront = new DigitalInput(RobotMap.manipulator_switchFront_port);
  // public Boolean limitSwitchFront_value = limitSwitchFront.get();
  // public DigitalInput limitSwitchBack = new DigitalInput(RobotMap.manipulator_switchBack_port);
  // public Boolean limitSwitchBack_value = limitSwitchBack.get();
  //Compressor
  //public Compressor compress = new Compressor(RobotMap.manipulator_compressor_port);
  //public double current = compress.getCompressorCurrent();
  //Solenoids
  public Solenoid cargo_solenoid = new Solenoid(RobotMap.cargo_solenoid_port);
  public Solenoid hatch_solenoid = new Solenoid(RobotMap.hatch_solenoid_port);
  //Motors
  // Spark rackAndPinionMotorController = new Spark(RobotMap.rackAndPinionMotorController);
  //Encoders
  // public Encoder cargo_encoder = new Encoder(RobotMap.cargo_encoder_port_one, RobotMap.cargo_encoder_port_two, false, Encoder.EncodingType.k4X);
  // public int count = cargo_encoder.get();
  // public double raw_distance = cargo_encoder.getRaw();
  // public double distance = cargo_encoder.getDistance();
  // public double period = cargo_encoder.getRate();
  // public double rate = cargo_encoder.getRate();
  // public boolean direction = cargo_encoder.getDirection();
  // public boolean stopped = cargo_encoder.getStopped();

  //Compressor
  public void compressor(){
    //boolean pressureSwitch = compress.getPressureSwitchValue();
    // compress.enabled();
    // if (!pressureSwitch){
    //   compress.setClosedLoopControl(true);
    //   System.out.println("Pneumatic Compressor On");
    // } else {
    //   compress.setClosedLoopControl(false);
    //   System.out.println("Pneumatic Compressor Off");
    // }
  }

  //Extends Manipulator system beyond chassis frame
  public void extendManipulator(){
    // rackAndPinionMotorController.set(1);
    // if (limitSwitchFront_value == false){
    //   rackAndPinionMotorController.set(0.5);
    // } else {
    //   rackAndPinionMotorController.set(0);
    // }
      // rackAndPinionMotorController.set(0.5);
  }

  //Tretracts manipulator system within chassi frame
  public void retractManipulator(){
    // rackAndPinionMotorController.set(-1);
    // if (limitSwitchBack_value == false){
    //   rackAndPinionMotorController.set(-0.5);
    // } else {
    //   rackAndPinionMotorController.set(0);
    // }
    // rackAndPinionMotorController.set(-0.5);
  }



  //Extends cargo piston
  public void openCargo(){
    cargo_solenoid.set(true);
  }

  //Retracts cargo piston
  public void closeCargo(){
    cargo_solenoid.set(false);
  }

  //Opens clasps to put on hatches
  public void openHatch(){
    hatch_solenoid.set(true);
  }

  //Closes clasps to secure hatch onto robot
  public void closeHatch(){
    hatch_solenoid.set(false);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new OpenHatchCommand());
  }
}
