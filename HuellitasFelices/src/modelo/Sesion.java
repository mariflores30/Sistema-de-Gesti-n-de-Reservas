/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author maria
 */
public class Sesion {
    private static Sesion instancia;

    private String usuario;
    private String rol;
    private boolean activa;

    private Sesion() {
        activa = false;
    }

    public static Sesion getInstancia() {
        if (instancia == null) {
            instancia = new Sesion();
        }
        return instancia;
    }

    public void iniciarSesion(String usuario, String rol) {
        if (activa == false) {
            this.usuario = usuario;
            this.rol = rol;
            activa = true;
        }
    }

    public void cerrarSesion() {
        usuario = null;
        rol = null;
        activa = false;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getRol() {
        return rol;
    }

    public boolean getActiva() {
        return activa;
    }
}

