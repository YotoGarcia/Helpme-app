package co.edu.iudigital.helmelud.security;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthorityJsonCreator {

    public SimpleGrantedAuthorityJsonCreator(@JsonProperty("authority") String role){

    }
}
