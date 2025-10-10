/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.projettp;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author selli
 */
public class Archer extends Personnage {
    private int nbFleches;
    
    public Archer(int pV, int dA, int paAtt, int paPar, int pPar, Point2D p, int nb, String n, int dMax){
        super(pV,dA,paAtt, paPar, pPar, p,n,dMax);
        this.nbFleches=nb;
    }
    public Archer(Archer a){
        super((Personnage)a);
        nbFleches=a.nbFleches;
    }
    public Archer(){
        super(10,3,4,3,3,new Point2D(0,0), "Archer", 0);
        nbFleches=0;
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
        if (this.getPos().distance(c.getPos())<this.getDistAttMax()){  
            if (na<=this.getPageAtt()){
                c.setPtVie(c.getPtVie()-this.getDegAtt());
            }
        }
    }
    
    public void declaAge() throws NumberFormatException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez l'âge de votre personnage (en nombre) : ");
        String saisieAge = scanner.nextLine();
        int age = Integer.parseInt(saisieAge);
        System.out.println("L'archer a " + age + " ans !");
    }
}
