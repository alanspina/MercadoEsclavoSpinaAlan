package com.example.mercadoesclavospinaalan.View;

import java.io.Serializable;
import java.util.ArrayList;

public class UserMF implements Serializable {

private Integer id;
private String nombre;
private String apellido;
private String username;
private String email;

    public UserMF(String nombre, String apellido, String username, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.username = username;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserMF(){
        
    }


}
