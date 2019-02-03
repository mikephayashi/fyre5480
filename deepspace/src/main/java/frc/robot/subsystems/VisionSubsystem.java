/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class VisionSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  //Setting Values into Table
  NetworkTableEntry xEntry;
  NetworkTableEntry yEntry;
  double x = 0;
  double y = 0;

  public void robotInit(){
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    edu.wpi.first.networktables.NetworkTable netTable = inst.getTable("datatable");
    xEntry = netTable.getEntry("X");
    yEntry = netTable.getEntry("Y");
  }

  public void setData(){
    xEntry.setDouble(x);
    yEntry.setDouble(y);
  }

  //Listening to Value changes in table
  //Add an EntryListener for changed values of "X". The lambda ("->" operator) defines the code that should run when "X" changes.
  public void listenToNetworkTable(){
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    edu.wpi.first.networktables.NetworkTable netTable = inst.getTable("datatable");
    NetworkTableEntry yEntry = netTable.getEntry("Y");
    inst.startClientTeam(5480);

    netTable.addEntryListener("X", (table, key, entry, value, flags) -> {
      System.out.println("X changed value: " + value.getValue());
    }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

    netTable.addEntryListener("Y", (table, key, entry, value, flags) -> {
      System.out.println("Y changed value: " + value.getValue());
    }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

    try{
      Thread.sleep(100000);
    } catch (InterruptedException ex){
      System.out.println("interrupted!");
      return;
    }
  }

}
