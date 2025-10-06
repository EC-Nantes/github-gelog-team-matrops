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
    
    public Loup (Loup l){
        super((Monstre)l);
    }
    
    public Loup (){
        super(10,3,0,4,0,new Point2D(0,0));
    }
    
    public void combattre (Creature c){
        Random alea=new Random();
        int na =alea.nextInt(100);
        int np =alea.nextInt(100);
        if (this.getPos()==c.getPos()){
            if (na<=this.getPageAtt()){
                if (np>c.getPagePar()){
                    c.setPtVie(c.getPtVie()-this.getDegAtt());
                }
                else {
                    c.setPtVie(c.getPtVie()-this.getDegAtt()+c.getPtPar());
                }
            }
        }
    }
}
