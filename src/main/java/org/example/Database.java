package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.*;

public class Database {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/pap21l";
    private static final String DATABASE_USERNAME = "bindas";
    private static final String DATABASE_PASSWORD = "bindas";

    Hashtable<String, String> INSERT_QUERIES = new Hashtable<>() {{
        put("Clients", "INSERT INTO Clients(email, first_name, last_name, zip_code, city, street_address) VALUES (?, ?, ?, ?, ?, ?)");
        put("Orders", "INSERT INTO Orders(order_id, product_id, email_client, quantity, order_date, shipping_status) VALUES (?, ?, ?, ?, ?, ?)");
        put("Products", "INSERT INTO Products(product_name, price, ean) VALUES (?, ?, ?)");
    }};
    Hashtable<String, String> DELETE_QUERIES = new Hashtable<>() {{
        put("Clients", "DELETE FROM Clients WHERE email=?");
        put("Orders", "DELETE FROM Orders WHERE order_id=?");
        put("Products", "DELETE FROM Products WHERE product_id=?");
    }};

    private static final String JOIN_QUERY = "SELECT orders.order_date, orders.quantity * products.price AS Value_of_order FROM orders\n" +
            "INNER JOIN products on orders.product_id=products.product_id;";

    private static final String CHANGE_STATUS_QUERY = "UPDATE Orders SET shipping_status = ? WHERE order_id = ?";

    public void insertRecord(String table, List<Object> arguments) throws SQLException {
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try {
            Connection connection = DriverManager
                    .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERIES.get(table));

            for (int i=0; i< arguments.size(); i++) {
                preparedStatement.setString(i+1, arguments.get(i).toString());
            }

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
    }
    public ResultSet selectRecord(String table) throws SQLException {
        String select_query= "SELECT * FROM " + table;

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try {
            Connection connection = DriverManager
                    .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

            System.out.println(select_query);
            // Step 2: Execute the query
            return connection.createStatement().executeQuery(select_query);
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return null;
    }
    public ResultSet selectJoinRecords() throws SQLException {
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try {
            Connection connection = DriverManager
                    .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

            // Step 2: Execute the query
            return connection.createStatement().executeQuery(JOIN_QUERY);
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return null;
    }

    public List<String> ExportRecords(String table){
        List<String> dataArray = new ArrayList<String>();
        try {
            Connection connection = DriverManager
                    .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            ResultSet rs = selectRecord(table);
            //PreparedStatement preparedStatement = connection.prepareStatement(EXPORT_QUERIES.get(table));
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++) {
                while (rs.next()) {
                    //Iterate Row
                    String row = "";
                    for (int k = 1; k <= rs.getMetaData().getColumnCount(); k++) {
                        //Iterate Column
                        row = row.concat(rs.getString(k));
                        row = row + ", ";
                    }
                    dataArray.add(row);
                }
            }
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return dataArray;
    }

    public void deleteRecord(String table, String name) throws SQLException {
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try {
            Connection connection = DriverManager
                    .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERIES.get(table));
            preparedStatement.setString(1, name);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            printSQLException(e);
        }
    }
    public void changeOrderStatus(String name, int statusChange) {
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try {
            Connection connection = DriverManager
                    .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_STATUS_QUERY);
            preparedStatement.setInt(1, statusChange);
            preparedStatement.setString(2, name);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
