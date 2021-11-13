package com.bridgelabz;

public class ParkingLotSystemOwner {
    private boolean isFullCapacity;
//private boolean isParkingLotFull;
//    public boolean getStatusIfCapacityFull() {
//        return this.isParkingLotFull();



    public void capacityIsFull() {
        isFullCapacity = true;
    }

    public boolean isCapacityFull() {
        return this.isFullCapacity;
    }
}
