package com.decryption.utility;

import com.decryption.utility.utils.FetchNodeAddress;
import com.decryption.utility.utils.FileOperations;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetDateOfExpiryImplementation {
    public static Map<String, Date> getLicenseExpiration(String filePath, List<String> featureList) throws IOException, ParseException {


        Date date = null;

        File file = new File(filePath);
        String registeredMacAddress = null;
        String clientMacAddress = FetchNodeAddress.findMacAddressOfLocal();

        String[] fileContents = FileOperations.readFile(file).split("\n");

        Map<String, Date> featureAndItsDateToExpiry = new HashMap<>();

        for (String feature : featureList) {
            for (String str : fileContents) {
                if (str.contains("nodeAddress")) {
                    registeredMacAddress = str.split(" ")[1].trim();
                }
                if (str.contains(feature)) {
                    date = new SimpleDateFormat("dd/MM/yyyy").parse(str.split(" ")[2]);
                }
            }
            if (clientMacAddress.equals(registeredMacAddress)) {
                featureAndItsDateToExpiry.put(feature, date);
            }
        }
        return null;
    }
}
