/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.projet.objet.projettp2;

import java.util.Random;

/**
 * @author Justine Sellier
 * @author Max Perron
 */
public class Lapin extends Monstre {

    /**
     *
     * @param pV
     * @param dA
     * @param pPar
     * @param paAtt
     * @param paPar
     * @param p
     */
    public Lapin(int pV, int dA, int pPar, int paAtt, int paPar, Point2D p){
        super(pV,dA,pPar,paAtt,paPar,p);
    }

    /**
     *
     * @param l
     */
    public Lapin(Lapin l){
        super(l);
    }

    /**
     *
     */
    public Lapin(){
        super();
    }
   
}
