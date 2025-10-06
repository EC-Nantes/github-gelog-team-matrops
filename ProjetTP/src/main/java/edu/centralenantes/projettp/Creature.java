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
public class Creature {
    private int ptVie;
    private int degAtt;
    private int ptPar;
    private int pageAtt;
    private int pagePar;
    private Point2D pos;

        
    public Creature(int pV,int dA,int pPar, int paAtt, int paPar, Point2D p){
        ptVie = pV;
        degAtt=dA;
        ptPar=pPar;
        pageAtt=paAtt;
        pagePar=paPar;
        pos=p;
    }
    
    public Creature(Creature m){
        ptVie=m.ptVie;
        degAtt=m.degAtt;
        ptPar=m.ptPar;
        pageAtt=m.pageAtt;
        pagePar=m.pagePar;
        pos=m.pos;
    }
    
    public Creature(){
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

        
    
    
    /**
     *
     */
    public void deplace() {
        Random alea=new Random();
        Point2D p = new Point2D(getPos());
        int n =alea.nextInt(7);
        if (n==0){
            p.translate(0,1);
        }
        if (n==1){
            p.translate(1,1);
        }
        if (n==2){
            p.translate(1,0);
        }
        if (n==3){
            p.translate(1,-1);
        }
        if (n==4){
            p.translate(0,-1);
        }
        if (n==5){
            p.translate(-1,-1);
        }
        if (n==6){
            p.translate(-1,0);
        }
        if (n==7){
            p.translate(-1,1);
        }
        setPos(p);
    }

}
