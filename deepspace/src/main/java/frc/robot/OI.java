/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.RobotMap;
import frc.robot.commands.Lift.*;
import frc.robot.commands.Manipulator.*;
import frc.robot.commands.Climbing.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  // XBox
  public XboxController m_xBox = new XboxController(RobotMap.xbox_port);

  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a joystick.
  // You create one by telling it which joystick it's on and which button number
  //// it is.
  public Joystick stick = new Joystick(RobotMap.joy_stick_port);

  // Button button = new JoystickButton(stick, buttonNumber);
  Button button1 = new JoystickButton(stick, RobotMap.button_one),
  button2 = new JoystickButton(stick, RobotMap.button_two),
  button3 = new JoystickButton(stick, RobotMap.button_three),
  button4 = new JoystickButton(stick, RobotMap.button_four),
  button5 = new JoystickButton(stick, RobotMap.button_five),
  button6 = new JoystickButton(stick, RobotMap.button_six); 
  

  public OI(){
    button1.whenPressed(new UpLiftCommand());
    button2.whenPressed(new DownLiftCommand());
    button3.whenPressed(new CargoCommand());
    button4.whenPressed(new CloseHatchCommand());
    button5.whenPressed(new OpenHatchCommand());
    button6.whenPressed(new ClimbingCommand());
  }

		

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  /*1.) Start the command when the button is pressed and let it run the command
  until it is finished as determined by it's isFinished method.
  button.whenPressed(new ExampleCommand());

  2.) Run the command while the button is being held down and interrupt it once
  the button is released.
  button.whileHeld(new ExampleCommand());

  3.) Start the command when the button is released and let it run the command
  until it is finished as determined by it's isFinished method.
  button.whenReleased(new ExampleCommand());*/
}
