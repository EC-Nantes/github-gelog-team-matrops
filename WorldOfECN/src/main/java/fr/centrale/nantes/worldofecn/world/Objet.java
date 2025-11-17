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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kwyhr
 */
public class Objet extends ElementDeJeu {

    private static final String OBJETPOTIONDESOIN = "Potion de Soin";
    private static final String OBJETPOTIONDUGUERRIER = "Potion du Guerrier";
    private static final String OBJETPOTIONDEMAGIE = "Potion de Magie";
    private static final String OBJETPOTIONDEMAGE = "Potion de Mage";
    private static final String OBJETEPEEDEMONIAQUE = "Epée démoniaque";
    private static final String OBJETARCENCHANTE = "Arc enchanté";
    private static final String OBJETARBRE = "Arbre";
    private static final String OBJETROCHER = "Rocher";
    private static final String OBJETNUAGETOXIQUE = "Nuage toxique";

    private String type;

    private static List<String> typesList;
    /**
     *
     */
    public static void init() {
        typesList = new ArrayList<String>();
        typesList.add(OBJETPOTIONDESOIN);
        typesList.add(OBJETPOTIONDUGUERRIER);
        typesList.add(OBJETPOTIONDEMAGIE);
        typesList.add(OBJETPOTIONDEMAGE);
        typesList.add(OBJETEPEEDEMONIAQUE);
        typesList.add(OBJETARCENCHANTE);
        typesList.add(OBJETARBRE);
        typesList.add(OBJETROCHER);
        typesList.add(OBJETNUAGETOXIQUE);
    }

    /**
     *
     * @return
     */
    public static int getNbTypes() {
        init();
        if (typesList != null) {
            return typesList.size();
        }
        return 0;
    }

    /**
     *
     * @param type
     * @return
     */
    public static String intToType(int type) {
        init();
        if (type < typesList.size()) {
            return typesList.get(type);
        }
        return "";
    }

    /**
     *
     * @param world
     */
    public Objet(World world) {
        super(world);
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * Can any creature walk on this object
     * @return 
     */
    public boolean canWalkOn() {
        switch (this.getType()) {
            case OBJETARBRE :
            case OBJETROCHER :
                return false;
            default :
                return true;
        }
    }

    @Override
    public Integer saveToDatabase(Connection connection) {
        Integer id = -1;
        try{
            String query = "Insert into objet(type,x,y) Values(?,?,?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,this.getType());
            stmt.setInt(2, this.getPosition().getX());
            stmt.setInt(3, this.getPosition().getY());
            stmt.executeUpdate();
            query = "Select id_objet_fixe from objet order by id_objet_fixe DESC Limit 1";
            stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int idsauv = rs.getInt("id_objet_fixe");

            
            return idsauv;
        }catch (SQLException ex) {
                Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
            }
        return id;
    }

    @Override
    public void getFromDatabase(Connection connection, Integer id) {
    }

    @Override
    public void removeFromDatabase(Connection connection, Integer id) {
    }
}
