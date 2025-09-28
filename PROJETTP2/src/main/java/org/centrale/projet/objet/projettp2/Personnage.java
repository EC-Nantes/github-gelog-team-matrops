/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.projet.objet.projettp2;
import java.util.Random;
/**
 *
 * @author Justine Sellier
 * @author Max Perron
 * @version 24.0.2
 */
public class Personnage extends Creature{
    private String nom;
    private int distAttMax;
    
    public Personnage(String n, int pV, int dA, int pPar, int pAtt,int paPar,int dMax, Point2D p){
        super.Creature(pV,dA,pPar,pAtt,paPar,p);
        this.nom=n;
        this.distAttMax = dMax;
    }
    public Personnage(Personnage perso){
        super.Creature(perso);
        this.distAttMax = perso.distAttMax;
        this.nom = perso.nom;
    }
    public Personnage(){
        super.Creature();
        this.nom="Steve";
        this.distAttMax = 1;
    }
    /**
     *
     */
    public void affiche() {
        System.out.println("Le personnage s'appelle " + this.nom +".");
        System.out.println(super.toString());
        System.out.println("Le personnage peut attaquer de " + this.distAttMax +" case.");
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
     * @return String
     */
    public String toString(){
        String s = "Nom : " + this.nom + "Distance d'attaque Maximal : " + this.distAttMax;
        return s ;
    }
}