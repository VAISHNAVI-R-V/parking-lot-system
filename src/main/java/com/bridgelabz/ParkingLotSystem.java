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
    private int actualCapacity;
    private int currentCapacity;
    private List<Vehicle> vehicle;
    private ParkingLotSystemOwner owner;

    public ParkingLotSystem(int capacity) {
        this.vehicle = new ArrayList<>();
        this.currentCapacity = 0;
        this.actualCapacity = capacity;
    }

    /**
     * Purpose : To create method of registerOwner to Inform the owner
     *
     * @param owner :
     */
    public void registerOwner(ParkingLotSystemOwner owner) {
        this.owner = owner;
    }

    /**
     * Purpose : To print Welcome Message.
     */
    public void welcomeMessage() {
        System.out.println("Welcome to Parking Lot Service Program...!! :-) ");
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
            owner.capacityIsFull();
            throw new ParkingLotSystemException("Parking Lot is Full");
        }

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
        if (this.vehicle.contains(vehicle)) {
            this.vehicle = null;
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
