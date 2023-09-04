/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.pojo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "schedule_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScheduleDetail.findAll", query = "SELECT s FROM ScheduleDetail s"),
    @NamedQuery(name = "ScheduleDetail.findById", query = "SELECT s FROM ScheduleDetail s WHERE s.id = :id"),
    @NamedQuery(name = "ScheduleDetail.findByReason", query = "SELECT s FROM ScheduleDetail s WHERE s.reason = :reason"),
    @NamedQuery(name = "ScheduleDetail.findByDate", query = "SELECT s FROM ScheduleDetail s WHERE s.date = :date"),
    @NamedQuery(name = "ScheduleDetail.findByIsConfirm", query = "SELECT s FROM ScheduleDetail s WHERE s.isConfirm = :isConfirm")})
public class ScheduleDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 80)
    @Column(name = "reason")
    private String reason;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "is_confirm")
    private Short isConfirm;
    @Column(name = "is_cancel")
    private Short isCancel;
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    @ManyToOne
    private Doctor doctorId;
    @JoinColumn(name = "hour_id", referencedColumnName = "id")
    @ManyToOne
    private Hour hourId;
    @JoinColumn(name = "nurse_id", referencedColumnName = "id")
    @ManyToOne
    private Nurse nurseId;
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    @ManyToOne
    private Patient patientId;
    @JoinColumn(name = "register_patient_id", referencedColumnName = "id")
    @ManyToOne
    private Patient registerPatient;

    public ScheduleDetail() {
    }

    public ScheduleDetail(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Short getIsConfirm() {
        return isConfirm;
    }

    public void setIsConfirm(Short isConfirm) {
        this.isConfirm = isConfirm;
    }

    public Doctor getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Doctor doctorId) {
        this.doctorId = doctorId;
    }

    public Hour getHourId() {
        return hourId;
    }

    public void setHourId(Hour hourId) {
        this.hourId = hourId;
    }

    public Nurse getNurseId() {
        return nurseId;
    }

    public void setNurseId(Nurse nurseId) {
        this.nurseId = nurseId;
    }

    public Patient getPatientId() {
        return patientId;
    }

    public void setPatientId(Patient patientId) {
        this.patientId = patientId;
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
        if (!(object instanceof ScheduleDetail)) {
            return false;
        }
        ScheduleDetail other = (ScheduleDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clinic.pojo.ScheduleDetail[ id=" + id + " ]";
    }

    /**
     * @return the isCancel
     */
    public Short getIsCancel() {
        return isCancel;
    }

    /**
     * @param isCancel the isCancel to set
     */
    public void setIsCancel(Short isCancel) {
        this.isCancel = isCancel;
    }

    /**
     * @return the registerPatient
     */
    public Patient getRegisterPatient() {
        return registerPatient;
    }

    /**
     * @param registerPatient the registerPatient to set
     */
    public void setRegisterPatient(Patient registerPatient) {
        this.registerPatient = registerPatient;
    }
    
}
