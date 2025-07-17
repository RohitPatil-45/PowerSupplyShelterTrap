/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.npm.main;

import com.npm.datasource.Datasource;
import com.npm.model.EventLog;
import com.npm.model.SNMPTrapLogModel;
import com.npm.model.SNMPTrapUpdateModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.PDU;
import org.snmp4j.smi.VariableBinding;

/**
 *
 * @author Kratos
 */
public class TrapThreadClass implements Runnable {

    public CommandResponderEvent e;

    public TrapThreadClass(CommandResponderEvent events) {
        this.e = events;

    }

    public void run() {

        PDU command = e.getPDU();
        if (command != null) {
//            String device_ip = e.getPeerAddress().toString();
//            device_ip = device_ip.split("/")[0];
            String device_ip = "172.30.161.22";
            System.out.println("Device IP:" + device_ip);
            String device_typeC = "POWER SUPPLY";

            System.out.println("snmp trap received: " + command.toString());
            Timestamp trapReceivedTime = new Timestamp(System.currentTimeMillis());
            Map<String, String> parsedData = new HashMap<>();
            boolean isTrapValid = false;

            for (VariableBinding vb : command.getVariableBindings()) {
                // System.out.println(vb.getOid() + " = " + vb.getVariable());
                String oid = vb.getOid().toString();
                String value = vb.getVariable().toString();
                System.out.println(oid + " = " + value);

                parsedData.put(oid, value);
            }

            // Example: Extract specific data from trap
            if (parsedData.containsKey("1.3.6.1.6.3.1.1.4.1.0")) {

                String trapOID = parsedData.get("1.3.6.1.6.3.1.1.4.1.0");

                if (trapOID.equals("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")) {
                    isTrapValid = true;

//                        MF Delay #warning,(Main Failure)
//1.3.6.1.2.1.1.3.0 = 87 days, 23:52:44.07  node_uptime
//1.3.6.1.6.3.1.1.4.1.0 = 1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1 trap oid
//1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0 = W4-PWR2     
//1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0 = DMBS PSU
//1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0 = 2025-06-06T16:23:12
//1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0 = 1
//1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0 = 32825
//1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0 = 3  
                    //.1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1	
                    //OID: .iso.org.dod.internet.mgmt.mib-2.system.sysUpTime.0. Value: 2110 hours 42 minutes 36.07 seconds; 
                    //OID: .1.3.6.1.6.3.1.1.4.1.0. Value: .1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1; 
                    //OID: .1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0. Value: W4-PWR2; 
                    //OID: .1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0. Value: DMBS PSU; 
                    //OID: .1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0. Value: 2025-06-06T15:13:04; 
                    //OID: .1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0. Value: 0; 
                    //OID: .1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0. Value: 25; 
                    //OID: .1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0. Value: 1; 
                    //OID: .1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0. Value: Non Urg RFA; 
                    try {

                        String deviceName = parsedData.get("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0");

                        String deviceType = parsedData.get("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0");
                        String eventTime = parsedData.get("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0");
                        String alarmStatus = parsedData.get("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0");
                        String trapValue = parsedData.get("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0");
                        String severity = parsedData.get("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0");
                        String alarmName = parsedData.get("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0");

                        String nodeUptime = parsedData.get("1.3.6.1.2.1.1.3.0");

                        // Parsed summary
                        System.out.println("\n### Parsed Summary ###");
                        System.out.println("Trap OID     : " + trapOID);
                        System.out.println("nodeUptime   : " + nodeUptime);
                        System.out.println("deviceName   : " + deviceName);
                        System.out.println("deviceType   : " + deviceType);
                        System.out.println("Event Time   : " + eventTime);
                        System.out.println("Alarm Name   : " + alarmName);
                        System.out.println("Alarm Status : " + (alarmStatus != null && alarmStatus.equals("1") ? "Active" : "Cleared"));
                        System.out.println("Severity     : " + severity);
                        System.out.println("trap Value : " + trapValue);

                        SNMPTrapLogModel log = new SNMPTrapLogModel();
                        log.setDeviceIP(device_ip);
                        log.setDeviceType(deviceType);
                        log.setDeviceName(deviceName);
                        log.setEventTime(Timestamp.valueOf(LocalDateTime.parse(eventTime)));
                        log.setAlarmName(alarmName);
                        log.setAlarmStatus(alarmStatus);
                        log.setSeverity(severity);
                        log.setAlarmID(trapValue);
                        log.setNodeUptime(nodeUptime);
                        log.setTrapOID(trapOID);
                        log.setTrapTime(trapReceivedTime);

                        SnmpTrapListener.trapLogList.add(log);

                        SNMPTrapUpdateModel model = new SNMPTrapUpdateModel();
                        model.setDeviceIP(device_ip);
                        model.setAlarmStatus(alarmStatus);
                        model.setSeverity(severity);
                        model.setTrapValue(trapValue);
                        model.setNodeUptime(nodeUptime);
                        model.setDeviceName(deviceName);
                        model.setDeviceType(deviceType);
                        model.setServiceName(alarmName);

                        SnmpTrapListener.updateTrapList.add(model);

                        String currentAlarmStatus = SnmpTrapListener.alarmStatus.get(device_ip + "~" + trapValue);
                        if (!currentAlarmStatus.equalsIgnoreCase(alarmStatus)) {

                            EventLog event = new EventLog();
                            event.setDeviceId(device_ip);
                            event.setDeviceName(deviceName);
                            event.setEventMsg(alarmName + " is " + (alarmStatus != null && alarmStatus.equals("1") ? "Active" : "Cleared"));
                            event.setEventTimestamp(trapReceivedTime);
                            event.setNetadminMsg(alarmName + " is " + (alarmStatus != null && alarmStatus.equals("1") ? "Active" : "Cleared"));
                            event.setServiceName(alarmName);
                            event.setSeverity(severity);
                            event.setDevicetype(deviceType);
                            event.setServiceId(trapValue);
                            event.setAlarmStatus(alarmStatus);

                            SnmpTrapListener.insertEventLogList.add(event);
                            SnmpTrapListener.alarmStatus.put(device_ip + "~" + trapValue, alarmStatus);

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Exception occured while parsing data = " + e);
                    }

                } else if (trapOID.equals("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.2")) {
                    isTrapValid = true;

//                        Door Open-#warning,
//1.3.6.1.2.1.1.3.0 = 87 days, 23:49:30.42 - uptime
//1.3.6.1.6.3.1.1.4.1.0 = 1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.2
//1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0 = W4-PWR2 : 	# Name or identifier 
//1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0 = DMBS PSU      # Type or description 
//1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0 = 2025-06-06T16:19:59  #Timestamp of the event
//1.3.6.1.4.1.20246.2.3.1.1.1.2.2.3.0 = 1    #warning, 1 = active, 0 = cleared.
//1.3.6.1.4.1.20246.2.3.1.1.1.2.2.6.0 = 32840
//1.3.6.1.4.1.20246.2.3.1.1.1.2.2.7.0 = 3 :Door Open
//1.3.6.1.4.1.20246.2.3.1.1.1.2.2.10.0 = DoorOpen alarm
//OID: .iso.org.dod.internet.mgmt.mib-2.system.sysUpTime.0. Value: 2110 hours 42 minutes 30.49 seconds; 
//OID: .1.3.6.1.6.3.1.1.4.1.0. Value: .1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.2; 
//OID: .1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0. Value: W4-PWR2; 
//OID: .1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0. Value: DMBS PSU; 
//OID: .1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0. Value: 2025-06-06T15:12:59; 
//OID: .1.3.6.1.4.1.20246.2.3.1.1.1.2.2.3.0. Value: 0; 
//OID: .1.3.6.1.4.1.20246.2.3.1.1.1.2.2.6.0. Value: 32840; 
//OID: .1.3.6.1.4.1.20246.2.3.1.1.1.2.2.7.0. Value: 1; 
//OID: .1.3.6.1.4.1.20246.2.3.1.1.1.2.2.10.0. Value: DoorOpen alarm; 
                    try {

                        String nodeUptime = parsedData.get("1.3.6.1.2.1.1.3.0");
                        String deviceName = parsedData.get("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0");

                        String deviceType = parsedData.get("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0");
                        String eventTime = parsedData.get("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0");
                        String alarmStatus = parsedData.get("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.3.0");
                        String trapValue = parsedData.get("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.6.0");
                        String severity = parsedData.get("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.7.0");
                        String alarmName = parsedData.get("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.10.0");

                        // Parsed summary
                        System.out.println("Trap OID     : " + trapOID);
                        System.out.println("nodeUptime   : " + nodeUptime);
                        System.out.println("deviceName   : " + deviceName);
                        System.out.println("deviceType   : " + deviceType);
                        System.out.println("Event Time   : " + eventTime);
                        System.out.println("Alarm Nme    : " + alarmName);
                        System.out.println("Alarm Status : " + (alarmStatus != null && alarmStatus.equals("1") ? "Active" : "Cleared"));
                        System.out.println("Severity     : " + severity);
                        System.out.println("trap Value : " + trapValue);

                        SNMPTrapLogModel log = new SNMPTrapLogModel();
                        log.setDeviceIP(device_ip);
                        log.setDeviceType(deviceType);
                        log.setDeviceName(deviceName);
                        log.setEventTime(Timestamp.valueOf(LocalDateTime.parse(eventTime)));
                        log.setAlarmName(alarmName);
                        log.setAlarmStatus(alarmStatus);
                        log.setSeverity(severity);
                        log.setAlarmID(trapValue);
                        log.setNodeUptime(nodeUptime);
                        log.setTrapOID(trapOID);
                        log.setTrapTime(trapReceivedTime);

                        SnmpTrapListener.trapLogList.add(log);

                        SNMPTrapUpdateModel model = new SNMPTrapUpdateModel();
                        model.setDeviceIP(device_ip);
                        model.setAlarmStatus(alarmStatus);
                        model.setSeverity(severity);
                        model.setTrapValue(trapValue);
                        model.setNodeUptime(nodeUptime);
                        model.setDeviceName(deviceName);
                        model.setDeviceType(deviceType);
                        model.setServiceName(alarmName);

                        SnmpTrapListener.updateTrapList.add(model);

                        System.out.println("Data:" + device_ip + "~" + trapValue + ":");
                        System.out.println("SnmpTrapListener.alarmStatus value:" + SnmpTrapListener.alarmStatus.toString());

                        String currentAlarmStatus = SnmpTrapListener.alarmStatus.get(device_ip + "~" + trapValue);
                        System.out.println("status:" + currentAlarmStatus + ":" + alarmStatus);
                        if (!currentAlarmStatus.equalsIgnoreCase(alarmStatus)) {

                            EventLog event = new EventLog();
                            event.setDeviceId(device_ip);
                            event.setDeviceName(deviceName);
                            event.setEventMsg(alarmName + " is " + (alarmStatus != null && alarmStatus.equals("1") ? "Active" : "Cleared"));
                            event.setEventTimestamp(trapReceivedTime);
                            event.setNetadminMsg(alarmName + " is " + (alarmStatus != null && alarmStatus.equals("1") ? "Active" : "Cleared"));
                            event.setServiceName(alarmName);
                            event.setSeverity(severity);
                            event.setDevicetype(deviceType);
                            event.setServiceId(trapValue);
                            event.setAlarmStatus(alarmStatus);
                            
                            SnmpTrapListener.insertEventLogList.add(event);
                            SnmpTrapListener.alarmStatus.put(device_ip + "~" + trapValue, alarmStatus);

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Exception occured while parsing data = " + e);
                    }

                } else if (trapOID.equals("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.3")) {
                    isTrapValid = true;

//                        OID: .1.3.6.1.6.3.1.1.4.1.0. Value: .1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.3; 
//OID: .1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0. Value: W4-PWR2; 
//OID: .1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0. Value: DMBS PSU; 
//OID: .1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0. Value: 2025-06-06T12:59:38; 
//OID: .1.3.6.1.4.1.20246.2.3.1.1.1.2.2.13.0. Value: 0; 
//OID: .1.3.6.1.4.1.20246.2.3.1.1.1.2.2.14.0. Value: 32839; 
//OID: .1.3.6.1.4.1.20246.2.3.1.1.1.2.2.15.0. Value: 1; 
//OID: .1.3.6.1.4.1.20246.2.3.1.1.1.2.2.16.0. Value: HighTemp alarm; 
                    try {

                        String nodeUptime = parsedData.get("1.3.6.1.2.1.1.3.0");
                        String deviceName = parsedData.get("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0");

                        String deviceType = parsedData.get("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0");
                        String eventTime = parsedData.get("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0");
                        String alarmStatus = parsedData.get("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.13.0");
                        String trapValue = parsedData.get("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.14.0");
                        String severity = parsedData.get("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.15");
                        String alarmName = parsedData.get("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.16.0");

                        // Parsed summary
                        System.out.println("Trap OID     : " + trapOID);
                        System.out.println("nodeUptime   : " + nodeUptime);
                        System.out.println("deviceName   : " + deviceName);
                        System.out.println("deviceType   : " + deviceType);
                        System.out.println("Event Time   : " + eventTime);
                        System.out.println("Alarm Nme    : " + alarmName);
                        System.out.println("Alarm Status : " + (alarmStatus != null && alarmStatus.equals("1") ? "Active" : "Cleared"));
                        System.out.println("Severity     : " + severity);
                        System.out.println("trap Value : " + trapValue);

                        SNMPTrapLogModel log = new SNMPTrapLogModel();
                        log.setDeviceIP(device_ip);
                        log.setDeviceType(deviceType);
                        log.setDeviceName(deviceName);
                        log.setEventTime(Timestamp.valueOf(LocalDateTime.parse(eventTime)));
                        log.setAlarmName(alarmName);
                        log.setAlarmStatus(alarmStatus);
                        log.setSeverity(severity);
                        log.setAlarmID(trapValue);
                        log.setNodeUptime(nodeUptime);
                        log.setTrapOID(trapOID);
                        log.setTrapTime(trapReceivedTime);

                        SnmpTrapListener.trapLogList.add(log);

                        SNMPTrapUpdateModel model = new SNMPTrapUpdateModel();
                        model.setDeviceIP(device_ip);
                        model.setAlarmStatus(alarmStatus);
                        model.setSeverity(severity);
                        model.setTrapValue(trapValue);
                        model.setNodeUptime(nodeUptime);
                        model.setDeviceName(deviceName);
                        model.setDeviceType(deviceType);
                        model.setServiceName(alarmName);

                        SnmpTrapListener.updateTrapList.add(model);

                        String currentAlarmStatus = SnmpTrapListener.alarmStatus.get(device_ip + "~" + trapValue);
                        if (!currentAlarmStatus.equalsIgnoreCase(alarmStatus)) {

                            EventLog event = new EventLog();
                            event.setDeviceId(device_ip);
                            event.setDeviceName(deviceName);
                            event.setEventMsg(alarmName + " is " + (alarmStatus != null && alarmStatus.equals("1") ? "Active" : "Cleared"));
                            event.setEventTimestamp(trapReceivedTime);
                            event.setNetadminMsg(alarmName + " is " + (alarmStatus != null && alarmStatus.equals("1") ? "Active" : "Cleared"));
                            event.setServiceName(alarmName);
                            event.setSeverity(severity);
                            event.setDevicetype(deviceType);
                            event.setServiceId(trapValue);
                            event.setAlarmStatus(alarmStatus);

                            SnmpTrapListener.insertEventLogList.add(event);
                            SnmpTrapListener.alarmStatus.put(device_ip + "~" + trapValue, alarmStatus);

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Exception occured while parsing data = " + e);
                    }
                }
            }

            if (!isTrapValid) {
                System.out.println("Trap Not valid");

                String insertQuery = "insert into snmp_raw_trap_log (DEVICE_IP, RAW_SNMP_DATA, EVENT_TIME) values(?,?,?)";
                try (Connection connection = Datasource.getConnection();
                        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

                    preparedStatement.setString(1, device_ip);
                    preparedStatement.setString(2, command.toString());
                    preparedStatement.setTimestamp(3, trapReceivedTime);
                    preparedStatement.executeUpdate();

                    System.out.println("Inserted in snmp_raw_trap_log");

                } catch (Exception exp) {
                    System.out.println("DB Exception: " + exp);
                }
            }

        }

    }

}
