/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.projet.objet.projettp2;

import java.util.Random;

/**
 *
 * @author Max
 */
public class Guerrier extends Personnage{

    public Guerrier(String n, int pV, int dA,int pPar, int paAtt, int paPar, int dMax, Point2D p){
        super(n,pV,dA,pPar,paAtt, paPar, dMax,p);
    }
    public Guerrier(Guerrier p){
        super((Personnage)p); 
    }
    public Guerrier(){
        super("Guillaume",100,10,5,80,15,1,new Point2D(5,5));
    }
    
    public void combattre(Creature c){
        if (getPos() == c.getPos()){
            Random aleaInt = new Random();
            int na = aleaInt.nextInt(101);
            if (na > getPageAtt()){
                System.out.println("L'attaque a échoué");
            }else {
                int np = aleaInt.nextInt(101);
                System.out.println("L'attaque a réussi");
                if (np > getPagePar()){
                    System.out.println("La défense a réussi");
                    c.setPtVie(c.getPtVie() - getDegAtt() + c.getPtPar());
                }else {
                    System.out.println("La défense a échoué");
                    c.setPtVie(c.getPtVie() - getDegAtt());
                }
              
            }
                
        }
        
    }
    
}
