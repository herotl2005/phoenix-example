package com.geekcap.javaworld.phoenixexample;

import java.sql.*;

/**
 * Created by shaines on 12/20/15.
 */
public class PhoenixExample {

    public static void main(String[] args) {
        // Create variables
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            System.out.println("start....");
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:phoenix:52.208.47.78");
System.out.println("start....2");
            // Create a JDBC statement
            statement = connection.createStatement();

            // Execute our statements
            statement.executeUpdate("create table kul (mykey integer not null primary key, mycolumn varchar)");
            statement.executeUpdate("upsert into kul values (1,'Hello World')");
            statement.executeUpdate("upsert into kul values (2,'Tôi có người em vĩ dạ gặp bên chợ đông ba')");
            connection.commit();

            // Query for table
            ps = connection.prepareStatement("select * from kul");
            rs = ps.executeQuery();
            System.out.println("Table Values");
            while(rs.next()) {
                Integer myKey = rs.getInt(1);
                String myColumn = rs.getString(2);
                System.out.println("\tRow: " + myKey + " = " + myColumn);
            }
        }
        catch(SQLException e) {
             System.out.println("Table SQLException");
            e.printStackTrace();
        }
        finally {
            if(ps != null) {
                try {
                    ps.close();
                }
                catch(Exception e) {}
            }
            if(rs != null) {
                try {
                    rs.close();
                }
                catch(Exception e) {}
            }
            if(statement != null) {
                try {
                    statement.close();
                }
                catch(Exception e) {}
            }
            if(connection != null) {
                try {
                    connection.close();
                }
                catch(Exception e) {}
            }
        }


    }
}
