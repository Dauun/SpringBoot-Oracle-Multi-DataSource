package com.test.app.model;

public class Login {
	
    private String nombre;

    private Integer exito;

    private String mensaje;

    public Login() {
    }

    public Login(String nombre, Integer exito, String mensaje) {
        this.nombre = nombre;
        this.exito = exito;
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getExito() {
        return exito;
    }

    public void setExito(Integer exito) {
        this.exito = exito;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}