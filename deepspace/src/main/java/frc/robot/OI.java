/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.RobotMap;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a joystick.
  // You create one by telling it which joystick it's on and which button number it is.
  public Joystick stick = new Joystick(RobotMap.joy_stick_port);
  // Button button = new JoystickButton(stick, buttonNumber);

  /*Button button1 = new JoystickButton(stick, 1),
			button2 = new JoystickButton(stick, 2),
			button3 = new JoystickButton(stick, 3),
			button4 = new JoystickButton(stick, 4),
			button5 = new JoystickButton(stick, 5),
			button6 = new JoystickButton(stick, 6),
			button7 = new JoystickButton(stick, 7),
      button8 = new JoystickButton(stick, 8); */
      
    /*button1.whenPressed(new PrepareToGrab());
		button2.whenPressed(new Grab());
		button3.whenPressed(new DriveToDistance(0.11));
		button4.whenPressed(new PlaceSoda());
    button6.whenPressed(new DriveToDistance(0.2));
    button7.whenPressed(new SodaDelivery());
		button8.whenPressed(new Stow());*/
		

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
