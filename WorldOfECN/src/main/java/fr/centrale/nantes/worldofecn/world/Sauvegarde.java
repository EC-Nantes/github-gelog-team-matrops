/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.centrale.nantes.worldofecn.world;

import java.sql.Date;

/**
 *
 * @author selli
 */
public class Sauvegarde {
    private int id_sauv;
    private String nom;
    private int id_partie;
    private Date date;

    public int getId_sauv() {
        return id_sauv;
    }

    public void setId_sauv(int id_sauv) {
        this.id_sauv = id_sauv;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId_partie() {
        return id_partie;
    }

    public void setId_partie(int id_partie) {
        this.id_partie = id_partie;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public void Sauvegarde(){
        this.id_sauv=1;
        this.id_partie=1;
        this.nom="Sauvegarde 1";
        this.date= new Date(01,01,2001);
    }
    
    public void Sauvegarde(Sauvegarde s){
        this.id_sauv=s.id_sauv;
        this.id_partie=s.id_partie;
        this.nom=s.nom;
        this.date= s.date;
    }
    
    public void Sauvegarde(int a, int b, String c, Date d){
        this.id_sauv=a;
        this.id_partie=b;
        this.nom=c;
        this.date= d;
    }
}
