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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "shift")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Shift.findAll", query = "SELECT s FROM Shift s"),
    @NamedQuery(name = "Shift.findById", query = "SELECT s FROM Shift s WHERE s.id = :id"),
    @NamedQuery(name = "Shift.findByStartTime", query = "SELECT s FROM Shift s WHERE s.startTime = :startTime"),
    @NamedQuery(name = "Shift.findByEndTime", query = "SELECT s FROM Shift s WHERE s.endTime = :endTime")})
public class Shift implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 8)
    @Column(name = "start_time")
    private String startTime;
    @Size(max = 8)
    @Column(name = "end_time")
    private String endTime;
    @OneToMany(mappedBy = "shiftId")
    @JsonIgnore
    private Collection<DoctorShift> doctorShiftCollection;
    @OneToMany(mappedBy = "shiftId")
    @JsonIgnore
    private Collection<NurseShift> nurseShiftCollection;

    public Shift() {
    }

    public Shift(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @XmlTransient
    public Collection<DoctorShift> getDoctorShiftCollection() {
        return doctorShiftCollection;
    }

    public void setDoctorShiftCollection(Collection<DoctorShift> doctorShiftCollection) {
        this.doctorShiftCollection = doctorShiftCollection;
    }

    @XmlTransient
    public Collection<NurseShift> getNurseShiftCollection() {
        return nurseShiftCollection;
    }

    public void setNurseShiftCollection(Collection<NurseShift> nurseShiftCollection) {
        this.nurseShiftCollection = nurseShiftCollection;
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
        if (!(object instanceof Shift)) {
            return false;
        }
        Shift other = (Shift) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clinic.pojo.Shift[ id=" + id + " ]";
    }
    
}
