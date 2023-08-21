/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.pojo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "report_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReportDetail.findAll", query = "SELECT r FROM ReportDetail r"),
    @NamedQuery(name = "ReportDetail.findById", query = "SELECT r FROM ReportDetail r WHERE r.id = :id"),
    @NamedQuery(name = "ReportDetail.findByQuantity", query = "SELECT r FROM ReportDetail r WHERE r.quantity = :quantity"),
    @NamedQuery(name = "ReportDetail.findByUsageInfo", query = "SELECT r FROM ReportDetail r WHERE r.usageInfo = :usageInfo")})
public class ReportDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 100)
    @Column(name = "quantity")
    private String quantity;
    @Size(max = 100)
    @Column(name = "usage_info")
    private String usageInfo;
    @JoinColumn(name = "medicalreport_id", referencedColumnName = "id")
    @ManyToOne
    private MedicalReport medicalreportId;
    @JoinColumn(name = "medicine_unit_id", referencedColumnName = "id")
    @ManyToOne
    private MedicineUnit medicineUnitId;

    public ReportDetail() {
    }

    public ReportDetail(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUsageInfo() {
        return usageInfo;
    }

    public void setUsageInfo(String usageInfo) {
        this.usageInfo = usageInfo;
    }

    public MedicalReport getMedicalreportId() {
        return medicalreportId;
    }

    public void setMedicalreportId(MedicalReport medicalreportId) {
        this.medicalreportId = medicalreportId;
    }

    public MedicineUnit getMedicineUnitId() {
        return medicineUnitId;
    }

    public void setMedicineUnitId(MedicineUnit medicineUnitId) {
        this.medicineUnitId = medicineUnitId;
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
        if (!(object instanceof ReportDetail)) {
            return false;
        }
        ReportDetail other = (ReportDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clinic.pojo.ReportDetail[ id=" + id + " ]";
    }
    
}
