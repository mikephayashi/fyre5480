/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.DriveManuallyCommand;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  Spark m_frontLeft = new Spark(RobotMap.leftMaster);
  Spark m_rearLeft = new Spark(RobotMap.leftSlave);
  SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);

  Spark m_frontRight = new Spark(RobotMap.rightMaster);
  Spark m_rearRight = new Spark(RobotMap.rightSlave);
  SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);

  DifferentialDrive drive = new DifferentialDrive(m_left, m_right);


  public DriveSubsystem(){

  }

  public void manualDrive(double move, double turn){
    drive.arcadeDrive(move, turn);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveManuallyCommand());

  }
}
