/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.centrale.projet.objet.projettp2;
import java.util.Random;
/**
 * @author Justine Sellier
 * @author Max Perron
 */
public class World{
    
    /**
     * Déclaration d'un Archer 
     */
    public Archer robin;

    /**
     * Déclaration d'un Paysan
     */
    public Paysan peon;

    /**
     * Déclaration d'un Lapin
     */
    public Lapin bugs;
    
    public Guerrier guillaumeT;
    public PotionSoin potS;
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
    

