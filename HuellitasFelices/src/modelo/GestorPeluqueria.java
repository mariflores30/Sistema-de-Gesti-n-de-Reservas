/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author maria
 */
public class GestorPeluqueria {
    private String nombre; 
    private int ubicacion;
    private String direccion;
    
    public GestorPeluqueria(String nombre, int ubicacion, String direccion){
       this.nombre=nombre;
       this.direccion=direccion;
       this.ubicacion=ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getUbicacion() {
        return ubicacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUbicacion(int ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "GestorPeluqueria{" + "nombre=" + nombre + ", ubicacion=" + ubicacion + ", direccion=" + direccion + '}';
    }
   
    
}
