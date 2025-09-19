package org.centrale.projet.objet.projettp2;
import java.util.Random;
import java.util.Set;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Max
 */
public class Monstre {

    private int ptVie;
    private int degAtt;
    private int ptPar;
    private int pageAtt;
    private int pagePar;
    private Point2D pos;
    // Création d'un monstre à partir de tout les paramètres
    public void Monstre(int pV, int dA, int pPar, int paAtt, int paPar, Point2D p) {
        this.ptVie = pV;
        this.degAtt = dA;
        this.ptPar = pPar;
        this.pageAtt = paAtt;
        this.pagePar = paPar;
        this.pos = new Point2D(p);

    }
    // Création d'un monstre en recopie
    public void Monstre(Monstre m) {
        this.ptVie = m.ptVie;
        this.degAtt = m.degAtt;
        this.ptPar = m.ptPar;
        this.pageAtt = m.pageAtt;
        this.pagePar = m.pagePar;
        this.pos = new Point2D(m.pos);
    }
    // Création d'un monstre null
    public void Monstre(){
        this.ptVie = 0;
        this.degAtt = 0;
        this.ptPar = 0;
        this.pageAtt = 0;
        this.pagePar = 0;
        this.pos = new Point2D();
    }
    // Recuperer les point de vie
    public int getPtVie() {
        return ptVie;
    }
    // Modifier les points de vie
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
    
    
    public void affiche(){
        System.out.println(toString());
        
    }
    
    public String toString(){
        String s = "Le monstre a " + this.ptVie + "points de vie" + "\n" +"Le monstre inflige " + this.degAtt + "points de dégât"+ "\n" + "Le monstre a" + this.ptPar + "points de parade" + "\n" + "Le monstre a " + this.pageAtt + "% de chance d'attaquer"+ "\n" + "Le monstre a " + this.pagePar + "% de chance de parer"+"\n" + "Le monstre est aux coordonées" + this.pos.toString();
        return s;
        }
    public void deplace(){
        Random aleaInt = new Random();
        int aleaX = aleaInt.nextInt(3)-1;
        int aleaY = aleaInt.nextInt(3)-1;
        pos.translation(aleaX,aleaY);
        
    }
}
