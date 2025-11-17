/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.projettp;
import java.util.Random;

/**
 *
 * @author selli
 */
public class PotionSoin extends Objet {
    private int ptVie;

    public int getPtVie() {
        return ptVie;
    }

    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }
    
    public PotionSoin(String m, Point2D p, int n, int v){
        super(m, p,n);
        ptVie=v;
    }
    
    public PotionSoin(PotionSoin ps){
        super((Objet)ps);
        ptVie=ps.ptVie;
    }
    
    public PotionSoin(){
        super("Soin", new Point2D(0,0),1);
        ptVie=1;
    }
    
    public void soigner (Creature c){
        Random alea=new Random();
        if (this.getPos()==c.getPos()){
            c.setPtVie(c.getPtVie()+this.getPtVie());
            this.setPtVie(0);
            }
        }
}
