/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.projettp;

/**
 *
 * @author selli
 */
public class Point2D {

    private int x;
    private int y;

    Point2D() {
        x = 0;
        y = 0;
    }

    Point2D(int a, int b) {
        x = a;
        y = b;
    }

    Point2D(Point2D point) {
        x = point.x;
        y = point.y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void affiche() {
        System.out.println("Les coordonnées du points sont :" + "[" + getX() + ";" + getY() + "]");

    }
    
    public void translate(int m, int n) {
        setX(m + getX());
        setY(n + getY());
    }
    
    public void setPosition(int a, int b) {
        setX(a);
        setY(b);
    }
    
    public float distance(Point2D p) {
        float d = (float) Math.sqrt((x-p.getX())*(x-p.getX())+(y-p.getY())*(y-p.getY()));
        System.out.println(d);
        return d;
    }
}
