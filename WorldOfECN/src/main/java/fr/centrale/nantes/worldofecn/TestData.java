/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.centrale.nantes.worldofecn;

/**
 *
 * @author Max
 */
public class TestData {
    public static void main(String[] args){
        DatabaseTools base = new DatabaseTools();
        base.connect();
        int tx = base.getPlayerID("Admin","admin");
        base.disconnect();
        System.out.println(tx);
    }
    
}
