/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author maria
 */

public class HabitacionIndividual extends Habitacion {

    public HabitacionIndividual(String numero) {
        super(numero);
    }

    @Override
    public int getCapacidadMaxima() {
        return 1;
    }

    @Override
    public int getTarifaPorNoche() {
        return 25000;
    }

    @Override
    public String getTipo() {
        return "Individual";
    }
}

