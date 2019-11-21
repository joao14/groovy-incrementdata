package com.pichincha.marketing.model;

import java.io.Serializable;

public class InClienteResponse implements Serializable {

    private String primer_nombre;
    private String monto_actual;
    private String monto_minimo;
    private String monto_maximo;
    private String monto_sugerido;
    private String email;
    private String telefono;
    private String hashCallmeback;


    public InClienteResponse() {
    }

    public InClienteResponse(String primer_nombre, String monto_actual, String monto_minimo, String monto_maximo, String monto_sugerido, String email,String telefono,String hashCallmeback) {
        this.primer_nombre = primer_nombre;
        this.monto_actual = monto_actual;
        this.monto_minimo = monto_minimo;
        this.monto_maximo = monto_maximo;
        this.monto_sugerido = monto_sugerido;
        this.email = email;
        this.telefono=telefono;
        this.hashCallmeback=hashCallmeback;
    }

    public String getPrimer_nombre() {
        return primer_nombre;
    }

    public void setPrimer_nombre(String primer_nombre) {
        this.primer_nombre = primer_nombre;
    }

    public String getMonto_actual() {
        return monto_actual;
    }

    public void setMonto_actual(String monto_actual) {
        this.monto_actual = monto_actual;
    }

    public String getMonto_minimo() {
        return monto_minimo;
    }

    public void setMonto_minimo(String monto_minimo) {
        this.monto_minimo = monto_minimo;
    }

    public String getMonto_maximo() {
        return monto_maximo;
    }

    public void setMonto_maximo(String monto_maximo) {
        this.monto_maximo = monto_maximo;
    }

    public String getMonto_sugerido() {
        return monto_sugerido;
    }

    public void setMonto_sugerido(String monto_sugerido) {
        this.monto_sugerido = monto_sugerido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {return telefono;}

    public void setTelefono(String telefono) {this.telefono = telefono;}

    public String getHashCallmeback() {return hashCallmeback;}

    public void setHashCallmeback(String hashCallmeback) {this.hashCallmeback = hashCallmeback;}
}
