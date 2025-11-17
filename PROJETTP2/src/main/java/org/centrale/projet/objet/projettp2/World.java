/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.centrale.projet.objet.projettp2;
import java.util.Random;
/**
 * Un monde
 * @author Justine Sellier
 * @author Max Perron
 * 
 */
public class World{
    
    /**
     * Déclaration d'un Archer "robin"
     */
    public Archer robin;

    /**
     * Déclaration d'un Paysan "peon"
     */
    public Paysan peon;

    /**
     * Déclaration d'un Lapin "bugs"
     */
    public Lapin bugs;
    
    /**
     *Déclaration d'un Guerrier "guillaumeT"
     */
    public Guerrier guillaumeT;

    /**
     * Déclaration d'une potion de soin "potS"
     */
    public PotionSoin potS;

    /**
     *Déclaration d'une épee "epee"
     */
    public Epee epee;
    

    
    /**
     * Affectation d'une nouvelle mémoire d'un Archer, Paysan, Lapin
     */
    public void World(){
        robin = new Archer();
        peon = new Paysan();
        bugs = new Lapin();
        guillaumeT = new Guerrier();
        potS = new PotionSoin();
        epee = new Epee();
    
    }

    /**
     * Création d'un monde avec des positions aléatoire pour personnages/monstres
     * Création d'un archer, guerrier, paysan, lapin, potionsoin
     */
    public void creerMondeAlea(){
        Point2D pA,pP,pL,pG,pPotS;
        Random aleaInt = new Random();
        pA = new Point2D();
        pP = new Point2D();
        pL = new Point2D();
        pG = new Point2D();
        pPotS = new Point2D();
        pA.randomPoint2D(10);
        pP.randomPoint2D(10);
        pL.randomPoint2D(10);
        pG.randomPoint2D(10);
        pPotS.randomPoint2D(10);
        while (pA == pP) {
            pP.randomPoint2D(10);
            }
        while ((pA == pL) || (pP == pL)){
            pL.randomPoint2D(10);
            }
        
        
        robin = new Archer();
        robin.setPos(pA);
        
        peon = new Paysan();
        peon.setPos(pP);
        
        bugs = new Lapin();
        bugs.setPos(pL);
        
        guillaumeT = new Guerrier();
        guillaumeT.setPos(pG);
        
        potS = new PotionSoin();
        potS.setSoin(aleaInt.nextInt(50));
        potS.setPos(pPotS);
        
        
        }
    
    
}
    

