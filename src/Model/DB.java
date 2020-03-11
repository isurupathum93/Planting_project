package Model;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Isuru pathum
 */
public class DB {
    static Connection c;
    static String hostname="localhost";
    static String port="3306";
    static String pw="1234";
    static String uname="root";
    static String dbname="planting";
    
    
    public static Connection DBConn()throws Exception{
        if (c==null) {
             Class.forName("com.mysql.jdbc.Driver");
         c= DriverManager.getConnection("jdbc:mysql://"+hostname+":"+port+"/"+dbname+"",""+uname+"",""+pw+"");
        }
       
        return c;
    
    }
    
   
    
    
    
    
    
    
}