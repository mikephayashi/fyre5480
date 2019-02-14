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
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.RobotMap;
import frc.robot.commands.Lift.*;
import frc.robot.commands.Manipulator.*;
import frc.robot.commands.Vision.SwitchCameraCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  // XBox
  public XboxController m_xBox = new XboxController(RobotMap.xbox_port);

  //Joystick
  public Joystick stick = new Joystick(RobotMap.joy_stick_port);

  // Joystick
  // Working:
  // x-axis
  // y-axis
  // slider
  // POV
  // Buttons(1,2,3,4,5,6,8,10)
  // 
  // Broken:
  // Buttons(7,9,11,12)


//// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a joystick.
  // You create one by telling it which joystick it's on and which button number
  //// it is.

  public POVButton povOne = new POVButton(stick, 0);
  public POVButton povTwo = new POVButton(stick, 180);

  Button button1 = new JoystickButton(stick, RobotMap.button_one);
  Button button2 = new JoystickButton(stick, RobotMap.button_two);
  Button button3 = new JoystickButton(stick, RobotMap.button_three);
  Button button4 = new JoystickButton(stick, RobotMap.button_four);
  Button button5 = new JoystickButton(stick, RobotMap.button_five);
  Button button6 = new JoystickButton(stick, RobotMap.button_six);
  Button button8 = new JoystickButton(stick, RobotMap.button_eight); 
  Button button10 = new JoystickButton(stick, RobotMap.button_ten);
  
 


  public OI(){
    
    button1.whenPressed(new OpenCargoCommand());
    button2.whenPressed(new CloseCargoCommand());
    button3.whenPressed(new OpenHatchCommand());
    button4.whenPressed(new CloseHatchCommand());
    button5.whileHeld(new ExtendRackAndPinionCommand());
    button6.whileHeld(new RetractRackAndPinionCommand());
    povOne.whileHeld(new ManualLiftUpCommand());
    button8.whenPressed(new SwitchCameraCommand());
    povTwo.whileHeld(new ManipulatorLiftDownCommand());

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
