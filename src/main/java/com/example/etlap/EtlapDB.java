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

    public int ujEtel(String nev, String leiras, String kategoria, int ar) throws SQLException {
        String sql = "INSERT INTO etlap (nev, leiras,  kategoria, ar) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, nev);
        stmt.setString(2, leiras);
        stmt.setString(3, kategoria);
        stmt.setInt(4, ar);
        return stmt.executeUpdate();
    }

    public boolean deleteEtel(int id) throws SQLException {
        String sql = "DELETE FROM etlap WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, id);
        return stmt.executeUpdate() == 1;
    }

}
