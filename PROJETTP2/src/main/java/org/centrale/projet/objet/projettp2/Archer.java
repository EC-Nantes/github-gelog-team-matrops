/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.projet.objet.projettp2;

import java.util.Random;
import java.util.Set;

/**
 * Un archer
 *
 * @author Justine Sellier
 * @author Max Perron
 */
public class Archer extends Personnage {

    private int nbFleches;

    Archer(String n, int pV, int dA, int pPar, int paAtt, int paPar, int dMax, Point2D p, int nb) {
        super(n, pV, dA, pPar, paAtt, paPar, dMax, p);
        this.nbFleches = nb;
    }

    Archer(Archer a) {
        super((Personnage) a);
        nbFleches = a.nbFleches;
    }

    Archer() {
        super("Légolas", 100, 7, 2, 80, 7, 2, new Point2D(0, 0));
        nbFleches = 0;
    }

    public int getNbFleches() {
        return nbFleches;
    }

    /**
     * public void copieArcher(Archer p){ super = new Archer(); = p;
     * p.deplace();
    }
     */
    public void setNbFleches(int nbFleches) {
        this.nbFleches = nbFleches;
    }

    public void combattre(Creature c) {
        if (this.nbFleches != 0) {
            if (getPos() == c.getPos()) {
                Random aleaInt = new Random();
                int na = aleaInt.nextInt(101);
                if (na > getPageAtt()) {
                    System.out.println("L'attaque a échoué");
                } else {
                    int np = aleaInt.nextInt(101);
                    System.out.println("L'attaque a réussi");
                    if (np > getPagePar()) {
                        System.out.println("La défense a réussi");
                        c.setPtVie(c.getPtVie() - getDegAtt() + c.getPtPar());
                    } else {
                        System.out.println("La défense a échoué");
                        c.setPtVie(c.getPtVie() - getDegAtt());
                    }
                }
            }
            if (getPos().distance(c.getPos()) < getDistAttMax()) {
                this.nbFleches -= 1;
                Random aleaInt = new Random();
                int na = aleaInt.nextInt(101);
                if (na > getPageAtt()) {
                    System.out.println("L'attaque a échoué");
                } else {
                    int np = aleaInt.nextInt(101);
                    System.out.println("L'attaque a réussi");
                    if (np > getPagePar()) {
                        System.out.println("La défense a réussi");
                        c.setPtVie(c.getPtVie() - getDegAtt() + c.getPtPar());
                    } else {
                        System.out.println("La défense a échoué");
                        c.setPtVie(c.getPtVie() - getDegAtt());
                    }
                }

            }
        } else {
            System.out.println("L'archer n'a pas de flèches");
        }
    }
    
    
    public void soin(PotionSoin ptS){
        if (getPos()==ptS.getPos()){
            setPtVie(getPtVie() + ptS.getSoin());
            ptS.setSoin(0);
            ptS.setPos(null);
            ptS.setNom(null);
            ptS.setDansInv(0);
            
        }
        
    }
}
