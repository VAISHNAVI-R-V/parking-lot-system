package com.bridgelabz;

/**
 * Purpose : To create AirportSecurity class to behave as AirportSecurity Observer.
 *
 * @author : VAISHNAVI R. VISHWAKRMA
 * @since : 13-11-2021.
 */
public class AirportSecurity implements ParkingLotObserver {
    private boolean isFullCapacity;

    /**
     * Purpose : To get Status i.e. Capacity is full inParking Lot
     *
     * @return : Capacity is full.
     */
    public boolean isCapacityFullStatus() {
        return this.isFullCapacity;
    }

    /**
     * Purpose : This Method is created to check the Full capacity of Parking lot
     */
    @Override
    public void parkingLotCapacity() {
        isFullCapacity = true;
    }

    /**
     * Purpose : This Method is created to check the Full capacity is available in Parking lot
     */
    @Override
    public void parkingLotCapacityAvailable() {
        isFullCapacity = false;
    }
}
