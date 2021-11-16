package com.decryption.utility;

import com.decryption.utility.utils.FileOperations;
import com.decryption.utility.utils.MultipartUtility;
import com.sun.security.ntlm.Client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This program demonstrates a usage of the com.decryption.utility.utils.MultipartUtility class.
 *
 * @author www.codejava.net
 */
public class ReadLocalLicenseImplementation {

    public static Boolean requestReadLicense(String path, String requestURL) throws IOException {
        String charset = "UTF-8";
        File uploadFile = new File(path);

        try {
            MultipartUtility multipart = new MultipartUtility(requestURL, charset);
            multipart.addFilePart("multipartFile", uploadFile);


            List<String> fileContents = new ArrayList<>(multipart.finish());
            BufferedWriter writer = new BufferedWriter(new FileWriter(uploadFile));

            for(String str : fileContents)
            writer.write(str+"\n");
            writer.close();

        } catch (IOException ex) {
            System.err.println(ex);
        }

        if(FileOperations.readFile(uploadFile) != null) return true;
        return false;
    }



}

