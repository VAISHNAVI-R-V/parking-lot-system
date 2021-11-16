package com.bridgelabz;

/**************************************************************
 * Purpose: To Implement the PARKING LOT SYSTEM program.
 *
 * @author : VAISHNAVI R. VISHWAKARMA.
 * @since : 09-11-2021.
 **************************************************************/
public class Vehicle {
    private final String name;
    private final String vehicleNumber;
    private final String vehicleColor;
    private final String parkedTime;

    public Vehicle(String vehicleNumber, String vehicleColor, String name, String parkedTime) {
        this.name = name;
        this.vehicleNumber = vehicleNumber;
        this.vehicleColor = vehicleColor;
        this.parkedTime = parkedTime;
    }

    // Generated Getters
    public String getName() {
        return name;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public String getParkedTime() {
        return parkedTime;
    }
}
