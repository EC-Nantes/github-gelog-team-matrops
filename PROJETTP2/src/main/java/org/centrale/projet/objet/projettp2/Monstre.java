package org.centrale.projet.objet.projettp2;
import java.util.Random;
import java.util.Set;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 * @author Justine Sellier
 * @author Max Perron
 */
public class Monstre extends Creature{

    // Création d'un monstre à partir de tout les paramètres

    /**
     *
     * @param pV
     * @param dA
     * @param pPar
     * @param paAtt
     * @param paPar
     * @param p
     */
    public Monstre(int pV, int dA, int pPar, int paAtt, int paPar, Point2D p) {
        super.Creature(pV,dA,pPar,paAtt,paPar,p);

    }
    // Création d'un monstre en recopie

    /**
     *
     * @param m
     */
    public Monstre(Monstre m) {
        super.Creature(m);
    }
   

    public Monstre(){
        super.Creature(100,10,5,80,15,new Point2D(7,7));
    }
    
}
