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
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM etlap;";
        ResultSet result = statement.executeQuery(sql);
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

    public boolean ujEtel(String nev, String leiras, String kategoria, int ar) throws SQLException {
        String sql = "INSERT INTO etlap (nev, leiras,  kategoria, ar) VALUES (?, ?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nev);
        statement.setString(2, leiras);
        statement.setString(3, kategoria);
        statement.setInt(4, ar);
        return statement.executeUpdate() == 1;
    }

    public boolean deleteEtel(int id) throws SQLException {
        String sql = "DELETE FROM etlap WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        return statement.executeUpdate() == 1;
    }

    public boolean aremelesSzazalek(int szazalek, int id) throws SQLException {
        String sql = "UPDATE etlap SET ar = ar * ? WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDouble(1, szazalek / 100.0 + 1);
        statement.setInt(2, id);
        return statement.executeUpdate() == 1;
    }

    public boolean aremelesSzazalek(int szazalek) throws SQLException {
        String sql = "UPDATE etlap SET ar = ar * ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setDouble(1, szazalek / 100.0 + 1);
        return statement.executeUpdate() == 1;
    }

    public boolean aremelesFt(int ft, int id) throws SQLException {
        String sql = "UPDATE etlap SET ar = ar + ? WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, ft);
        statement.setInt(2, id);
        return statement.executeUpdate() == 1;
    }

    public boolean aremelesFt(int ft) throws SQLException {
        String sql = "UPDATE etlap SET ar = ar + ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, ft);
        return statement.executeUpdate() == 1;
    }

}
