/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.time.LocalDate;
import modelo.GestorHotel;
import java.util.ArrayList;
import modelo.Habitacion;
import vista.PanelDisponibilidad;
import java.time.format.DateTimeParseException;


/**
 *
 * @author UTN
 */
public class ControladorDisponibilidad {
    
    private final PanelDisponibilidad vista;
    private final GestorHotel gestor=GestorHotel.getInstancia();

    public ControladorDisponibilidad(PanelDisponibilidad vista) {
        this.vista= vista;
        vista.getBtnConsultar().addActionListener(e -> consultar());
    }
    
    private void consultar(){
        try {
        LocalDate fecha = LocalDate.parse(vista.getTxtFechaConsulta().getText().trim());

        //aquí la cantidad se calcula con el metodo recursivo del gestor
        int cantidad = gestor.contarHabitacionesDisponibles(gestor.getHabitaciones(), gestor.getReservas(), fecha);
        vista.getLblCantidad().setText("" + cantidad);

        ArrayList<Habitacion> disponibles=gestor.obtenerHabitacionesDisponibles(fecha);
        String numeros= "";
        for(int i=0; i< disponibles.size();i++){
            numeros += disponibles.get(i).getNumero() + "  ";
        }
        vista.getTxaHabitaciones().setText(numeros);
    } catch(DateTimeParseException ex){ //atrapa cualquier error mientras todo se ejecuta
        vista.mostrarMensaje("Escriba la fecha correctamente");
    }
    }
    
    
    }


   
    
    
    
    

