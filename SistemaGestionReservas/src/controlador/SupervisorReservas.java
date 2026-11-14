/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import modelo.GestorHotel;
/**
 *
 * @author maria
 */
public class SupervisorReservas implements Runnable {

    private GestorHotel gestor;

    public SupervisorReservas(GestorHotel gestor) {
        this.gestor = gestor;
    }

    @Override
    public void run() {

        System.out.println("SupervisorReservas: Inicia ejecucion.");

        for (int i = 1; i <= 5; i++) {

            int cantidad = gestor.actualizarReservasVencidas();

            System.out.println("SupervisorReservas: Ciclo " + i
                    + ". Reservas actualizadas: " + cantidad);

            if (i < 5) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("SupervisorReservas interrumpido.");
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }

        System.out.println("SupervisorReservas: Finaliza ejecucion.");
    }
}

