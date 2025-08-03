/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.npm.main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.npm.datasource.Datasource;
import com.npm.model.EventLog;
import com.npm.model.SNMPTrapLogModel;
import com.npm.model.SNMPTrapUpdateModel;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.PDU;
import org.snmp4j.smi.VariableBinding;

/**
 *
 * @author Kratos
 */
public class TrapThreadClass implements Runnable {

    public CommandResponderEvent e;

    String eventMsg1 = null;
    String netadminMsg = null;
    String isAffected = null;
    String problem = null;

    private static final int MAX_RETRIES = 3;
    private static final int RETRY_DELAY_MS = 2000;

    private final ObjectMapper mapper = new ObjectMapper();

    public TrapThreadClass(CommandResponderEvent events) {
        this.e = events;

    }

    public void run() {

        SnmpTrapListener.trap_count = SnmpTrapListener.trap_count + 1;
        System.out.println("trap_count:" + SnmpTrapListener.trap_count);

        try {

            PDU command = e.getPDU();
            if (command != null) {
//            String device_ip = e.getPeerAddress().toString();
//           device_ip = device_ip.split("/")[0];
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
                            System.out.println("Alarm Status OG : " + alarmStatus);

                            System.out.println("Alarm Status : " + (alarmStatus != null && alarmStatus.equals("1") ? "Active" : "Cleared"));
                            System.out.println("Alarm Name   : " + alarmName);

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

                            // String currentAlarmStatus = SnmpTrapListener.alarmStatus.get(device_ip + "~" + trapValue);
                            //if (!currentAlarmStatus.equalsIgnoreCase(alarmStatus)) {
//                            EventLog event = new EventLog();
//                            event.setDeviceId(device_ip);
//                            event.setDeviceName(deviceName);
//                            event.setEventMsg(alarmName + " is " + (alarmStatus != null && alarmStatus.equals("1") ? "Active" : "Cleared"));
//                            event.setEventTimestamp(trapReceivedTime);
//                            event.setNetadminMsg(alarmName + " is " + (alarmStatus != null && alarmStatus.equals("1") ? "Active" : "Cleared"));
//                            event.setServiceName(alarmName);
//                            event.setSeverity(severity);
//                            event.setDevicetype(deviceType);
//                            event.setServiceId(trapValue);
//                            event.setAlarmStatus(alarmStatus);
//
//                            SnmpTrapListener.insertEventLogList.add(event);
                            //   SnmpTrapListener.alarmStatus.put(device_ip + "~" + trapValue, alarmStatus);
                            eventMsg1 = alarmName + " is " + (alarmStatus != null && alarmStatus.equals("1") ? "Active" : "Cleared");
                            netadminMsg = eventMsg1;
                            isAffected = alarmStatus != null && alarmStatus.equals("1") ? "1" : "0";
                            problem = alarmStatus != null && alarmStatus.equals("1") ? "problem" : "clear";
                            sendEventLogToApi(device_ip, deviceName, eventMsg1, Integer.valueOf(severity), alarmName, trapReceivedTime, netadminMsg, isAffected, problem, trapValue, deviceType, 0);

                            //  }
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
                            //  System.out.println("SnmpTrapListener.alarmStatus value:" + SnmpTrapListener.alarmStatus.toString());

                            // String currentAlarmStatus = SnmpTrapListener.alarmStatus.get(device_ip + "~" + trapValue);
                            //System.out.println("status:" + currentAlarmStatus + ":" + alarmStatus);
                            // if (!currentAlarmStatus.equalsIgnoreCase(alarmStatus)) {
//                            EventLog event = new EventLog();
//                            event.setDeviceId(device_ip);
//                            event.setDeviceName(deviceName);
//                            event.setEventMsg(alarmName + " is " + (alarmStatus != null && alarmStatus.equals("1") ? "Active" : "Cleared"));
//                            event.setEventTimestamp(trapReceivedTime);
//                            event.setNetadminMsg(alarmName + " is " + (alarmStatus != null && alarmStatus.equals("1") ? "Active" : "Cleared"));
//                            event.setServiceName(alarmName);
//                            event.setSeverity(severity);
//                            event.setDevicetype(deviceType);
//                            event.setServiceId(trapValue);
//                            event.setAlarmStatus(alarmStatus);
//
//                            SnmpTrapListener.insertEventLogList.add(event);
                            eventMsg1 = alarmName + " is " + (alarmStatus != null && alarmStatus.equals("1") ? "Active" : "Cleared");
                            netadminMsg = eventMsg1;
                            isAffected = alarmStatus != null && alarmStatus.equals("1") ? "1" : "0";
                            problem = alarmStatus != null && alarmStatus.equals("1") ? "problem" : "clear";
                            sendEventLogToApi(device_ip, deviceName, eventMsg1, Integer.valueOf(severity), alarmName, trapReceivedTime, netadminMsg, isAffected, problem, trapValue, deviceType, 0);
                            //   SnmpTrapListener.alarmStatus.put(device_ip + "~" + trapValue, alarmStatus);

                            //  }
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

                            // String currentAlarmStatus = SnmpTrapListener.alarmStatus.get(device_ip + "~" + trapValue);
                            // if (!currentAlarmStatus.equalsIgnoreCase(alarmStatus)) {
//                            EventLog event = new EventLog();
//                            event.setDeviceId(device_ip);
//                            event.setDeviceName(deviceName);
//                            event.setEventMsg(alarmName + " is " + (alarmStatus != null && alarmStatus.equals("1") ? "Active" : "Cleared"));
//                            event.setEventTimestamp(trapReceivedTime);
//                            event.setNetadminMsg(alarmName + " is " + (alarmStatus != null && alarmStatus.equals("1") ? "Active" : "Cleared"));
//                            event.setServiceName(alarmName);
//                            event.setSeverity(severity);
//                            event.setDevicetype(deviceType);
//                            event.setServiceId(trapValue);
//                            event.setAlarmStatus(alarmStatus);
//
//                            SnmpTrapListener.insertEventLogList.add(event);
                            //    SnmpTrapListener.alarmStatus.put(device_ip + "~" + trapValue, alarmStatus);
                            eventMsg1 = alarmName + " is " + (alarmStatus != null && alarmStatus.equals("1") ? "Active" : "Cleared");
                            netadminMsg = eventMsg1;
                            isAffected = alarmStatus != null && alarmStatus.equals("1") ? "1" : "0";
                            problem = alarmStatus != null && alarmStatus.equals("1") ? "problem" : "clear";
                            sendEventLogToApi(device_ip, deviceName, eventMsg1, Integer.valueOf(severity), alarmName, trapReceivedTime, netadminMsg, isAffected, problem, trapValue, deviceType, 0);

                            //  }
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

        } catch (Exception exp) {
            exp.printStackTrace();
            System.out.println("Exception Trpthread class: " + exp);
        }

    }

    public void sendEventLogToApi(String deviceID, String deviceName, String eventMsg, int severity, String serviceName, Timestamp evenTimestamp,
            String netadmin_msg, String isAffected, String problem, String serviceId, String deviceType, int attempt) {
        EventLog log = new EventLog();
        log.setDeviceId(deviceID);
        log.setDeviceName(deviceName);
        log.setEventMsg(eventMsg);
        log.setSeverity(String.valueOf(severity));
        log.setServiceName(serviceName);
        log.setEventTimestamp(evenTimestamp);
        log.setNetadminMsg(netadmin_msg);
        log.setIsaffected(Integer.valueOf(isAffected));
        log.setProblemClear(problem);
        log.setServiceID(serviceId);
        log.setDeviceType(deviceType);

        System.out.println("service id = " + serviceId);
        System.out.println("sAffected = " + isAffected);

        CloseableHttpClient httpClient = HttpClients.createDefault();
        ObjectMapper mapper = new ObjectMapper();

        try {
            String json = mapper.writeValueAsString(log);
            HttpPost request = new HttpPost("http://localhost:8083/api/event/log"); // adjust host/port
            request.setHeader("Content-Type", "application/json");
            request.setEntity(new StringEntity(json));

            CloseableHttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode >= 200 && statusCode < 300) {
                System.out.println("Log sent successfully: " + statusCode);
            } else {
                System.err.println("Failed to send log, status: " + statusCode);
                retryIfNeeded(log, attempt);
            }

            response.close();
        } catch (IOException e) {
            System.err.println("Exception while sending log: " + e.getMessage());
            retryIfNeeded(log, attempt);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void retryIfNeeded(EventLog log, int attempt) {
        if (attempt < MAX_RETRIES) {
            System.out.println("Retrying sendEventLogToApi... Attempt " + (attempt + 1));
            try {
                Thread.sleep(RETRY_DELAY_MS);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt(); // Preserve interrupt status
                return;
            }

            // Retry the API call with incremented attempt count
            sendEventLogToApi(
                    log.getDeviceId(),
                    log.getDeviceName(),
                    log.getEventMsg(),
                    Integer.valueOf(log.getSeverity()),
                    log.getServiceName(),
                    log.getEventTimestamp(),
                    log.getNetadminMsg(),
                    log.getIsaffected().toString(),
                    log.getProblemClear(),
                    log.getServiceID(),
                    log.getDeviceType(),
                    attempt + 1
            );
        } else {
            System.err.println("Max retries reached. Dropping event log.");
        }
    }

}
