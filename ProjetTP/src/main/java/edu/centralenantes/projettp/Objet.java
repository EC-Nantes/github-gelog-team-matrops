/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.projettp;

/**
 *
 * @author selli
 */
public class Objet {
    private String nom;
    private Point2D pos;
    private int dansinv;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    public int getDansinv() {
        return dansinv;
    }

    public void setDansinv(int dansinv) {
        this.dansinv = dansinv;
    }
    
    public Objet(String nm, Point2D p, int n){
        nom=nm;
        pos=p;
        dansinv=n;
    }
    public Objet(Objet O){
        nom=O.nom;
        pos=O.pos;
        dansinv=O.dansinv;
    }
    public Objet(){
        nom="Lampe";
        pos= new Point2D();
        dansinv=0;
    }
}
