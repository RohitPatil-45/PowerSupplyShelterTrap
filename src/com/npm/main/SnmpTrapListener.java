package com.npm.main;

import com.npm.datasource.Datasource;
import com.npm.model.EventLog;
import com.npm.model.SNMPTrapLogModel;
import com.npm.model.SNMPTrapUpdateModel;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import org.snmp4j.CommandResponder;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.transport.DefaultUdpTransportMapping;

/**
 *
 * @author Joel Patrick Llosa
 *
 */
public class SnmpTrapListener {

    public static ArrayList<SNMPTrapLogModel> trapLogList = null;
    public static ArrayList<SNMPTrapLogModel> trapLogListTemp = null;

    public static ArrayList<SNMPTrapUpdateModel> updateTrapList = null;
    public static ArrayList<SNMPTrapUpdateModel> updateTrapListTemp = null;

    public static ArrayList<EventLog> insertEventLogList = null;
    public static ArrayList<EventLog> insertEventLogListTemp = null;

    public static HashMap<String, String> alarmStatus = null;

    public void start() throws IOException {

        trapLogList = new ArrayList<>();
        trapLogListTemp = new ArrayList<>();

        updateTrapList = new ArrayList<>();
        updateTrapListTemp = new ArrayList<>();

        insertEventLogList = new ArrayList<>();
        insertEventLogListTemp = new ArrayList<>();

        alarmStatus = new HashMap<>();

        String selectQuery = "select DEVICE_IP, ALARM_ID, ALARM_STATUS from snmp_trap_live_status";
        try (
                Connection con = Datasource.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(selectQuery);) {

            while (rs.next()) {
                alarmStatus.put(rs.getString("DEVICE_IP") + "~" + rs.getString("ALARM_ID"), rs.getString("ALARM_STATUS"));
            }

        } catch (Exception e) {
            System.out.println("Exception while fetching alarm status  = " + e);
        }

        System.out.println("SNMP trap receiver start");
        Address address = GenericAddress.parse("udp:0.0.0.0/162");
        TransportMapping<UdpAddress> transport = new DefaultUdpTransportMapping((UdpAddress) address);
        transport.listen();

        CommandResponder trapPrinter = (CommandResponderEvent e) -> {
            System.out.println("Trap Received");
            Thread t1 = new Thread(new TrapThreadClass(e));
            t1.start();
        };

        Snmp snmp = new Snmp(transport);
        snmp.addCommandResponder(trapPrinter);

//        while (true) {
//            try {
//                //System.out.println("listening... Ctrl-C to stop");
//                Thread.sleep(5000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(SnmpTrapListener.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }

}
