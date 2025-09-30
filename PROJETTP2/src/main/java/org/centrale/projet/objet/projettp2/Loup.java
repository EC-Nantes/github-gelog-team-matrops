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
public class Loup extends Monstre{
    public Loup(){
        super();
    }
    public Loup(int pV, int dA, int pPar, int paAtt, int paPar, Point2D p){
        super(pV,dA,pPar,paAtt,paPar,p);
    }
    public Loup(Loup l){
        super(l);
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
