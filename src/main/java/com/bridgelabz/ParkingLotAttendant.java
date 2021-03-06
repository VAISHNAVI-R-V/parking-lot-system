package com.bridgelabz;
/************************************************************************
 * Purpose : To implement that the vehicles are
 * parked by attender or unpark by attender
 *
 * @author : VAISHNAVI R. VISHWAKRMA
 * @since : 2021-11-14
 ***********************************************************************/
public class ParkingLotAttendant {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem(2);

    /**
     * Purpose :  To park the car by attendant in parking lot
     *
     * @param vehicle : vehicle is parkedByAttendant
     * @throws ParkingLotSystemException : when the parking lot is full
     */
    public void parkedByAttendant(Vehicle vehicle) throws ParkingLotSystemException {
        parkingLotSystem.parkVehicle(vehicle, Vehicle.DriverType.NORMAL);
    }

    /**
     * Purpose : To unpark the car by attendant in parking lot
     *
     * @param vehicle : vehicle is unparkedByAttendant
     * @throws ParkingLotSystemException : when there is no vehicle to unpark
     */
    public void unparkedByAttendant(Vehicle vehicle) throws ParkingLotSystemException {
        parkingLotSystem.unParkVehicle(vehicle);
    }
}