package com.pichincha.marketing.dto;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @author gamerino
 */
@Entity
@Table(name = "in_cliente")
@XmlRootElement
public class InCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "clie_id")
    private Integer clieId;
    @Column(name = "clie_hashiden")
    private String clieHashiden;
    @Basic(optional = false)
    @Column(name = "clie_identificacion")
    private String clieIdentificacion;
    @Basic(optional = false)
    @Column(name = "clie_idenenma")
    private String clieIdenenma;
    @Column(name = "clie_primnomb")
    private String cliePrimnomb;
    @Column(name = "clie_segunomb")
    private String clieSegunomb;
    @Column(name = "clie_primapel")
    private String cliePrimapel;
    @Column(name = "clie_seguapel")
    private String clieSeguapel;
    @Column(name = "clie_estacivi")
    private Character clieEstacivi;
    @Column(name = "clie_cuenahorfutu")
    private String clieCuenahorfutu;
    @Column(name = "clie_cuenorig")
    private String clieCuenorig;
    @Column(name = "clie_genero")
    private Character clieGenero;
    @Column(name = "clie_nacionalidad")
    private String clieNacionalidad;
    @Column(name = "clie_fechnaci")
    @Temporal(TemporalType.DATE)
    private Date clieFechnaci;
    @Column(name = "clie_luganaci")
    private String clieLuganaci;
    @Column(name = "clie_actiecon")
    private String clieActiecon;
    @Column(name = "clie_diredomi")
    private String clieDiredomi;
    @Column(name = "clie_diretrab")
    private String clieDiretrab;
    @Column(name = "clie_teledomi")
    private String clieTeledomi;
    @Column(name = "clie_teletrab")
    private String clieTeletrab;
    @Basic(optional = false)
    @Column(name = "clie_celular")
    private String clieCelular;
    @Basic(optional = false)
    @Column(name = "clie_email")
    private String clieEmail;
    @Column(name = "clie_contpers")
    private String clieContpers;
    @Column(name = "clie_montact")
    private String clieMontact;
    @Column(name = "clie_montsuge")
    private String clieMontsuge;
    @Column(name = "clie_montmaxi")
    private String clieMontmaxi;
    @Column(name = "clie_segmento")
    private Character clieSegmento;
    @Column(name = "clie_hashcmb")
    private String clieHashcmb;
    @Basic(optional = false)
    @Column(name = "clie_estado")
    private Character clieEstado;
    @Basic(optional = false)
    @Column(name = "clie_tipoahorfutu")
    private String clieTipoahorfutu;
    @Column(name = "clie_premio")
    private Character cliePremio;
    @JoinColumn(name = "baca_id", referencedColumnName = "baca_id")
    @ManyToOne
    private InBasecampa bacaId;
    @OneToMany(mappedBy = "clieId")
    private Collection<InSoliincr> inSoliincrCollection;

    public InCliente() {
    }

    public InCliente(Integer clieId) {
        this.clieId = clieId;
    }

    public InCliente(Integer clieId, String clieIdentificacion, String clieIdenenma, String clieCelular, String clieEmail, Character clieEstado , Character cliePremio) {
        this.clieId = clieId;
        this.clieIdentificacion = clieIdentificacion;
        this.clieIdenenma = clieIdenenma;
        this.clieCelular = clieCelular;
        this.clieEmail = clieEmail;
        this.clieEstado = clieEstado;
        this.cliePremio=cliePremio;
    }

    public Integer getClieId() {
        return clieId;
    }

    public void setClieId(Integer clieId) {
        this.clieId = clieId;
    }

    public String getClieHashiden() {
        return clieHashiden;
    }

    public void setClieHashiden(String clieHashiden) {
        this.clieHashiden = clieHashiden;
    }

    public String getClieIdentificacion() {
        return clieIdentificacion;
    }

    public void setClieIdentificacion(String clieIdentificacion) {
        this.clieIdentificacion = clieIdentificacion;
    }

    public String getClieIdenenma() {
        return clieIdenenma;
    }

    public void setClieIdenenma(String clieIdenenma) {
        this.clieIdenenma = clieIdenenma;
    }

    public String getCliePrimnomb() {
        return cliePrimnomb;
    }

    public void setCliePrimnomb(String cliePrimnomb) {
        this.cliePrimnomb = cliePrimnomb;
    }

    public String getClieSegunomb() {
        return clieSegunomb;
    }

    public void setClieSegunomb(String clieSegunomb) {
        this.clieSegunomb = clieSegunomb;
    }

    public String getCliePrimapel() {
        return cliePrimapel;
    }

    public void setCliePrimapel(String cliePrimapel) {
        this.cliePrimapel = cliePrimapel;
    }

    public String getClieSeguapel() {
        return clieSeguapel;
    }

    public void setClieSeguapel(String clieSeguapel) {
        this.clieSeguapel = clieSeguapel;
    }

    public Character getClieEstacivi() {
        return clieEstacivi;
    }

    public void setClieEstacivi(Character clieEstacivi) {
        this.clieEstacivi = clieEstacivi;
    }

    public String getClieCuenahorfutu() {
        return clieCuenahorfutu;
    }

    public void setClieCuenahorfutu(String clieCuenahorfutu) {
        this.clieCuenahorfutu = clieCuenahorfutu;
    }

    public String getClieCuenorig() {
        return clieCuenorig;
    }

    public void setClieCuenorig(String clieCuenorig) {
        this.clieCuenorig = clieCuenorig;
    }

    public Character getClieGenero() {
        return clieGenero;
    }

    public void setClieGenero(Character clieGenero) {
        this.clieGenero = clieGenero;
    }

    public String getClieNacionalidad() {
        return clieNacionalidad;
    }

    public void setClieNacionalidad(String clieNacionalidad) {
        this.clieNacionalidad = clieNacionalidad;
    }

    public Date getClieFechnaci() {
        return clieFechnaci;
    }

    public void setClieFechnaci(Date clieFechnaci) {
        this.clieFechnaci = clieFechnaci;
    }

    public String getClieLuganaci() {
        return clieLuganaci;
    }

    public void setClieLuganaci(String clieLuganaci) {
        this.clieLuganaci = clieLuganaci;
    }

    public String getClieActiecon() {
        return clieActiecon;
    }

    public void setClieActiecon(String clieActiecon) {
        this.clieActiecon = clieActiecon;
    }

    public String getClieDiredomi() {
        return clieDiredomi;
    }

    public void setClieDiredomi(String clieDiredomi) {
        this.clieDiredomi = clieDiredomi;
    }

    public String getClieDiretrab() {
        return clieDiretrab;
    }

    public void setClieDiretrab(String clieDiretrab) {
        this.clieDiretrab = clieDiretrab;
    }

    public String getClieTeledomi() {
        return clieTeledomi;
    }

    public void setClieTeledomi(String clieTeledomi) {
        this.clieTeledomi = clieTeledomi;
    }

    public String getClieTeletrab() {
        return clieTeletrab;
    }

    public void setClieTeletrab(String clieTeletrab) {
        this.clieTeletrab = clieTeletrab;
    }

    public String getClieCelular() {
        return clieCelular;
    }

    public void setClieCelular(String clieCelular) {
        this.clieCelular = clieCelular;
    }

    public String getClieEmail() {
        return clieEmail;
    }

    public void setClieEmail(String clieEmail) {
        this.clieEmail = clieEmail;
    }

    public String getClieContpers() {
        return clieContpers;
    }

    public void setClieContpers(String clieContpers) {
        this.clieContpers = clieContpers;
    }

    public String getClieMontact() {
        return clieMontact;
    }

    public void setClieMontact(String clieMontact) {
        this.clieMontact = clieMontact;
    }

    public String getClieMontsuge() {
        return clieMontsuge;
    }

    public void setClieMontsuge(String clieMontsuge) {
        this.clieMontsuge = clieMontsuge;
    }

    public String getClieMontmaxi() {
        return clieMontmaxi;
    }

    public void setClieMontmaxi(String clieMontmaxi) {
        this.clieMontmaxi = clieMontmaxi;
    }

    public Character getClieSegmento() {
        return clieSegmento;
    }

    public void setClieSegmento(Character clieSegmento) {
        this.clieSegmento = clieSegmento;
    }

    public String getClieHashcmb() {
        return clieHashcmb;
    }

    public void setClieHashcmb(String clieHashcmb) {
        this.clieHashcmb = clieHashcmb;
    }

    public Character getClieEstado() {
        return clieEstado;
    }

    public void setClieEstado(Character clieEstado) {
        this.clieEstado = clieEstado;
    }

    public Character getCliePremio() {
        return cliePremio;
    }

    public void setCliePremio(Character cliePremio) {
        this.cliePremio = cliePremio;
    }

    public InBasecampa getBacaId() {
        return bacaId;
    }

    public void setBacaId(InBasecampa bacaId) {
        this.bacaId = bacaId;
    }

    public String getClieTipoahorfutu() {
        return clieTipoahorfutu;
    }

    public void setClieTipoahorfutu(String clieTipoahorfutu) {
        this.clieTipoahorfutu = clieTipoahorfutu;
    }

    @XmlTransient
    public Collection<InSoliincr> getInSoliincrCollection() {
        return inSoliincrCollection;
    }

    public void setInSoliincrCollection(Collection<InSoliincr> inSoliincrCollection) {
        this.inSoliincrCollection = inSoliincrCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clieId != null ? clieId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InCliente)) {
            return false;
        }
        InCliente other = (InCliente) object;
        if ((this.clieId == null && other.clieId != null) || (this.clieId != null && !this.clieId.equals(other.clieId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.InCliente[ clieId=" + clieId + " ]";
    }

}

