package com.example.etlap;

public class Etel {
    private int id, ar;
    private String nev, kategoria, leiras;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAr() {
        return ar;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public Etel(int id, int ar, String nev, String kategoria, String leiras) {
        this.id = id;
        this.ar = ar;
        this.nev = nev;
        this.kategoria = kategoria;
        this.leiras = leiras;
    }
}
