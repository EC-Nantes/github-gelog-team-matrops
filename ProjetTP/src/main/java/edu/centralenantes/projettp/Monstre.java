/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.projettp;

/**
 *
 * @author selli
 */
public class Monstre {
    private int ptVie;
    private int degAtt;
    private int ptPar;
    private int pageAtt;
    private int pagePar;
    private Point2D pos;
    
    Monstre(int pV,int dA,int pPar, int paAtt, int paPar, Point2D p){
        ptVie = pV;
        degAtt=dA;
        ptPar=pPar;
        pageAtt=paAtt;
        pagePar=paPar;
        pos=p;
    }
    
    Monstre(Monstre m){
        ptVie=m.ptVie;
        degAtt=m.degAtt;
        ptPar=m.ptPar;
        pageAtt=m.pageAtt;
        pagePar=m.pagePar;
        pos=m.pos;
    }
    
    Monstre(){
        ptVie=0;
        degAtt=0;
        ptPar=0;
        pageAtt=0;
        pagePar=0;
        pos=new Point2D(0,0);
    }

    public int getPtVie() {
        return ptVie;
    }

    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }

    public int getDegAtt() {
        return degAtt;
    }

    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }

    public int getPtPar() {
        return ptPar;
    }

    public void setPtPar(int ptPar) {
        this.ptPar = ptPar;
    }

    public int getPageAtt() {
        return pageAtt;
    }

    public void setPageAtt(int pageAtt) {
        this.pageAtt = pageAtt;
    }

    public int getPagePar() {
        return pagePar;
    }

    public void setPagePar(int pagePar) {
        this.pagePar = pagePar;
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }
    
    public void deplace() {
        Point2D p = new Point2D(getPos());
        p.translate(1,1);
    }
    public void affiche() {
        System.out.println("Le monstre est situé " + getPos()+".");
        System.out.println("Le monstre a " + getPtVie()+" de points de vie.");
    }
}
