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

    double maxVal;
    double maxLoc;

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

        netTable.addEntryListener("maxVal", (table, key, entry, value, flags) -> {
            System.out.println("maxVal changed value: " + value.getValue());
            maxVal = (double) value.getValue();
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        netTable.addEntryListener("maxLoc", (table, key, entry, value, flags) -> {
            System.out.println("maxLoc changed value: " + value.getValue());
            maxLoc = (double) value.getValue();
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

        java.util.List<Double> position = Arrays.asList(frameWidth_Entry, frameHeight_Entry, maxVal, maxLoc);

    return position;
  }

}