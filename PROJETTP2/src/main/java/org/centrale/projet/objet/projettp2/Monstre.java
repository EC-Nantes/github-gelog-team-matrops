package org.centrale.projet.objet.projettp2;
import java.util.Random;
import java.util.Set;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * Un monstre
 * @author Justine Sellier
 * @author Max Perron
 * 
 */
public class Monstre extends Creature{

    // Création d'un monstre à partir de tout les paramètres

    /**
     *
     * @param pV Point de vie du monstre
     * @param dA Point d'attaque du monstre
     * @param pPar Point de parade du monstre
     * @param paAtt pourcentage d'attaque du monstre
     * @param paPar pourcentage de parade du monstre
     * @param p position du monstre
     */
    public Monstre(int pV, int dA, int pPar, int paAtt, int paPar, Point2D p) {
        super.Creature(pV,dA,pPar,paAtt,paPar,p);

    }

    /**
     *
     * @param m Monstre que la méthode recopie
     */
    public Monstre(Monstre m) {
        super.Creature(m);
    }
   
    /**
     * Création d'un monstre initial
     */
    public Monstre(){
        super.Creature(100,10,5,80,15,new Point2D(7,7));
    }
    
}
