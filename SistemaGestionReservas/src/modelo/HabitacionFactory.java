/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author maria
 */
public class HabitacionFactory {

    public static Habitacion crearHabitacion(String numero, String tipo) {

        if (tipo == null || tipo.equals("")) {
            return null;
        }

        if (tipo.equalsIgnoreCase("Individual")) {
            return new HabitacionIndividual(numero);

        } else if (tipo.equalsIgnoreCase("Doble")) {
            return new HabitacionDoble(numero);

        } else if (tipo.equalsIgnoreCase("Familiar")) {
            return new HabitacionFamiliar(numero);
        }

        return null;
    }
}

