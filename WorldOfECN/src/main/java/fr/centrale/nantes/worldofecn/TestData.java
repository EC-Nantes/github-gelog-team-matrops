/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.centrale.nantes.worldofecn;

import fr.centrale.nantes.worldofecn.world.World;

/**
 *
 * @author Max
 */
public class TestData {
    public static void main(String[] args){
        DatabaseTools base = new DatabaseTools();
        World monde = new World();
        
        base.connect();
        base.saveWorld(-1, "Test 1", "Sauvegarde 2", monde);
        int tx = base.getPlayerID("admin","admin");
        monde = base.readWorld(tx, "Test 1", "1");
        //monde.affiche();
        //base.removeWorld(tx, "Test 1", "Sauvegarde 2");
        base.disconnect();
        System.out.println(tx);
    }
    
}
