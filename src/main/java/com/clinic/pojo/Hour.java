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
@Table(name = "hour")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hour.findAll", query = "SELECT h FROM Hour h"),
    @NamedQuery(name = "Hour.findById", query = "SELECT h FROM Hour h WHERE h.id = :id"),
    @NamedQuery(name = "Hour.findByHour", query = "SELECT h FROM Hour h WHERE h.hour = :hour")})
public class Hour implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 8)
    @Column(name = "hour")
    private String hour;
    @OneToMany(mappedBy = "hourId")
    @JsonIgnore
    private Collection<ScheduleDetail> scheduleDetailCollection;

    public Hour() {
    }

    public Hour(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    @XmlTransient
    public Collection<ScheduleDetail> getScheduleDetailCollection() {
        return scheduleDetailCollection;
    }

    public void setScheduleDetailCollection(Collection<ScheduleDetail> scheduleDetailCollection) {
        this.scheduleDetailCollection = scheduleDetailCollection;
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
        if (!(object instanceof Hour)) {
            return false;
        }
        Hour other = (Hour) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.clinic.pojo.Hour[ id=" + id + " ]";
    }
    
}
