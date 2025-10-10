/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.projettp;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author selli
 */
public class Conteneur {
    
    LinkedList<Creature> liste;
    
    public LinkedList<Creature> getListe() {
        return liste;
    }

    public void setListe(LinkedList<Creature> liste) {
        this.liste = liste;
    } 
    
    
    public Conteneur(){
        this.liste= new LinkedList<Creature>();
    }
    
    public void ajoute(Creature c){
        this.liste.add(c);
    }
    
    public void parcours(){
        Iterator<Creature> listeIt1 = liste.iterator();
        while(listeIt1.hasNext()){
            Creature c = listeIt1.next();
            System.out.println(c.getClass().getSimpleName());
        }
    }
    
    public void pdvtot(){
        int t = 0;
        Iterator<Creature> listeIt2 = liste.iterator();
        while(listeIt2.hasNext()){
            Creature c = listeIt2.next();
            t+=c.getPtVie();
        }
        System.out.println("Le nombre total de points de vie de l'ensemble des personnages est "+t+".");
    }
}
