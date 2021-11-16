package com.decryption.utility;

import com.decryption.utility.utils.FetchNodeAddress;
import com.decryption.utility.utils.FileOperations;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IsFeatureLicensedImplementation {

    public static Map<String, Boolean> isFeatureLicensed(String filePath, List<String> featureList) throws IOException {


        File file = new File(filePath);
        String fileContents = FileOperations.readFile(file);
        String macAddressOfLocal = FetchNodeAddress.findMacAddressOfLocal();
        String macAddressRegistered = null;

        Map<String, Boolean> featureAndItsVisibility = new HashMap<>();

        for(String feature : featureList){
            boolean isFeatureLicensed = false;
            for (String str : fileContents.split("\n")) {
                if (str.contains("nodeAddress")) {
                    macAddressRegistered = str.split(" ")[1].trim();
                }
                if (str.contains(feature)) {
                    isFeatureLicensed = true;
                }
            }
            if(macAddressOfLocal.equals(macAddressRegistered))
                featureAndItsVisibility.put(feature, isFeatureLicensed);
        }
        return featureAndItsVisibility;
    }
}
