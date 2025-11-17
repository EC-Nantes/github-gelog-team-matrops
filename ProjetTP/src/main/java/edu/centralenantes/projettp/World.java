/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.projettp;
import java.util.Random;
import java.util.Set;

/**
 * Classe World qui génère un monde en y ilplentant 3 personnages aléatoirement
 * @author Max Perron
 * @author Justine Sellier
 * @version 24.0.2
 */

public class World {
    private Archer robin;
    private Paysan peon;
    private Lapin bugs1;
    private Lapin bugs2;
    private Archer guillaumeT;
    private Guerrier grosBill;
    private Loup wolfie;
    private PotionSoin Potion1;
    private PotionSoin Potion2;
    private PotionSoin Potion3;
    
    public World(){
        robin = new Archer();
        peon = new Paysan();
        bugs1 = new Lapin();
        bugs2 = new Lapin();
        guillaumeT = new Archer(robin);
        grosBill = new Guerrier();
        wolfie = new Loup();
        Potion1 = new PotionSoin();
        Potion2 = new PotionSoin();
        Potion3 = new PotionSoin();
    }

    /**
     *
     * @return
     */
    public Archer getGuillaumeT() {
        return guillaumeT;
    }

    /**
     *
     * @param guillaumeT
     */
    public void setGuillaumeT(Archer guillaumeT) {
        this.guillaumeT = guillaumeT;
    }

    /**
     *
     * @return
     */
    public Archer getRobin() {
        return robin;
    }

    /**
     *
     * @param robin
     */
    public void setRobin(Archer robin) {
        this.robin = robin;
    }

    /**
     *
     * @return
     */
    public Paysan getPeon() {
        return peon;
    }

    /**
     *
     * @param peon
     */
    public void setPeon(Paysan peon) {
        this.peon = peon;
    }

    public Lapin getBugs1() {
        return bugs1;
    }

    public void setBugs1(Lapin bugs1) {
        this.bugs1 = bugs1;
    }

    public Lapin getBugs2() {
        return bugs2;
    }

    public void setBugs2(Lapin bugs2) {
        this.bugs2 = bugs2;
    }

    public Guerrier getGrosBill() {
        return grosBill;
    }

    public void setGrosBill(Guerrier grosBill) {
        this.grosBill = grosBill;
    }

    public Loup getWolfie() {
        return wolfie;
    }

    public void setWolfie(Loup wolfie) {
        this.wolfie = wolfie;
    }
    
    public PotionSoin getPotion1() {
        return Potion1;
    }

    public void setPotion1(PotionSoin Potion1) {
        this.Potion1 = Potion1;
    }

    public PotionSoin getPotion2() {
        return Potion2;
    }

    public void setPotion2(PotionSoin Potion2) {
        this.Potion2 = Potion2;
    }

    public PotionSoin getPotion3() {
        return Potion3;
    }

    public void setPotion3(PotionSoin Potion3) {
        this.Potion3 = Potion3;
    }
    
    
    /**
     *
     */
    public void creerMondeAlea(){
        Random alea=new Random();
        int n1=alea.nextInt(10);
        int n2=alea.nextInt(10);
        int n3=alea.nextInt(10);
        int n4=alea.nextInt(10);
        int n5=alea.nextInt(10);
        int n6=alea.nextInt(10);
        int n7=alea.nextInt(10);
        int n8=alea.nextInt(10);
        int n9=alea.nextInt(10);
        int n10=alea.nextInt(10);
        int n11=alea.nextInt(10);
        int n12=alea.nextInt(10);
        int n13=alea.nextInt(10);
        int n14=alea.nextInt(10);
        int n15=alea.nextInt(10);
        int n16=alea.nextInt(10);
        int n17=alea.nextInt(10);
        int n18=alea.nextInt(10);
        int n19=alea.nextInt(10);
        int n20=alea.nextInt(10);
        this.getRobin().setPos(new Point2D(n1,n2));
        this.peon.setPos(new Point2D(n3,n4));
        this.bugs1.setPos(new Point2D(n5,n6));
        this.bugs2.setPos(new Point2D(n7,n8));
        this.guillaumeT.setPos(new Point2D(n9,n10));
        this.grosBill.setPos(new Point2D(n11,n12));
        this.wolfie.setPos(new Point2D(n13,n14));
        this.Potion1.setPos(new Point2D(n15,n16));
        this.Potion2.setPos(new Point2D(n17,n18));
        this.Potion3.setPos(new Point2D(n19,n20));
    }
    
    public void tourDeJeu(){
        this.robin.deplace();
        this.peon.deplace();
        this.bugs1.deplace();
        this.bugs2.deplace();
        this.guillaumeT.deplace();
        this.grosBill.deplace();
        this.wolfie.deplace();
        
         //Combats possibles
        
        this.getWolfie().combattre(this.getRobin());
        this.getWolfie().combattre(this.getPeon());
        this.getWolfie().combattre(this.getBugs1());
        this.getWolfie().combattre(this.getBugs2());
        this.getWolfie().combattre(this.getGuillaumeT());
        this.getWolfie().combattre(this.getGrosBill());
        
        this.getGrosBill().combattre(this.getRobin());
        this.getGrosBill().combattre(this.getPeon());
        this.getGrosBill().combattre(this.getBugs1());
        this.getGrosBill().combattre(this.getBugs2());
        this.getGrosBill().combattre(this.getGuillaumeT());
        this.getGrosBill().combattre(this.getWolfie());
        
        this.getRobin().combattre(this.getWolfie());
        this.getRobin().combattre(this.getPeon());
        this.getRobin().combattre(this.getBugs1());
        this.getRobin().combattre(this.getBugs2());
        this.getRobin().combattre(this.getGuillaumeT());
        this.getRobin().combattre(this.getGrosBill());
        
        this.getGuillaumeT().combattre(this.getWolfie());
        this.getGuillaumeT().combattre(this.getRobin());
        this.getGuillaumeT().combattre(this.getBugs1());
        this.getGuillaumeT().combattre(this.getBugs2());
        this.getGuillaumeT().combattre(this.getPeon());
        this.getGuillaumeT().combattre(this.getGrosBill());
        
        this.getPotion1().soigner(this.getRobin());
        this.getPotion1().soigner(this.getPeon());
        this.getPotion1().soigner(this.getBugs1());
        this.getPotion1().soigner(this.getBugs2());
        this.getPotion1().soigner(this.getWolfie());
        this.getPotion1().soigner(this.getGuillaumeT());
        this.getPotion1().soigner(this.getGrosBill());
        
        this.getPotion2().soigner(this.getRobin());
        this.getPotion2().soigner(this.getPeon());
        this.getPotion2().soigner(this.getBugs1());
        this.getPotion2().soigner(this.getBugs2());
        this.getPotion2().soigner(this.getWolfie());
        this.getPotion2().soigner(this.getGuillaumeT());
        this.getPotion2().soigner(this.getGrosBill());
        
        this.getPotion3().soigner(this.getRobin());
        this.getPotion3().soigner(this.getPeon());
        this.getPotion3().soigner(this.getBugs1());
        this.getPotion3().soigner(this.getBugs2());
        this.getPotion3().soigner(this.getWolfie());
        this.getPotion3().soigner(this.getGuillaumeT());
        this.getPotion3().soigner(this.getGrosBill());
    }
    
    public void afficheWorld(){
        this.getRobin().affiche();
        this.getPeon().affiche();
        this.getBugs1().affiche();
        this.getBugs2().affiche();
        this.getGuillaumeT().affiche();
        this.getGrosBill().affiche();
        this.getWolfie().affiche();
    }
    
    
}
