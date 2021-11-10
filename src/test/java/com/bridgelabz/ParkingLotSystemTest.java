package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingLotSystemTest {
    @Test
    void givenMessage_ShouldPrintMessage() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        parkingLotSystem.welcomeMessage();
    }

    @Test
    void givenAVehicle_WhenParked_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("KA-2580","Black","Audi");
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        boolean isParked = parkingLotSystem.parkVehicle(new Object());
        Assertions.assertTrue(isParked);
    }
}


