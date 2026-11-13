/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.ArrayList;
import java.time.LocalDate;
/**
 *
 * @author maria
 */

public class GestorHotel {

    private static GestorHotel instancia;

    private ArrayList<Huesped> huespedes;
    private ArrayList<Habitacion> habitaciones;
    private ArrayList<Reserva> reservas;

    private String recepcionistaActivo;

    private GestorHotel() {

        huespedes = new ArrayList<>();
        habitaciones = new ArrayList<>();
        reservas = new ArrayList<>();

        recepcionistaActivo = null;
    }

    public static GestorHotel getInstancia() {

        if (instancia == null) {
            instancia = new GestorHotel();
        }

        return instancia;
    }

    public synchronized boolean iniciarSesion(String usuario, String contrasena) {

        if (recepcionistaActivo != null) {
            return false;
        }

        if (usuario == null || contrasena == null) {
            return false;
        }

        if (usuario.equals("recepcion1")
                && contrasena.equals("Hotel123")) {

            recepcionistaActivo = usuario;
            return true;

        } else if (usuario.equals("recepcion2")
                && contrasena.equals("Hotel456")) {

            recepcionistaActivo = usuario;
            return true;
        }

        return false;
    }

    public synchronized void cerrarSesion() {
        recepcionistaActivo = null;
    }

    public synchronized boolean haySesionActiva() {
        return recepcionistaActivo != null;
    }

    public synchronized String getRecepcionistaActivo() {
        return recepcionistaActivo;
    }

    public synchronized ArrayList<Huesped> getHuespedes() {
        return new ArrayList<>(huespedes);
    }

    public synchronized ArrayList<Habitacion> getHabitaciones() {
        return new ArrayList<>(habitaciones);
    }

    public synchronized ArrayList<Reserva> getReservas() {
        return new ArrayList<>(reservas);
    }

    public synchronized String registrarHuesped(String identificacion,
            String nombreCompleto, String telefono, String correoElectronico) {

        if (identificacion == null || nombreCompleto == null
                || telefono == null || correoElectronico == null) {
            return "Debe completar todos los campos";
        }

        identificacion = identificacion.trim();
        nombreCompleto = nombreCompleto.trim();
        telefono = telefono.trim();
        correoElectronico = correoElectronico.trim();

        if (identificacion.equals("") || nombreCompleto.equals("")
                || telefono.equals("") || correoElectronico.equals("")) {
            return "Debe completar todos los campos";
        }

        int arroba = correoElectronico.indexOf("@");

        if (arroba <= 0
                || arroba != correoElectronico.lastIndexOf("@")
                || arroba == correoElectronico.length() - 1
                || correoElectronico.indexOf(".", arroba + 1) == -1) {
            return "El correo electronico no es valido";
        }

        for (int i = 0; i < huespedes.size(); i++) {

            if (huespedes.get(i).getIdentificacion().equals(identificacion)) {
                return "La identificacion ya existe";
            }
        }

        Huesped nuevo = new Huesped(identificacion, nombreCompleto,
                telefono, correoElectronico);

        huespedes.add(nuevo);

        return "Huesped registrado correctamente";
    }

    public synchronized String registrarHabitacion(String numero, String tipo) {

        if (numero == null || tipo == null) {
            return "Debe completar todos los campos";
        }

        numero = numero.trim();
        tipo = tipo.trim();

        if (numero.equals("") || tipo.equals("")) {
            return "Debe completar todos los campos";
        }

        for (int i = 0; i < habitaciones.size(); i++) {

            if (habitaciones.get(i).getNumero().equals(numero)) {
                return "La habitacion ya existe";
            }
        }

        if (!tipo.equals("Individual")
                && !tipo.equals("Doble")
                && !tipo.equals("Familiar")) {
            return "Tipo de habitacion no valido";
        }

        Habitacion nueva = HabitacionFactory.crearHabitacion(numero, tipo);

        habitaciones.add(nueva);

        return "Habitacion registrada correctamente";
    }

    private Huesped buscarHuesped(String identificacion) {

        for (int i = 0; i < huespedes.size(); i++) {

            if (huespedes.get(i).getIdentificacion().equals(identificacion)) {
                return huespedes.get(i);
            }
        }

        return null;
    }

