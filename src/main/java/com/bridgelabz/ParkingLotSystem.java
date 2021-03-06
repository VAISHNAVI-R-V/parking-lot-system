package com.bridgelabz;

import com.bridgelabz.Vehicle.DriverType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bridgelabz.Vehicle.Size.SMALL;

/*****************************************************************************************
 * Purpose: To Implement the PARKING LOT SYSTEM program.
 *
 * @author : VAISHNAVI R. VISHWAKARMA.
 * @since : 09-11-2021.
 *****************************************************************************************/
public class ParkingLotSystem {
    private ParkingLotSystemOwner owner;
    private int actualCapacity;
    public static List<Vehicle> vehicles;
    private List<ParkingLotObserver> observers;
    private AirportSecurity security;
    private Map<Integer, Vehicle> parkingLot1 = new HashMap<>();
    private Map<Integer, Vehicle> parkingLot2 = new HashMap<>();
    Map currentLot = parkingLot1;
    int slotOfLot1 = 0;
    int slotOfLot2 = 0;

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
     * Purpose: To distribute vehicles evenly in Lots.
     *
     * @param vehicle : vehicle is used to park.
     */
    public void evenlyParkedVehicle(Vehicle vehicle) {
        if (currentLot == parkingLot1) {
            slotOfLot1 = slotOfLot1 + 1;
            parkingLot1.put(1, vehicle);
            currentLot = parkingLot2;
            return;
        }
        if (currentLot == parkingLot2) {
            slotOfLot2 = slotOfLot2 + 1;
            parkingLot2.put(2, vehicle);
            currentLot = parkingLot1;
            return;
        }
    }

