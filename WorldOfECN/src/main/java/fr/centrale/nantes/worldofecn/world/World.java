/* --------------------------------------------------------------------------------
 * WoE
 * 
 * Ecole Centrale Nantes - Septembre 2022
 * Equipe pédagogique Informatique et Mathématiques
 * JY Martin
 * -------------------------------------------------------------------------------- */
package fr.centrale.nantes.worldofecn.world;

import fr.centrale.nantes.worldofecn.DatabaseTools;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ECN
 */
public class World {

    private static final int MAXPEOPLE = 20;
    private static final int MAXMONSTERS = 10;
    private static final int MAXOBJECTS = 20;

    private Integer width;
    private Integer height;

    private int roundNo;
    private List<ElementDeJeu> listElements;
    private Joueur player;
    private List<Point2D> positions;
    private List<ElementDeJeu> roundElements;

    /**
     * Default constructor
     */
    public World() {
        this(20, 20);
    }

    /**
     * Constructor for specific world size
     *
     * @param width : world width
     * @param height : world height
     */
    public World(int width, int height) {
        this.setHeightWidth(height, width);
        init();
        generate();

        this.roundNo = 0;
        this.roundElements = null;
    }

    /**
     * Initialize elements
     */
    private void init() {
        this.listElements = new LinkedList<ElementDeJeu>();
        this.player = new Joueur("Player");
        this.positions = new ArrayList<Point2D>();
    }

    /**
     *
     * @return
     */
    public Integer getWidth() {
        return width;
    }

    /**
     *
     * @param width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     *
     * @return
     */
    public Integer getHeight() {
        return height;
    }

    /**
     *
     * @param height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     *
     * @param height
     * @param width
     */
    public final void setHeightWidth(Integer height, Integer width) {
        this.setHeight(height);
        this.setWidth(width);
    }

    /**
     * Check element can be created
     *
     * @param element
     * @return
     */
    private ElementDeJeu check(ElementDeJeu element) {
        return element;
    }

    /**
     * Generate personnages
     */
    private void generatePersonnages(int nbElements) {
        Random rand = new Random();
        for (int i = 0; i < nbElements; i++) {
            int race = rand.nextInt(Personnage.getNbRaces());
            String raceStr = Personnage.intToRace(race);

            int metier = rand.nextInt(Personnage.getNbMetiers());
            String metierStr = Personnage.intToMetier(metier);

            Personnage item = new Personnage(this);
            item.setRace(raceStr);
            item.setMetier(metierStr);

            // Add to list
            this.listElements.add(item);
            this.positions.add(item.getPosition());
        }
    }

    /**
     * Generate Monsters
     */
    private void generateMonsters(int nbElements) {
        Random rand = new Random();

        // Generate monsters
        for (int i = 0; i < nbElements; i++) {
            int race = rand.nextInt(Monstre.getNbRaces());
            String raceStr = Monstre.intToRace(race);

            Monstre item = new Monstre(this);
            this.listElements.add(item);
            this.positions.add(item.getPosition());
        }
    }

    /**
     * Generate Objects
     */
    private void generateObjects(int nbElements) {
        Random rand = new Random();

        // Generate objects
        for (int i = 0; i < nbElements; i++) {
            int type = rand.nextInt(Objet.getNbTypes());
            String typeStr = Objet.intToType(type);

            Objet item = new Objet(this);
            item.setType(typeStr);

            // Add to list
            this.listElements.add(item);
            this.positions.add(item.getPosition());
        }
    }

    /**
     * Generate Player
     */
    private void generatePlayer(int itemType) {
        Random rand = new Random();

        int race = rand.nextInt(5);
        String raceStr = Personnage.intToRace(race);

        int metier = rand.nextInt(Personnage.getNbMetiers());
        String metierStr = Personnage.intToMetier(metier);

        Personnage item = new Personnage(this);
        item.setRace(raceStr);
        item.setMetier(metierStr);

        // Add to list
        this.listElements.add(item);

        player.setPersonnage(item);
    }

    /**
     * Generate elements randomly
     */
    private void generate() {
        Random rand = new Random();

        generatePlayer(1);

        generatePersonnages(rand.nextInt(MAXPEOPLE));
        generateMonsters(rand.nextInt(MAXMONSTERS));
        generateObjects(rand.nextInt(MAXOBJECTS));
    }

