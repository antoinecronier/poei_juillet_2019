package com.poei_juillet_2019.mysql.entities;

public class Entreprise extends EntityDb {

    private String nom;
    private String adresse;
    private String type;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Entreprise(String nom, String adresse, String type) {
        super();
        this.nom = nom;
        this.adresse = adresse;
        this.type = type;
    }

    public Entreprise() {

    }

    @Override
    public String toString() {
        return "Entreprise [nom=" + nom + ", adresse=" + adresse + ", type=" + type + ", getId()=" + getId() + "]";
    }


}
