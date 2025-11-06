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
import java.util.List;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author kwyhr
 */
@Entity
@Table(name = "phd_student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhdStudent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "phd_student_id")
    private Integer phdStudentId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "phd_start")
    @Temporal(TemporalType.DATE)
    private Date phdStart;
    @Size(max = 256)
    @Column(name = "phd_main_funding")
    private String phdMainFunding;
    @Column(name = "phd_defense_date")
    @Temporal(TemporalType.DATE)
    private Date phdDefenseDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "phd_duration")
    private int phdDuration;
    @Size(max = 2147483647)
    @Column(name = "phd_futur")
    private String phdFutur;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phdStudentId")
    private List<Supervisor> supervisorList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "phdStudent")
    private List<PhdAssociatedCompany> phdAssociatedCompanyList;
    @JoinColumn(name = "phd_type_id", referencedColumnName = "phd_type_id")
    @ManyToOne(optional = false)
    private PhdType phdTypeId;
    @JoinColumn(name = "researcher_id", referencedColumnName = "researcher_id")
    @ManyToOne(optional = false)
    private Researcher researcherId;

    public Integer getPhdStudentId() {
        return phdStudentId;
    }

    public void setPhdStudentId(Integer phdStudentId) {
        this.phdStudentId = phdStudentId;
    }

    public Date getPhdStart() {
        return phdStart;
    }

    public void setPhdStart(Date phdStart) {
        this.phdStart = phdStart;
    }

    public String getPhdMainFunding() {
        return phdMainFunding;
    }

    public void setPhdMainFunding(String phdMainFunding) {
        this.phdMainFunding = phdMainFunding;
    }

    public Date getPhdDefenseDate() {
        return phdDefenseDate;
    }

    public void setPhdDefenseDate(Date phdDefenseDate) {
        this.phdDefenseDate = phdDefenseDate;
    }

    public int getPhdDuration() {
        return phdDuration;
    }

    public void setPhdDuration(int phdDuration) {
        this.phdDuration = phdDuration;
    }

    public String getPhdFutur() {
        return phdFutur;
    }

    public void setPhdFutur(String phdFutur) {
        this.phdFutur = phdFutur;
    }

    public List<Supervisor> getSupervisorList() {
        return supervisorList;
    }

    public void setSupervisorList(List<Supervisor> supervisorList) {
        this.supervisorList = supervisorList;
    }

    public List<PhdAssociatedCompany> getPhdAssociatedCompanyList() {
        return phdAssociatedCompanyList;
    }

    public void setPhdAssociatedCompanyList(List<PhdAssociatedCompany> phdAssociatedCompanyList) {
        this.phdAssociatedCompanyList = phdAssociatedCompanyList;
    }

    public PhdType getPhdTypeId() {
        return phdTypeId;
    }

    public void setPhdTypeId(PhdType phdTypeId) {
        this.phdTypeId = phdTypeId;
    }

    public Researcher getResearcherId() {
        return researcherId;
    }

    public void setResearcherId(Researcher researcherId) {
        this.researcherId = researcherId;
    }
    
}