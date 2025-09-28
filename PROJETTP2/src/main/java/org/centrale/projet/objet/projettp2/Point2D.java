/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.centrale.projet.objet.projettp2;
import java.util.Random;
/**
 * @author Justine Sellier
 * @author Max Perron
 */
public class Point2D {

    /**
     *Création des coordonnées accesibles en privé x,y 
     */
    private int x;
    private int y;
    private Object math;

    /**
     *
     * @param x
     * @param y
     */
    public Point2D(int x, int y) {
        /**
         * Constructeur d'un point en 2D
         * entier x et y
         * Point au coordonées x,y
         */
        this.x = x;
        this.y = y;
    }
    
    /**
     *
     */
    public Point2D() {
        /**
         * Construction d'un point à l'origine (un choix)
         * Sans paramètres
         * Point au coordonnées [0;0]
         */
        Random aleaInt = new Random();
        this.x =  0;
        this.y =  0;
    }

    /**
     *
     * @param grille
     */
    public void randomPoint2D(int grille){
        Random aleaInt = new Random();
        this.x = aleaInt.nextInt(grille);
        this.y = aleaInt.nextInt(grille);
    }
    
    /**
     *
     * @return
     */
    public int getX() {
        return x;
    }
    
    /**
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @param x
     */
    public void setX(int x){
        this.x = x;
    }
    
    /**
     *
     * @param y
     */
    public void setY(int y){
        this.y = y;
    }
    
    /**
     *
     * @param x
     * @param y
     */
    public void setPosition(int x, int y) {
        setX(x);
        setY(y);
    }
    
    /**
     *
     */
    public void affiche(){
        // Possibilité d'afficher le nom du point ?
        // Par exemple : le point p2 est aux coordonnées...
    System.out.println("Le point est aux coordonées : [" + this.x + ";" + this.y + "]");
     }
    
    /**
     *
     * @return
     */
    public String toString(){
    String s = "[" + this.x + "," + this.y + "]";
    return s;
    }
    
    /**
     *
     * @param x
     * @param y
     */
    public void translation(int x, int y){
        this.x +=x;
        this.y +=y;
    }
    
    /**
     *
     * @param p
     */
    public Point2D(Point2D p){
        this.x = p.getX();
        this.y = p.getY();
    }
    /**
     * 
     * @param p Point2D comparaison de la distance à ce point
     * @return d une distance en entier
     */
    public int distance(Point2D p){
    int d;
    int dx,dy;
    dx = (this.x - p.getX())*(this.x - p.getX());
    dy = (this.y - p.getY())*(this.y - p.getY());
    d=(int) Math.sqrt(dx+dy);
    return d;
    }
}
