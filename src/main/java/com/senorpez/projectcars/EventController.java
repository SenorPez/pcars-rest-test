package com.senorpez.projectcars;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EventController {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/projectcars";
    private static final String USER = "dbuser";
    private static final String PASS = "";

    @RequestMapping(value = "/event/{id}")
    public Event event(@PathVariable Integer id) {
        Connection conn = null;
        Statement stmt = null;
        Event event = null;
        List<Round> rounds = new ArrayList<>();
        List<Car> cars = new ArrayList<>();

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            stmt = conn.createStatement();
            String sql = "SELECT track, laps FROM rounds WHERE eventID = " + id;
            ResultSet results = stmt.executeQuery(sql);
            while (results.next()) {
                rounds.add(new Round(
                        results.getString("track"),
                        results.getInt("laps")
                ));
            }

            sql = "SELECT name, carFilter FROM events WHERE id = " + id;
            results = stmt.executeQuery(sql);
            while (results.next()) {
                String eventName = results.getString("name");
                String carSql = results.getString("carFilter");

                Statement carStmt = conn.createStatement();
                ResultSet carResults = carStmt.executeQuery(carSql);
                while (carResults.next()) {
                    cars.add(new Car(
                            carResults.getString("name"),
                            carResults.getString("class"),
                            carResults.getInt("topSpeed"),
                            carResults.getInt("horsepower"),
                            carResults.getFloat("acceleration"),
                            carResults.getFloat("braking"),
                            carResults.getInt("weight"),
                            carResults.getInt("torque"),
                            carResults.getInt("weightBalanceFront"),
                            carResults.getFloat("wheelbase")
                    ));
                }
                carResults.close();
                carStmt.close();

                event = new Event(
                        eventName,
                        rounds,
                        cars,
                        sql
                );
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
        return event;
    }
}