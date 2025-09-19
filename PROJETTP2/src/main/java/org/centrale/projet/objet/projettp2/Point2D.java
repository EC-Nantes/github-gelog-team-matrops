/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.centrale.projet.objet.projettp2;
import java.util.Random;
/**
 *
 * @author Max
 */
public class Point2D {

    /**
     *Création des coordonnées accesibles en privé x,y 
     */
    private int x;
    private int y;
    private Object math;

    public Point2D(int x, int y) {
        /**
         * Constructeur d'un point en 2D
         * entier x et y
         * Point au coordonées x,y
         */
        this.x = x;
        this.y = y;
    }
    
    public Point2D() {
        /**
         * Construction d'un point à l'origine (un choix)
         * Sans paramètres
         * Point au coordonnées [0;0]
         */
        Random aleaInt = new Random();
        this.x =  aleaInt.nextInt(10);
        this.y =  aleaInt.nextInt(10);
    }
    
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    public void setX(int x){
        this.x = x;
    }
    
    public void setY(int y){
        this.y = y;
    }
    
    
    public void setPosition(int x, int y) {
        setX(x);
        setY(y);
    }
    
    public void affiche(){
        // Possibilité d'afficher le nom du point ?
        // Par exemple : le point p2 est aux coordonnées...
    System.out.println("Le point est aux coordonées : [" + this.x + ";" + this.y + "]");
     }
    
    public String toString(){
    String s = "[" + this.x + "," + this.y + "]";
    return s;
    }
    
    public void translation(int x, int y){
        this.x +=x;
        this.y +=y;
    }
    
    
    public Point2D(Point2D p){
        this.x = p.getX();
        this.y = p.getY();
    }
}
