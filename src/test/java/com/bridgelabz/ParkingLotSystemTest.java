package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotSystemTest {
     ParkingLotSystem parkingLotSystem;

    @BeforeEach
    void setUp() {
         parkingLotSystem = new ParkingLotSystem();
    }

    @Test
    void givenMessage_ShouldPrintMessage() {
        parkingLotSystem.welcomeMessage();
    }

    @Test
    void givenAVehicle_WhenParked_ShouldReturnTrue() {
        Vehicle vehicle = new Vehicle("KA-2580","Black","Audi");
        boolean isParked = parkingLotSystem.parkVehicle(new Object());
        Assertions.assertTrue(isParked);
    }
}


