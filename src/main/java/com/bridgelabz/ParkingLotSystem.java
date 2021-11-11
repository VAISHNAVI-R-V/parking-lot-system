package com.bridgelabz;

/**
 * Purpose: To Implement the PARKING LOT SYSTEM program.
 *
 * @author : VAISHNAVI R. VISHWAKARMA.
 * @since : 09-11-2021.
 */
public class ParkingLotSystem {
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
     * @param vehicle : takes vehicle as parameter
     * @return True : For Vehicle Parked
     */
    public void parkVehicle(Object vehicle) throws ParkingLotSystemException {
        if (this.vehicle != null)
            throw new ParkingLotSystemException("Parking Lot is Full");
        this.vehicle = vehicle;
    }

    /**
     * Purpose : To UnParked the Vehicle from parking lot
     *
     * @param vehicle : takes vehicle as parameter
     * @return : Vehicle UnParked
     */
    public boolean unParkVehicle(Object vehicle) {
        if (this.vehicle == null)
            return false;
        if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
            return true;
        }
        return false;
    }

    /**
     * Purpose : To Check Vehicle is Parked Or Not
     *
     * @param vehicle : given Vehicle as parameter
     * @return : Vehicle Equals
     */
    public boolean isVehicleParked(Object vehicle) {
        if (this.vehicle.equals(vehicle))
            return true;
        return false;

    }

    /**
     * Purpose : To Check a Vehicle is UnParked Or Not
     *
     * @param vehicle : takes the Vehicle Param
     * @return : UnParked vehicle.
     */
    public boolean isVehicleUnParked(Object vehicle) {
        return this.vehicle == null;
    }
}
