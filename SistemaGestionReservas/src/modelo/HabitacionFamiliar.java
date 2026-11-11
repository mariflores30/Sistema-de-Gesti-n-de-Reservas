/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author maria
 */

public class HabitacionFamiliar extends Habitacion {

    public HabitacionFamiliar(String numero) {
        super(numero);
    }

    @Override
    public int getCapacidadMaxima() {
        return 4;
    }

    @Override
    public int getTarifaPorNoche() {
        return 65000;
    }

    @Override
    public String getTipo() {
        return "Familiar";
    }
}

