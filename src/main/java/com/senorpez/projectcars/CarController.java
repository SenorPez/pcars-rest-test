package com.senorpez.projectcars;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CarController {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/projectcars";
    private static final String USER = "dbuser";
    private static final String PASS = "";

    @RequestMapping(value = "/car", params = "name")
    public List<Car> car(@RequestParam(value = "name") String filter) {
        Connection conn = null;
        Statement stmt = null;
        List<Car> cars = new ArrayList<>();

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT name, class, topSpeed, horsepower, acceleration, braking, weight, torque, weightBalanceFront, wheelbase FROM cars " +
                    "WHERE name LIKE '" + filter + "'";
            ResultSet results = stmt.executeQuery(sql);

            while (results.next()) {
                cars.add(new Car(
                        results.getString("name"),
                        results.getString("class"),
                        results.getInt("topSpeed"),
                        results.getInt("horsepower"),
                        results.getFloat("acceleration"),
                        results.getFloat("braking"),
                        results.getInt("weight"),
                        results.getInt("torque"),
                        results.getInt("weightBalanceFront"),
                        results.getFloat("wheelbase")));
            }

            results.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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

        return cars;

    }

    @RequestMapping("/car")
    public List<Car> car() {
        Connection conn = null;
        Statement stmt = null;
        List<Car> cars = new ArrayList<>();

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT name, class, topSpeed, horsepower, acceleration, braking, weight, torque, weightBalanceFront, wheelbase FROM cars";
            ResultSet results = stmt.executeQuery(sql);

            while (results.next()) {
                cars.add(new Car(
                        results.getString("name"),
                        results.getString("class"),
                        results.getInt("topSpeed"),
                        results.getInt("horsepower"),
                        results.getFloat("acceleration"),
                        results.getFloat("braking"),
                        results.getInt("weight"),
                        results.getInt("torque"),
                        results.getInt("weightBalanceFront"),
                        results.getFloat("wheelbase")));
            }

            results.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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

        return cars;
    }
}
