/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;
import modelo.GestorHotel;
import modelo.Reserva;
import vista.PanelSimulacion;
import java.time.format.DateTimeParseException;


/**
 *
 * @author UTN
 */
public class ControladorSimulacion {
    
    private final PanelSimulacion vista;
    private final GestorHotel gestor= GestorHotel.getInstancia();

    public ControladorSimulacion(PanelSimulacion vista){
        this.vista = vista;
        vista.getBtnSimular().addActionListener(e -> simular());
    }
    
    private void simular(){
    try{
     LocalDate fecha= LocalDate.parse(vista.getTxtFechaReferencia().getText().trim());
     DefaultTableModel modelo= (DefaultTableModel) vista.getTblSimulacion().getModel();
     modelo.setRowCount(0); //borra todas las filas de las tablas
    
    for(Reserva r: gestor.simularVencimientos(fecha)){
    modelo.addRow(new Object[]{r.getCodigo(), r.getHuesped().getNombreCompleto(),
     r.getFechaEntrada(), "Vencida"});
     }
  }catch(DateTimeParseException ex) {
    vista.mostrarMensaje("Escriba la fecha correctamente");
}
} 
    
    
}
