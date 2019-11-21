package com.pichincha.marketing.model;

import java.util.Date;

public class OtpResponse {
    private String codigo;
    private String mensaje;
    private String error;
    private Date fecha;

    public OtpResponse() {
    }

    public OtpResponse(String codigo, String mensaje, String error, Date fecha) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.error = error;
        this.fecha = fecha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
