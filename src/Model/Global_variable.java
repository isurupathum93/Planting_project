/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Isuru Pathum
 */
public class Global_variable {

    static String dbname;
    static String portno;
    static String username;
    static String pasward;
    static String hostname;

    public static ArrayList<Model.category> product = new ArrayList<Model.category>();
    public static ArrayList<String> product_level = new ArrayList<>();
}
