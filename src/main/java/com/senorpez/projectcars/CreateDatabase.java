package com.senorpez.projectcars;

import java.sql.*;

public class CreateDatabase {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/projectcars;MODE=mysql";

    private static final String USER = "dbuser";
    private static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Dropping table...");
            stmt = conn.createStatement();

            String sql = "DROP TABLE IF EXISTS cars, events, rounds";
            stmt.execute(sql);

            System.out.println("Creating table...");
            stmt = conn.createStatement();
            sql = "CREATE TABLE cars " +
                    "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                    " name VARCHAR(255), " +
                    " class VARCHAR(255), " +
                    " topSpeed INTEGER, " +
                    " horsepower INTEGER, " +
                    " acceleration DECIMAL(4,2), " +
                    " braking DECIMAL(4,2), " +
                    " weight INTEGER, " +
                    " torque INTEGER, " +
                    " weightBalanceFront INTEGER, " +
                    " wheelbase DECIMAL(4, 2), " +
                    " PRIMARY KEY (id))";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE events " +
                    "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                    " name VARCHAR(255), " +
                    " carFilter VARCHAR(255), " +
                    " PRIMARY KEY (id))";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE rounds " +
                    "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                    " eventID INTEGER, " +
                    " track VARCHAR(255), " +
                    " laps INTEGER, " +
                    " PRIMARY KEY (id), " +
                    " FOREIGN KEY (eventID) REFERENCES events(id) ON DELETE CASCADE)";
            stmt.executeUpdate(sql);

            System.out.println("Adding cars...");
            stmt = conn.createStatement();
            sql = "INSERT INTO cars " +
                    "(name, class, topSpeed, horsepower, acceleration, braking, weight, torque, weightBalanceFront, wheelbase) " +
                    "VALUES " +
                    " ('Ford Mustang Cobra TransAm', 'Trans-Am', 214, 633, 3.09, 1.64, 2800, 445, 48, 2.59), " +
                    " ('Audi 90 quattro IMSA GTO', 'Trans-Am', 193, 720, 3.10, 2.15, 2659, 525, 50, 2.54), " +
                    " ('Mercedes-Benz Sauber C9 Mercedes-Benz', 'Group C1', 254, 737, 2.79, 1.96, 2161, 833, 60, 2.7)";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO events " +
                    "(name, carFilter) " +
                    "VALUES " +
                    " ('Historic C1 Euro Cup', 'SELECT * FROM cars WHERE class LIKE ''Trans-Am''') ";
            stmt.executeUpdate(sql);

            sql = "INSERT INTO rounds " +
                    "(eventID, track, laps) " +
                    "VALUES " +
                    " (1, 'Brno', 8)," +
                    " (1, 'Monza GP', 8)," +
                    " (1, 'Spa', 6)," +
                    " (1, 'La Sarthe', 4)";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
