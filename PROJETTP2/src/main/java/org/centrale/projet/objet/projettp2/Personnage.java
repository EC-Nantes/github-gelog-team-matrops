/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.projet.objet.projettp2;
import java.util.Random;
/**
 * Un personnage
 * @author Justine Sellier
 * @author Max Perron
 * @version 24.0.2
 *
 */
public class Personnage extends Creature{
    private String nom;
    private int distAttMax;
    
    /**
     *
     * @param n nom du personnage
     * @param pV point de vie du personnage
     * @param dA point d'attaque du personnage
     * @param pPar point de parade du personnage
     * @param pAtt pourcentage de réussite d'attaque
     * @param paPar pourcentage de réussite de parade
     * @param dMax distance d'attaque maximal du personnage
     * @param p position du personnage
     */
    public Personnage(String n, int pV, int dA, int pPar, int pAtt,int paPar,int dMax, Point2D p){
        super.Creature(pV,dA,pPar,pAtt,paPar,p);
        this.nom=n;
        this.distAttMax = dMax;
    }

    /**
     *
     * @param perso Personnage
     * recopie du personnage
     */
    public Personnage(Personnage perso){
        super.Creature(perso);
        this.distAttMax = perso.distAttMax;
        this.nom = perso.nom;
    }

    /**
     * Création d'un personnage "Steve" avec une distance d'attaque 1
     */
    public Personnage(){
        super.Creature();
        this.nom="Steve";
        this.distAttMax = 1;
    }
    /**
     * Méthode pour afficher le nom du personnage, la distance d'attaque, et les autres caractéristiques
     */
    public void affiche() {
        System.out.println("Le personnage s'appelle " + this.nom +".");
        System.out.println(super.toString());
        System.out.println("Le personnage peut attaquer de " + this.distAttMax +" case.");
    }

    /**
     *
     * @return nom 
     * Getter du nom du personnage
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom
     * Setter du nom du personnage
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return distAttMax
     * getter de la distance d'attaque max
     */
    public int getDistAttMax() {
        return distAttMax;
    }

    /**
     *
     * @param distAttMax
     * Setter de la distance d'attaque max
     */
    public void setDistAttMax(int distAttMax) {
        this.distAttMax = distAttMax;
    }
    

    /**
     *
     * @return String
     * chaine de caractères de toutes les caractéristiques du personnages
     */
    public String toString(){
        String s = "Nom : " + this.nom + "Distance d'attaque Maximal : " + this.distAttMax;
        return s ;
    }
}