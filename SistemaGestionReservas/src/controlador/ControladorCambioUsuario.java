/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import javax.swing.JOptionPane;
import modelo.GestorHotel;
import vista.FrmHotel;
import vista.FrmLogin;


/**
 *
 * @author UTN
 */
public class ControladorCambioUsuario {
    
    private final FrmHotel vista;
    private final GestorHotel gestor= GestorHotel.getInstancia();

    public ControladorCambioUsuario(FrmHotel vista) {
        this.vista = vista;
        vista.getBtnCambiarUsuario().addActionListener(e -> cambiarUsuario());
    }
    public void cambiarUsuario(){
        int respuesta= JOptionPane.showConfirmDialog(vista,//muestra los botones si y no
        "Para ingresar con otro usuario debe cerrar la sesion actual,¿Desea continuar?",
        "Cambiar usuario", JOptionPane.YES_NO_OPTION);
        
        if(respuesta == JOptionPane.YES_OPTION){
            gestor.cerrarSesion();
            vista.dispose();
            new FrmLogin().setVisible(true);
        }
    }
    
    
    
}
