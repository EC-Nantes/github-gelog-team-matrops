/* --------------------------------------------------------------------------------
 * WoE Tools
 * 
 * Ecole Centrale Nantes - Septembre 2022
 * Equipe pédagogique Informatique et Mathématiques
 * JY Martin
 * -------------------------------------------------------------------------------- */
package fr.centrale.nantes.worldofecn;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.centrale.nantes.worldofecn.world.World;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Manage database connectio, saves and retreive informations
 *
 * @author ECN
 */

public class DatabaseTools {

    private String login;
    private String password;
    private String url;
    private Connection connection;

    /**
     * Load infos
     */
    public DatabaseTools() {
        try {
            // Get Properties file
            ResourceBundle properties = ResourceBundle.getBundle(DatabaseTools.class.getPackage().getName() + ".database");

            // USE config parameters
            login = properties.getString("login");
            password = properties.getString("password");
            String server = properties.getString("server");
            String database = properties.getString("database");
            url = "jdbc:postgresql://" + server + "/" + database;

            // Mount driver
            Driver driver = DriverManager.getDriver(url);
            if (driver == null) {
                Class.forName("org.postgresql.Driver");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
            // If driver is not found, cancel url
            url = null;
        }
        this.connection = null;
    }

    /**
     * Get connection to the database
     */
    public void connect() {
        if ((this.connection == null) && (url != null) && (!url.isEmpty())) {
            try {
                this.connection = DriverManager.getConnection(url, login, password);
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Disconnect from database
     */
    public void disconnect() {
        if (this.connection != null) {
            try {
                this.connection.close();
                this.connection = null;
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * get Player ID
     *
     * @param nomJoueur : le login du joueur
     * @param password : le mot de passe du joueur
     * @return
     */
    public Integer getPlayerID(String nomJoueur, String password) {
        // TO BE DEFINED
        // retreive player ID according to his/her name (unique) and password from database
        // may return null if player is not found, the database ID if found.
        try {
            String query = "SELECT id_joueur FROM joueur WHERE nom = ? and motdepasse = ? ";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, nomJoueur);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs != null) {
                if (rs.next() == true) {
                    return (rs.getInt("id_joueur"));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }
        return null;
    }

    /**
     * save world as sauvegarde in database
     *
     * @param idJoueur : l'ID du joueur dans la BD
     * @param nomPartie : le nom de la partie
     * @param nomSauvegarde : le nom de la sauvegarde
     * @param monde: le monde à enregistrer
     */

    public void saveWorld(Integer idJoueur, String nomPartie, String nomSauvegarde, World monde) {
        // TO BE DEFINED

        // Create a new "partie" in database if it does not exists and link it to the player
        // Save partie's infos in the sauvegarde (height, width, ...) if necessary
        try{
         String query = "SELECT id_partie FROM partie WHERE nom = ?";
         PreparedStatement stmt = connection.prepareStatement(query) ;
         stmt.setString(1,"Partie 1"); 
         ResultSet rs = stmt.executeQuery(); 
         if (rs == null){ 
            query = "Insert INTO partie Values(id_partie = ? , id_joueur = ? , nom = ? , dimension_x = ?, dimension_x = ?)";
            stmt = connection.prepareStatement(query) ;
            stmt.setInt(1,2);
            stmt.setInt(2, idJoueur);
            stmt.setString(3,nomPartie);
            stmt.setInt(3,monde.getHeight());
            stmt.setInt(4,monde.getWidth());
            stmt.executeUpdate();
         }
         query = "SELECT id_partie FROM partie WHERE nom = ?";
         rs = stmt.executeQuery();
         int idpartie = rs.getInt("id_partie");
         

        // Create a new sauvegarde if it does not exist for the partie 
        //Update sauvegarde infos for the partie 
        // Remove existing elements de jeu for the sauvegarde 
        // Save world's elementdejeu in database
        query = "Select nomSauvegarde FROM sauvegarde WHERE nom = ?";
        stmt = connection.prepareStatement(query);
        stmt.setString(1, nomSauvegarde);
        rs = stmt.executeQuery();
        if (rs == null){
            query = "Insert INTO sauvegarde Values( id_partie = ? , nom = ? , date = ?)";
            stmt = connection.prepareStatement(query) ;
            //Id partie et sauvegarde choisis
            stmt.setInt(1, 2);
            stmt.setString(3,nomSauvegarde);
            stmt.executeUpdate();
            
        }
        
        
        
         
         // Save player infos and the player's creature infos for this partie
         
        }catch (SQLException ex) {
         Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE,null, ex); }
}
         

    /**
     * get world sauvegarde from database
     *
     * @param idJoueur
     * @param nomPartie
     * @param nomSauvegarde
     * @return monde
     */
    public World readWorld(Integer idJoueur, String nomPartie, String nomSauvegarde) {
        World monde = new World();
        // TO BE DEFINED

        // Retreive partie infos for the player
        // Retreive sauvegarde infos for the partie
        // Retreive world infos
        // Generate object world according to the infos
        // Retreive element de jeu from sauvegarde
        // Generate approprite objects
        // Link objects to the world
        // Associate player with the player's creature
        // Return created world
        return monde;
    }

    /**
     * remove world sauvegarde from database
     *
     * @param idJoueur
     * @param nomPartie
     * @param nomSauvegarde
     */
    public void removeWorld(Integer idJoueur, String nomPartie, String nomSauvegarde) {
        World monde = new World();
        // TO BE DEFINED

        // Retreive partie infos for the player
        // Retreive sauvegarde infos for the partie
        // remove elements de jeu linked to the sauvegarde
        // remove sauvegarde
        // remove if partie has no mode sauvegarde, remove partie
    }

}
