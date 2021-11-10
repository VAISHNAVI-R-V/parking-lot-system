package com.bridgelabz;

/**
 * Purpose: To Implement the PARKING LOT SYSTEM program.
 *
 * @author : VAISHNAVI R. VISHWAKARMA.
 * @since : 09-11-2021.
 */
public class Vehicle {
    private final String name;
    private final String vehicleNumber;
    private final String vehicleColor;

    public Vehicle(String vehicleNumber, String vehicleColor, String name) {
        this.name = name;
        this.vehicleNumber = vehicleNumber;
        this.vehicleColor = vehicleColor;
    }
}
