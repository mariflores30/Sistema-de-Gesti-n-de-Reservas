/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author maria
 */


public class Huesped extends Persona {

    private final String correoElectronico;

    public Huesped(String identificacion, String nombreCompleto,
            String telefono, String correoElectronico) {

        super(identificacion, nombreCompleto, telefono);

        this.correoElectronico = correoElectronico;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    @Override
    public String toString() {
        return getNombreCompleto() + " - " + getIdentificacion();
    }
}

