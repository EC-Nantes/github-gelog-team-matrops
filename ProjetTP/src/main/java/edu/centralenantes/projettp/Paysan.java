/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.projettp;

/**
 *
 * @author selli
 */
public class Paysan extends Personnage {
    Paysan(String n, int pV, int dA, int paAtt, int paPar, int dMax, Point2D p){
        super(n,pV,dA,paAtt, paPar, dMax,p);
    }
    Paysan(Paysan p){
        super((Personnage)p); 
    }
    Paysan(){
        super("Perceval",0,0,0,0,0,new Point2D(0,0));
    }
}
