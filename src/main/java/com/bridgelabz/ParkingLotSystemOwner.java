package com.bridgelabz;
/**
 * Purpose : The Implementation of Exception for parking lot system.
 *
 * @author VAISHNAVI R. VISHWAKARMA
 * @since 2021-11-12.
 */
public class ParkingLotSystemOwner {
    private boolean isFullCapacity;

    public void capacityIsFull() {
        isFullCapacity = true;
    }

    public boolean isCapacityFull() {
        return this.isFullCapacity;
    }
}
