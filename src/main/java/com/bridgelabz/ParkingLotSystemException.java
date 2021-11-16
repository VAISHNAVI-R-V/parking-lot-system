package com.bridgelabz;

/*****************************************************************************
 * Purpose : The Implementation of Exception for parking lot system.
 *
 * @author VAISHNAVI R. VISHWAKARMA
 * @since 2021-11-10
 *******************************************************************************/
public class ParkingLotSystemException extends Exception {
    public ExceptionType exceptionType;

    public ParkingLotSystemException(ExceptionType exceptionType, String message) {
        super(message);
        this.exceptionType = exceptionType;

    }

    public enum ExceptionType {PARKING_LOT_IS_FULL, NO_SUCH_A_VEHICLE}
}
