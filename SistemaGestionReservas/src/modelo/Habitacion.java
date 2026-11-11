/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author maria
 */



public abstract class Habitacion {

    private final String numero;

    protected Habitacion(String numero) {
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public abstract int getCapacidadMaxima();

    public abstract int getTarifaPorNoche();

    public abstract String getTipo();

    @Override
    public String toString() {
        return numero + " - " + getTipo();
    }
}

