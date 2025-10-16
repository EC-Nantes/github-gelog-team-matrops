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
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 * @author kwyhr
 */
@Entity
@Table(name = "publication_statistics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicationStatistics implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @EmbeddedId
    protected PublicationStatisticsPK publicationStatisticsPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "publication_statistics_pdc")
    private int publicationStatisticsPdc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "publication_statistics_collab_int")
    private int publicationStatisticsCollabInt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "publication_statistics_collab_labo")
    private int publicationStatisticsCollabLabo;
    @JoinColumn(name = "team_id", referencedColumnName = "team_id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Team team;
}