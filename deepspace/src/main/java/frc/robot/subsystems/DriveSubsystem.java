/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import frc.robot.RobotMap;
import frc.robot.commands.Driving.DriveManuallyCommand;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public double TARGET_COLOR_VALUE = 0.0;

  // Drive Train
  Spark m_left = new Spark(RobotMap.leftMotor);
  Spark m_right = new Spark(RobotMap.rightMotor);
  DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
   // Ultrasonic sensor
  // public AnalogInput ultraLeft = new AnalogInput(RobotMap.ultraLeft_analog_in);
  // public AnalogInput ultraRight = new AnalogInput(RobotMap.ultraRight_analog_in);
  // public double leftRange = ultraLeft.getAverageVoltage();
  // public double rightRange = ultraRight.getAverageVoltage();
  // //Gyro
  public Gyro gyro_sensor = new AnalogGyro(RobotMap.gyro_port);
  public double angle = gyro_sensor.getAngle(); // get current heading
  //Analog input -> color sensor
  // public AnalogInput colorSensor = new AnalogInput(RobotMap.color_sensor_port);
  // double averageVolts = colorSensor.getAverageVoltage();
  /*
  Raw value - The instantaneous raw 12-bit (0-4096) value representing the 0-5V range of the ADC. Note that this method does not take into account the calibration information stored in the module.
  Voltage - The instantaneous voltage value of the channel. This method takes into account the calibration information stored in the module to convert the raw value to a voltage.
  Average Raw value - The raw, unscaled value output from the oversampling and averaging engine. See above for information on the effect of oversampling and averaging and how to set the number of bits for each.
  Average Voltage - The scaled voltage value output from the oversampling and averaging engine. This method uses the stored calibration information to convert the raw average value into a voltage.
  */
  // int raw = colorSensor.getValue();
  // double volts = colorSensor.getVoltage();
  // int averageRaw = colorSensor.getAverageValue();
  // int bits;
  // colorSensor.setOversampleBits(4);
  // bits = exampleAnalog.getOversampleBits();
  // colorSensor.setAverageBits(2);
  // bits = exampleAnalog.getAverageBits();
  

  public DriveSubsystem(){
    // colorSensor.setGlobalSampleRate(62500); //default value of 62,500 samples per channel per second (500kS/s total)
    gyro_sensor.calibrate();
    // gyro_sensor.reset();
  }
  

  //Manual Drive
  public void manualDrive(double move, double turn) {
    m_drive.arcadeDrive(move, turn);
  }

  //Auto - Detecting white tape 
  public void white_tape(){
    // if (averageVolts == TARGET_COLOR_VALUE){
    //   if (angle>270 | angle < 90){
    //     //On near side of white line
    //     m_drive.arcadeDrive(0, angle);
    //   } else {
    //     //On far side of white line
    //     m_drive.arcadeDrive(0, angle);
    //   }

    //   if (angle != 90){

    //   }
      

    // }
  }
   

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new DriveManuallyCommand());

  }
}

