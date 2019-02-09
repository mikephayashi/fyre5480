/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Driving;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;


public class DriveManuallyCommand extends Command {


  public DriveManuallyCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveSubsystemRef);
  }


  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    /* double throttle = Robot.m_oi.stick.getThrottleChannel();
    double move = -Robot.m_oi.stick.getY()*throttle;
    double turn = Robot.m_oi.stick.getX(); */
    
    // double throttle = 0.25;  
    // double move = -Robot.m_oi.stick.getY() * throttle;
    // double turn = Robot.m_oi.stick.getX() * throttle;
    
    double move = Robot.m_oi.m_xBox.getY(Hand.kLeft) * 0.5;
    double turn = Robot.m_oi.m_xBox.getY(Hand.kRight) * 0.5;
    Robot.driveSubsystemRef.manualDrive(move, turn);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
