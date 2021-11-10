package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem();
        vehicle = new Object();
    }

    @Test
    void givenMessage_ShouldPrintMessage() {
        parkingLotSystem.welcomeMessage();
    }

    @Test
    void givenAVehicle_WhenParked_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("KA-2580", "Black", "Audi");
        boolean isParked = parkingLotSystem.parkVehicle(new Object());
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        Vehicle vehicle = new Vehicle("KA-2580", "Black", "Audi");
        parkingLotSystem.parkVehicle(vehicle);
        boolean isParked = parkingLotSystem.parkVehicle(new Object());
        Assertions.assertFalse(isParked);
    }
}


