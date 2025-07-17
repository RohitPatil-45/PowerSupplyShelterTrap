/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.npm.model;

/**
 *
 * @author Kratos
 */
public class SNMPTrapUpdateModel {

    private String deviceIP;

    private String deviceName;

    private String deviceType;

    private String serviceName;

    private String severity;

    private String alarmStatus;

    private String trapValue;

    private String nodeUptime;

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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(String alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public String getTrapValue() {
        return trapValue;
    }

    public void setTrapValue(String trapValue) {
        this.trapValue = trapValue;
    }

    public String getNodeUptime() {
        return nodeUptime;
    }

    public void setNodeUptime(String nodeUptime) {
        this.nodeUptime = nodeUptime;
    }

}
