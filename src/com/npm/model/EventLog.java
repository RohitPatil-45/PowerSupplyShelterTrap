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
public class EventLog {

    private Timestamp eventTimestamp;
    private String serviceName;
    private String deviceId;
    private String deviceName;
    private String severity;
    private String eventMsg;
    private String netadminMsg;
    private String devicetype;
    private String serviceId;
    private String deviceType;
    private String alarmStatus;
    private Integer isaffected;

    private String alarmAcknowledgedBy;
    private Timestamp alarmAcknowledgedTime;
    private Timestamp clearedEventTimestamp;

    private String problemClear;
    private String serviceID;
    private int acknowledgementStatus;

    private String isNotification;
    private Integer isAcknowledged;

    public Timestamp getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(Timestamp eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getEventMsg() {
        return eventMsg;
    }

    public void setEventMsg(String eventMsg) {
        this.eventMsg = eventMsg;
    }

    public String getNetadminMsg() {
        return netadminMsg;
    }

    public void setNetadminMsg(String netadminMsg) {
        this.netadminMsg = netadminMsg;
    }

    public String getDevicetype() {
        return devicetype;
    }

    public void setDevicetype(String devicetype) {
        this.devicetype = devicetype;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(String alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public Integer getIsaffected() {
        return isaffected;
    }

    public void setIsaffected(Integer isaffected) {
        this.isaffected = isaffected;
    }

    public String getAlarmAcknowledgedBy() {
        return alarmAcknowledgedBy;
    }

    public void setAlarmAcknowledgedBy(String alarmAcknowledgedBy) {
        this.alarmAcknowledgedBy = alarmAcknowledgedBy;
    }

    public Timestamp getAlarmAcknowledgedTime() {
        return alarmAcknowledgedTime;
    }

    public void setAlarmAcknowledgedTime(Timestamp alarmAcknowledgedTime) {
        this.alarmAcknowledgedTime = alarmAcknowledgedTime;
    }

    public Timestamp getClearedEventTimestamp() {
        return clearedEventTimestamp;
    }

    public void setClearedEventTimestamp(Timestamp clearedEventTimestamp) {
        this.clearedEventTimestamp = clearedEventTimestamp;
    }

    public String getProblemClear() {
        return problemClear;
    }

    public void setProblemClear(String problemClear) {
        this.problemClear = problemClear;
    }

    public String getServiceID() {
        return serviceID;
    }

    public void setServiceID(String serviceID) {
        this.serviceID = serviceID;
    }

    public int getAcknowledgementStatus() {
        return acknowledgementStatus;
    }

    public void setAcknowledgementStatus(int acknowledgementStatus) {
        this.acknowledgementStatus = acknowledgementStatus;
    }

    public String getIsNotification() {
        return isNotification;
    }

    public void setIsNotification(String isNotification) {
        this.isNotification = isNotification;
    }

    public Integer getIsAcknowledged() {
        return isAcknowledged;
    }

    public void setIsAcknowledged(Integer isAcknowledged) {
        this.isAcknowledged = isAcknowledged;
    }
    
    
    

}
