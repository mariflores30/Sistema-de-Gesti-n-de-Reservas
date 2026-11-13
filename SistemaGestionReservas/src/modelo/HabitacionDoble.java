/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author maria
 */
    public class HabitacionDoble extends Habitacion {

    public HabitacionDoble(String numero) {
        super(numero);
    }

    @Override
    public int getCapacidadMaxima() {
        return 2;
    }

    @Override
    public int getTarifaPorNoche() {
        return 40000;
    }

    @Override
    public String getTipo() {
        return "Doble";
    }
}

