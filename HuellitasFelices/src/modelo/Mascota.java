/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author maria
 */
public class Mascota {
    private String nombre; 
    private int raza;
    private double edad;
    
    public Mascota( String nombre, int raza, double edad){
        this.nombre=nombre;
        this.edad=edad;
        this.raza=raza;
    }

    public String getNombre() {
        return nombre;
    }

    public int getRaza() {
        return raza;
    }

    public double getEdad() {
        return edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setRaza(int raza) {
        this.raza = raza;
    }

    public void setEdad(double edad) {
        this.edad = edad;
    }
    private int calcularEdad(){
        return 0;
    }

    @Override
    public String toString() {
        return "Mascota{" + "nombre=" + nombre + ", raza=" + raza + ", edad=" + edad + '}';
    }
    
}

