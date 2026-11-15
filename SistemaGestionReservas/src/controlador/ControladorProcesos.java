/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import modelo.GestorHotel;
import vista.PanelInicio;
import javax.swing.JButton;
/**
 *
 * @author maria
 */
public class ControladorProcesos {

    private GestorHotel gestor;
    private PanelInicio vista;
    private JButton btnEjecutar;
    private boolean procesosActivos;

    public ControladorProcesos(GestorHotel gestor,
            PanelInicio vista, JButton btnEjecutar) {
        this.gestor = gestor;
        this.vista = vista;
        this.btnEjecutar = btnEjecutar;
        procesosActivos = false;
    }
    public void ejecutarProcesos() {

        if (procesosActivos) {
            return;
        }
        procesosActivos = true;
        btnEjecutar.setEnabled(false);

        Thread hiloSupervisor = new Thread(
                new SupervisorReservas(gestor),
                "SupervisorReservas");

        Thread hiloIndicadores = new Thread(
                new ActualizadorIndicadores(
                        gestor, vista, hiloSupervisor, this),
                "ActualizadorIndicadores");

        System.out.println("Iniciando procesos concurrentes.");
        hiloSupervisor.start();
        hiloIndicadores.start();
    }
    public void finalizarProcesos() {
        vista.actualizarIndicadores();
        btnEjecutar.setEnabled(true);
        procesosActivos = false;
        System.out.println("Ambos procesos finalizaron.");
    }
}