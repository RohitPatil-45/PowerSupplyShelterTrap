/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.npm.dao;

import com.npm.datasource.Datasource;
import com.npm.main.SnmpTrapListener;
import com.npm.model.SNMPTrapUpdateModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

/**
 *
 * @author Kratos
 */
public class SNMPTrapUpdate implements Runnable {

    String updateQuery = null;
    String selectQuery = null;
    String insertQuery = null;

//    @Override
//    public void run() {
//       
//        System.out.println("Start update in snmp_trap_live_status");
//        int count = 0;
//        updateQuery = "update snmp_trap_live_status set SEVERITY=?,ALARM_STATUS=?, NODE_UPTIME=? where DEVICE_IP=? and ALARM_ID=?";
////        selectQuery = "select DEVICE_IP from snmp_trap_live_status";
////        insertQuery = "insert into snmp_trap_live_status (DEVICE_IP,DEVICE_NAME,SERVICE_NAME,SEVERITY,ALARM_STATUS,TRAP_VALUE,NODE_UPTIME) values(?,?,?,?,?,?,?)";
////        Set<String> existingDeviceIPs = new HashSet<>();
//
//        try {
//            SnmpTrapListener.updateTrapListTemp.clear();
//            SnmpTrapListener.updateTrapListTemp.addAll(SnmpTrapListener.updateTrapList);
//            SnmpTrapListener.updateTrapList.clear();
//
//        } catch (Exception e) {
//            System.out.println("Exception in batch update=" + e);
//        }
//
//        if (SnmpTrapListener.updateTrapListTemp.isEmpty()) {
//            System.out.println("No data to update.");
//            return;
//        }
//
//        try (Connection connection = Datasource.getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery); //                PreparedStatement insertStmt = connection.prepareStatement(insertQuery)
//                ) {
//
//            connection.setAutoCommit(false);
////            try (PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
////                    ResultSet rs = selectStmt.executeQuery()) {
////                while (rs.next()) {
////                    existingDeviceIPs.add(rs.getString("DEVICE_IP"));
////                }
////            }
//
//            for (SNMPTrapUpdateModel trapLog : SnmpTrapListener.updateTrapListTemp) {
//                try {
////                    if (existingDeviceIPs.contains(trapLog.getDeviceIP())) {
//                    preparedStatement.setString(1, trapLog.getSeverity());
//                    preparedStatement.setString(2, trapLog.getAlarmStatus());
//                    preparedStatement.setString(3, trapLog.getNodeUptime());
//                    preparedStatement.setString(4, trapLog.getDeviceIP());
//                    preparedStatement.setString(5, trapLog.getTrapValue());
//                    preparedStatement.addBatch();
////                    } else {
////                        System.out.println("inside insert");
////                        insertStmt.setString(1, trapLog.getDeviceIP());
////                        insertStmt.setString(2, trapLog.getDeviceName());
////                        insertStmt.setString(3, trapLog.getServiceName());
////                        insertStmt.setString(4, trapLog.getSeverity());
////                        insertStmt.setString(5, trapLog.getAlarmStatus());
////                        insertStmt.setString(6, trapLog.getTrapValue());
////                        insertStmt.setString(7, trapLog.getNodeUptime());
////                        insertStmt.addBatch();
////                    }
//                    if (++count % 1 == 0) {
//                        System.out.println("inside update batch");
//                        preparedStatement.executeBatch();
////                        insertStmt.executeBatch();
//                        preparedStatement.clearBatch();
////                        insertStmt.clearBatch();
//
//                    }
//
//                } catch (Exception e) {
//                    System.out.println("update error: " + e);
//                }
//            }
//
//            preparedStatement.executeBatch();
////            insertStmt.executeBatch();
//            connection.commit();
//            System.out.println("update " + count + " snmp trap live status records.");
//
//        } catch (Exception exp) {
//            System.out.println("DB Exception: " + exp);
//        }
//
//    }
    @Override
    public void run() {
        System.out.println("Start update in snmp_trap_live_status");
        int count = 0;

        // Queries
        String updateQueryGenerate = "update snmp_trap_live_status set SEVERITY=?, ALARM_STATUS=?, NODE_UPTIME=?, GENERATED_TIME=?, CLEARED_TIME=?,IS_NOTIFICATION=? where DEVICE_IP=? and ALARM_ID=?";
        String updateQueryCleared = "update snmp_trap_live_status set SEVERITY=?, ALARM_STATUS=?, NODE_UPTIME=?, CLEARED_TIME=?,IS_NOTIFICATION=? where DEVICE_IP=? and ALARM_ID=?";

        try {
            SnmpTrapListener.updateTrapListTemp.clear();
            SnmpTrapListener.updateTrapListTemp.addAll(SnmpTrapListener.updateTrapList);
            SnmpTrapListener.updateTrapList.clear();
        } catch (Exception e) {
            System.out.println("Exception in batch update=" + e);
        }

        if (SnmpTrapListener.updateTrapListTemp.isEmpty()) {
            System.out.println("No data to update.");
            return;
        }

        try (
                Connection connection = Datasource.getConnection();
                PreparedStatement psGenerate = connection.prepareStatement(updateQueryGenerate);
                PreparedStatement psCleared = connection.prepareStatement(updateQueryCleared);) {
            connection.setAutoCommit(false);
            Timestamp logDateTime = new Timestamp(System.currentTimeMillis());
            for (SNMPTrapUpdateModel trapLog : SnmpTrapListener.updateTrapListTemp) {
                try {
                    if ("1".equals(trapLog.getAlarmStatus())) {
                        // Use psResolved
                        psGenerate.setString(1, trapLog.getSeverity());
                        psGenerate.setString(2, trapLog.getAlarmStatus());
                        psGenerate.setString(3, trapLog.getNodeUptime());
                        psGenerate.setTimestamp(4, logDateTime);
                        psGenerate.setNull(5, java.sql.Types.TIMESTAMP); // if you want empty (NULL)
//                        psResolved.setString(5, trapLog.getResolvedBy());     // You need to add getResolvedBy()
                        psGenerate.setString(6, "true");
                        psGenerate.setString(7, trapLog.getDeviceIP());
                        psGenerate.setString(8, trapLog.getTrapValue());
                        psGenerate.addBatch();
                    } else {
                        // Use psNormal
                        psCleared.setString(1, trapLog.getSeverity());
                        psCleared.setString(2, trapLog.getAlarmStatus());
                        psCleared.setString(3, trapLog.getNodeUptime());

                        psCleared.setTimestamp(4, logDateTime);
//                        psNormal.setNull(5, java.sql.Types.TIMESTAMP); // if you want empty (NULL)
                        psCleared.setString(5, "false");
                        psCleared.setString(6, trapLog.getDeviceIP());

                        psCleared.setString(7, trapLog.getTrapValue());
                        psCleared.addBatch();
                    }

                    if (++count % 50 == 0) { // batch size
                        System.out.println("Executing batch...");
                        psCleared.executeBatch();
                        psGenerate.executeBatch();
                        psCleared.clearBatch();
                        psGenerate.clearBatch();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("update error: " + e);
                }
            }

            psCleared.executeBatch();
            psGenerate.executeBatch();
            connection.commit();
            System.out.println("Updated " + count + " snmp trap live status records.");

        } catch (Exception exp) {
            System.out.println("DB Exception: " + exp);
        }
    }

}
