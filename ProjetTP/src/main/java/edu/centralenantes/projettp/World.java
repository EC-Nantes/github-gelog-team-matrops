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
    
    World(){
        robin = new Archer();
        peon = new Paysan();
        bugs1 = new Lapin();
        bugs2 = new Lapin();
        guillaumeT = new Archer(robin);
        grosBill = new Guerrier();
        wolfie = new Loup();
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
    
    
    
    /**
     *
     */
    public void creerMondeAlea(){
        Random alea=new Random();
        int n1=alea.nextInt(1000);
        int n2=alea.nextInt(1000);
        int n3=alea.nextInt(1000);
        int n4=alea.nextInt(1000);
        int n5=alea.nextInt(1000);
        int n6=alea.nextInt(1000);
        this.getRobin().setPos(new Point2D(n1,n2));
        this.peon.setPos(new Point2D(n3,n4));
        this.bugs1.setPos(new Point2D(n5,n6));
    }
    
    public void tourDeJeu(){
        
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
