package frc.robot;

import java.util.Arrays;
import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;

public class Vision {

    // public void robotInit(){
    // NetworkTableInstance inst = NetworkTableInstance.getDefault();
    // edu.wpi.first.networktables.NetworkTable netTable =
    // inst.getTable("datatable");
    // xEntry = netTable.getEntry("X");
    // yEntry = netTable.getEntry("Y");
    // radiusEntry = netTable.getEntry("Radius");
    // }

    // public void setData(){
    // xEntry.setDouble(x);
    // yEntry.setDouble(y);
    // radiusEntry.setDouble(radius);
    // }

    double cX;
    double cY;
    double radius;
    double TARGET_RADIUS = 40.0;

    // Listening to Value changes in table
    // Add an EntryListener for changed values of "X". The lambda ("->" operator)
    // defines the code that should run when "X" changes.
    public void listenToNetworkTable() {
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        edu.wpi.first.networktables.NetworkTable netTable = inst.getTable("datatable");
        NetworkTableEntry xEntry = netTable.getEntry("X");
        NetworkTableEntry yEntry = netTable.getEntry("Y");
        NetworkTableEntry radiusEntry = netTable.getEntry("Radius");
        inst.startClientTeam(5480);

        netTable.addEntryListener("X", (table, key, entry, value, flags) -> {
            System.out.println("X changed value: " + value.getValue());
            cX = (double) value.getValue();
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        netTable.addEntryListener("Y", (table, key, entry, value, flags) -> {
            System.out.println("Y changed value: " + value.getValue());
            cY = (double) value.getValue();
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        netTable.addEntryListener("Radius", (table, key, entry, value, flags) -> {
            System.out.println("Radius changed value: " + value.getValue());
            radius = (double) value.getValue();
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        try {
            Thread.sleep(100000);
        } catch (InterruptedException ex) {
            System.out.println("interrupted!");
            return;
        }
    }

    public java.util.List<Double> visionProcessing() {

        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        edu.wpi.first.networktables.NetworkTable netTable = inst.getTable("datatable");
        double frameWidth_Entry = netTable.getEntry("Frame Width").getDouble(0);
        double frameHeight_Entry = netTable.getEntry("Frame Height").getDouble(0);


        double xError = cX/frameWidth_Entry;
        double yError = cY/frameHeight_Entry;
        double radiusError = radius/TARGET_RADIUS;

        java.util.List<Double> error = Arrays.asList(xError, yError, radiusError);

    return error;
  }

}