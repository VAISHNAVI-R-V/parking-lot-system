package com.bridgelabz;

import org.junit.jupiter.api.Test;

public class ParkingLotSystemTest {
    @Test
    void givenMessage_ShouldPrintMessage() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        parkingLotSystem.welcomeMessage();
    }
}
