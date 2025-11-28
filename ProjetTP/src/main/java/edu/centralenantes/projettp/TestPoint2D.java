/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.projettp;

/**
 *
 * @author selli
 */
public class TestPoint2D {
    public static void main(String[] args) {
        Point2D point1;
        Point2D point2;
        Point2D point3;
        
        //Test des constructeurs et de leur surcharge
        point1 = new Point2D();
        point2 = new Point2D(1,2);
        point3 = new Point2D(point1);
        
        //Test de la fonction affiche
        point1.affiche();
        point2.affiche();
        point3.affiche();
        
        //Test de la fonction translate
        point1.translate(1,2);
        point1.affiche();
        
        //Test de la fonction setPosition
        point1.setPosition(8,78);
        point1.affiche();
        
        //Test de la fonction distance
        point3.distance(point1);
    }
}
