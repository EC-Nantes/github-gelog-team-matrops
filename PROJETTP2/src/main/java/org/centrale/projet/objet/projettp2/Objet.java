/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.projet.objet.projettp2;

/**
 *
 * @author Max
 */
public class Objet {
    private String nom;
    private Point2D pos;
    private int dansInv;
    
    public Objet(){
        this.nom ="Objet";
        this.pos = new Point2D();
        this.dansInv = 0;
    }
    
    public Objet(Objet o){
        this.nom = o.nom;
        this.pos = new Point2D(o.pos);
        this.dansInv = o.dansInv;
    }
    
    public Objet(String nom, Point2D p, int dansInv){
        this.nom = nom;
        this.pos = new Point2D(p);
        this.dansInv = dansInv;
    }

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

    public int getDansInv() {
        return dansInv;
    }

    public void setDansInv(int dansInv) {
        this.dansInv = dansInv;
    }
    
    
}
