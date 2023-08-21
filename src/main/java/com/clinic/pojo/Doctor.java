/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.clinic.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "doctor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Doctor.findAll", query = "SELECT d FROM Doctor d"),
    @NamedQuery(name = "Doctor.findById", query = "SELECT d FROM Doctor d WHERE d.id = :id")})
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(mappedBy = "doctorId")
    @JsonIgnore
    private Collection<MedicalReport> medicalReportCollection;
    @OneToMany(mappedBy = "doctorId")
    @JsonIgnore
    private Collection<DoctorShift> doctorShiftCollection;
    @OneToMany(mappedBy = "doctorId")
    @JsonIgnore
    private Collection<ScheduleDetail> scheduleDetailCollection;
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    @ManyToOne
    private Department departmentId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;

    public Doctor() {
    }

    public Doctor(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public Collection<MedicalReport> getMedicalReportCollection() {
        return medicalReportCollection;
    }

    public void setMedicalReportCollection(Collection<MedicalReport> medicalReportCollection) {
        this.medicalReportCollection = medicalReportCollection;
    }

    @XmlTransient
    public Collection<DoctorShift> getDoctorShiftCollection() {
        return doctorShiftCollection;
    }

    public void setDoctorShiftCollection(Collection<DoctorShift> doctorShiftCollection) {
        this.doctorShiftCollection = doctorShiftCollection;
    }

    @XmlTransient
    public Collection<ScheduleDetail> getScheduleDetailCollection() {
        return scheduleDetailCollection;
    }

    public void setScheduleDetailCollection(Collection<ScheduleDetail> scheduleDetailCollection) {
        this.scheduleDetailCollection = scheduleDetailCollection;
    }

    public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof Doctor)) {
            return false;
        }
        Doctor other = (Doctor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clinic.pojo.Doctor[ id=" + id + " ]";
    }
    
}