    /**
     * Purpose : To create method of registerOwner to Inform the owner.
     *
     * @param observer : takes observer to register ParkingLot Observer.
     */
    public void registerParkingLotObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }

    /**
     * Purpose : To register Airport Security.
     *
     * @param airportSecurity : takes airportSecurity to register Security.
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
     * Purpose : To Park the Vehicle in Parking Lot.
     *
     * @param vehicle : vehicle is used to park
     * @throws : ParkingLotSystemException : Exception Type Message.
     */
    public void parkVehicle(Vehicle vehicle, Vehicle.DriverType driverType) throws ParkingLotSystemException {
        if (this.slotOfLot1 == this.actualCapacity && this.slotOfLot2 == this.actualCapacity) {
            for (ParkingLotObserver parkingLotSystemObserver : observers)
                parkingLotSystemObserver.isFullParkingLotCapacity();
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL,
                    "Parking lot is full");
        }
        if (this.parkingLot1.containsValue(vehicle) || this.parkingLot2.containsValue(vehicle)) {
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_A_VEHICLE,
                    "Vehicle already exist");
        }
        if (driverType.equals(DriverType.HANDICAPED)){
            this.handicappedPark(vehicle);
        }
        this.evenlyParkedVehicle(vehicle);
    }

    /**
     * Purpose : To UnParked the Vehicle from parking lot.
     *
     * @param vehicle : vehicle is used to unpark
     * @throws : ParkingLotSystemException : Exception Type Message.
     */
    public void unParkVehicle(Vehicle vehicle) throws ParkingLotSystemException {
        if (vehicle == null) {
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_A_VEHICLE,
                    "Vehicle is not available");
        }
        if (this.parkingLot1.containsValue(vehicle)) {
            this.parkingLot1.remove(vehicle);
            for (ParkingLotObserver parkingLotSystemObserver : observers) {
                parkingLotSystemObserver.isAvailableParkingLotCapacity();
            }
        }
        if (this.parkingLot2.containsValue(vehicle)) {
            this.parkingLot2.remove(vehicle);
            for (ParkingLotObserver parkingLotSystemObserver : observers) {
                parkingLotSystemObserver.isAvailableParkingLotCapacity();
            }
        }
    }

    /**
     * Purpose : To Check Vehicle is Parked Or Not
     *
     * @param vehicle : vehicle is used to park
     * @return : Vehicle Equals
     */
    public boolean isVehicleParked(Vehicle vehicle) {
        if (this.parkingLot1.containsValue(vehicle) || this.parkingLot2.containsValue(vehicle)) {
            return true;
        }
        return false;
    }

    /**
     * Purpose : To Check a Vehicle is UnParked Or Not
     *
     * @param vehicle : vehicle is used to Unpark
     * @return : UnParked vehicle.
     */
    public boolean isVehicleUnParked(Vehicle vehicle) {
        if (this.parkingLot1.containsValue(vehicle) || this.parkingLot2.containsValue(vehicle)) {
            return false;
        }
        return true;
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
     * @param vehicle : vehicle is used to find vehicle.
     * @throws ParkingLotSystemException : No Vehicle is found to go home
     */
    public int findVehicle(Vehicle vehicle) throws ParkingLotSystemException {
        for (Map.Entry<Integer, Vehicle> position : parkingLot1.entrySet()) {
            if (position.getValue().equals(vehicle)) {
                return position.getKey();
            }
        }
        for (Map.Entry<Integer, Vehicle> position : parkingLot2.entrySet()) {
            if (position.getValue().equals(vehicle)) {
                return position.getKey();
            }
        }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_A_VEHICLE,
                "No Vehicle is found to go home");
    }

    /**
     * Purpose : To get parking time of vehicle
     *
     * @param vehicle : vehicle is used to get vehicle class.
     * @return : the parking time if the vehicle is parked
     */
    public String getVehicleParkingTime(Vehicle vehicle) throws ParkingLotSystemException {
        for (Vehicle vehicle1 : parkingLot1.values()) {
            if (vehicle1.equals(vehicle)) {
                return vehicle1.getParkedTime();
            }
        }
        for (Vehicle vehicle1 : parkingLot2.values()) {
            if (vehicle1.equals(vehicle)) {
                return vehicle1.getParkedTime();
            }
        }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_A_VEHICLE,
                "Vehicle is not found");
    }

    /**
     * Purpose : To get vehicles by WHITE color.
     *
     * @param vehicle : to get vehicle object to find vehicle is present in slot.
     * @param color : described color property to vehicle
     * @return : position of vehicle.
     * @throws ParkingLotSystemException : White color vehicle not found.
     */
    public int getVehicleBYColour(Vehicle vehicle, String color) throws ParkingLotSystemException {
        if (this.parkingLot1.containsValue(vehicle)) {
            for (Map.Entry<Integer, Vehicle> vehicleMap : parkingLot1.entrySet()) {
                if (vehicleMap.getValue().getVehicleColor().equalsIgnoreCase(color)) {
                    return vehicleMap.getKey();
                }
            }
        } else if (this.parkingLot2.containsValue(vehicle)) {
            for (Map.Entry<Integer, Vehicle> vehicleMap : parkingLot2.entrySet()) {
                if (vehicleMap.getValue().getVehicleColor().equalsIgnoreCase(color)) {
                    return vehicleMap.getKey();
                }
            }
        }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_A_VEHICLE,
                "White color vehicle not found");
    }

    /**
     * Purpose : To get vehicles by color.
     *
     * @param vehicle : to get vehicle object to find vehicle is present in slot.
     * @param name : described name property to vehicle
     * @param color : described color property to vehicle
     * @return : position of vehicle.
     * @throws ParkingLotSystemException : Toyota blue color vehicle not found.
     */
    public int getVehicleBYNameAndColour(Vehicle vehicle, String name, String color) throws ParkingLotSystemException {
        if (this.parkingLot1.containsValue(vehicle)) {
            for (Map.Entry<Integer, Vehicle> vehicleMap : parkingLot1.entrySet()) {
                if (vehicleMap.getValue().getVehicleColor().equalsIgnoreCase(color)
                        && vehicleMap.getValue().getName().equalsIgnoreCase(name)) {
                    return vehicleMap.getKey();
                }
            }
        } else if (this.parkingLot2.containsValue(vehicle)) {
            for (Map.Entry<Integer, Vehicle> vehicleMap : parkingLot2.entrySet()) {
                if (vehicleMap.getValue().getVehicleColor().equalsIgnoreCase(color)
                        && vehicleMap.getValue().getName().equalsIgnoreCase(name)) {
                    return vehicleMap.getKey();
                }
            }
        }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_A_VEHICLE,
                "Toyota blue color vehicle not found");
    }

    /**
     * Purpose : To get blue color toyota vehicle with plate number.
     *
     * @param vehicle : to get vehicle object to find vehicle is present in slot.
     * @param name : described name property to vehicle.
     * @param color : described color property to vehicle
     * @param numberPlate : described number plate property to vehicle
     * @return : position of vehicle.
     * @throws ParkingLotSystemException : Toyota blue color vehicle number plate not found.
     */
    public int getVehicleBYBlueColorToyotaWithNumberPlate(Vehicle vehicle, String name, String color,
                                                          String numberPlate) throws ParkingLotSystemException {
        if (this.parkingLot1.containsValue(vehicle)) {
            for (Map.Entry<Integer, Vehicle> vehicleMap : parkingLot1.entrySet()) {
                if (vehicleMap.getValue().getVehicleColor().equalsIgnoreCase(color) &&
                        vehicleMap.getValue().getName().equalsIgnoreCase(name) &&
                        vehicleMap.getValue().getVehicleNumber().equalsIgnoreCase(numberPlate)) {
                    return vehicleMap.getKey();
                }
            }
        } else if (this.parkingLot2.containsValue(vehicle)) {
            for (Map.Entry<Integer, Vehicle> vehicleMap : parkingLot2.entrySet()) {
                if (vehicleMap.getValue().getVehicleColor().equalsIgnoreCase(color)
                        && vehicleMap.getValue().getName().equalsIgnoreCase(name)
                        && vehicleMap.getValue().getVehicleNumber().equalsIgnoreCase(numberPlate)) {
                    return vehicleMap.getKey();
                }
            }
        }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL,
                "Toyota blue color vehicle number plate not found");
    }

    /**
     * Purpose : To get BMW vehicles.
     *
     * @param vehicle : to get vehicle object to find vehicle is present in slot.
     * @param name : described name property to vehicle.
     * @return : position of vehicle.
     * @throws ParkingLotSystemException : BMW vehicle not found.
     */
    public int getBMWVehicle(Vehicle vehicle, String name) throws ParkingLotSystemException {
        if (this.parkingLot1.containsValue(vehicle)) {
            for (Map.Entry<Integer, Vehicle> vehicleMap : parkingLot1.entrySet()) {
                if (vehicleMap.getValue().getName().equalsIgnoreCase(name)) {
                    return vehicleMap.getKey();
                }
            }
        } else if (this.parkingLot2.containsValue(vehicle)) {
            for (Map.Entry<Integer, Vehicle> vehicleMap : parkingLot2.entrySet()) {
                if (vehicleMap.getValue().getName().equalsIgnoreCase(name)) {
                    return vehicleMap.getKey();
                }
            }
        }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_A_VEHICLE,
                "BMW vehicle not found");
    }

    /**
     * Purpose : To Park Handicapped vehicles at nearer lots.
     *
     * @param vehicle : to Park the vehicle.
     */
    private void handicappedPark(Vehicle vehicle)   {
        for (Map.Entry<Integer, Vehicle> vehicleMap : parkingLot1.entrySet()) {
            if (vehicleMap.getValue() == null) {
                parkingLot1.put(vehicleMap.getKey(), vehicle);
            }
        }
        if (!parkingLot1.containsValue(vehicle)) {
            for (Map.Entry<Integer, Vehicle> vehicleMap : parkingLot2.entrySet()) {
                if (vehicleMap.getValue() == null) {
                    parkingLot2.put(vehicleMap.getKey(), vehicle);
                }
            }
        }
    }
}