/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.table.DefaultTableModel;
import modelo.GestorHotel;
import modelo.Habitacion;
import modelo.Huesped;
import modelo.Reserva;
import vista.PanelReservas;

/**
 *
 * @author UTN
 */
public class ControladorReservas {
 private final PanelReservas vista;
    private final GestorHotel gestor;

    public ControladorReservas(PanelReservas vista) {

        this.vista = vista;
        this.gestor = GestorHotel.getInstancia();

        vista.getBtnRegistrar().addActionListener(e -> registrarReserva());

        vista.getBtnLimpiar().addActionListener(e -> {
            vista.limpiarCampos();
            vista.getCmbHuesped().setSelectedIndex(-1);
            vista.getCmbHabitacion().setSelectedIndex(-1);
        });

        actualizarCombos();
        actualizarTabla();
    }

    public void actualizarCombos() {
        vista.getCmbHuesped().removeAllItems();
        for (Huesped h : gestor.getHuespedes()) {
            vista.getCmbHuesped().addItem(h.toString());
        }
        vista.getCmbHabitacion().removeAllItems();
        for (Habitacion hab : gestor.getHabitaciones()) {
            vista.getCmbHabitacion().addItem(hab.toString());
        }
        vista.getCmbHuesped().setSelectedIndex(-1);
        vista.getCmbHabitacion().setSelectedIndex(-1);
    }
    private void registrarReserva() {
        int posHuesped = vista.getCmbHuesped().getSelectedIndex();
        int posHabitacion = vista.getCmbHabitacion().getSelectedIndex();
        if (posHuesped < 0 || posHabitacion < 0) {
            vista.mostrarMensaje("Debe registrar un huesped y una habitacion");
            return;
        }
        try {

            int personas = Integer.parseInt(
                    vista.getTxtPersonas().getText().trim());
            LocalDate entrada = LocalDate.parse(
                    vista.getTxtFechaEntrada().getText().trim());
            LocalDate salida = LocalDate.parse(
                    vista.getTxtFechaSalida().getText().trim());
            String identificacion = gestor.getHuespedes()
                    .get(posHuesped).getIdentificacion();
            String numeroHabitacion = gestor.getHabitaciones()
                    .get(posHabitacion).getNumero();
            String resultado = gestor.registrarReserva(
                    identificacion, numeroHabitacion,
                    personas, entrada, salida);
            vista.mostrarMensaje(resultado);
            if (resultado.startsWith("Reserva registrada")) {
                vista.limpiarCampos();
                vista.getCmbHuesped().setSelectedIndex(-1);
                vista.getCmbHabitacion().setSelectedIndex(-1);
                actualizarTabla();
            }
        } catch (NumberFormatException ex) {
            vista.mostrarMensaje(
                    "La cantidad de personas debe ser un número entero.");
        } catch (DateTimeParseException ex) {
            vista.mostrarMensaje(
                    "Las fechas deben tener el formato 2026-11-20.");
        }
    }

    public void actualizarTabla() {
        DefaultTableModel modelo =
                (DefaultTableModel) vista.getTblReservas().getModel();
        modelo.setRowCount(0);
        for (Reserva r : gestor.getReservas()) {
            modelo.addRow(new Object[]{
                r.getCodigo(),
                r.getHuesped().getNombreCompleto(),
                r.getHabitacion().getNumero(),
                r.getFechaEntrada().toString(),
                r.getFechaSalida().toString(),
                "₡" + r.getImporteTotal(),
                r.getEstado().toString()
            });
        }
    }
}
    