    private Habitacion buscarHabitacion(String numero) {

        for (int i = 0; i < habitaciones.size(); i++) {

            if (habitaciones.get(i).getNumero().equals(numero)) {
                return habitaciones.get(i);
            }
        }

        return null;
    }

    private Reserva buscarReserva(String codigo) {

        for (int i = 0; i < reservas.size(); i++) {

            if (reservas.get(i).getCodigo().equals(codigo)) {
                return reservas.get(i);
            }
        }

        return null;
    }

    public synchronized String registrarReserva(String identificacion,
            String numeroHabitacion, int cantidadPersonas,
            LocalDate fechaEntrada, LocalDate fechaSalida) {

        if (recepcionistaActivo == null) {
            return "Debe iniciar sesion";
        }

        if (identificacion == null || numeroHabitacion == null
                || fechaEntrada == null || fechaSalida == null) {
            return "Debe completar todos los datos";
        }

        identificacion = identificacion.trim();
        numeroHabitacion = numeroHabitacion.trim();

        if (identificacion.equals("") || numeroHabitacion.equals("")) {
            return "Debe completar todos los datos";
        }

        if (fechaEntrada.isBefore(LocalDate.now())) {
            return "La fecha de entrada no puede ser anterior a hoy";
        }

        if (!fechaSalida.isAfter(fechaEntrada)) {
            return "La fecha de salida debe ser posterior a la entrada";
        }

        if (cantidadPersonas <= 0) {
            return "La cantidad de personas no es valida";
        }

        Huesped huesped = buscarHuesped(identificacion);
        Habitacion habitacion = buscarHabitacion(numeroHabitacion);

        if (huesped == null) {
            return "El huesped no existe";
        }

        if (habitacion == null) {
            return "La habitacion no existe";
        }

        if (cantidadPersonas > habitacion.getCapacidadMaxima()) {
            return "La habitacion no tiene capacidad suficiente";
        }

        for (int i = 0; i < reservas.size(); i++) {

            Reserva existente = reservas.get(i);

            if (existente.getHabitacion().getNumero().equals(numeroHabitacion)
                    && (existente.getEstado() == EstadoReserva.CONFIRMADA
                    || existente.getEstado() == EstadoReserva.HOSPEDADO)) {

                if (fechaEntrada.isBefore(existente.getFechaSalida())
                        && fechaSalida.isAfter(existente.getFechaEntrada())) {

                    return "La habitacion ya esta reservada en esas fechas";
                }
            }
        }

        String codigo = "R" + (reservas.size() + 1);

        Reserva nueva = new Reserva(codigo, huesped, habitacion,
                cantidadPersonas, fechaEntrada, fechaSalida,
                recepcionistaActivo);

        reservas.add(nueva);

        return "Reserva registrada correctamente. Codigo: " + codigo;
    }

    public synchronized String realizarCheckIn(String codigo) {

        Reserva reserva = buscarReserva(codigo);

        if (reserva == null) {
            return "La reserva no existe";
        }

        if (reserva.realizarCheckIn()) {
            return "Check-in realizado correctamente";
        }

        return "No se puede realizar el check-in";
    }

    public synchronized String realizarCheckOut(String codigo) {

        Reserva reserva = buscarReserva(codigo);

        if (reserva == null) {
            return "La reserva no existe";
        }

        if (reserva.realizarCheckOut()) {
            return "Check-out realizado correctamente";
        }

        return "No se puede realizar el check-out";
    }

    private boolean habitacionDisponibleEnFecha(Habitacion habitacion,
            ArrayList<Reserva> listaReservas, LocalDate fecha) {

        for (int i = 0; i < listaReservas.size(); i++) {

            Reserva reserva = listaReservas.get(i);

            if (reserva.getHabitacion().getNumero().equals(habitacion.getNumero())
                    && (reserva.getEstado() == EstadoReserva.CONFIRMADA
                    || reserva.getEstado() == EstadoReserva.HOSPEDADO)) {

                if (!fecha.isBefore(reserva.getFechaEntrada())
                        && fecha.isBefore(reserva.getFechaSalida())) {
                    return false;
                }
            }
        }

        return true;
    }

