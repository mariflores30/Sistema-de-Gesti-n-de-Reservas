/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package modelo;

import java.time.LocalDate;

/**
 *
 * @author maria
 */
public class Reserva {

    private final String codigo;
    private final Huesped huesped;
    private final Habitacion habitacion;
    private final int cantidadPersonas;
    private final LocalDate fechaEntrada;
    private final LocalDate fechaSalida;
    private LocalDate fechaSalidaReal;
    private final long cantidadNoches;
    private final long importeTotal;
    private EstadoReserva estado;
    private final String recepcionista;

    public Reserva(String codigo, Huesped huesped,
            Habitacion habitacion, int cantidadPersonas,
            LocalDate fechaEntrada, LocalDate fechaSalida,
            String recepcionista) {

        this.codigo = codigo;
        this.huesped = huesped;
        this.habitacion = habitacion;
        this.cantidadPersonas = cantidadPersonas;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.recepcionista = recepcionista;

        this.fechaSalidaReal = null;

        long noches = 0;
        LocalDate fecha = fechaEntrada;

        while (fecha.isBefore(fechaSalida)) {
            noches++;
            fecha = fecha.plusDays(1);
        }

        this.cantidadNoches = noches;

        this.importeTotal = cantidadNoches
                * habitacion.getTarifaPorNoche();

        this.estado = EstadoReserva.CONFIRMADA;
    }

    public String getCodigo() {
        return codigo;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public LocalDate getFechaSalidaReal() {
        return fechaSalidaReal;
    }

    public long getCantidadNoches() {
        return cantidadNoches;
    }

    public long getImporteTotal() {
        return importeTotal;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public String getRecepcionista() {
        return recepcionista;
    }

    public boolean realizarCheckIn() {

        if (estado == EstadoReserva.CONFIRMADA
                && fechaEntrada.equals(LocalDate.now())) {

            estado = EstadoReserva.HOSPEDADO;
            return true;
        }

        return false;
    }

    public boolean realizarCheckOut() {

        if (estado == EstadoReserva.HOSPEDADO) {

            fechaSalidaReal = LocalDate.now();
            estado = EstadoReserva.FINALIZADA;
            return true;
        }

        return false;
    }

    public boolean verificarVencimiento() {

        if (estado == EstadoReserva.CONFIRMADA
                && fechaEntrada.isBefore(LocalDate.now())) {

            estado = EstadoReserva.VENCIDA;
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Reserva: " + codigo
                + " - Huesped: " + huesped.getNombreCompleto()
                + " - Estado: " + estado;
    }
}
    

