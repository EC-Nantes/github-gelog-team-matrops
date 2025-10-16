/* --------------------------------------------------------------------------------
 * Projet HCERES
 * 
 * Gestion de données pour l'HCERES
 * 
 * Ecole Centrale Nantes - laboratoire CRTI
 * Avril 2021
 * L LETERTRE, S LIMOUX, JY MARTIN
 * -------------------------------------------------------------------------------- */
package org.centrale.hceres.items;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author kwyhr
 */
@Entity
@Table(name = "team_referent")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamReferent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "team_referent_id")
    private Integer teamReferentId;
    @Column(name = "team_referent_start")
    @Temporal(TemporalType.DATE)
    private Date teamReferentStart;
    @Column(name = "team_referent_end")
    @Temporal(TemporalType.DATE)
    private Date teamReferentEnd;
    @JoinColumn(name = "researcher_id", referencedColumnName = "researcher_id")
    @ManyToOne(optional = false)
    private Researcher researcherId;
    @JoinColumn(name = "team_id", referencedColumnName = "team_id")
    @ManyToOne(optional = false)
    private Team teamId;
}