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
        String nombreHilo = Thread.currentThread().getName();
        System.out.println(nombreHilo + ": Inicia ejecucion.");
        for (int i = 1; i <= 5; i++) {
            int cantidad = gestor.actualizarReservasVencidas();
            System.out.println(nombreHilo + ": Ciclo " + i
                    + " de 5. Reservas actualizadas: " + cantidad);

            if (i < 5) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                    System.out.println(nombreHilo
                            + ": Ejecucion interrumpida.");
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        System.out.println(nombreHilo + ": Finaliza ejecucion.");
    }
}