/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Isuru Pathum
 */
public class Create_file {

    public String DB_connection_file() {

        String retun_msg = "";
        try {
            File theDir = new File("C:\\Plantation");
            Process p = Runtime.getRuntime().exec("attrib +h " + theDir.getPath());

            if (!theDir.exists()) {
                System.out.println("creating directory: " + "data");
                boolean result = theDir.mkdir();

                System.out.println("created");
                retun_msg = "created";
            } else {
                System.out.println("orride");
                retun_msg = "override";
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return retun_msg;
    }

    public String DB_connection_Input_data(String dbname, String portno, String username, String password, String hostname) {

        String return_msg = "";
        try {

            File theDir = new File("C:\\Plantation");
            Process p = Runtime.getRuntime().exec("attrib +h " + theDir.getPath());

            if (!theDir.exists()) {
                System.out.println("creating directory: " + "data");
                boolean result = theDir.mkdir();

                if (result) {
                    System.out.println("DIR created");
                    BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Plantation\\dbConData.txt"));
                    out.write(AESencrp.encrypt(dbname));
                    out.newLine();
                    out.write(AESencrp.encrypt(portno));
                    out.newLine();
                    out.write(AESencrp.encrypt(username));
                    out.newLine();
                    out.write(AESencrp.encrypt(password));
                    out.newLine();
                    out.write(AESencrp.encrypt(hostname));
                    out.close();
                    return_msg = "Success";
                }
            } else {
                System.out.println("DIR override");
                BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Plantation\\dbConData.txt"));
                out.write(AESencrp.encrypt(dbname));
                out.newLine();
                out.write(AESencrp.encrypt(portno));
                out.newLine();
                out.write(AESencrp.encrypt(username));
                out.newLine();
                out.write(AESencrp.encrypt(password));
                out.newLine();
                out.write(AESencrp.encrypt(hostname));
                out.close();
                return_msg = "Success";
            }

        } catch (Exception e) {
            return_msg = e.toString();
        }

        return return_msg;
    }
}
