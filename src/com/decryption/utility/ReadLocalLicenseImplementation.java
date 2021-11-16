package com.decryption.utility;

import com.decryption.utility.utils.FileOperations;
import com.decryption.utility.utils.MultipartUtility;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * This program demonstrates a usage of the com.decryption.utility.utils.MultipartUtility class.
 *
 * @author www.codejava.net
 */
public class ReadLocalLicenseImplementation {

    public static File requestReadLicense(String path, String requestURL) throws IOException {
        String charset = "UTF-8";
        File uploadFile = new File(path);

        MultipartUtility multipart = new MultipartUtility(requestURL, charset);
        multipart.addFilePart("multipartFile", uploadFile);
        int productId = Integer.parseInt(multipart.finish().get(0));

        return getLicenseFile(productId);
    }

    public static File getLicenseFile(int productId) throws IOException {

        File file = new File("D:\\WORK\\Hackathon2021\\DecryptionImplementation\\src\\resources\\archive.zip");
        URL urlForGetRequest = new URL("http://localhost:8081/getLicenseFile/" + productId);
        HttpURLConnection connection = (HttpURLConnection) urlForGetRequest.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();


        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (InputStream in = urlForGetRequest.openStream();
                 BufferedInputStream bis = new BufferedInputStream(in);
                 FileOutputStream fos = new FileOutputStream(file.getName())) {

                byte[] data = new byte[1024];
                int count;
                while ((count = bis.read(data, 0, 1024)) != -1) {
                    fos.write(data, 0, count);
                }
            }

        } else {
            System.out.println("GET NOT WORKED");
        }

        return file;
    }


}

