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
@Table(name = "sei_network_unit_creation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeiNetworkUnitCreation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_activity")
    private Integer idActivity;

    @JsonIgnore
    @JoinColumn(name = "id_activity")
    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    private Activity activity;

    @Column(name = "network_start_date")
    @Temporal(TemporalType.DATE)
    private Date networkStartDate;
    @Size(max = 256)
    @Column(name = "name_network")
    private String nameNetwork;
    @Size(max = 256)
    @Column(name = "name_partner")
    private String namePartner;
    @Size(max = 256)
    @Column(name = "title_project")
    private String titleProject;
    @Column(name = "network_end_date")
    @Temporal(TemporalType.DATE)
    private Date networkEndDate;
    @Size(max = 256)
    @Column(name = "associated_publi_ref")
    private String associatedPubliRef;

}