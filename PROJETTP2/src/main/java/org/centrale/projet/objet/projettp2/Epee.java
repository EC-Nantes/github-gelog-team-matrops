/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.projet.objet.projettp2;

/**
 *
 * @author Max
 */
public class Epee extends Objet{
    private int deg;
    private int dura;
    
    public Epee(){
        super();
        this.deg=0;
        this.dura=0;
    }
    
    public Epee(Epee e){
        super(e);
        this.deg = e.deg;
        this.dura = e.dura;
    }
    
    public Epee(String nom,Point2D p,int dansInv, int deg, int dura){
        super(nom,p,dansInv);
        this.deg = deg;
        this.dura = dura;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public int getDura() {
        return dura;
    }

    public void setDura(int dura) {
        this.dura = dura;
    }
    
    
    
}
