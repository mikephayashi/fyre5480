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
    public void listenToNetworkTable(String NtwkKey) {
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        edu.wpi.first.networktables.NetworkTable netTable = inst.getTable("datatable");
        NetworkTableEntry xEntry = netTable.getEntry("X");
        NetworkTableEntry yEntry = netTable.getEntry("Y");
        inst.startClientTeam(5480);

        netTable.addEntryListener("X", (table, key, entry, value, flags) -> {
            System.out.println("X changed value: " + value.getValue());
            maxVal = (double) value.getValue();
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        netTable.addEntryListener("Y", (table, key, entry, value, flags) -> {
            System.out.println("Y changed value: " + value.getValue());
            maxLoc = (double) value.getValue();
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        netTable.addEntryListener("leftArea", (table, key, entry, value, flags) -> {
            System.out.println("leftArea changed value: " + value.getValue());
            maxLoc = (double) value.getValue();
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        netTable.addEntryListener("rightArea", (table, key, entry, value, flags) -> {
            System.out.println("rightArea changed value: " + value.getValue());
            maxLoc = (double) value.getValue();
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        try {
            Thread.sleep(100000);
        } catch (InterruptedException ex) {
            System.out.println("interrupted!");
            return;
        }
    }

}