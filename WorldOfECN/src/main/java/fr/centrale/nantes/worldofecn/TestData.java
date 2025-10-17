/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.centrale.nantes.worldofecn;
import fr.centrale.nantes.worldofecn.world.World;


/**
 *
 * @author selli
 */
public class TestData {
    public static void main(String[] args){
        
        //Initialisation de la base
        DatabaseTools base = new DatabaseTools();
        base.connect();
        Integer tx = base.getPlayerID("Admin","admin");
        if (tx != null) {
                System.out.println("ID du joueur Admin : " + tx);
            } else {
                System.out.println("Joueur non trouvé ou identifiants incorrects.");
            }
        
        //Initialisation du monde et tests des méthodes de génération d'éléments
        World monde = new World();
        monde.generate();
        
        //Test de saveWorld
        base.removeWorld(tx, "bla", "bla");
        base.saveWorld(tx, "monde1", "Sauvegarde1", monde);
        System.out.println("L'identifiant de la sauvegarde est .");//TOBEFIXED?
        
        //Test de readWorld
        base.readWorld(tx,"monde1","Sauvegarde1");
        
        //Test de remove World
        //base.removeWorld(tx, "monde1", "Sauvegarde1");
        
        //Déconnection de la base
        base.disconnect();
        System.out.println(tx);
    }
}
//saveWorld(String idJoueur, String nomPartie, String nomSauvegarde, World monde) 
//private int readIdPartie(String idjoueur,String nomPartie)
//public void removeWorld(String idJoueur, String nomPartie, String nomSauvegarde) 
//public World readWorld(String idJoueur, String nomPartie, String nomSauvegarde) {