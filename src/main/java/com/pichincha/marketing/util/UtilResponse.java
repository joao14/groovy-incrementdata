package com.pichincha.marketing.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class UtilResponse {

    /**
     * Funcion que permite enmascarar el correo y validarlo
     *
     * @param email
     * @return
     */
    public static String enmascarar_email(String email) {
        //Valida como se visualizara el correo
        String[] email_ = email.split("@");
        String inicio = "", fin = "", newEma_ = "", char_ = "";
        if (email_[0].length() > 4) {
            inicio = email_[0].substring(0, 2);
            fin = email_[0].substring(Integer.valueOf(email_[0].length()) - 2, Integer.valueOf(email_[0].length()));
            for (int i = 2; i < email_[0].length() - 2; i++) {
                char_ = char_ + "*";
            }
            newEma_ = inicio + char_ + fin + "@" + email_[1];
        } else {
            inicio = email_[0].substring(0, 1);
            for (int i = 1; i < email_[0].length(); i++) {
                char_ = char_ + "*";
            }
            newEma_ = inicio + char_ + "@" + email_[1];
        }
        return newEma_;
    }

    /**
     * Funcion quqe permite enmascarar el telefono
     *
     * @param numero
     * @return
     */
    public static String enmascarar_cedula(String numero) {
        String inicio = numero.substring(0, 2);
        String fin = numero.substring(numero.length() - 2, numero.length());
        String character = "";
        for (int i = 2; i < numero.length() - 2; i++) {
            character = character + "*";
        }
        return inicio + "" + character + "" + fin;
    }

    /**
     * Funcion que permite obtener la fecha actual del sistema
     *
     * @return
     */
    public static Date getTimeStamp() {
        System.out.println("Ingresando get fecha??");
        Instant timeStamp = Instant.now();
        ZonedDateTime LAZone = timeStamp.atZone(ZoneId.of("America/Guayaquil"));
        Instant timeTemp = LAZone.toInstant();
        Date date = Date.from(timeTemp);
        return date;
    }

}
