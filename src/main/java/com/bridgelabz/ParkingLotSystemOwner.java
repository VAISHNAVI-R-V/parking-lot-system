package com.bridgelabz;
/**********************************************************************
 * Purpose : The Implementation of Exception for parking lot system.
 *
 * @author VAISHNAVI R. VISHWAKARMA
 * @since 2021-11-12.
 **********************************************************************/
public class ParkingLotSystemOwner implements ParkingLotObserver {
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
