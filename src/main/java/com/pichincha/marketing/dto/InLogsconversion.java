package com.pichincha.marketing.dto;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author gamerino
 */
@Entity
@Table(name = "in_logsconversion")
@XmlRootElement
public class InLogsconversion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "loco_id")
    private Integer locoId;
    @Basic(optional = false)
    @Column(name = "loco_identificacion")
    private String locoIdentificacion;
    @Column(name = "loco_hash")
    private String locoHash;
    @Basic(optional = false)
    @Column(name = "loco_nombapell")
    private String locoNombapell;
    @Basic(optional = false)
    @Column(name = "loco_estado")
    private Character locoEstado;
    @Basic(optional = false)
    @Column(name = "loco_pantalla")
    private String locoPantalla;
    @Generated(GenerationTime.INSERT)
    @Basic(optional = false)
    @Column(name = "loco_fechingr")
    @Temporal(TemporalType.TIMESTAMP)
    private Date locoFechingr;
    @JoinColumn(name = "baca_id", referencedColumnName = "baca_id")
    @ManyToOne
    private InBasecampa bacaId;

    public InLogsconversion() {
    }

    public InLogsconversion(Integer locoId) {
        this.locoId = locoId;
    }

    public InLogsconversion(Integer locoId, String locoIdentificacion, String locoNombapell, String locoPantalla, Character locoEstado, Date locoFechingr) {
        this.locoId = locoId;
        this.locoIdentificacion = locoIdentificacion;
        this.locoPantalla = locoPantalla;
        this.locoNombapell = locoNombapell;
        this.locoEstado = locoEstado;
        this.locoFechingr = locoFechingr;
    }

    public Integer getLocoId() {
        return locoId;
    }

    public void setLocoId(Integer locoId) {
        this.locoId = locoId;
    }

    public String getLocoIdentificacion() {
        return locoIdentificacion;
    }

    public void setLocoIdentificacion(String locoIdentificacion) {
        this.locoIdentificacion = locoIdentificacion;
    }

    public String getLocoHash() {
        return locoHash;
    }

    public void setLocoHash(String locoHash) {
        this.locoHash = locoHash;
    }

    public String getLocoNombapell() {
        return locoNombapell;
    }

    public void setLocoNombapell(String locoNombapell) {
        this.locoNombapell = locoNombapell;
    }

    public String getLocoPantalla() {
        return locoPantalla;
    }

    public void setLocoPantalla(String locoPantalla) {
        this.locoPantalla = locoPantalla;
    }

    public Character getLocoEstado() {
        return locoEstado;
    }

    public void setLocoEstado(Character locoEstado) {
        this.locoEstado = locoEstado;
    }

    public Date getLocoFechingr() {
        return locoFechingr;
    }

    public void setLocoFechingr(Date locoFechingr) {
        this.locoFechingr = locoFechingr;
    }

    public InBasecampa getBacaId() {
        return bacaId;
    }

    public void setBacaId(InBasecampa bacaId) {
        this.bacaId = bacaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locoId != null ? locoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InLogsconversion)) {
            return false;
        }
        InLogsconversion other = (InLogsconversion) object;
        if ((this.locoId == null && other.locoId != null) || (this.locoId != null && !this.locoId.equals(other.locoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades2.InLogsconversion[ locoId=" + locoId + " ]";
    }

}
