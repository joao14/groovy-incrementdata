package com.pichincha.marketing.dto;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author gamerino
 */
@Entity
@Table(name = "in_datastudio")
@XmlRootElement

public class InDatastudio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "daes_id")
    private Integer daesId;
    @Column(name = "daes_codicamp")
    private Character daesCodicamp;
    @Column(name = "daes_proveedor")
    private Character daesProveedor;
    @Column(name = "daes_agenventa")
    private String daesAgenventa;
    @Column(name = "daes_identificacion")
    private String daesIdentificacion;
    @Column(name = "daes_nombre")
    private String daesNombre;
    @Column(name = "daes_tipoahorr")
    private String daesTipoahorr;
    @Column(name = "daes_cuenahorrfutu")
    private String daesCuenahorrfutu;
    @Column(name = "daes_cuendebiahorrcorr")
    private Character daesCuendebiahorrcorr;
    @Column(name = "daes_montbasemadr")
    private String daesMontbasemadr;
    @Column(name = "daes_valoherra")
    private Character daesValoherra;
    @Column(name = "daes_valoincre")
    private String daesValoincre;
    @Column(name = "daes_valototal")
    private String daesValototal;
    @Column(name = "daes_cambfech")
    private Character daesCambfech;
    @Column(name = "daes_fechincr")
    private Character daesFechincr;
    @Column(name = "daes_obsecallcent")
    private Character daesObsecallcent;
    @Column(name = "daes_usuario1")
    private Character daesUsuario1;
    @Column(name = "daes_usuario2")
    private Character daesUsuario2;
    @Column(name = "daes_fechcontr")
    private Character daesFechcontr;
    @Column(name = "daes_obsecontr")
    private Character daesObsecontr;
    @Basic(optional = false)
    @Column(name = "daes_bacaid")
    private Integer daesBacaid;
    @Generated(GenerationTime.INSERT)
    @Basic(optional = false)
    @Column(name = "daes_fechagest")
    @Temporal(TemporalType.TIMESTAMP)
    private Date daesFechagest;

    public Integer getDaesBacaid() {
        return daesBacaid;
    }

    public void setDaesBacaid(Integer daesBacaid) {
        this.daesBacaid = daesBacaid;
    }

    public InDatastudio() {
    }

    public InDatastudio(Integer daesId) {
        this.daesId = daesId;
    }

    public Integer getDaesId() {
        return daesId;
    }

    public void setDaesId(Integer daesId) {
        this.daesId = daesId;
    }

    public Character getDaesCodicamp() {
        return daesCodicamp;
    }

    public void setDaesCodicamp(Character daesCodicamp) {
        this.daesCodicamp = daesCodicamp;
    }

    public Character getDaesProveedor() {
        return daesProveedor;
    }

    public void setDaesProveedor(Character daesProveedor) {
        this.daesProveedor = daesProveedor;
    }

    public String getDaesAgenventa() {
        return daesAgenventa;
    }

    public void setDaesAgenventa(String daesAgenventa) {
        this.daesAgenventa = daesAgenventa;
    }

    public String getDaesIdentificacion() {
        return daesIdentificacion;
    }

    public void setDaesIdentificacion(String daesIdentificacion) {
        this.daesIdentificacion = daesIdentificacion;
    }

    public String getDaesNombre() {
        return daesNombre;
    }

    public void setDaesNombre(String daesNombre) {
        this.daesNombre = daesNombre;
    }

    public String getDaesTipoahorr() {
        return daesTipoahorr;
    }

    public void setDaesTipoahorr(String daesTipoahorr) {
        this.daesTipoahorr = daesTipoahorr;
    }

    public String getDaesCuenahorrfutu() {
        return daesCuenahorrfutu;
    }

    public void setDaesCuenahorrfutu(String daesCuenahorrfutu) {
        this.daesCuenahorrfutu = daesCuenahorrfutu;
    }

    public Character getDaesCuendebiahorrcorr() {
        return daesCuendebiahorrcorr;
    }

    public void setDaesCuendebiahorrcorr(Character daesCuendebiahorrcorr) {
        this.daesCuendebiahorrcorr = daesCuendebiahorrcorr;
    }

    public String getDaesMontbasemadr() {
        return daesMontbasemadr;
    }

    public void setDaesMontbasemadr(String daesMontbasemadr) {
        this.daesMontbasemadr = daesMontbasemadr;
    }

    public Character getDaesValoherra() {
        return daesValoherra;
    }

    public void setDaesValoherra(Character daesValoherra) {
        this.daesValoherra = daesValoherra;
    }

    public String getDaesValoincre() {
        return daesValoincre;
    }

    public void setDaesValoincre(String daesValoincre) {
        this.daesValoincre = daesValoincre;
    }

    public String getDaesValototal() {
        return daesValototal;
    }

    public void setDaesValototal(String daesValototal) {
        this.daesValototal = daesValototal;
    }

    public Character getDaesCambfech() {
        return daesCambfech;
    }

    public void setDaesCambfech(Character daesCambfech) {
        this.daesCambfech = daesCambfech;
    }

    public Character getDaesFechincr() {
        return daesFechincr;
    }

    public void setDaesFechincr(Character daesFechincr) {
        this.daesFechincr = daesFechincr;
    }

    public Character getDaesObsecallcent() {
        return daesObsecallcent;
    }

    public void setDaesObsecallcent(Character daesObsecallcent) {
        this.daesObsecallcent = daesObsecallcent;
    }

    public Character getDaesUsuario1() {
        return daesUsuario1;
    }

    public void setDaesUsuario1(Character daesUsuario1) {
        this.daesUsuario1 = daesUsuario1;
    }

    public Character getDaesUsuario2() {
        return daesUsuario2;
    }

    public void setDaesUsuario2(Character daesUsuario2) {
        this.daesUsuario2 = daesUsuario2;
    }

    public Character getDaesFechcontr() {
        return daesFechcontr;
    }

    public void setDaesFechcontr(Character daesFechcontr) {
        this.daesFechcontr = daesFechcontr;
    }

    public Character getDaesObsecontr() {
        return daesObsecontr;
    }

    public void setDaesObsecontr(Character daesObsecontr) {
        this.daesObsecontr = daesObsecontr;
    }

    public Date getDaesFechagest() {
        return daesFechagest;
    }

    public void setDaesFechagest(Date daesFechagest) {
        this.daesFechagest = daesFechagest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (daesId != null ? daesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InDatastudio)) {
            return false;
        }
        InDatastudio other = (InDatastudio) object;
        if ((this.daesId == null && other.daesId != null) || (this.daesId != null && !this.daesId.equals(other.daesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades2.InDatastudio[ daesId=" + daesId + " ]";
    }
    

}
