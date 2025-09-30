/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.projet.objet.projettp2;

/**
 * @author Justine Sellier
 * @author Max Perron
 */
public class Paysan extends Personnage {
    Paysan(String n, int pV, int dA,int pPar, int paAtt, int paPar, int dMax, Point2D p){
        super(n,pV,dA,pPar,paAtt, paPar, dMax,p);
    }
    Paysan(Paysan p){
        super((Personnage)p); 
    }
    Paysan(){
        super("Perceval",100,10,100,15,15,1,new Point2D(5,5));
    }
}