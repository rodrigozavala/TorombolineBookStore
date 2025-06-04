package com.torombolinebookstore.authentication.dbconnection;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DBConnectionProvider implements DBConnectionProviderInterface {

    //private static DBConnectionProvider instance;

    private String url;
    private String username;
    private String password;
    private String driverName;

    private Connection connection;

    private DBConnectionProvider(@Value("${spring.datasource.url}")String url,
                                 @Value("${spring.datasource.username}") String username,
                                 @Value("${spring.datasource.password}") String password,
                                 @Value("${spring.datasource.driver-class-name}") String driverName){
        this.url = url;
        this.username = username;
        this.password = password;
        this.driverName = driverName;

    }


    /*public static DBConnectionProvider getInstance () throws SQLException {
        if (instance == null) {
            synchronized(DBConnectionProvider.class) {
                if (instance == null) {
                    System.out.println("Creating connection to db");
                    instance = new DBConnectionProvider();
                }
            }
        }
        return instance;
    }*/

    public Connection getConnection(){
        try{

            System.out.println("Creating connection");
            //com.mysql.cj.jdbc.Driver
            System.out.println(driverName);
            System.out.println(url+" "+username+" "+password);
            Class.forName(driverName);
            connection = DriverManager.getConnection(url,username,password);
            System.out.println("Connection created");
        }catch (SQLException e){
            //TODO: add logs https://www.baeldung.com/java-logging-intro
            System.out.println("Some error just happened this thing was not created");
            //System.out.println(e.getMessage());
        }catch (ClassNotFoundException e){
            System.out.println("Some error just happened this thing was not created 2");
            //System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println("I don't even know what exception is this but it was not created");
        }
        return this.connection;
    }


}
