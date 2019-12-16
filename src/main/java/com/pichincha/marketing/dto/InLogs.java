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
@Table(name = "in_logs")
@XmlRootElement
public class InLogs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "log_id")
    private Integer logId;
    @Basic(optional = false)
    @Column(name = "log_identificacion")
    private String logIdentificacion;
    @Basic(optional = false)
    @Column(name = "log_nombapell")
    private String logNombapell;
    @Basic(optional = false)
    @Column(name = "log_montante")
    private String logMontante;
    @Basic(optional = false)
    @Column(name = "log_montnuev")
    private String logMontnuev;
    @Basic(optional = false)
    @Column(name = "log_cuenahorfutu")
    private String logCuenahorfutu;
    @Basic(optional = false)
    @Column(name = "log_cuenorig")
    private String logCuenorig;
    @Basic(optional = false)
    @Column(name = "log_otp")
    private String logOtp;
    @Basic(optional = false)
    @Column(name = "log_ip")
    private String logIp;
    @Generated(GenerationTime.INSERT)
    @Basic(optional = false)
    @Column(name = "log_creado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logCreado; 

    public InLogs() {
    }

    public InLogs(Integer logId) {
        this.logId = logId;
    }

    public InLogs(String logIdentificacion, String logNombapell, String logMontante, String logMontnuev, String logCuenahorfutu, String logCuenorig, String logOtp, String logIp, Date logCreado) {
        this.logIdentificacion = logIdentificacion;
        this.logNombapell = logNombapell;
        this.logMontante = logMontante;
        this.logMontnuev = logMontnuev;
        this.logCuenahorfutu = logCuenahorfutu;
        this.logCuenorig = logCuenorig;
        this.logOtp = logOtp;
        this.logIp = logIp;
        this.logCreado = logCreado;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getLogIdentificacion() {
        return logIdentificacion;
    }

    public void setLogIdentificacion(String logIdentificacion) {
        this.logIdentificacion = logIdentificacion;
    }

    public String getLogNombapell() {
        return logNombapell;
    }

    public void setLogNombapell(String logNombapell) {
        this.logNombapell = logNombapell;
    }

    public String getLogMontante() {
        return logMontante;
    }

    public void setLogMontante(String logMontante) {
        this.logMontante = logMontante;
    }

    public String getLogMontnuev() {
        return logMontnuev;
    }

    public void setLogMontnuev(String logMontnuev) {
        this.logMontnuev = logMontnuev;
    }

    public String getLogCuenahorfutu() {
        return logCuenahorfutu;
    }

    public void setLogCuenahorfutu(String logCuenahorfutu) {
        this.logCuenahorfutu = logCuenahorfutu;
    }

    public String getLogCuenorig() {
        return logCuenorig;
    }

    public void setLogCuenorig(String logCuenorig) {
        this.logCuenorig = logCuenorig;
    }

    public String getLogOtp() {
        return logOtp;
    }

    public void setLogOtp(String logOtp) {
        this.logOtp = logOtp;
    }

    public String getLogIp() {
        return logIp;
    }

    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }

    public Date getLogCreado() {
        return logCreado;
    }

    public void setLogCreado(Date logCreado) {
        this.logCreado = logCreado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logId != null ? logId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InLogs)) {
            return false;
        }
        InLogs other = (InLogs) object;
        if ((this.logId == null && other.logId != null) || (this.logId != null && !this.logId.equals(other.logId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.InLogs[ logId=" + logId + " ]";
    }

}