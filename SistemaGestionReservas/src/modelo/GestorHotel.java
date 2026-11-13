/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.ArrayList;
/**
 *
 * @author maria
 */

public class GestorHotel {

    private static GestorHotel instancia;

    private ArrayList<Huesped> huespedes;
    private ArrayList<Habitacion> habitaciones;
    private ArrayList<Reserva> reservas;

    private String recepcionistaActivo;

    private GestorHotel() {

        huespedes = new ArrayList<>();
        habitaciones = new ArrayList<>();
        reservas = new ArrayList<>();

        recepcionistaActivo = null;
    }

    public static GestorHotel getInstancia() {

        if (instancia == null) {
            instancia = new GestorHotel();
        }

        return instancia;
    }

    public boolean iniciarSesion(String usuario, String contrasena) {

        if (recepcionistaActivo != null) {
            return false;
        }

        if (usuario == null || contrasena == null) {
            return false;
        }

        if (usuario.equals("recepcion1")
                && contrasena.equals("Hotel123")) {

            recepcionistaActivo = usuario;
            return true;

        } else if (usuario.equals("recepcion2")
                && contrasena.equals("Hotel456")) {

            recepcionistaActivo = usuario;
            return true;
        }

        return false;
    }

    public void cerrarSesion() {
        recepcionistaActivo = null;
    }

    public boolean haySesionActiva() {
        return recepcionistaActivo != null;
    }

    public String getRecepcionistaActivo() {
        return recepcionistaActivo;
    }

    public ArrayList<Huesped> getHuespedes() {
        return new ArrayList<>(huespedes);
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return new ArrayList<>(habitaciones);
    }

    public ArrayList<Reserva> getReservas() {
        return new ArrayList<>(reservas);
    }
}
