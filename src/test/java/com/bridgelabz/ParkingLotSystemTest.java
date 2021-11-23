package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/*****************************************************************************************
 * Purpose: To Implement the PARKING LOT SYSTEM TEST.
 *
 * @author : VAISHNAVI R. VISHWAKARMA.
 * @since : 09-11-2021.
 *****************************************************************************************/

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
//    Vehicle vehicle = null;
    ParkingLotSystemOwner owner = null;
    AirportSecurity airportSecurity = null;
    ParkingLotAttendant attendant = null;

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem(2);
        owner = new ParkingLotSystemOwner();
        airportSecurity = new AirportSecurity();
        attendant = new ParkingLotAttendant();
    }

    @Test
    void givenMessage_ShouldPrintMessage() {
        parkingLotSystem.welcomeMessage();
    }

    @Test
    void givenAVehicle_WhenParked_ShouldReturnTrue() throws ParkingLotSystemException {
        Vehicle vehicle = new Vehicle("KA-2580", "Black", "Audi", "20:30");
        parkingLotSystem.parkVehicle(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() throws ParkingLotSystemException {
        Vehicle vehicle = new Vehicle("KA-2640", "Black", "Audi", "9:25");
        parkingLotSystem.parkVehicle(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertFalse(isUnParked);
    }

    @Test
    void givenAVehicle_WhenUnParked_ShouldReturnTrue() throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("KA-3280", "Silver", "KIA", "7:30");
        parkingLotSystem.parkVehicle(vehicle1);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle1);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAVehicle_WhenUnParkedFromEmptySlot_ShouldReturnFalse() {
        Vehicle vehicle = new Vehicle("IN-4586", "Marron", "Porsche", "16:00");
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    void givenAVehicle_WhenUnParkedAnotherVehicle_ShouldReturnFalse() throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("MH-2021", "Red", "Ford", "6:15");
        Vehicle vehicle2 = new Vehicle("MH-2020", "Yellow", "Alto", "14:15");
        parkingLotSystem.parkVehicle(vehicle1);
        parkingLotSystem.parkVehicle(vehicle2);
        parkingLotSystem.unParkVehicle(vehicle2);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle2);
        Assertions.assertFalse(isUnParked);
    }

    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldInformTheOwner() {
        parkingLotSystem.registerParkingLotObserver(owner);
        Vehicle vehicle1 = new Vehicle("KA-3690", "Brown", "Toyota", "13:50");
        Vehicle vehicle2 = new Vehicle("KA-8520", "Grey", "Fiat", "3:35");
        Vehicle vehicle3 = new Vehicle("MH-3214", "Blue", "Tata", "4:00");
        Vehicle vehicle4 = new Vehicle("MH-7895", "White", "Lexus", "18:00");
        Vehicle vehicle5 = new Vehicle("MH-7095", "White", "BMW", "18:10");
        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle2);
            parkingLotSystem.parkVehicle(vehicle3);
            parkingLotSystem.parkVehicle(vehicle4);
            parkingLotSystem.parkVehicle(vehicle5);
        }, "Parking Lot is Full");
    }

    @Test
    void givenAVehicle_WhenParkingLotIsFull_ShouldInformTheSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        Vehicle vehicle1 = new Vehicle("KA-5710", "Red", "Maruti Suzuki", "2:45");
        Vehicle vehicle2 = new Vehicle("KA-5510", "Red", "BMW", "1:45");
        Vehicle vehicle3 = new Vehicle("KA-5210", "White", "Alto", "4:05");
        Vehicle vehicle4 = new Vehicle("KA-5730", "Red", "Maruti Suzuki", "14:45");
        Vehicle vehicle5 = new Vehicle("MH-4814", "Black", "Nano", "2:00");
        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle2);
            parkingLotSystem.parkVehicle(vehicle3);
            parkingLotSystem.parkVehicle(vehicle4);
            parkingLotSystem.parkVehicle(vehicle5);
        }, "Parking Lot is Full");
    }

    @Test
    void givenWhenParkingLotSpaceAvailableAfterFull_ShouldReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem.registerParkingLotObserver(owner);
        Vehicle vehicle1 = new Vehicle("MH-8595", "Silver", "Hyundai", "5:15");
        Vehicle vehicle2 = new Vehicle("KA-9614", "Blue", "BMW", "11:45");
        Vehicle vehicle3 = new Vehicle("KA-9654", "Blue", "BMW", "5:45");
        Vehicle vehicle4 = new Vehicle("KA-9023", "Orange", "Kia", "1:30");
        Vehicle vehicle5 = new Vehicle("KA-6423", "Orange", "Kia", "1:20");
        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle2);
            parkingLotSystem.parkVehicle(vehicle3);
            parkingLotSystem.parkVehicle(vehicle4);
            parkingLotSystem.parkVehicle(vehicle5);
        }, "Parking Lot is Full");
        parkingLotSystem.unParkVehicle(vehicle2);
        Assertions.assertFalse(owner.isAvailableParkingLotCapacity());
    }

    @Test
    void givenAVehicle_WhenFound_DriverCanGoHome() throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("IN-9771", "Silver", "BMW", "12:30");
        parkingLotSystem.parkVehicle(vehicle1);
        int findMyVehicle = parkingLotSystem.findVehicle(vehicle1);
        Assertions.assertEquals(1, findMyVehicle);
    }

    @Test
    void givenAVehicleToAttendant_WhenParked_ShouldReturnTrue() throws ParkingLotSystemException {
        Vehicle vehicle = new Vehicle("KA-9671", "Black", "Scorpio", "12:00");
        attendant.parkedByAttendant(vehicle);
        Assertions.assertFalse(parkingLotSystem.isVehicleParked(vehicle));
    }

    @Test
    void givenAVehicle_WhenParked_ShouldReturnParkingTime() throws ParkingLotSystemException {
        Vehicle vehicle = new Vehicle("KA-9671", "Black", "BMW", "12:00");
        parkingLotSystem.parkVehicle(vehicle);
        String vehicleParkedTime = parkingLotSystem.getVehicleParkingTime(vehicle);
        Assertions.assertEquals("12:00", vehicleParkedTime);
    }
}

