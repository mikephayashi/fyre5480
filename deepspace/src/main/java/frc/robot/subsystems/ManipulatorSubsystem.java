/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ManipulatorCommand;

/**
 * Add your docs here.
 */
public class ManipulatorSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //Switches
  //public DigitalInput limitSwitch = new DigitalInput(RobotMap.manipulator_switch_port);

  //Compressor
  public Compressor compress = new Compressor(RobotMap.manipulator_compressor_port);
  

  //Solenoid
  // public Solenoid manipulator_solenoid = new Solenoid(RobotMap.manipulator_solenoid_port);
  // public Boolean solenoid_state;


  public void compressor_toggle(){

    boolean pressureSwitch = compress.getPressureSwitchValue();
    double current = compress.getCompressorCurrent();

    compress.enabled();

    while (pressureSwitch){
      compress.setClosedLoopControl(true);
    }
    
    compress.setClosedLoopControl(false);
    

  }

  // public void solenoid_toggle(){
  //   if (solenoid_state == true){
  //     solenoid_state = false;
  //   } else {
  //     solenoid_state = true;
  //   }
  //   manipulator_solenoid.set(solenoid_state);
  // }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ManipulatorCommand());
  }
}
