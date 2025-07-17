/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.npm.main;

import com.npm.dao.SNMPTrapLog;
import com.npm.dao.SNMPTrapUpdate;
import com.npm.dao.insertEventLog;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Kratos
 */
public class PowerSupplyMonitoring {
    
    public static final int THREAD_POOL_SIZE = 3;

    public static void main(String[] args) {

        try {
            // Run the SNMP listener in a new thread
            Thread snmpThread = new Thread(() -> {
                try {
                    SnmpTrapListener mon = new SnmpTrapListener();
                    mon.start();
                } catch (Exception e) {
                    System.out.println("SNMP Listener Exception: " + e);
                    e.printStackTrace();
                }
            });
            snmpThread.start(); // Start SNMP thread
        } catch (Exception e) {
            System.out.println("Exception === " + e);
        }

        try {
            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(THREAD_POOL_SIZE);
            //Insertion in SNMPTrapLog Table
            scheduler.scheduleAtFixedRate(new SNMPTrapLog(), 0, 5, TimeUnit.SECONDS);

            //update in snmp_trap_live_status;
            scheduler.scheduleAtFixedRate(new SNMPTrapUpdate(), 0, 5, TimeUnit.SECONDS);
            
            //insert in event log;
            scheduler.scheduleAtFixedRate(new insertEventLog(), 0, 5, TimeUnit.SECONDS);
            
        } catch (Exception e) {
            System.out.println("Exception === " + e);
        }

    }
}
