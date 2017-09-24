package com.arquisix.sistemaminas.entities;

/**
 * Created by juanchaves on 24/09/17.
 */
public class Usuario {

    private Long id;
    private String rol;
    private String login;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario(Long id, String rol, String login, String password) {

        this.id = id;
        this.rol = rol;
        this.login = login;
        this.password = password;
    }
}
