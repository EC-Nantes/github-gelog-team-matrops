/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.centralenantes.projettp;
import java.util.Scanner;

/**
 *
 * @author selli
 */
public class TestNumberFormatException {
    public static void main(String[] args){
        Archer a = new Archer();
        try{
            a.declaAge();
        } catch (NumberFormatException e) {
            System.out.println("❌ Erreur : vous devez entrer un nombre pour l'âge !");
        } 
    }
}


/*//Proposition d'une méthode contenant des blocs try/catch/finally permettant de gérer l'exception
Scanner scanner = new Scanner(System.in);

System.out.print("Entrez le nom de votre personnage : ");
String nom = scanner.nextLine();

System.out.print("Entrez l'âge de votre personnage (en nombre) : ");
String saisieAge = scanner.nextLine(); 

try {
    int age = Integer.parseInt(saisieAge); 
    System.out.println("Personnage créé : " + nom + ", " + age + " ans !");
} catch (NumberFormatException e) {
    System.out.println("❌ Erreur : vous devez entrer un nombre pour l'âge !");
    System.out.println("Valeur invalide saisie : " + saisieAge);
} finally {
    System.out.println("Bonne partie !");
}

scanner.close();
*/