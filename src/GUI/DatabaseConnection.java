/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaseLink;
    
    public Connection getConnection(){
        String databaseName = "sadcarrent";
        String databaseUser = "root";
        String databasePassword = "";
        String url ="jdbc:mysql://localhost:/" + databaseName;
        
        try{
                Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }catch(Exception e){
            e.getCause();
        }
        return databaseLink;
    }
}