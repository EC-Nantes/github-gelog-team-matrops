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
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kwyhr
 */
@Entity
@Table(name = "sei_lead_consortium_industry")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeiLeadConsortiumIndustry implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_activity")
    private Integer idActivity;

    @JsonIgnore
    @JoinColumn(name = "id_activity")
    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    private Activity activity;

    @Column(name = "consortium_start_date")
    @Temporal(TemporalType.DATE)
    private Date consortiumStartDate;
    @Size(max = 256)
    @Column(name = "name_consortium")
    private String nameConsortium;
    @Size(max = 256)
    @Column(name = "title_project")
    private String titleProject;
    @Size(max = 256)
    @Column(name = "private_party_involved")
    private String privatePartyInvolved;
    @Column(name = "consortium_end_date")
    @Temporal(TemporalType.DATE)
    private Date consortiumEndDate;
    @Size(max = 256)
    @Column(name = "associated_publi_ref")
    private String associatedPubliRef;

    @JoinColumn(name = "type_consortium_id", referencedColumnName = "type_consortium_id")
    @ManyToOne(optional = false)
    private TypeConsortium typeConsortiumId;
}