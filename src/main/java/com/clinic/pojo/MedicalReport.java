/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "medical_report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicalReport.findAll", query = "SELECT m FROM MedicalReport m"),
    @NamedQuery(name = "MedicalReport.findById", query = "SELECT m FROM MedicalReport m WHERE m.id = :id"),
    @NamedQuery(name = "MedicalReport.findBySyntomp", query = "SELECT m FROM MedicalReport m WHERE m.syntomp = :syntomp"),
    @NamedQuery(name = "MedicalReport.findByDiagnose", query = "SELECT m FROM MedicalReport m WHERE m.diagnose = :diagnose"),
    @NamedQuery(name = "MedicalReport.findByCreatedDate", query = "SELECT m FROM MedicalReport m WHERE m.createdDate = :createdDate")})
public class MedicalReport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "syntomp")
    private String syntomp;
    @Size(max = 100)
    @Column(name = "diagnose")
    private String diagnose;
    @Column(name = "createdDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @JoinColumn(name = "bill_id", referencedColumnName = "id")
    @ManyToOne
    private Bill billId;
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    @ManyToOne
    private Doctor doctorId;
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    @ManyToOne
    private Patient patientId;
    @OneToMany(mappedBy = "medicalreportId")
    @JsonIgnore
    private Collection<ReportDetail> reportDetailCollection;

    public MedicalReport() {
    }

    public MedicalReport(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSyntomp() {
        return syntomp;
    }

    public void setSyntomp(String syntomp) {
        this.syntomp = syntomp;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Bill getBillId() {
        return billId;
    }

    public void setBillId(Bill billId) {
        this.billId = billId;
    }

    public Doctor getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Doctor doctorId) {
        this.doctorId = doctorId;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
    }

    @XmlTransient
    public Collection<ReportDetail> getReportDetailCollection() {
        return reportDetailCollection;
    }

    public void setReportDetailCollection(Collection<ReportDetail> reportDetailCollection) {
        this.reportDetailCollection = reportDetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicalReport)) {
            return false;
        }
        MedicalReport other = (MedicalReport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clinic.pojo.MedicalReport[ id=" + id + " ]";
    }
    
}
