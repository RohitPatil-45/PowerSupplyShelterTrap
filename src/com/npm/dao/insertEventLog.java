/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.npm.dao;

import com.npm.datasource.Datasource;
import com.npm.main.SnmpTrapListener;
import com.npm.model.EventLog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

/**
 *
 * @author Kratos
 */
public class insertEventLog implements Runnable {

    String insertQuery = null;
    String updateQuery = null;
    String selectQuery = null;

    @Override
    public void run() {
        System.out.println("Start insertion in event Log" + SnmpTrapListener.insertEventLogList.size());
        int count = 0;
        selectQuery = "";
        insertQuery = "INSERT INTO event_log (device_id, device_name, event_timestamp, service_name, event_msg, netadmin_msg, severity,Device_Type,isAffected,isNotification,service_id,Problem_Clear)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
//        updateQuery = "update event_log set Cleared_event_timestamp=?, netadmin_msg=?, isAffected=? where ID=(select ID from event_log where service_id=? and device_id=? order by ID desc limit 1)";

        updateQuery = "UPDATE event_log\n"
                + "SET\n"
                + "    Cleared_event_timestamp = ?,\n"
               // + "    netadmin_msg = ?,\n"
                + "netadmin_msg = CONCAT(netadmin_msg, ' => ', ?),\n"
                + "    isAffected = ?\n"
                + "WHERE\n"
                + "    ID = (\n"
                + "        SELECT id_alias.ID\n"
                + "        FROM (\n"
                + "            SELECT ID\n"
                + "            FROM event_log\n"
                + "            WHERE service_id = ?\n"
                + "              AND device_id = ?\n"
                + "            AND isaffected = '1' ORDER BY ID DESC\n"
                + "            LIMIT 1\n"
                + "        ) AS id_alias\n"
                + "    )\n"
                + ";";
        try {

            SnmpTrapListener.insertEventLogListTemp.clear();
            SnmpTrapListener.insertEventLogListTemp.addAll(SnmpTrapListener.insertEventLogList);
            SnmpTrapListener.insertEventLogList.clear();

        } catch (Exception e) {
            System.out.println("Exception in batch insert=" + e);
        }

        if (SnmpTrapListener.insertEventLogListTemp.isEmpty()) {
            System.out.println("No data to insert.");
            return;
        }

        try (Connection connection = Datasource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                PreparedStatement preparedStatement2 = connection.prepareStatement(updateQuery)) {

            connection.setAutoCommit(false);
            Timestamp logDateTime = new Timestamp(System.currentTimeMillis());
            for (EventLog log : SnmpTrapListener.insertEventLogListTemp) {
                try {
                    //if ("1".equals(log.getAlarmStatus())) {
                    String problem_clear = "";
                    if ("1".equals(log.getAlarmStatus())) {
                        problem_clear = "problem";
                    } else {
                        problem_clear = "clear";
                    }

                    preparedStatement.setString(1, log.getDeviceId());
                    preparedStatement.setString(2, log.getDeviceName());
                    preparedStatement.setTimestamp(3, log.getEventTimestamp());
                    preparedStatement.setString(4, log.getServiceName());
                    preparedStatement.setString(5, log.getEventMsg());
                    preparedStatement.setString(6, log.getNetadminMsg());
                    preparedStatement.setString(7, log.getSeverity());
                    preparedStatement.setString(8, log.getDevicetype());
                    preparedStatement.setString(9, log.getAlarmStatus());
                    preparedStatement.setString(10, "1");
                    preparedStatement.setString(11, log.getServiceId());
                    preparedStatement.setString(12, problem_clear);

                    preparedStatement.addBatch();
                    //} 
                    try {
                        if ("0".equals(log.getAlarmStatus())) {
                            preparedStatement2.setTimestamp(1, logDateTime);
                            
                            preparedStatement2.setString(2, log.getNetadminMsg()); // To Do
                            preparedStatement2.setString(3, "0");
                            preparedStatement2.setString(4, log.getServiceId());
                            preparedStatement2.setString(5, log.getDeviceId());

                            preparedStatement2.addBatch();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Exption__Update :: " + e);
                    }

// updateQuery = "update event_log set Cleared_event_timestamp=?, netadmin_msg=?, isAffected=? where ID=(select ID from event_log where service_id=? and device_id=? order by ID desc limit 1)";
                    if (++count % 1000 == 0) {
                        preparedStatement.executeBatch();
                        preparedStatement.clearBatch();
                    }

                } catch (Exception e) {

                    System.out.println("Insert error: " + e);
                }
            }
            try {
                preparedStatement.executeBatch();
            } catch (Exception e) {
                System.out.println("Exption_Insert_Log : " + e);
            }

            try {
                preparedStatement2.executeBatch();
            } catch (Exception e) {
                System.out.println("Exption_Update_Log : " + e);
            }

            connection.commit();
            System.out.println("Inserted " + count + "event Log records.");

        } catch (Exception exp) {
            System.out.println("DB Exception: " + exp);
        }

    }
}
