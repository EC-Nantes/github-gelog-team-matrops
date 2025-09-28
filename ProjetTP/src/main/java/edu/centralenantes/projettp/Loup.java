/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.projettp;
import java.util.Random;

/**
 *
 * @author selli
 */
public class Loup extends Monstre {
    
    public Loup (int pV,int dA,int pPar, int paAtt, int paPar, Point2D p){
        super(pV,dA,pPar,paAtt,paPar,p);
    }
    
    public Loup (Lapin l){
        super((Monstre)l);
    }
    
    public Loup (){
        super(0,0,0,0,0,new Point2D(0,0));
    }
    
    public void combattre (Creature c){
        Random alea=new Random();
        int na =alea.nextInt(100);
        int np =alea.nextInt(100);
        if (na<=c.getPageAtt()){
            if (np>=this.getPagePar()){
                this.setPtVie(this.getPtVie()-c.getDegAtt());
            }
        }
        if (na<=this.getPageAtt()){
            if (np>=c.getPagePar()){
                c.setPtVie(c.getPtVie()-this.getDegAtt());
            }
        }
    }
}
