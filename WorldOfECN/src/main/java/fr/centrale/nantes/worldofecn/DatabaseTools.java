/* --------------------------------------------------------------------------------
 * WoE Tools
 * 
 * Ecole Centrale Nantes - Septembre 2022
 * Equipe pédagogique Informatique et Mathématiques
 * JY Martin
 * -------------------------------------------------------------------------------- */
package fr.centrale.nantes.worldofecn;

import fr.centrale.nantes.worldofecn.world.Creature;
import fr.centrale.nantes.worldofecn.world.ElementDeJeu;
import fr.centrale.nantes.worldofecn.world.Joueur;
import fr.centrale.nantes.worldofecn.world.Monstre;
import fr.centrale.nantes.worldofecn.world.Objet;
import fr.centrale.nantes.worldofecn.world.Personnage;
import fr.centrale.nantes.worldofecn.world.Point2D;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.centrale.nantes.worldofecn.world.World;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    public void saveWorld(int idJoueur, String nomPartie, String nomSauvegarde, World monde) {
        // TO BE DEFINED

        // Create a new "partie" in database if it does not exists and link it to the player
        // Save partie's infos in the sauvegarde (height, width, ...) if necessary
        try {
            String query = "SELECT id_partie FROM partie WHERE nom = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, nomPartie);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                query = "Insert INTO partie(id_joueur, nom, dimension_x , dimension_y ) Values(? , ? , ?, ?)";
                stmt = connection.prepareStatement(query);
                //stmt.setInt(1, 10);
                stmt.setInt(1, idJoueur);
                stmt.setString(2, nomPartie);
                stmt.setInt(3, monde.getHeight());
                stmt.setInt(4, monde.getWidth());
                stmt.executeUpdate();
            }
            query = "SELECT id_partie FROM partie WHERE nom = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, nomPartie);
            rs = stmt.executeQuery();
            rs.next();
            int idpartie = rs.getInt("id_partie");

            // Create a new sauvegarde if it does not exist for the partie 
            // Update sauvegarde infos for the partie 
            // Remove existing elements de jeu for the sauvegarde 
            // Save world's elementdejeu in database
            query = "Select nom,id_sauv FROM sauvegarde WHERE nom = ? and id_partie = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, nomSauvegarde);
            stmt.setInt(2, idpartie);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int idsauv = rs.getInt("id_sauv");
                query = "DELETE From sauvegarde WHERE nom = ? and id_partie = ?";
                stmt = connection.prepareStatement(query);
                stmt.setString(1, nomSauvegarde);
                stmt.setInt(2, idpartie);
                stmt.executeUpdate();
                //date 3 
                query = "Insert INTO sauvegarde(id_partie, nom,id_sauv)  Values(? , ? , ?)";
                stmt = connection.prepareStatement(query);
                stmt.setInt(1, idpartie);
                stmt.setString(2, nomSauvegarde);
                //stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                stmt.setInt(3, idsauv);
                stmt.executeUpdate();
            } else {
                query = "Insert INTO sauvegarde(id_partie, nom)  Values(? , ?)";
                stmt = connection.prepareStatement(query);
                stmt.setInt(1, idpartie);
                stmt.setString(2, nomSauvegarde);
                //stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                stmt.executeUpdate();
            }

            query = "Select id_sauv FROM sauvegarde WHERE nom = ? and id_partie = ?";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, nomSauvegarde);
            stmt.setInt(2, idpartie);
            rs = stmt.executeQuery();
            rs.next();
            int idsauv = rs.getInt("id_sauv");

            // Save player infos and the player's creature infos for this partie
            monde.saveToDatabase(connection, idpartie, nomSauvegarde, idJoueur);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            // Retreive partie infos for the player
            String query = "Select id_sauv FROM partie inner join sauvegarde using (id_partie) WHERE id_joueur = ? and partie.nom = ? and sauvegarde.nom = ? ";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, idJoueur);
            stmt.setString(2, nomPartie);
            stmt.setString(3, nomSauvegarde);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int idsauv = rs.getInt("id_sauv");
                // Retreive sauvegarde infos for the partie
                // Retreive world infos
                // Generate object world according to the infos

                // Linked list car on add() complexité O(1)
                List<ElementDeJeu> listElements = new LinkedList<ElementDeJeu>();
                List<Point2D> positions = new LinkedList<Point2D>();
                // Retreive element de jeu from sauvegarde
                query = "Select joueur.nom, joueur.motdepasse,id_creature from joueur inner join association using (id_joueur) inner join sauvegarde using(id_sauv) where joueur.id_joueur = ? and sauvegarde.nom = ?";
                stmt = connection.prepareStatement(query);
                stmt.setInt(1, idJoueur);
                stmt.setString(2, nomSauvegarde);
                rs = stmt.executeQuery();
                rs.next();
                Joueur player = new Joueur(rs.getString("nom"));
                player.setLogin(rs.getString("nom"));
                player.setPassword(rs.getString("motdepasse"));
                int id_creaturej = rs.getInt("id_creature");
                query = "Select * from objet where id_sauv = ?";
                stmt = connection.prepareStatement(query);
                stmt.setInt(1, idsauv);
                rs = stmt.executeQuery();
                Objet o = new Objet(monde);

                if (rs != null) {
                    while (rs.next() == true) {
                        // Generate approprite objects
                        o.setType(rs.getString("type"));
                        o.setPosition(rs.getInt("x"), rs.getInt("y"));
                        listElements.add(o);
                        positions.add(new Point2D(rs.getInt("x"), rs.getInt("y")));
                        rs.next();

                    }
                }
                query = "Select * from creature where id_sauv = ?";
                stmt = connection.prepareStatement(query);
                stmt.setInt(1, idsauv);
                rs = stmt.executeQuery();
                Monstre m = new Monstre(monde);
                if (rs != null) {
                    while (rs.next() == true) {
                        if (m.getRacesList().contains(rs.getString("race"))) {
                            m.setRace(rs.getString("race"));
                            m.setPosition(new Point2D(rs.getInt("x"), rs.getInt("y")));
                            listElements.add(m);
                            positions.add(new Point2D(rs.getInt("x"), rs.getInt("y")));

                        }
                        rs.next();
                    }
                }

                query = "Select creature.id_creature,pageatt,race,dmax,x,y,ptvie,metier,ppar,nbPar,ptmagmax,ptmag,dmax FROM humanoide Natural join creature Where id_sauv = ? ";
                stmt = connection.prepareStatement(query);
                stmt.setInt(1, idsauv);
                rs = stmt.executeQuery();
                Personnage p = new Personnage(monde);
                if (rs != null) {
                    while (rs.next() == true) {
                        if (rs.getString("metier").equals(" Archer")) {
                            p.init(rs.getString("race"), rs.getString("metier"), rs.getFloat("ppar"), rs.getFloat("nbPar"), rs.getFloat("ptmagmax"), rs.getFloat("ptmag"), rs.getFloat("dmax"), rs.getInt("nbfleches"));
                            p.setPosition(rs.getInt("x"), rs.getInt("y"));
                            listElements.add(p);
                            positions.add(new Point2D(rs.getInt("x"), rs.getInt("y")));
                            if (rs.getInt("id_creature") == id_creaturej) {
                                player.setPersonnage(p);
                            }
                        } else {
                            p.init(rs.getString("race"), rs.getString("metier"), rs.getFloat("ppar"), rs.getFloat("nbPar"), rs.getFloat("ptmagmax"), rs.getFloat("ptmag"), rs.getFloat("dmax"), 0);
                            p.setPosition(rs.getInt("x"), rs.getInt("y"));
                            listElements.add(p);
                            positions.add(new Point2D(rs.getInt("x"), rs.getInt("y")));
                        }

                        rs.next();
                    }
                }

                // Associate player with the player's creature
                monde.setPositions(positions);
                monde.setListElements(listElements);
                monde.setPlayer(player);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        monde = readWorld(idJoueur, nomPartie, nomSauvegarde);

        // TO BE DEFINED
        // Retreive partie infos for the player
        // Retreive sauvegarde infos for the partie
        // remove elements de jeu linked to the sauvegarde
        // remove sauvegarde
        // remove if partie has no mode sauvegarde, remove partie
        try {
            String query = "Select partie.id_partie, id_sauv From partie inner join sauvegarde USING (id_partie) where id_joueur = ? and partie.nom = ? and sauvegarde.nom = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, idJoueur);
            stmt.setString(2, nomPartie);
            stmt.setString(3, nomSauvegarde);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int idpartie = rs.getInt("id_partie");
            int idsauv = rs.getInt("id_sauv");

            query = "Select id_creature from creature where id_sauv = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idsauv);
            rs = stmt.executeQuery();
            rs.next();
            List<Integer> idDesCreaList = new ArrayList<>();
            rs = stmt.executeQuery();
            while (rs.next()) {
                idDesCreaList.add(rs.getInt("id_creature"));
            }
            Array idDesCrea = connection.createArrayOf("INTEGER", idDesCreaList.toArray());
            //Suppresion de la créature du joueur 
            query = "Delete from archer where id_creature = ANY(?)";
            stmt = connection.prepareStatement(query);
            stmt.setArray(1,idDesCrea);
            stmt.executeUpdate();

            //Suppression des Archers (dans une autre table)
            query = "Delete from archer where id_creature = ANY(?)";
            stmt = connection.prepareStatement(query);
            stmt.setArray(1, (Array) idDesCrea);
            stmt.executeUpdate();

            //Suppression de tout les humanoides
            query = "Delete from humanoide where id_creature = ANY(?) ";
            stmt = connection.prepareStatement(query);
            stmt.setArray(1, (Array) idDesCrea);
            stmt.executeUpdate();

            // Suppression de toutes les créatures
            query = "Delete from creature where (id_sauv = ? )  ";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idsauv);
            stmt.executeUpdate();

            //Suppression de tout les objets
            query = "Delete from objet where (id_sauv = ? ) ";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idsauv);
            stmt.executeUpdate();

            query = "Delete from sauvegarde where id_sauv = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idsauv);
            stmt.executeUpdate();
            query = "Select id_sauv from sauvegarde where id_partie = ?";
            stmt = connection.prepareStatement(query);
            stmt.setInt(1, idpartie);
            rs = stmt.executeQuery();
            if (rs == null) {
                query = "Delete from partie where id_partie = ?";
                stmt = connection.prepareStatement(query);
                stmt.setInt(1, idpartie);
                stmt.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
