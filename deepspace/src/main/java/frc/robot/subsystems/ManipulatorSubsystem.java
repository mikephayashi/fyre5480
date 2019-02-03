/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.commands.Manipulator.CargoCommand;
import frc.robot.commands.Manipulator.CloseHatchCommand;
import frc.robot.commands.Manipulator.OpenHatchCommand;
import java.io.*;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * Add your docs here.
 */
public class ManipulatorSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //Switches
  public DigitalInput limitSwitch = new DigitalInput(RobotMap.manipulator_switch_port);
  public Boolean limitSwitch_value = limitSwitch.get();
  //Compressor
  public Compressor compress = new Compressor(RobotMap.manipulator_compressor_port);
  
  public double current = compress.getCompressorCurrent();
  //Solenoids
  public Solenoid cargo_solenoid = new Solenoid(RobotMap.cargo_solenoid_port);
  public Solenoid hatch_solenoid = new Solenoid(RobotMap.hatch_solenoid_port);
  //Motors
  Spark manipulatorMotorController = new Spark(RobotMap.manipulator_motor_port);
  //Spark cargoWingsMotorController = new Spark(RobotMap.cargo_wings_motor_port);
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
    boolean pressureSwitch = compress.getPressureSwitchValue();
    compress.enabled();
    if (!pressureSwitch){
      compress.setClosedLoopControl(true);
      System.out.println("Pneumatic Compressor On");
    } else {
      compress.setClosedLoopControl(false);
      System.out.println("Pneumatic Compressor Off");
    }
  }

  //Extends Manipulator system beyond chassis frame
  public void extendManipulator(){
    // if (limitSwitch_value){
    //   manipulatorMotorController.set(0.5);
    // } else {
    //   manipulatorMotorController.set(0);
    // }
  }

  //Tretracts manipulator system within chassi frame
  public void retractManipulator(){
    // if (limitSwitch_value){
    //   manipulatorMotorController.set(0.5);
    // } else {
    //   manipulatorMotorController.set(0);
    // }
  }

  //Extend cargo wings to hold balls
  public void extendCargoWings(){
    // cargo_encoder.reset();
    // if (count==10){
    //   cargoWingsMotorController.set(0.5);
    // } else {
    //   cargoWingsMotorController.set(0);
    // }
    
  }

  //Retracts Crgo wing back into lift
  public void retractCargoWings(){
    // cargo_encoder.reset();
    // if (count==10){
    //   cargoWingsMotorController.set(0.5);
    // } else {
    //   cargoWingsMotorController.set(0);
    // }
  }

  //Kicks cargo ball out
  public void cargo(){
    cargo_solenoid.set(true);
    try {
      Thread.sleep(1000);
  } catch(InterruptedException e) {
      System.out.println("got interrupted!");
  }
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
    setDefaultCommand(new CargoCommand());
  }
}
