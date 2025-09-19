/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.projettp;

/**
 *
 * @author selli
 */
public class Archer extends Personnage {
    private int nbFleches;
    
    Archer(String n, int pV, int dA, int paAtt, int paPar, int dMax, Point2D p, int nb){
        super(n,pV,dA,paAtt, paPar, dMax,p);
        this.nbFleches=nb;
    }
    Archer(Archer a){
        super((Personnage)a);
        nbFleches=a.nbFleches;
    }
    Archer(){
        super("Légolas",0,0,0,0,0,new Point2D(0,0));
        nbFleches=0;
    }
}
