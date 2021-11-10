package com.bridgelabz;

/**
 * Purpose: To Implement the PARKING LOT SYSTEM program.
 *
 * @author : VAISHNAVI R. VISHWAKARMA.
 * @since : 09-11-2021.
 */
public class ParkingLotSystem {
    //    private Vehicle vehicle;
    private Object vehicle;

    /**
     * Purpose : To print Welcome Message.
     */
    public void welcomeMessage() {
        System.out.println("Welcome to Parking Lot Service Program...!! :-) ");
    }

    /**
     * Purpose : To Park the Vehicle in Parking Lot
     *
     * @param vehicle given vehicle as parameter
     * @return True For Vehicle Parked
     */
    public boolean parkVehicle(Object vehicle) {
        if (this.vehicle != null)
            return false;
        this.vehicle = vehicle;
        return true;
    }
}
