package com.npm.main;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.smi.*;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class TrapSender {

    public static void main(String[] args) throws Exception {
        TrapSender sender = new TrapSender();
        // Send one of the traps by uncommenting
//        sender.sendTrapTypeUsAlarmGenerate();
//         sender.sendTrapTypeUsAlarmCleared();

//        sender.sendTrapTypeNonUrgRFAGenerate();
//         sender.sendTrapTypeNonUrgRFACleared();
         
//        sender.sendTrapTypeMFDelayGenerate();
//        sender.sendTrapTypeMFDelayCleared();
        
//        sender.sendTrapTypeRMLackofPowerGenerate();
//        sender.sendTrapTypeRMLackofPowerCleared();
        
//         sender.sendTrapTypeRedundancyLostGenerate();
//        sender.sendTrapTypeRMRedundancyLostCleared();
        
         
//        sender.sendTrapTypeSitePowDGalarmGenerate();
//        sender.sendTrapTypeSitePowDGalarmCleared();
        
//         sender.sendTrapTypeMainsAlarmGenerate();
//        sender.sendTrapTypeMainsAlarmCleared();
        
//         sender.sendTrapTypeSysVoltageAlarmGenerate();
//        sender.sendTrapTypeSysVoltageAlarmCleared();
        
//         sender.sendTrapTypeRectifierAlarmGenerate();
//        sender.sendTrapTypeRectifierAlarmCleared();
        
//        sender.sendTrapTypeBatteryAlarmGenerate();
//        sender.sendTrapTypeBatteryAlarmCleared();
        
//        sender.sendTrapTypeSBackupTimeLostGenerate();
//        sender.sendTrapTypeSBackupTimeLostCleared();
        
//        sender.sendTrapTypeSUrgentRFAGenerate();
//        sender.sendTrapTypeSUrgentRFACleared();
        
//        sender.sendTrapTypeSHWFailureGenerate();
//        sender.sendTrapTypeSHWFailureCleared();
        
//        sender.sendTrapTypeUrgentRFAGenerate();
//        sender.sendTrapTypeUrgentRFACleared();
        
//        sender.sendTrapTypeBattAlarmGenerate();
//        sender.sendTrapTypeBattAlarmCleared();
        
//        sender.sendTrapTypeDGHighTempGenerate();
//         sender.sendTrapTypeDGHighTempCleared();
        
//        sender.sendTrapTypeFireSmokealarmGenerate();
//         sender.sendTrapTypeFireSmokealarmCleared();
        
//         sender.sendTrapTypeDoorAlarmGenerate();
//           sender.sendTrapTypeDoorAlarmCleared();
        
//         sender.sendTrapTypeHighTempalarmGenerate();
//        sender.sendTrapTypeHighTempalarmCleared();
        
//        sender.sendTrapTypeLowFuelalarmGenerate();
//         sender.sendTrapTypeLowFuelalarmCleared();
    }

    public void sendTrapTypeUsAlarmGenerate() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(1))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(32800))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(1))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString(" Us Alarm 1%")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }

    public void sendTrapTypeUsAlarmCleared() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(0))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(32800))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(3))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString(" Us Alarm 1%")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }

    public void sendTrapTypeNonUrgRFAGenerate() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(1))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(25))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(1))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("Non Urg RFA")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }

    public void sendTrapTypeNonUrgRFACleared() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(0))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(25))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(1))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("Non Urg RFA")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }

    public void sendTrapTypeMFDelayGenerate() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(1))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(32825))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(3))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("MF Delay")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }

    public void sendTrapTypeMFDelayCleared() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(0))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(32825))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(1))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("MF Delay")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }

    public void sendTrapTypeRMLackofPowerGenerate() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(1))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(93))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(3))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("RM Lack of Power")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }

    public void sendTrapTypeRMLackofPowerCleared() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(0))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(93))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(1))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("RM Lack of Power")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }
    
    
     public void sendTrapTypeRedundancyLostGenerate() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(1))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(92))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(3))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("RM Redundancy Lost")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }

    public void sendTrapTypeRMRedundancyLostCleared() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(0))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(92))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(1))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("RM Redundancy Lost")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }
    
    
     public void sendTrapTypeSitePowDGalarmGenerate() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(1))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(32842))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(3))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("SitePowDG alarm")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }

    public void sendTrapTypeSitePowDGalarmCleared() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(0))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(32842))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(1))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("SitePowDG alarm")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }
    
    
         public void sendTrapTypeMainsAlarmGenerate() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(1))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(32798))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(3))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("Mains Alarm")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }

    public void sendTrapTypeMainsAlarmCleared() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(0))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(32798))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(1))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("Mains Alarm")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }
    
    
        public void sendTrapTypeSysVoltageAlarmGenerate() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(1))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(32796))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(3))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("SysVoltageAlarm")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }

    public void sendTrapTypeSysVoltageAlarmCleared() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(0))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(32796))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(1))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("SysVoltageAlarm")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }
    
    
     public void sendTrapTypeRectifierAlarmGenerate() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(1))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(32789))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(3))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("Rectifier Alarm")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }

    public void sendTrapTypeRectifierAlarmCleared() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(0))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(32789))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(1))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("Rectifier Alarm")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }
    
    
     public void sendTrapTypeBatteryAlarmGenerate() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(1))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(32787))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(3))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("Battery Alarm")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }

    public void sendTrapTypeBatteryAlarmCleared() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(0))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(32787))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(1))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("Battery Alarm")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }
    
     public void sendTrapTypeSBackupTimeLostGenerate() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(1))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(63))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(3))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("S Backup Time Lost")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }

    public void sendTrapTypeSBackupTimeLostCleared() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(0))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(63))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(1))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("S Backup Time Lost")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }
    
    
     public void sendTrapTypeSUrgentRFAGenerate() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(1))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(26))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(3))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("S Urgent RFA")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }

    public void sendTrapTypeSUrgentRFACleared() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(0))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(26))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(1))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("S Urgent RFA")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }
    
    
    public void sendTrapTypeSHWFailureGenerate() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(1))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(44))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(3))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("S HW Failure")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }

    public void sendTrapTypeSHWFailureCleared() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(0))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(44))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(1))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("S HW Failure")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }
    
    
     public void sendTrapTypeBattAlarmGenerate() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(1))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(32786))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(3))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("BattAlarm")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }

    public void sendTrapTypeBattAlarmCleared() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(0))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(32786))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(1))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("BattAlarm")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }
    
    
     public void sendTrapTypeDGHighTempGenerate() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(1))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(32848))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(3))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("DG HighTemp")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }

    public void sendTrapTypeDGHighTempCleared() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(0))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(32848))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(1))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("DG HighTemp")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }
    
    
     public void sendTrapTypeFireSmokealarmGenerate() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("GENERIC")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(1))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(32838))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(3))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("Fire&Smoke alarm")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }

    public void sendTrapTypeFireSmokealarmCleared() throws Exception {
        PDU pdu = new PDU();

        // Common OIDs
        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.1")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("GENERIC")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:23:12")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.4.0"), new Integer32(0))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.8.0"), new Integer32(32838))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.9.0"), new Integer32(1))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.11.0"), new OctetString("Fire&Smoke alarm")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 1 Trap");
    }

    public void sendTrapTypeDoorAlarmGenerate() throws Exception {
        PDU pdu = new PDU();

        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("GENERIC")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:19:59")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.3.0"), new Integer32(1))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.6.0"), new Integer32(32840))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.7.0"), new Integer32(3))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.10.0"), new OctetString("DoorOpen alarm")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 2 Trap");
    }

    public void sendTrapTypeDoorAlarmCleared() throws Exception {
        PDU pdu = new PDU();

        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("GENERIC")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:19:59")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.3.0"), new Integer32(0))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.6.0"), new Integer32(32840))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.7.0"), new Integer32(1))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.10.0"), new OctetString("DoorOpen alarm")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 2 Trap");
    }
    
    
     public void sendTrapTypeUrgentRFAGenerate() throws Exception {
        PDU pdu = new PDU();

        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:19:59")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.3.0"), new Integer32(1))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.6.0"), new Integer32(26))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.7.0"), new Integer32(3))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.10.0"), new OctetString("Urgent RFA")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 2 Trap");
    }

    public void sendTrapTypeUrgentRFACleared() throws Exception {
        PDU pdu = new PDU();

        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("POWER SUPPLY")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:19:59")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.3.0"), new Integer32(0))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.6.0"), new Integer32(26))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.7.0"), new Integer32(1))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.10.0"), new OctetString("Urgent RFA")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 2 Trap");
    }

   public void sendTrapTypeHighTempalarmGenerate() throws Exception {
        PDU pdu = new PDU();

        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("GENERIC")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:19:59")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.3.0"), new Integer32(1))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.6.0"), new Integer32(32839))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.7.0"), new Integer32(3))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.10.0"), new OctetString("HighTemp alarm")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 2 Trap");
    }

    public void sendTrapTypeHighTempalarmCleared() throws Exception {
        PDU pdu = new PDU();

        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("GENERIC")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:19:59")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.3.0"), new Integer32(0))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.6.0"), new Integer32(32839))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.7.0"), new Integer32(1))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.10.0"), new OctetString("HighTemp alarm")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 2 Trap");
    }
    
    
    public void sendTrapTypeLowFuelalarmGenerate() throws Exception {
        PDU pdu = new PDU();

        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("GENERIC")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:19:59")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.3.0"), new Integer32(1))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.6.0"), new Integer32(32841))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.7.0"), new Integer32(3))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.10.0"), new OctetString("LowFuel alarm")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 2 Trap");
    }

    public void sendTrapTypeLowFuelalarmCleared() throws Exception {
        PDU pdu = new PDU();

        pdu.add(new VariableBinding(new OID("1.3.6.1.6.3.1.1.4.1.0"), new OID("1.3.6.1.4.1.20246.2.3.1.1.1.3.1.0.2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.2.1.1.3.0"), new TimeTicks(123456))); // uptime

        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.1.0"), new OctetString("W4-PWR2")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.2.0"), new OctetString("GENERIC")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.1.3.0"), new OctetString("2025-06-06T16:19:59")));
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.3.0"), new Integer32(0))); // alarm status
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.6.0"), new Integer32(32841))); // trap value
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.7.0"), new Integer32(1))); // severity
        pdu.add(new VariableBinding(new OID("1.3.6.1.4.1.20246.2.3.1.1.1.2.2.10.0"), new OctetString("LowFuel alarm")));

        pdu.setType(PDU.TRAP);

        send(pdu);
        System.out.println("Sent Type 2 Trap");
    }

    private void send(PDU pdu) throws Exception {
        TransportMapping<UdpAddress> transport = new DefaultUdpTransportMapping();
        Snmp snmp = new Snmp(transport);
        transport.listen();

        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("public")); // Change if needed
        target.setVersion(SnmpConstants.version2c);
        target.setAddress(new UdpAddress("127.0.0.1/162")); // Target IP and port of your SNMP trap receiver
        target.setRetries(2);
        target.setTimeout(1500);

        snmp.send(pdu, target);
        snmp.close();
    }
}
