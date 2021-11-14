package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;

/**
 * Purpose: To Implement the PARKING LOT SYSTEM program.
 *
 * @author : VAISHNAVI R. VISHWAKARMA.
 * @since : 09-11-2021.
 */
public class ParkingLotSystem {
    //    private int currentCapacity;
    private int actualCapacity;
    private List<Vehicle> vehicle;
    private List<ParkingLotObserver> observers;
    //    private ParkingLotSystemOwner owner;
    private AirportSecurity security;

    public ParkingLotSystem(int capacity) {
        this.observers = new ArrayList<>();
        this.vehicle = new ArrayList<>();
//        this.currentCapacity = 0;
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
        if (this.vehicle.size() == this.actualCapacity) {
            for (ParkingLotObserver observer : observers) {
                observer.parkingLotCapacity();
            }
//            owner.capacityIsFull();
//            security.capacityIsFull();
            throw new ParkingLotSystemException("Parking Lot is Full");
        }
        if (isVehicleParked(vehicle)) throw new ParkingLotSystemException("Vehicle already Parked");
        this.vehicle.add(vehicle);
    }

    /**
     * Purpose : To UnParked the Vehicle from parking lot
     *
     * @param vehicle : takes vehicle as parameter
     * @throws : ParkingLotSystemException
     */
    public void unParkVehicle(Vehicle vehicle) throws ParkingLotSystemException {
        if (this.vehicle == null) throw new ParkingLotSystemException("No such A Vehicle Found");
        else if (this.vehicle.contains(vehicle)) {
            this.vehicle = null;
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
        return this.vehicle.contains(vehicle);
    }

    /**
     * Purpose : To Check a Vehicle is UnParked Or Not
     *
     * @param vehicle : takes the Vehicle Param
     * @return : UnParked vehicle.
     */
    public boolean isVehicleUnParked(Vehicle vehicle) {
        return this.vehicle == null;
    }
}
