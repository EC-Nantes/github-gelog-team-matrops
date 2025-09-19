/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.projettp;

/**
 *
 * @author selli
 */
public class Personnage {
    private String nom;
    private int ptVie;
    private int degAtt;
    private int ptPar;
    private int pageAtt;
    private int pagePar;
    private int distAttMax;
    private Point2D pos;
    
    Personnage(String n, int pV, int dA, int paAtt, int paPar, int dMax, Point2D p){
        nom=n;
        ptVie=pV;
        degAtt = dA;
        ptPar = paAtt;
        pageAtt = paPar;
        pagePar = dMax;
        distAttMax = dMax;
        pos=p;
    }
    Personnage(Personnage perso){
        nom=perso.nom;
        ptVie=perso.ptVie;
        degAtt = perso.degAtt;
        ptPar = perso.ptPar;
        pageAtt = perso.pageAtt;
        pagePar = perso.pagePar;
        distAttMax = perso.distAttMax;
        pos=perso.pos;
    }
    Personnage(){
        nom="Frodon";
        ptVie=0;
        degAtt = 0;
        ptPar = 0;
        pageAtt = 0;
        pagePar = 0;
        distAttMax = 0;
        pos=new Point2D(0,0);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPtVie() {
        return ptVie;
    }

    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }

    public int getDegAtt() {
        return degAtt;
    }

    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }

    public int getPtPar() {
        return ptPar;
    }

    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }

    public int getPageAtt() {
        return pageAtt;
    }

    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    public int getPagePar() {
        return pagePar;
    }

    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }

    public int getDistAttMax() {
        return distAttMax;
    }

    public void setDistAttMax(int distAttMax) {
        this.distAttMax = distAttMax;
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }
    
    public void deplace() {
        Point2D p = new Point2D(getPos());
        p.translate(1,1);
    }
    public void affiche() {
        System.out.println("Le personnage s'appelle " + getNom()+".");
        System.out.println("Le personnage est situé " + getPos()+".");
        System.out.println("Le personnage a " + getPtVie()+" de points de vie.");
    }
}
