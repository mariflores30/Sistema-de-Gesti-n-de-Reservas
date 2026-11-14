/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import modelo.GestorHotel;
import vista.PanelInicio;
import java.awt.EventQueue;
/**
 *
 * @author maria
 */
public class ActualizadorIndicadores implements Runnable {
    private GestorHotel gestor;
    private PanelInicio vista;
    public ActualizadorIndicadores(GestorHotel gestor, PanelInicio vista) {
        this.gestor = gestor;
        this.vista = vista;
    }
    @Override
    public void run() {
        System.out.println("ActualizadorIndicadores: Inicia ejecucion.");

        for (int i = 1; i <= 5; i++) {
            int total = gestor.getTotalHabitaciones();
            int ocupadas = gestor.getHabitacionesOcupadasActualmente();
            int disponibles = gestor.getHabitacionesNoOcupadasActualmente();
            int confirmadas = gestor.getCantidadReservasConfirmadas();
            int vencidas = gestor.getCantidadReservasVencidas();
            long ingresos = gestor.calcularIngresosEsperados(
                    gestor.getReservas());

            EventQueue.invokeLater(() -> {
                vista.mostrarIndicadores(total, ocupadas,
                        disponibles, confirmadas, vencidas, ingresos);
            });
            System.out.println("ActualizadorIndicadores: Ciclo " + i);
            if (i < 5) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("ActualizadorIndicadores interrumpido.");
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        System.out.println("ActualizadorIndicadores: Finaliza ejecucion.");
    }
}

