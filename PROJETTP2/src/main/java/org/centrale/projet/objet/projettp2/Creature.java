/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.projet.objet.projettp2;

import java.util.Random;

/**
 *
 * @author Max
 */
public class Creature {
    private int ptVie;
    private int degAtt;
    private int ptPar;
    private int pageAtt;
    private int pagePar;
    private Point2D pos;
    
    public void Creature(int pV, int dA, int pPar, int paAtt, int paPar, Point2D p) {
        this.ptVie = pV;
        this.degAtt = dA;
        this.ptPar = pPar;
        this.pageAtt = paAtt;
        this.pagePar = paPar;
        this.pos = new Point2D(p);
    }
    /**
     *
     * @param m
     */
    public void Creature(Creature c) {
        this.ptVie = c.ptVie;
        this.degAtt = c.degAtt;
        this.ptPar = c.ptPar;
        this.pageAtt = c.pageAtt;
        this.pagePar = c.pagePar;
        this.pos = new Point2D(c.pos);
    }
    /**
     * Creature nulle
     */
    public void Creature(){
        this.ptVie = 0;
        this.degAtt = 0;
        this.ptPar = 0;
        this.pageAtt = 0;
        this.pagePar = 0;
        this.pos = new Point2D();
    }
    
    /**
     *
     * @return
     */
    public int getPtVie() {
        return ptVie;
    }
    // Modifier les points de vie

    /**
     *
     * @param ptVie
     */
    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }
    
    /**
     *
     * @return
     */
    public int getDegAtt() {
        return degAtt;
    }

    /**
     *
     * @param degAtt
     */
    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }

    /**
     *
     * @return
     */
    public int getPtPar() {
        return ptPar;
    }

    /**
     *
     * @param ptPar
     */
    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }

    /**
     *
     * @return
     */
    public int getPageAtt() {
        return pageAtt;
    }

    /**
     *
     * @param pageAtt
     */
    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    /**
     *
     * @return
     */
    public int getPagePar() {
        return pagePar;
    }

    /**
     *
     * @param pagePar
     */
    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }

    /**
     *
     * @return
     */
    public Point2D getPos() {
        return pos;
    }

    /**
     *
     * @param pos
     */
    public void setPos(Point2D pos) {
        this.pos = pos;
    }
    
    /**
     *
     */
    public void affiche(){
        System.out.println(toString());
        
    }
    
    /**
     *
     * @return
     */
    public String toString(){
        String s = "La Creature a " + this.ptVie + "points de vie" + "\n" +"La Creature inflige " + this.degAtt + "points de dégât"+ "\n" + "La Creature a" + this.ptPar + "points de parade" + "\n" + "La Creature a " + this.pageAtt + "% de chance d'attaquer"+ "\n" + "La Creature a " + this.pagePar + "% de chance de parer"+"\n" + "La Creature est aux coordonées" + this.pos.toString();
        return s;
        }
    
    public void deplace(){
        Random aleaInt = new Random();
        int aleaX = aleaInt.nextInt(3)-1;
        int aleaY = aleaInt.nextInt(3)-1;
        pos.translation(aleaX,aleaY);
    }
    public void deplace(int x, int y){
        int dx,dy;
        dx = 0;
        dy = 0;
        if (x != 0){
            dx = x/Math.abs(x);
        }
        if (y != 0){
            dy = y/Math.abs(y);
        }
        pos.translation(dx,dy);
    }
    
    
    
}
