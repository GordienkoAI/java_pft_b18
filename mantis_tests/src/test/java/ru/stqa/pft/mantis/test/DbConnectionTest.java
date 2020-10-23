package ru.stqa.pft.mantis.test;

import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionTest {

    @Test
    public void testDbConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
