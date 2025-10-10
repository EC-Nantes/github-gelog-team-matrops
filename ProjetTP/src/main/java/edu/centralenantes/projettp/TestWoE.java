/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.projettp;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author selli
 */
public class TestWoE {
    public static void main(String[] args) {
        
        //Création du monde
        World w = new World();
        w.creerMondeAlea();
        w.getGuillaumeT().setNom("GuillaumeT");
        w.setGuillaumeT(new Archer(w.getRobin()));
        
        //Création du conteneur
        Conteneur l = new Conteneur();
        
        //Ajout de 100 personnages dont les positions sont deux à deux distinctes dans le conteneur
        for (int i =0;i<100;i++){
            LinkedList<Point2D> positions = new LinkedList<>();
            
            if (i%5==0){
                Archer a = new Archer();
                Point2D pos = a.getPos();
                Random alea=new Random();
                while (positions.contains(pos)){
                    int n1=alea.nextInt(10);
                    int n2=alea.nextInt(10);
                    a.setPos(new Point2D(n1,n2));
                    pos=a.getPos();
                }
                l.ajoute(a);
                positions.add(pos);
                a.affiche();
            }
            if (i%5==1){
                Guerrier g = new Guerrier();
                Point2D pos = g.getPos();
                Random alea=new Random();
                while (positions.contains(pos)){
                    int n1=alea.nextInt(10);
                    int n2=alea.nextInt(10);
                    g.setPos(new Point2D(n1,n2));
                    pos=g.getPos();
                }
                l.ajoute(g);
                positions.add(pos);
                g.affiche();
            }
            if (i%5==2){
                Lapin a = new Lapin();
                Point2D pos = a.getPos();
                Random alea=new Random();
                while (positions.contains(pos)){
                    int n1=alea.nextInt(10);
                    int n2=alea.nextInt(10);
                    a.setPos(new Point2D(n1,n2));
                    pos=a.getPos();
                }
                l.ajoute(a);
                positions.add(pos);
                a.affiche();
            }
            if (i%5==3){
                Loup a = new Loup();
                Point2D pos = a.getPos();
                Random alea=new Random();
                while (positions.contains(pos)){
                    int n1=alea.nextInt(10);
                    int n2=alea.nextInt(10);
                    a.setPos(new Point2D(n1,n2));
                    pos=a.getPos();
                }
                l.ajoute(a);
                positions.add(pos);
                a.affiche();
            }
            if (i%5==4){
                Paysan a = new Paysan();
                Point2D pos = a.getPos();
                Random alea=new Random();
                while (positions.contains(pos)){
                    int n1=alea.nextInt(10);
                    int n2=alea.nextInt(10);
                    a.setPos(new Point2D(n1,n2));
                    pos=a.getPos();
                }
                l.ajoute(a);
                positions.add(pos);
                a.affiche();
            }
        }

        //Test des fonctions parcours() et pdvtot()
        l.parcours();
        l.pdvtot();
        
    }
}
