/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.projettp;

/**
 *
 * @author selli
 */
public class Lapin extends Monstre {
    Lapin(int pV,int dA,int pPar, int paAtt, int paPar, Point2D p){
        super(pV,dA,pPar,paAtt,paPar,p);
    }
    
    Lapin(Lapin l){
        super((Monstre)l);
    }
    
    Lapin(){
        super(0,0,0,0,0,new Point2D(0,0));
    }
}
