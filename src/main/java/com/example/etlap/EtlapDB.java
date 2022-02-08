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
        String sql = "SELECT * FROM etlap INNER JOIN kategoria ON etlap.kategoria_id = kategoria.id;";
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int id = result.getInt("etlap.id");
            String nev = result.getString("etlap.nev");
            String leiras = result.getString("etlap.leiras");
            int ar = result.getInt("etlap.ar");
            String kategoria = result.getString("kategoria.nev");
            Etel etel = new Etel(id, ar, nev, kategoria, leiras);
            etlap.add(etel);
        }
        return etlap;
    }

    public List<Etel> getEtlapSzurt(String kat) throws SQLException {
        List<Etel> etlap = new ArrayList<>();
        String sql = "SELECT * FROM etlap INNER JOIN kategoria ON etlap.kategoria_id = kategoria.id WHERE kategoria.nev = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, kat);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int id = result.getInt("etlap.id");
            String nev = result.getString("etlap.nev");
            String leiras = result.getString("etlap.leiras");
            int ar = result.getInt("etlap.ar");
            String kategoria = result.getString("kategoria.nev");
            Etel etel = new Etel(id, ar, nev, kategoria, leiras);
            etlap.add(etel);
        }
        return etlap;
    }

    public List<Kategoria> getKategoriak() throws SQLException {
        List<Kategoria> kategoriak = new ArrayList<>();
        Statement statement = connection.createStatement();
        String sql = "SELECT * FROM kategoria;";
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int id = result.getInt("kategoria.id");
            String nev = result.getString("kategoria.nev");
            Kategoria kategoria = new Kategoria(id, nev);
            kategoriak.add(kategoria);
        }
        return kategoriak;
    }

    public boolean ujEtel(String nev, String leiras, int kategoria, int ar) throws SQLException {
        String sql = "INSERT INTO etlap (nev, leiras,  kategoria_id, ar) VALUES (?, ?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nev);
        statement.setString(2, leiras);
        statement.setInt(3, kategoria);
        statement.setInt(4, ar);
        return statement.executeUpdate() == 1;
    }

    public int ujKategoria(String nev) throws SQLException {
        String sql = "INSERT INTO kategoria (nev) VALUES (?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nev);
        return statement.executeUpdate();
    }

    public boolean deleteEtel(int id) throws SQLException {
        String sql = "DELETE FROM etlap WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        return statement.executeUpdate() == 1;
    }

    public boolean deleteKategoria(int id) throws SQLException {
        String sql = "DELETE FROM kategoria WHERE id = ?;";
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
