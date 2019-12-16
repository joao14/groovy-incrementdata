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
@Table(name = "in_soliincr")
@XmlRootElement
public class InSoliincr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "soin_id")
    private Integer soinId;
    @Column(name = "soin_hashiden")
    private String soinHashiden;
    @Basic(optional = false)
    @Column(name = "soin_incracep")
    private Character soinIncracep;
    @Basic(optional = false)
    @Column(name = "soin_montacep")
    private String soinMontacep;
    @Basic(optional = false)
    @Column(name = "soin_otp")
    private String soinOtp;
    @Basic(optional = false)
    @Column(name = "soin_noapli")
    private Character soinNoapli;
    @Basic(optional = false)
    @Column(name = "soin_bacaid")
    private Integer soinBacaid;
    @Generated(GenerationTime.INSERT)
    @Basic(optional = false)
    @Column(name = "soin_fechhora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date soinFechhora;
    @JoinColumn(name = "clie_id", referencedColumnName = "clie_id")
    @ManyToOne
    private InCliente clieId;

    public InSoliincr() {
    }

    public InSoliincr(Integer soinId) {
        this.soinId = soinId;
    }

    public InSoliincr(Character soinIncracep, String soinMontacep, String soinOtp, Character soinNoapli, InCliente clieId,String soinHashiden,Integer soinBacaid) {
        this.soinIncracep = soinIncracep;
        this.soinMontacep = soinMontacep;
        this.soinOtp = soinOtp;
        this.soinNoapli = soinNoapli;
        this.clieId=clieId;
        this.soinHashiden=soinHashiden;
        this.soinBacaid=soinBacaid;
    }

    public Integer getSoinId() {
        return soinId;
    }

    public void setSoinId(Integer soinId) {
        this.soinId = soinId;
    }

    public String getSoinHashiden() {
        return soinHashiden;
    }

    public void setSoinHashiden(String soinHashiden) {
        this.soinHashiden = soinHashiden;
    }

    public Character getSoinIncracep() {
        return soinIncracep;
    }

    public void setSoinIncracep(Character soinIncracep) {
        this.soinIncracep = soinIncracep;
    }

    public String getSoinMontacep() {
        return soinMontacep;
    }

    public void setSoinMontacep(String soinMontacep) {
        this.soinMontacep = soinMontacep;
    }

    public String getSoinOtp() {
        return soinOtp;
    }

    public void setSoinOtp(String soinOtp) {
        this.soinOtp = soinOtp;
    }

    public Character getSoinNoapli() {
        return soinNoapli;
    }

    public void setSoinNoapli(Character soinNoapli) {
        this.soinNoapli = soinNoapli;
    }

    public Integer getSoinBacaid() {
        return soinBacaid;
    }

    public void setSoinBacaid(Integer soinBacaid) {
        this.soinBacaid = soinBacaid;
    }

    public Date getSoinFechhora() {
        return soinFechhora;
    }

    public void setSoinFechhora(Date soinFechhora) {
        this.soinFechhora = soinFechhora;
    }

    public InCliente getClieId() {
        return clieId;
    }

    public void setClieId(InCliente clieId) {
        this.clieId = clieId;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (soinId != null ? soinId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InSoliincr)) {
            return false;
        }
        InSoliincr other = (InSoliincr) object;
        if ((this.soinId == null && other.soinId != null) || (this.soinId != null && !this.soinId.equals(other.soinId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.InSoliincr[ soinId=" + soinId + " ]";
    }

}
