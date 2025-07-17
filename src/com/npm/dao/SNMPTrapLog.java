/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.npm.dao;

import com.npm.datasource.Datasource;
import com.npm.main.SnmpTrapListener;
import com.npm.model.SNMPTrapLogModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Kratos
 */
public class SNMPTrapLog implements Runnable {

    String insertQuery = null;

    @Override
    public void run() {
        System.out.println("Start insertion in SNMP Trap Log");
        int count = 0;
        insertQuery = "INSERT INTO snmp_trap_log (DEVICE_IP, DEVICE_NAME, EVENT_TIMESTAMP, ALARM_STATUS, SEVERITY, ALARM_NAME, TRAP_OID, ALARM_ID, TRAP_TIME, NODE_UPTIME, DEVICE_TYPE, "
                + "EVENT_NAME)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            SnmpTrapListener.trapLogListTemp.clear();
            SnmpTrapListener.trapLogListTemp.addAll(SnmpTrapListener.trapLogList);
            SnmpTrapListener.trapLogList.clear();

        } catch (Exception e) {
            System.out.println("Exception in batch insert=" + e);
        }

        if (SnmpTrapListener.trapLogListTemp.isEmpty()) {
            System.out.println("No data to insert.");
            return;
        }

        try (Connection connection = Datasource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            connection.setAutoCommit(false);

            for (SNMPTrapLogModel trapLog : SnmpTrapListener.trapLogListTemp) {
                try {

                    preparedStatement.setString(1, trapLog.getDeviceIP());
                    preparedStatement.setString(2, trapLog.getDeviceName());
                    preparedStatement.setTimestamp(3, trapLog.getEventTime());
                    preparedStatement.setString(4, trapLog.getAlarmStatus());
                    preparedStatement.setString(5, trapLog.getSeverity());
                    preparedStatement.setString(6, trapLog.getAlarmName());
                    preparedStatement.setString(7, trapLog.getTrapOID());
                    preparedStatement.setString(8, trapLog.getAlarmID());
                    preparedStatement.setTimestamp(9, trapLog.getTrapTime());
                    preparedStatement.setString(10, trapLog.getNodeUptime());
                    preparedStatement.setString(11, trapLog.getDeviceType());
                    preparedStatement.setString(12, getAlarmName(trapLog.getAlarmID()));
                    preparedStatement.addBatch();

                    if (++count % 1000 == 0) {
                        preparedStatement.executeBatch();
                        preparedStatement.clearBatch();
                    }

                } catch (Exception e) {
                    System.out.println("Insert error: " + e);
                }
            }

            preparedStatement.executeBatch();
            connection.commit();
            System.out.println("Inserted " + count + " SNMP Trap Log records.");

        } catch (Exception exp) {
            System.out.println("DB Exception: " + exp);
        }

    }

    public String getAlarmName(String alarmId) {

        Map<String, String> alarmName = new HashMap<>();
        alarmName.put("32796", "SysVoltageAlarm");
        alarmName.put("32798", "Mains Alarm");
        alarmName.put("32789", "Rectifier Alarm");
        alarmName.put("32787", "Battery Alarm");
        alarmName.put("32838", "Fire&Smoke alarm");
        alarmName.put("32839", "HighTemp alarm");
        alarmName.put("32786", "BattAlarm");
        alarmName.put("44", "S HW Failure");
        alarmName.put("26", "S Urgent RFA");
        alarmName.put("32800", "Us Alarm 1%");
        alarmName.put("32840", "DoorOpen alarm");
        alarmName.put("32841", "LowFuel alarm");
        alarmName.put("32848", "DG High Temp");
        alarmName.put("32825", "MF Delay");
        alarmName.put("25", "S Non Urg RFA");
        alarmName.put("92", "S RM Redundancy Lost");
        alarmName.put("93", "S RM Lack of Power");
        alarmName.put("63", "S Backup Time Lost");
        alarmName.put("32842", "SitePowDG alarm");

        return alarmName.get(alarmId);
    }

}
