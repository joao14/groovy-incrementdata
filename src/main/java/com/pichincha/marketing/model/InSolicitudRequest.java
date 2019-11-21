package com.pichincha.marketing.model;

import java.io.Serializable;

public class InSolicitudRequest implements Serializable {
    private String hash;
    private String monto;
    private String otp;

    public InSolicitudRequest() {
    }

    public InSolicitudRequest(String hash, String monto, String otp) {
        this.hash = hash;
        this.monto = monto;
        this.otp = otp;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

}
