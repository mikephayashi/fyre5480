/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Vision extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  // public UsbCamera cam1 = new UsbCamera("cam1", 0);
  // public UsbCamera cam2 = new UsbCamera("cam2", 1);

  public Integer currentCamera = 0;

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(null);
  }

  public void switchCameras(){
    // currentCamera = currentCamera + 1;
    // if (currentCamera == 0){
    //   CameraServer.getInstance().removeCamera("cam2");
    //   CameraServer.getInstance().startAutomaticCapture("cam1", 0);
    // } else {
    //   CameraServer.getInstance().removeCamera("cam1");
    //   CameraServer.getInstance().startAutomaticCapture("cam2", 1);
    // }
    
  }
}
