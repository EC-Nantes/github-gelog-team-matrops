/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.projettp;

/**
 *
 * @author selli
 */
public class TestWoE {
    public static void main(String[] args) {
        World w = new World();
        w.creerMondeAlea();
        
        w.getGuillaumeT().setNom("GuillaumeT");
        w.setGuillaumeT(new Archer(w.getRobin()));
        
        w.afficheWorld();
        
        w.tourDeJeu();
        
        w.afficheWorld();
        
    }
}
