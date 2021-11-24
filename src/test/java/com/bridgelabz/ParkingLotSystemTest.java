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
        Vehicle vehicle = new Vehicle("KA-2580", "Black", "Audi", "20:30", Vehicle.Size.LARGE);
        parkingLotSystem.parkVehicle(vehicle, Vehicle.DriverType.HANDICAPED);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() throws ParkingLotSystemException {
        Vehicle vehicle = new Vehicle("KA-2640", "Black", "Audi", "9:25", Vehicle.Size.LARGE);
        parkingLotSystem.parkVehicle(vehicle, Vehicle.DriverType.HANDICAPED);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertFalse(isUnParked);
    }

    @Test
    void givenAVehicle_WhenUnParked_ShouldReturnFalse() throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("KA-3280", "Silver", "KIA", "7:30", Vehicle.Size.LARGE);
        parkingLotSystem.parkVehicle(vehicle1, Vehicle.DriverType.HANDICAPED);
        parkingLotSystem.unParkVehicle(vehicle1);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle1);
        Assertions.assertFalse(isUnParked);
    }

    @Test
    void givenAVehicle_WhenUnParkedFromEmptySlot_ShouldReturnFalse() {
        Vehicle vehicle = new Vehicle("IN-4586", "Marron", "Porsche", "16:00", Vehicle.Size.LARGE);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    void givenAVehicle_WhenUnParkedAnotherVehicle_ShouldReturnFalse() throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("MH-2021", "Red", "Ford", "6:15", Vehicle.Size.LARGE);
        Vehicle vehicle2 = new Vehicle("MH-2020", "Yellow", "Alto", "14:15", Vehicle.Size.LARGE);
        parkingLotSystem.parkVehicle(vehicle1, Vehicle.DriverType.HANDICAPED);
        parkingLotSystem.parkVehicle(vehicle2, Vehicle.DriverType.HANDICAPED);
        parkingLotSystem.unParkVehicle(vehicle2);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle2);
        Assertions.assertFalse(isUnParked);
    }

    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldInformTheOwner() {
        parkingLotSystem.registerParkingLotObserver(owner);
        Vehicle vehicle1 = new Vehicle("KA-3690", "Brown", "Toyota", "13:50", Vehicle.Size.LARGE);
        Vehicle vehicle2 = new Vehicle("KA-8520", "Grey", "Fiat", "3:35", Vehicle.Size.LARGE);
        Vehicle vehicle3 = new Vehicle("MH-3214", "Blue", "Tata", "4:00", Vehicle.Size.LARGE);
        Vehicle vehicle4 = new Vehicle("MH-7895", "White", "Lexus", "18:00", Vehicle.Size.LARGE);
        Vehicle vehicle5 = new Vehicle("MH-7095", "White", "BMW", "18:10", Vehicle.Size.LARGE);
        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
            parkingLotSystem.parkVehicle(vehicle1, Vehicle.DriverType.HANDICAPED);
            parkingLotSystem.parkVehicle(vehicle2, Vehicle.DriverType.HANDICAPED);
            parkingLotSystem.parkVehicle(vehicle3, Vehicle.DriverType.HANDICAPED);
            parkingLotSystem.parkVehicle(vehicle4, Vehicle.DriverType.HANDICAPED);
            parkingLotSystem.parkVehicle(vehicle5, Vehicle.DriverType.HANDICAPED);
        }, "Parking Lot is Full");
    }

    @Test
    void givenAVehicle_WhenParkingLotIsFull_ShouldInformTheSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotObserver(airportSecurity);
        Vehicle vehicle1 = new Vehicle("KA-5710", "Red", "Maruti Suzuki", "2:45", Vehicle.Size.LARGE);
        Vehicle vehicle2 = new Vehicle("KA-5510", "Red", "BMW", "1:45", Vehicle.Size.LARGE);
        Vehicle vehicle3 = new Vehicle("KA-5210", "White", "Alto", "4:05", Vehicle.Size.LARGE);
        Vehicle vehicle4 = new Vehicle("KA-5730", "Red", "Maruti Suzuki", "14:45", Vehicle.Size.LARGE);
        Vehicle vehicle5 = new Vehicle("MH-4814", "Black", "Nano", "2:00", Vehicle.Size.LARGE);
        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
            parkingLotSystem.parkVehicle(vehicle1, Vehicle.DriverType.HANDICAPED);
            parkingLotSystem.parkVehicle(vehicle2, Vehicle.DriverType.HANDICAPED);
            parkingLotSystem.parkVehicle(vehicle3, Vehicle.DriverType.HANDICAPED);
            parkingLotSystem.parkVehicle(vehicle4, Vehicle.DriverType.HANDICAPED);
            parkingLotSystem.parkVehicle(vehicle5, Vehicle.DriverType.HANDICAPED);
        }, "Parking Lot is Full");
    }

    @Test
    void givenWhenParkingLotSpaceAvailableAfterFull_ShouldReturnTrue() throws ParkingLotSystemException {
        parkingLotSystem.registerParkingLotObserver(owner);
        Vehicle vehicle1 = new Vehicle("MH-8595", "Silver", "Hyundai", "5:15", Vehicle.Size.LARGE);
        Vehicle vehicle2 = new Vehicle("KA-9614", "Blue", "BMW", "11:45", Vehicle.Size.LARGE);
        Vehicle vehicle3 = new Vehicle("KA-9654", "Blue", "BMW", "5:45", Vehicle.Size.LARGE);
        Vehicle vehicle4 = new Vehicle("KA-9023", "Orange", "Kia", "1:30", Vehicle.Size.LARGE);
        Vehicle vehicle5 = new Vehicle("KA-6423", "Orange", "alto", "1:20", Vehicle.Size.LARGE);
        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
            parkingLotSystem.parkVehicle(vehicle1, Vehicle.DriverType.HANDICAPED);
            parkingLotSystem.parkVehicle(vehicle2, Vehicle.DriverType.HANDICAPED);
            parkingLotSystem.parkVehicle(vehicle3, Vehicle.DriverType.HANDICAPED);
            parkingLotSystem.parkVehicle(vehicle4, Vehicle.DriverType.HANDICAPED);
            parkingLotSystem.parkVehicle(vehicle5, Vehicle.DriverType.HANDICAPED);
        }, "Parking Lot is Full");
        parkingLotSystem.unParkVehicle(vehicle2);
        Assertions.assertFalse(owner.isAvailableParkingLotCapacity());
    }

    @Test
    void givenAVehicle_WhenFound_DriverCanGoHome() throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("IN-9771", "Silver", "BMW", "12:30", Vehicle.Size.LARGE);
        parkingLotSystem.parkVehicle(vehicle1, Vehicle.DriverType.HANDICAPED);
        int findMyVehicle = parkingLotSystem.findVehicle(vehicle1);
        Assertions.assertEquals(1, findMyVehicle);
    }

    @Test
    void givenAVehicleToAttendant_WhenParked_ShouldReturnTrue() throws ParkingLotSystemException {
        Vehicle vehicle = new Vehicle("KA-9671", "Black", "Scorpio", "12:00", Vehicle.Size.LARGE);
        attendant.parkedByAttendant(vehicle);
        Assertions.assertFalse(parkingLotSystem.isVehicleParked(vehicle));
    }

    @Test
    void givenAVehicle_WhenParked_ShouldReturnParkingTime() throws ParkingLotSystemException {
        Vehicle vehicle = new Vehicle("KA-9671", "Black", "BMW", "12:00", Vehicle.Size.LARGE);
        parkingLotSystem.parkVehicle(vehicle, Vehicle.DriverType.HANDICAPED);
        String vehicleParkedTime = parkingLotSystem.getVehicleParkingTime(vehicle);
        Assertions.assertEquals("12:00", vehicleParkedTime);
    }

    @Test
    void givenAVehicle_WhenParked_ShouldParkedVehicleEvenly() throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("IN-5211", "White", "Toyota", "9:35", Vehicle.Size.LARGE);
        Vehicle vehicle2 = new Vehicle("IN-5281", "White", "BMW", "9:25", Vehicle.Size.LARGE);
        parkingLotSystem.parkVehicle(vehicle1, Vehicle.DriverType.HANDICAPED);
        parkingLotSystem.parkVehicle(vehicle2, Vehicle.DriverType.HANDICAPED);
        Assertions.assertEquals(1, parkingLotSystem.findVehicle(vehicle1));
        Assertions.assertEquals(2, parkingLotSystem.findVehicle(vehicle2));
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnThePositionOfWhiteColorVehicle() throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("KA-2654", "White", "BMW", "5:45", Vehicle.Size.LARGE);
        Vehicle vehicle2 = new Vehicle("KA-1023", "Orange", "Kia", "1:30", Vehicle.Size.LARGE);
        parkingLotSystem.parkVehicle(vehicle1, Vehicle.DriverType.HANDICAPED);
        Assertions.assertEquals(1, parkingLotSystem.getVehicleBYColour(vehicle1, "White"));
        parkingLotSystem.parkVehicle(vehicle2, Vehicle.DriverType.HANDICAPED);
        Assertions.assertThrows(ParkingLotSystemException.class, () -> parkingLotSystem.getVehicleBYColour(vehicle2,
                "White"));
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnThePositionOfToyotaBlueColorVehicle()
            throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("KA-1023", "Blue", "Toyota", "1:30", Vehicle.Size.LARGE);
        Vehicle vehicle2 = new Vehicle("KA-1023", "Orange", "Kia", "1:30", Vehicle.Size.LARGE);
        parkingLotSystem.parkVehicle(vehicle1, Vehicle.DriverType.HANDICAPED);
        Assertions.assertEquals(1, parkingLotSystem.getVehicleBYNameAndColour
                (vehicle1, "Toyota", "Blue"));
        parkingLotSystem.parkVehicle(vehicle2, Vehicle.DriverType.HANDICAPED);
        Assertions.assertThrows(ParkingLotSystemException.class, () ->
                parkingLotSystem.getVehicleBYNameAndColour(vehicle2, "Toyota", "Blue"));
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnThePositionOfToyotaBlueColorVehicleWithNumberPlate()
            throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("KA-2221", "Blue", "Toyota", "6:15", Vehicle.Size.LARGE);
        Vehicle vehicle2 = new Vehicle("MH-2020", "Yellow", "Alto", "14:15", Vehicle.Size.LARGE);
        parkingLotSystem.parkVehicle(vehicle1, Vehicle.DriverType.HANDICAPED);
        Assertions.assertEquals(1, parkingLotSystem.getVehicleBYBlueColorToyotaWithNumberPlate
                (vehicle1, "Toyota", "Blue", "KA-2221"));
        parkingLotSystem.parkVehicle(vehicle2, Vehicle.DriverType.HANDICAPED);
        Assertions.assertThrows(ParkingLotSystemException.class, () ->
                parkingLotSystem.getVehicleBYBlueColorToyotaWithNumberPlate
                        (vehicle2, "Toyota", "Blue", "MH-2020"));
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnBMWVehicle()
            throws ParkingLotSystemException {
        Vehicle vehicle1 = new Vehicle("KA-2561", "Blue", "BMW", "7:05", Vehicle.Size.LARGE);
        Vehicle vehicle2 = new Vehicle("MH-2620", "Yellow", "Alto", "6:45", Vehicle.Size.LARGE);
        parkingLotSystem.parkVehicle(vehicle1, Vehicle.DriverType.HANDICAPED);
        Assertions.assertEquals(1, parkingLotSystem.getBMWVehicle(vehicle1, "BMW"));
        parkingLotSystem.parkVehicle(vehicle2, Vehicle.DriverType.HANDICAPED);
        Assertions.assertThrows(ParkingLotSystemException.class, () ->
                parkingLotSystem.getBMWVehicle(vehicle2, "BMW"));
    }

    @Test
    void givenHandicappedVehicleParked_whenChecked_shouldReturnFirstEmptySpot() throws ParkingLotSystemException {
        Vehicle vehicle = new Vehicle("BMW", "HR-26CF2784", "Blue", "11:00", Vehicle.Size.LARGE);
        parkingLotSystem.parkVehicle(vehicle, Vehicle.DriverType.HANDICAPED);
        int spot = parkingLotSystem.findVehicle(vehicle);
        Assertions.assertEquals(1, spot);
    }

    @Test
    void givenHandicapped_whenChecked_returnsParkLot() {
        Vehicle vehicle1 = new Vehicle("IN-9431", "Silver", "BMW", "5:25", Vehicle.Size.LARGE);
        parkingLotSystem.parkVehicle(vehicle1, Vehicle.DriverType.HANDICAPED);
        int lot = parkingLotSystem.findVehicle(vehicle1);
        Assertions.assertEquals(1, lot);
        Vehicle vehicle2 = new Vehicle("IN-8531", "Silver", "Ferrari", "5:05", Vehicle.Size.LARGE);
        parkingLotSystem.parkVehicle(vehicle2, Vehicle.DriverType.NORMAL);
    }
}

