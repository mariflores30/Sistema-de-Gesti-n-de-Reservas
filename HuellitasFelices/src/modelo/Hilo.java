/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author maria
 */
public class Hilo {
    public static void main(String[] args) throws InterruptedException {

        Thread hiloA = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Cita " + i + " - " +
                        Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }, "Hilo-Citas");

        Thread hiloB = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println("Factura " + i + " - " +
                        Thread.currentThread().getName());
            }
        }, "Hilo-Facturas");

        System.out.println("Estado inicial: " + hiloA.getState());

        hiloA.start();
        hiloB.start();

        hiloA.join();
        hiloB.join();

        System.out.println("Estado final: " + hiloA.getState());
        System.out.println("Todos los trabajos finalizaron");
    }
    Private 
}

