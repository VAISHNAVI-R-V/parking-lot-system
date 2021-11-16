package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;

/*****************************************************************************************
 * Purpose: To Implement the PARKING LOT SYSTEM program.
 *
 * @author : VAISHNAVI R. VISHWAKARMA.
 * @since : 09-11-2021.
 *****************************************************************************************/
public class ParkingLotSystem {
    private ParkingLotSystemOwner owner;
    private int actualCapacity;
    private static List<Vehicle> vehicles;
    private List<ParkingLotObserver> observers;
    private AirportSecurity security;
    private Vehicle vehicle;

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
     * Purpose : To create method of registerOwner to Inform the owner
     *
     * @param observer : takes observer as parameter
     */
    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Purpose : To register Airport Security
     *
     * @param airportSecurity : takes parameter as airportSecurity
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
     * Purpose : To Park the Vehicle in Parking Lot
     *
     * @param vehicle : takes vehicle as parameter
     * @throws : ParkingLotSystemException
     */

    public void parkVehicle(Vehicle vehicle) throws ParkingLotSystemException {
        if (ParkingLotSystem.vehicles.size() == this.actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.parkingLotCapacity();
            }
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL,
                    "Parking Lot is Full");
        } else if (isVehicleParked(vehicle)) {
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_A_VEHICLE,
                    "Vehicle already Parked");
        }
        ParkingLotSystem.vehicles.add(vehicle);
    }

    /**
     * Purpose : To UnParked the Vehicle from parking lot
     *
     * @param vehicle : takes vehicle as parameter
     * @throws : ParkingLotSystemException
     */
    public void unParkVehicle(Vehicle vehicle) throws ParkingLotSystemException {
        if (ParkingLotSystem.vehicles == null)
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_A_VEHICLE,
                    "No such A Vehicle Found");
        else if (ParkingLotSystem.vehicles.contains(vehicle)) {
            ParkingLotSystem.vehicles = null;
            for (ParkingLotObserver observer : observers) {
                observer.parkingLotCapacityAvailable();
            }
        }
    }

    /**
     * Purpose : To Check Vehicle is Parked Or Not
     *
     * @param vehicle : given Vehicle as parameter
     * @return : Vehicle Equals
     */
    public boolean isVehicleParked(Vehicle vehicle) {
        return ParkingLotSystem.vehicles.contains(vehicle);
    }

    /**
     * Purpose : To Check a Vehicle is UnParked Or Not
     *
     * @param vehicle : takes the Vehicle Param
     * @return : UnParked vehicle.
     */
    public boolean isVehicleUnParked(Vehicle vehicle) {
        return ParkingLotSystem.vehicles == null;
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
     * @param vehicle : takes vehicle
     * @throws ParkingLotSystemException : No Vehicle is found to go home
     */
    public int findVehicle(Vehicle vehicle) throws ParkingLotSystemException {
        if (isVehicleParked(vehicle))
            for (Vehicle findVehicle : vehicles) {
                if (findVehicle.equals(vehicle))
                    return vehicles.indexOf(findVehicle);
            }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_A_VEHICLE,
                "No Vehicle is found to go home");
    }

    /**
     * Purpose : To get parking time of vehicle
     *
     * @param vehicle : takes vehicle
     * @return : the parking time if the vehicle is parked
     */
    public String getVehicleParkingTime(Vehicle vehicle) {
        if (isVehicleParked(vehicle)) {
            return vehicle.getParkedTime();
        }
        return null;
    }
}
