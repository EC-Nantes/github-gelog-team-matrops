/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.centrale.hceres;

import java.sql.*;
import java.nio.file.*;
import java.io.IOException;

/**
 *
 * @author Max
 */
public class HceresAdminAdd {
    public static void main(String[] args) {
        // ⚙️ 1. Paramètres de connexion
        String url = "jdbc:postgresql://appli-pfe.ec-nantes.fr:5432/hceres";
        String user = "hceres";
        String password = "hceres";

        // 🗂️ 2. Fichier SQL à exécuter
        String filePath = "src/main/SQLdata/InitializeDummyData.sql"; // adapte le chemin

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            // 📄 3. Lecture du fichier SQL
            String sql = Files.readString(Path.of(filePath));

            // 🧠 4. Découper les requêtes si le fichier en contient plusieurs
            for (String query : sql.split(";")) {
                query = query.trim();
                if (!query.isEmpty()) {
                    stmt.execute(query);
                    System.out.println("✅ Exécuté : " + query);
                }
            }

            System.out.println("🎉 Toutes les requêtes ont été exécutées avec succès.");

        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier SQL : " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erreur SQL : " + e.getMessage());
        }
    }
    
}
