/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.npm.model;

import java.sql.Timestamp;

/**
 *
 * @author Kratos
 */
public class SNMPTrapLogModel {
    
    private String deviceIP;
    
    private String deviceName;
    
    private String deviceType;
    
    private Timestamp eventTime;
    
    private String eventName;
    
    private String alarmStatus;
    
    private String alarmID;
    
    private String severity;
    
    private String alarmName;
    
    private String nodeUptime;
    
    private String trapOID;
    
    private Timestamp trapTime;
    
   

    public String getDeviceIP() {
        return deviceIP;
    }

    public void setDeviceIP(String deviceIP) {
        this.deviceIP = deviceIP;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Timestamp getEventTime() {
        return eventTime;
    }

    public void setEventTime(Timestamp eventTime) {
        this.eventTime = eventTime;
    }

    public String getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(String alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public String getAlarmID() {
        return alarmID;
    }

    public void setAlarmID(String alarmID) {
        this.alarmID = alarmID;
    }


    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public String getNodeUptime() {
        return nodeUptime;
    }

    public void setNodeUptime(String nodeUptime) {
        this.nodeUptime = nodeUptime;
    }

    public String getTrapOID() {
        return trapOID;
    }

    public void setTrapOID(String trapOID) {
        this.trapOID = trapOID;
    }

    public Timestamp getTrapTime() {
        return trapTime;
    }

    public void setTrapTime(Timestamp trapTime) {
        this.trapTime = trapTime;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

  
    
    
}
