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
    
    public void creerMondeAlea(){
        Random alea=new Random();
        int n1=alea.nextInt(10000);
        int n2=alea.nextInt(10000);
        int n3=alea.nextInt(10000);
        int n4=alea.nextInt(10000);
        int n5=alea.nextInt(10000);
        int n6=alea.nextInt(10000);
        Lapin(int pV,int dA,int pPar, int paAtt, int paPar, Point2D p)
    }
}
