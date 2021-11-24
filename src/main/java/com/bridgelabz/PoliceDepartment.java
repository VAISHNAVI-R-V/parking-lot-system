package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PoliceDepartment {

    /**
     * Purpose : Used to find if the given vehicle has fraudulent number plate.
     *
     * @param vehicle :  will be used to check the number
     * @return : Returns false if the number is fraudulent.
     */
    public static boolean validateVehicleNumber(Vehicle vehicle) {
        Pattern pattern = Pattern.compile("^[A-Z]{2}[-][0-9]{2,4}$");
        Matcher matcher = pattern.matcher(vehicle.getVehicleNumber());
        return matcher.matches();
    }


}
