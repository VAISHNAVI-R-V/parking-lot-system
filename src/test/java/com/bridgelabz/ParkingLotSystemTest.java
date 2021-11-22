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
    Vehicle vehicle = null;
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
        Assertions.assertFalse(isUnParked);
    }

    @Test
    void givenAVehicle_WhenUnParkedAnotherVehicle_ShouldReturnFalse() throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("MH-2021", "Red", "Ford", "6:15");
        Vehicle vehicle2 = new Vehicle("MH-2020", "Yellow", "Alto", "14:15");
        parkingLotSystem.parkVehicle(vehicle1);
        parkingLotSystem.unParkVehicle(vehicle2);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertFalse(isUnParked);
    }

    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldInformTheOwner() {
        parkingLotSystem.registerParkingLotObserver(owner);
        parkingLotSystem.setCapacity(3);
        Vehicle vehicle1 = new Vehicle("KA-3690", "Brown", "Toyota", "13:50");
        Vehicle vehicle2 = new Vehicle("KA-8520", "Grey", "Fiat", "3:35");
        Vehicle vehicle3 = new Vehicle("MH-3214", "Blue", "Tata", "4:00");
        Vehicle vehicle4 = new Vehicle("MH-7895", "White", "Lexus", "18:00");
        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle2);
            parkingLotSystem.parkVehicle(vehicle3);
            parkingLotSystem.parkVehicle(vehicle4);
        }, "Parking Lot is Full");

    }

    @Test
    void givenAVehicle_WhenParkingLotIsFull_ShouldInformTheSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.setCapacity(1);
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        Vehicle vehicle1 = new Vehicle("KA-5710", "Red", "Maruti Suzuki", "14:45");
        Vehicle vehicle2 = new Vehicle("MH-4814", "Black", "Nano", "2:00");
        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle2);
        }, "Parking Lot is Full");
    }

    @Test
    void givenWhenParkingLotSpaceAvailableAfterFull_ShouldReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem.registerParkingLotObserver(owner);
        Vehicle vehicle1 = new Vehicle("MH-8595", "Silver", "Hyundai", "5:15");
        Vehicle vehicle2 = new Vehicle("KA-9614", "Blue", "BMW", "12:45");
        Vehicle vehicle3 = new Vehicle("KA-9423", "Orange", "Kia", "1:30");
//        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
            parkingLotSystem.parkVehicle(vehicle1);
            parkingLotSystem.parkVehicle(vehicle3);
            parkingLotSystem.parkVehicle(vehicle2);
            parkingLotSystem.unParkVehicle(vehicle2);
        }, "Parking Lot is Full");
    }

    @Test
    void givenAVehicle_WhenFound_DriverCanGoHome() throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("IN-9771", "Silver", "BMW", "12:30");
        parkingLotSystem.parkVehicle(vehicle1);
        int findMyVehicle = parkingLotSystem.findVehicle(vehicle1);
        Assertions.assertEquals(0, findMyVehicle);
    }

    @Test
    void givenAVehicleToAttendant_WhenParked_ShouldReturnTrue() throws ParkingLotSystemException {
        Vehicle vehicle = new Vehicle("KA-9671", "Black", "Scorpio", "12:00");
        attendant.parkedByAttendant(vehicle);
        Assertions.assertTrue(parkingLotSystem.isVehicleParked(vehicle));
    }

    @Test
    void givenAVehicle_WhenParked_ShouldReturnParkingTime() throws ParkingLotSystemException {
        Vehicle vehicle = new Vehicle("KA-9671", "Black", "BMW", "12:00");
        parkingLotSystem.parkVehicle(vehicle);
        String vehicleParkedTime = parkingLotSystem.getVehicleParkingTime(vehicle);
        Assertions.assertEquals("12:00", vehicleParkedTime);
    }

    @Test
    void givenVehicle_WhenWhiteColoredVehicleSearched_ShouldReturnTheLocation() throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("MH-8595", "White", "Hyundai", "5:55");
        Vehicle vehicle2 = new Vehicle("KA-9614", "Black", "BMW", "12:05");
        parkingLotSystem.parkVehicle(vehicle1);
        parkingLotSystem.parkVehicle(vehicle2);
        int whiteColoredVehicle1 = parkingLotSystem.getWhiteColoredVehicle(vehicle1);
        Assertions.assertEquals(0, whiteColoredVehicle1);
        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
            parkingLotSystem.parkVehicle(vehicle2);
        }, "No such A Vehicle Found");
    }

    @Test
    void givenVehicle_WhenBlueColoredToyotaVehicleSearched_ShouldReturnTheLocation() throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("MH-8095", "Blue", "Toyota", "13:55");
        Vehicle vehicle2 = new Vehicle("KA-9610", "White", "BMW", "2:05");
        parkingLotSystem.parkVehicle(vehicle1);
        parkingLotSystem.parkVehicle(vehicle2);
        int blueColoredToyotaVehicle1 = parkingLotSystem.getBlueColoredToyotaVehicle(vehicle1);
        Assertions.assertEquals(0, blueColoredToyotaVehicle1);
        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
            parkingLotSystem.getBlueColoredToyotaVehicle(vehicle2);
        }, "No such A Vehicle Found");
    }

    @Test
    void givenVehicle_WhenBlueColoredToyatoVehicleNumberPlate_Searched_ShouldReturnVehicleNumberPlate() throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("IN-5145", "Blue", "Toyota", "13:55");
        Vehicle vehicle2 = new Vehicle("KA-6710", "White", "BMW", "2:05");
        parkingLotSystem.parkVehicle(vehicle1);
        parkingLotSystem.parkVehicle(vehicle2);
        String vehicleNumberPlate = parkingLotSystem.getBlueColoredToyotaVehicleNumber(vehicle1);
        Assertions.assertEquals(vehicle1.getVehicleNumber(), vehicleNumberPlate);
        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
            parkingLotSystem.getBlueColoredToyotaVehicleNumber(vehicle2);
        }, "No such A Vehicle Found");
    }
}

