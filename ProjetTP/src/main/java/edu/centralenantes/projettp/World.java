/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.projettp;
import java.util.Random;
import java.util.Set;
/**
 *
 * @author selli
 */
public class World {
    private Archer robin;
    private Paysan peon;
    private Lapin bugs;
    
    World(){
        robin = new Archer();
        peon = new Paysan();
        bugs = new Lapin();
    }

    public Archer getRobin() {
        return robin;
    }

    public void setRobin(Archer robin) {
        this.robin = robin;
    }

    public Paysan getPeon() {
        return peon;
    }

    public void setPeon(Paysan peon) {
        this.peon = peon;
    }

    public Lapin getBugs() {
        return bugs;
    }

    public void setBugs(Lapin bugs) {
        this.bugs = bugs;
    }
    
    public void creerMondeAlea(){
        Random alea=new Random();
        World w=new World();
        int n1=alea.nextInt(10000);
        int n2=alea.nextInt(10000);
        int n3=alea.nextInt(10000);
        int n4=alea.nextInt(10000);
        int n5=alea.nextInt(10000);
        int n6=alea.nextInt(10000);
        w.getRobin().setPos(new Point2D(n1,n2));
        w.peon.setPos(new Point2D(n3,n4));
        w.bugs.setPos(new Point2D(n5,n6));
    }
}
