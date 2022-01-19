package com.example.etlap;

import java.sql.*;

public class EtlapDB {

    private Connection connection;

    public EtlapDB() throws SQLException{
        connection = DriverManager.getConnection("jdbc:mysql://localhost/etlapdb", "root", "");
    }

}
