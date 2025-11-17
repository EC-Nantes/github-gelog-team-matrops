/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.projet.objet.projettp2;

import java.util.Set;

/**
 * @author Justine Sellier
 * @author Max Perron
 */
public class TestWoE {
    
    /**
     *
     * @param args Test de la classe World
     */
    public static void main(String[] args){
        World M = new World(); 
        M.creerMondeAlea();
        /**
         * Test copie
         * M.guillaumeT = new Archer(M.robin);
        System.out.println("Guillaume");
        M.guillaumeT.affiche();
        System.out.println("Robin");
        M.robin.affiche();
        
        M.robin.deplace();
        System.out.println("Guillaume");
        M.guillaumeT.affiche();
        System.out.println("Robin");
        M.robin.affiche();
        */
        System.out.println(M.guillaumeT.getPos());
        System.out.println(M.robin.getPos());
        System.out.println(M.guillaumeT.getPtVie());
        System.out.println(M.robin.getPtVie());
        M.robin.setPos(M.guillaumeT.getPos());
        M.robin.setNbFleches(3);
        M.robin.deplace(1,1);
        System.out.println(M.guillaumeT.getPos());
        System.out.println(M.robin.getPos());
        for(int i=0; i<10 ; i++){
            M.guillaumeT.combattre(M.robin);
            M.robin.combattre(M.guillaumeT);
            System.out.println(M.guillaumeT.getPtVie());
            System.out.println(M.robin.getPtVie());
        }
        
        M.robin.setPos(M.potS.getPos());
        M.robin.soin(M.potS);
        System.out.println(M.robin.getPtVie());
        System.out.println("Position : " + M.potS.getPos() + "Soin" + M.potS.getSoin());
        
        
        
        
        
        
        
    }
    
}
