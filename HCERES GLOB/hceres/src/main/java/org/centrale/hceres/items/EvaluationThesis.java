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
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kwyhr
 */
@Entity
@Table(name = "evaluation_thesis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationThesis implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id_activity")
    private Integer idActivity;

    @JsonIgnore
    @JoinColumn(name = "id_activity")
    @MapsId
    @OneToOne(cascade = CascadeType.ALL)
    private Activity activity;

    @Column(name = "year")
    private Integer year;
    @Size(max = 256)
    @Column(name = "name_university")
    private String nameUniversity;

    @JoinColumn(name = "evaluation_thesis_role_id", referencedColumnName = "evaluation_thesis_role_id")
    @ManyToOne(optional = false)
    private EvaluationThesisRole evaluationThesisRoleId;
    @JoinColumn(name = "type_thesis_id", referencedColumnName = "type_thesis_id")
    @ManyToOne(optional = false)
    private TypeThesis typeThesisId;
}