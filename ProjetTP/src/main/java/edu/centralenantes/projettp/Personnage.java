/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.projettp;

/**
 * Classe Personnage qui représente une créature disposant d’un nom, d’une position et de points de vie, de capacités d’attaque et de parade 
 * @author Max Perron
 * @author Justine Sellier
 * @version 24.0.2
 */

public class Personnage extends Creature {
    private String nom;
    private int distAttMax;
    

    public Personnage(int pV, int dA, int pPar, int paAtt, int paPar, Point2D p, String n, int dMax){
        super(pV,dA,pPar,paAtt,paPar,p);
        nom=n;
        distAttMax = dMax;
    }
    public Personnage(Personnage perso){
        super((Creature)perso);
        nom=perso.nom;
        distAttMax=perso.distAttMax;
    }
    public Personnage(){
        super(0,0,0,0,0, new Point2D(0,0));
        nom="Frodon";
        distAttMax = 0;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDistAttMax() {
        return distAttMax;
    }

    public void setDistAttMax(int distAttMax) {
        this.distAttMax = distAttMax;
    }
    
    
    

    /**
     *
     */
    public void affiche() {
        System.out.println(this.toString());
    }
    
    /**
     *
     * @return
     */
    public String toString() {
        return ("Le personnage s'appelle " + getNom() + ". Il a "+ getPtVie()+" points de vie.\nIl est à la position "+this.getPos().toString());
    }
}