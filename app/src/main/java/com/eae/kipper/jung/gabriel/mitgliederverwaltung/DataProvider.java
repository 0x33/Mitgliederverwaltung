package com.eae.kipper.jung.gabriel.mitgliederverwaltung;

public class DataProvider {

    private String name;
    private String strasse;
    private String plz;
    private String ort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public DataProvider(String name, String strasse, String plz, String ort){
        this.name = name;
        this.strasse = strasse;
        this.plz = plz;
        this.ort = ort;
    }
}
