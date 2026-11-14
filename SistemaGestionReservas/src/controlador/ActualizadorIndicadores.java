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
    private Thread hiloSupervisor;
    private ControladorProcesos controlador;
    public ActualizadorIndicadores(GestorHotel gestor,
            PanelInicio vista, Thread hiloSupervisor,
            ControladorProcesos controlador) {
        this.gestor = gestor;
        this.vista = vista;
        this.hiloSupervisor = hiloSupervisor;
        this.controlador = controlador;
    }
    @Override
    public void run() {

        String nombreHilo = Thread.currentThread().getName();
        boolean interrumpido = false;
        System.out.println(nombreHilo + ": Inicia ejecucion.");
        for (int ciclo = 1; ciclo <= 5; ciclo++) {
            int totalHabitaciones;
            int habitacionesOcupadas;
            int habitacionesDisponibles;
            int reservasConfirmadas;
            int reservasVencidas;
            long ingresos;
            synchronized (gestor) {totalHabitaciones =
                        gestor.getTotalHabitaciones();
                habitacionesOcupadas =gestor.getHabitacionesOcupadasActualmente();
                habitacionesDisponibles =gestor.getHabitacionesNoOcupadasActualmente();
                reservasConfirmadas =gestor.getCantidadReservasConfirmadas();
                reservasVencidas =gestor.getCantidadReservasVencidas();
                ingresos = gestor.calcularIngresosEsperados(gestor.getReservas());}
            System.out.println(nombreHilo
                    + ": Ciclo " + ciclo + " de 5");
            System.out.println("Total habitaciones: "
                    + totalHabitaciones);
            System.out.println("Habitaciones ocupadas: "
                    + habitacionesOcupadas);
            System.out.println("Habitaciones disponibles: "
                    + habitacionesDisponibles);
            System.out.println("Reservas confirmadas: "
                    + reservasConfirmadas);
            System.out.println("Reservas vencidas: "
                    + reservasVencidas);
            System.out.println("Ingresos esperados: ₡"
                    + ingresos);
            EventQueue.invokeLater(() -> {
                vista.actualizarIndicadores();
            });

            if (ciclo < 5) {

                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {

                    System.out.println(nombreHilo
                            + ": Ejecucion interrumpida.");
                    interrumpido = true;
                    break;
                }
            }
        }
        System.out.println(nombreHilo
                + ": Esperando finalizacion del supervisor.");
        boolean esperando = true;

        while (esperando) {

            try {
                hiloSupervisor.join();
                esperando = false;
            } catch (InterruptedException e) {
                System.out.println(nombreHilo
                        + ": Espera interrumpida.");

                interrumpido = true;
            }
        }
        EventQueue.invokeLater(() -> {
            controlador.finalizarProcesos();
        });
        System.out.println(nombreHilo
                + ": Finaliza ejecucion.");
        if (interrumpido) {
            Thread.currentThread().interrupt();
        }
    }
}