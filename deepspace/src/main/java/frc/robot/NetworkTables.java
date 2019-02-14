package frc.robot;

import java.util.Arrays;
import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;

public class NetworkTables {

    public double x;
    public double y;
    public double leftArea;
    public double rightArea;

    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    edu.wpi.first.networktables.NetworkTable netTable = inst.getTable("datatable");

    public NetworkTables(){
        inst.startClientTeam(5480);
    }

    public double getValue(String networkKey){
        NetworkTableEntry networkEntry = netTable.getEntry(networkKey);
        return networkEntry.getValue().getDouble();
    }


    // Listening to Value changes in table
    // Add an EntryListener for changed values of "X". The lambda ("->" operator)
    // defines the code that should run when "X" changes.
    public void listenToNetworkTable(String networkKey) {
        
        netTable.addEntryListener(networkKey, (table, key, entry, value, flags) -> {
            System.out.println(networkKey + "Changed value: " + value.getValue());
            x = (double) value.getValue();
        }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

        try {
            Thread.sleep(100000);
        } catch (InterruptedException ex) {
            System.out.println("interrupted!");
            return;
        }
    }

}