/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.projet.objet.projettp2;

/**
 *
 * @author Max
 */
public class PotionSoin extends Objet{
    
    private int soin;

    public PotionSoin(){
        super();
        this.soin = 0;
    }
    
    public PotionSoin(PotionSoin ps){
        super(ps);
        this.soin = ps.soin;
    }
    
    public PotionSoin(String nom, Point2D pos, int dansInv, int soin){
        super(nom,pos,dansInv);
        this.soin = soin;
    }

    public int getSoin() {
        return soin;
    }

    public void setSoin(int soin) {
        this.soin = soin;
    }
}
