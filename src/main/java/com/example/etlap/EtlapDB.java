package com.example.etlap;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtlapDB {

    private Connection connection;

    public EtlapDB() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/etlapdb", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Etel> getEtlap() throws SQLException {
        List<Etel> etlap = new ArrayList<>();
        Statement stmt = connection.createStatement();
        String sql = "SELECT * FROM etlap";
        ResultSet result = stmt.executeQuery(sql);
        while (result.next()) {
            int id = result.getInt("etlap.id");
            String nev = result.getString("etlap.nev");
            String leiras = result.getString("etlap.leiras");
            int ar = result.getInt("etlap.ar");
            String kategoria = result.getString("etlap.kategoria");
            Etel etel = new Etel(id, ar, nev, kategoria, leiras);
            etlap.add(etel);
        }
        return etlap;
    }

}
