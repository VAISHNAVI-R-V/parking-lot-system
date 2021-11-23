package com.bridgelabz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*****************************************************************************************
 * Purpose: To Implement the PARKING LOT SYSTEM program.
 *
 * @author : VAISHNAVI R. VISHWAKARMA.
 * @since : 09-11-2021.
 *****************************************************************************************/
public class ParkingLotSystem {
    private ParkingLotSystemOwner owner;
    private int actualCapacity;
    public static List<Vehicle> vehicles;
    private List<ParkingLotObserver> observers;
    private AirportSecurity security;
//    private Vehicle vehicle;
    private Map<Integer, Vehicle> parkingLot1 = new HashMap<>();
    private Map<Integer, Vehicle> parkingLot2 = new HashMap<>();
    Map currentLot = parkingLot1;
    int slotOfLot1 = 0;
    int slotOfLot2 = 0;

    public ParkingLotSystem(int capacity) {
        this.observers = new ArrayList<>();
        vehicles = new ArrayList<>();
        this.actualCapacity = capacity;
    }

    /**
     * Purpose : To print Welcome Message.
     */
    public void welcomeMessage() {
        System.out.println("Welcome to Parking Lot Service Program...!! :-) ");
    }

    /**
     * Purpose: To distribute vehicles evenly in Lots.
     *
     * @param vehicle : vehicle is used to park.
     */
    public void evenlyParkedVehicle(Vehicle vehicle) {
        if (currentLot == parkingLot1) {
            slotOfLot1 = slotOfLot1 + 1;
            parkingLot1.put(1, vehicle);
            this.parkingLot1.entrySet().forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));
            currentLot = parkingLot2;
            return;
        }
        if (currentLot == parkingLot2) {
            slotOfLot2 = slotOfLot2 + 1;
            parkingLot2.put(2, vehicle);
            this.parkingLot2.entrySet().forEach(entry -> System.out.println(entry.getKey() + " : " + entry.getValue()));
            currentLot = parkingLot1;
            return;
        }
    }

    /**
     * Purpose : To create method of registerOwner to Inform the owner.
     *
     * @param observer : takes observer to register ParkingLot Observer.
     */
    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Purpose : To register Airport Security.
     *
     * @param airportSecurity : takes airportSecurity to register Security.
     */
    public void registerSecurity(AirportSecurity airportSecurity) {
        this.security = airportSecurity;
    }

    /**
     * Purpose : To set the Capacity of ParkingLot.
     *
     * @param capacity : takes Capacity of Parking Lot.
     */
    public void setCapacity(int capacity) {
        this.actualCapacity = capacity;
    }

    /**
     * Purpose : To Park the Vehicle in Parking Lot.
     *
     * @param vehicle : vehicle is used to park
     * @throws : ParkingLotSystemException : Exception Type Message.
     */
    public void parkVehicle(Vehicle vehicle) throws ParkingLotSystemException {
        if (this.slotOfLot1 == this.actualCapacity && this.slotOfLot2 == this.actualCapacity) {
            for (ParkingLotObserver parkingLotSystemObserver : observers)
                parkingLotSystemObserver.isFullParkingLotCapacity();
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL, "Parking lot is full");
        }
        if (this.parkingLot1.containsValue(vehicle) || this.parkingLot2.containsValue(vehicle)) {
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_A_VEHICLE, "Vehicle already exist");
        }
        this.evenlyParkedVehicle(vehicle);
    }

    /**
     * Purpose : To UnParked the Vehicle from parking lot.
     *
     * @param vehicle : vehicle is used to unpark
     * @throws : ParkingLotSystemException : Exception Type Message.
     */
    public void unParkVehicle(Vehicle vehicle) throws ParkingLotSystemException {
        if (vehicle == null) {
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_A_VEHICLE, "Vehicle is not available");
        }
        if (this.parkingLot1.containsValue(vehicle)) {
            this.parkingLot1.remove(vehicle);
            for (ParkingLotObserver parkingLotSystemObserver : observers) {
                parkingLotSystemObserver.isAvailableParkingLotCapacity();
            }
        }
        if (this.parkingLot2.containsValue(vehicle)) {
            this.parkingLot2.remove(vehicle);
            for (ParkingLotObserver parkingLotSystemObserver : observers) {
                parkingLotSystemObserver.isAvailableParkingLotCapacity();
            }
        }
    }

    /**
     * Purpose : To Check Vehicle is Parked Or Not
     *
     * @param vehicle : vehicle is used to park
     * @return : Vehicle Equals
     */
    public boolean isVehicleParked(Vehicle vehicle) {
        if (this.parkingLot1.containsValue(vehicle) || this.parkingLot2.containsValue(vehicle)) {
            return true;
        }
        return false;
    }

    /**
     * Purpose : To Check a Vehicle is UnParked Or Not
     *
     * @param vehicle : vehicle is used to unpark
     * @return : UnParked vehicle.
     */
    public boolean isVehicleUnParked(Vehicle vehicle) {
        if (this.parkingLot1.containsValue(vehicle) || this.parkingLot2.containsValue(vehicle)) {
            return false;
        }
        return true;
    }

    /**
     * Purpose : To check whether the parking lot is full or not
     *
     * @return : the checked value
     */
    public boolean isFullCapacity() {
        return ParkingLotSystem.vehicles.size() == this.actualCapacity;
    }

    /**
     * Purpose : To Find vehicle to go home.
     *
     * @param vehicle : vehicle is used to find vehicle.
     * @throws ParkingLotSystemException : No Vehicle is found to go home
     */
    public int findVehicle(Vehicle vehicle) throws ParkingLotSystemException {
        for (Map.Entry<Integer, Vehicle> position : parkingLot1.entrySet()) {
            if (position.getValue().equals(vehicle)) {
                return position.getKey();
            }
        }
        for (Map.Entry<Integer, Vehicle> position : parkingLot2.entrySet()) {
            if (position.getValue().equals(vehicle)) {
                return position.getKey();
            }
        }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_A_VEHICLE,
                "No Vehicle is found to go home");
    }

    /**
     * Purpose : To get parking time of vehicle
     *
     * @param vehicle : vehicle is used to get vehicle class.
     * @return : the parking time if the vehicle is parked
     */
    public String getVehicleParkingTime(Vehicle vehicle) throws ParkingLotSystemException {
        for (Vehicle vehicle1 : parkingLot1.values()) {
            if (vehicle1.equals(vehicle)) {
                return vehicle1.getParkedTime();
            }
        }
        for (Vehicle vehicle1 : parkingLot2.values()) {
            if (vehicle1.equals(vehicle)) {
                return vehicle1.getParkedTime();
            }
        }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_A_VEHICLE,
                "Vehicle is not found");
    }
}