    public synchronized ArrayList<Habitacion> obtenerHabitacionesDisponibles(
            LocalDate fecha) {

        ArrayList<Habitacion> disponibles = new ArrayList<>();

        if (fecha == null) {
            return disponibles;
        }

        for (int i = 0; i < habitaciones.size(); i++) {

            if (habitacionDisponibleEnFecha(
                    habitaciones.get(i), reservas, fecha)) {

                disponibles.add(habitaciones.get(i));
            }
        }

        return disponibles;
    }

    public synchronized int actualizarReservasVencidas() {

        int cantidad = 0;

        for (int i = 0; i < reservas.size(); i++) {

            if (reservas.get(i).verificarVencimiento()) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public synchronized ArrayList<Reserva> simularVencimientos(
            LocalDate fechaReferencia) {

        ArrayList<Reserva> vencidas = new ArrayList<>();

        if (fechaReferencia == null) {
            return vencidas;
        }

        for (int i = 0; i < reservas.size(); i++) {

            Reserva reserva = reservas.get(i);

            if (reserva.getEstado() == EstadoReserva.CONFIRMADA
                    && reserva.getFechaEntrada().isBefore(fechaReferencia)) {

                vencidas.add(reserva);
            }
        }

        return vencidas;
    }

    public synchronized int getTotalHabitaciones() {
        return habitaciones.size();
    }

    public synchronized int getHabitacionesOcupadasActualmente() {

        int cantidad = 0;

        for (int i = 0; i < habitaciones.size(); i++) {

            boolean ocupada = false;

            for (int j = 0; j < reservas.size(); j++) {

                if (reservas.get(j).getHabitacion().getNumero()
                        .equals(habitaciones.get(i).getNumero())
                        && reservas.get(j).getEstado() == EstadoReserva.HOSPEDADO) {

                    ocupada = true;
                    break;
                }
            }

            if (ocupada) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public synchronized int getHabitacionesNoOcupadasActualmente() {

        return habitaciones.size() - getHabitacionesOcupadasActualmente();
    }

    public synchronized int getCantidadReservasConfirmadas() {

        int cantidad = 0;

        for (int i = 0; i < reservas.size(); i++) {

            if (reservas.get(i).getEstado() == EstadoReserva.CONFIRMADA) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public synchronized int getCantidadReservasVencidas() {

        int cantidad = 0;

        for (int i = 0; i < reservas.size(); i++) {

            if (reservas.get(i).getEstado() == EstadoReserva.VENCIDA) {
                cantidad++;
            }
        }

        return cantidad;
    }

    public synchronized long calcularIngresosEsperados(
            ArrayList<Reserva> listaReservas) {

        if (listaReservas == null) {
            return 0;
        }

        return calcularIngresosEsperadosRecursivo(listaReservas, 0);
    }

    private long calcularIngresosEsperadosRecursivo(
            ArrayList<Reserva> listaReservas, int posicion) {

        if (posicion >= listaReservas.size()) {
            return 0;
        }

        Reserva reserva = listaReservas.get(posicion);

        if (reserva.getEstado() == EstadoReserva.CONFIRMADA) {

            return reserva.getImporteTotal()
                    + calcularIngresosEsperadosRecursivo(
                            listaReservas, posicion + 1);
        }

        return calcularIngresosEsperadosRecursivo(
                listaReservas, posicion + 1);
    }

    public synchronized int contarHabitacionesDisponibles(
            ArrayList<Habitacion> listaHabitaciones,
            ArrayList<Reserva> listaReservas, LocalDate fecha) {

        if (listaHabitaciones == null || listaReservas == null
                || fecha == null) {
            return 0;
        }

        return contarHabitacionesDisponiblesRecursivo(
                listaHabitaciones, listaReservas, fecha, 0);
    }

    private int contarHabitacionesDisponiblesRecursivo(
            ArrayList<Habitacion> listaHabitaciones,
            ArrayList<Reserva> listaReservas,
            LocalDate fecha, int posicion) {

        if (posicion >= listaHabitaciones.size()) {
            return 0;
        }

        if (habitacionDisponibleEnFecha(
                listaHabitaciones.get(posicion), listaReservas, fecha)) {

            return 1 + contarHabitacionesDisponiblesRecursivo(
                    listaHabitaciones, listaReservas, fecha, posicion + 1);
        }

        return contarHabitacionesDisponiblesRecursivo(
                listaHabitaciones, listaReservas, fecha, posicion + 1);
    }
}
