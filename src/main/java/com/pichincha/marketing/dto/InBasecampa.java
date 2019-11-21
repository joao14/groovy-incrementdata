package com.pichincha.marketing.dto;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gamerino
 */
@Entity
@Table(name = "in_basecampa")
@XmlRootElement
public class InBasecampa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "baca_id")
    private Integer bacaId;
    @Column(name = "baca_descripcion")
    private String bacaDescripcion;
    @Column(name = "baca_shorname")
    private String bacaShorname;
    @Column(name = "baca_creado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bacaCreado;
    @OneToMany(mappedBy = "bacaId")
    private List<InCliente> inClienteList;

    public InBasecampa() {
    }

    public InBasecampa(Integer bacaId) {
        this.bacaId = bacaId;
    }

    public Integer getBacaId() {
        return bacaId;
    }

    public void setBacaId(Integer bacaId) {
        this.bacaId = bacaId;
    }

    public String getBacaDescripcion() {
        return bacaDescripcion;
    }

    public void setBacaDescripcion(String bacaDescripcion) {
        this.bacaDescripcion = bacaDescripcion;
    }

    public String getBacaShorname() {
        return bacaShorname;
    }

    public void setBacaShorname(String bacaShorname) {
        this.bacaShorname = bacaShorname;
    }

    public Date getBacaCreado() {
        return bacaCreado;
    }

    public void setBacaCreado(Date bacaCreado) {
        this.bacaCreado = bacaCreado;
    }

    @XmlTransient
    public List<InCliente> getInClienteList() {
        return inClienteList;
    }

    public void setInClienteList(List<InCliente> inClienteList) {
        this.inClienteList = inClienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bacaId != null ? bacaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InBasecampa)) {
            return false;
        }
        InBasecampa other = (InBasecampa) object;
        if ((this.bacaId == null && other.bacaId != null) || (this.bacaId != null && !this.bacaId.equals(other.bacaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.InBasecampa[ bacaId=" + bacaId + " ]";
    }

}