    /**
     * Set Player name
     *
     * @param name
     */
    public void setPlayer(String name) {
        this.player.setNom(name);
    }

    /**
     * Return used positions
     *
     * @return
     */
    public List<Point2D> getPositions() {
        return positions;
    }

    /**
     * Remove element from the world
     *
     * @param elementdejeu
     */
    public void removeFromWorld(ElementDeJeu elementdejeu) {
        if (elementdejeu != null) {
            this.positions.remove(elementdejeu.getPosition());
            this.listElements.remove(elementdejeu);
            this.roundElements.remove(elementdejeu);
        }
    }

    /**
     * Go to next round
     */
    public void nextRound() {
        this.roundNo++;
        this.roundElements = new LinkedList<ElementDeJeu>();
        this.roundElements.addAll(this.listElements);
    }

    /**
     * Returns next element who has to play
     *
     * @return
     */
    public ElementDeJeu nextElementInRound() {
        ElementDeJeu nextElement = this.roundElements.getFirst();
        if (nextElement != null) {
            this.roundElements.removeFirst();
        }
        return nextElement;
    }

    /**
     * Save world to database
     *
     * @param connection
     * @param gameName
     * @param saveName
     */
    public void saveToDatabase(Connection connection, int idPartie, String saveName, int idJoueur) {
        if (connection != null) {
            try {
                // Get Player ID
                String query = "Select id_sauv from sauvegarde where id_partie = ? and nom = ?";
                PreparedStatement stmt = connection.prepareStatement(query);
                stmt.setInt(1, idPartie);
                stmt.setString(2, saveName);
                ResultSet rs = stmt.executeQuery();
                rs.next();
                int idsauv = rs.getInt("id_sauv");
                
                for (ElementDeJeu edj : listElements){
                        int id = edj.saveToDatabase(connection);
                        if (id != -1){
                        
                        if (edj instanceof Monstre || edj instanceof Personnage){
                                query = "Update creature SET id_sauv = ? Where id_creature = ?";
                                stmt = connection.prepareStatement(query);
                                stmt.setInt(1, idsauv);
                                stmt.setInt(2, id);
                                stmt.executeUpdate();
                                }
                        if (edj instanceof Objet){
                                query = "Update objet SET id_sauv = ? where id_objet_fixe = ?";
                                stmt = connection.prepareStatement(query);
                                stmt.setInt(1, idsauv);
                                stmt.setInt(2, id);
                                stmt.executeUpdate();
                        }
                        if (edj instanceof Personnage){
                            if (((Personnage)edj).equals(player.getPersonnage())){
                                query = "Insert into association(id_creature,id_joueur,id_sauv) Values(?,?,?)";
                                stmt = connection.prepareStatement(query);
                                stmt.setInt(1, id);
                                stmt.setInt(2, idJoueur);
                                stmt.setInt(3,idsauv);
                                stmt.executeUpdate();
                            }
                            
                        }
                        
                        }
                }
                System.out.println("L'injection a fonctionné ");
                // Save world for Player ID
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Get world from database
     *
     * @param connection
     * @param gameName
     * @param saveName
     */
    public void getFromDatabase(Connection connection, String gameName, String saveName) {
        if (connection != null) {
            // Remove old data
            this.setHeightWidth(0, 0);
            init();

            // Get Player ID
            // get world for Player ID
        }
    }

    public int getRoundNo() {
        return roundNo;
    }

    public List<ElementDeJeu> getListElements() {
        return listElements;
    }

    public Joueur getPlayer() {
        return player;
    }

    public void setRoundNo(int roundNo) {
        this.roundNo = roundNo;
    }

    public void setListElements(List<ElementDeJeu> listElements) {
        this.listElements = listElements;
    }

    public void setPlayer(Joueur player) {
        this.player = player;
    }

    public void setPositions(List<Point2D> positions) {
        this.positions = positions;
    }
    
    public void affiche(){
        for (ElementDeJeu edj : listElements){
            System.out.println(edj.toString());
        }
        System.out.println("Joueur " + player.toString());
    }
}
