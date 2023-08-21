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
@Table(name = "nurse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nurse.findAll", query = "SELECT n FROM Nurse n"),
    @NamedQuery(name = "Nurse.findById", query = "SELECT n FROM Nurse n WHERE n.id = :id")})
public class Nurse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(mappedBy = "nurseId")
    @JsonIgnore
    private Collection<ScheduleDetail> scheduleDetailCollection;
    @OneToMany(mappedBy = "nurseId")
    @JsonIgnore
    private Collection<Bill> billCollection;
    @OneToMany(mappedBy = "nurseId")
    @JsonIgnore
    private Collection<NurseShift> nurseShiftCollection;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne
    private User userId;

    public Nurse() {
    }

    public Nurse(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public Collection<ScheduleDetail> getScheduleDetailCollection() {
        return scheduleDetailCollection;
    }

    public void setScheduleDetailCollection(Collection<ScheduleDetail> scheduleDetailCollection) {
        this.scheduleDetailCollection = scheduleDetailCollection;
    }

    @XmlTransient
    public Collection<Bill> getBillCollection() {
        return billCollection;
    }

    public void setBillCollection(Collection<Bill> billCollection) {
        this.billCollection = billCollection;
    }

    @XmlTransient
    public Collection<NurseShift> getNurseShiftCollection() {
        return nurseShiftCollection;
    }

    public void setNurseShiftCollection(Collection<NurseShift> nurseShiftCollection) {
        this.nurseShiftCollection = nurseShiftCollection;
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
        if (!(object instanceof Nurse)) {
            return false;
        }
        Nurse other = (Nurse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clinic.pojo.Nurse[ id=" + id + " ]";
    }

}
