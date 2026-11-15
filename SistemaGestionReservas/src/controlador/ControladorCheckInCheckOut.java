/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import modelo.GestorHotel;
import modelo.Reserva;
import modelo.EstadoReserva;
import vista.PanelCheckInCheckOut;
import java.time.LocalDate;
import javax.swing.JOptionPane;
/**
 *
 * @author maria
 */
public class ControladorCheckInCheckOut {
  private PanelCheckInCheckOut vista;
    private GestorHotel gestor;
    private Reserva reservaActual;

    public ControladorCheckInCheckOut(PanelCheckInCheckOut vista) {

        this.vista = vista;
        this.gestor = GestorHotel.getInstancia();

        vista.getBtnBuscar().addActionListener(e -> buscarReserva());

        vista.getBtnProcesar().addActionListener(e -> procesarOperacion());

        vista.getRdbCheckIn().addActionListener(e -> actualizarBotonProcesar());

        vista.getRdbCheckOut().addActionListener(e -> actualizarBotonProcesar());

        limpiarDetalle();
    }

    private void buscarReserva() {

        String codigo = vista.getTxtCodigoReserva().getText().trim();

        if (codigo.equals("")) {
            JOptionPane.showMessageDialog(vista,
                    "Ingrese el codigo de la reserva");
            return;
        }

        reservaActual = null;

        for (int i = 0; i < gestor.getReservas().size(); i++) {

            Reserva reserva = gestor.getReservas().get(i);

            if (reserva.getCodigo().equalsIgnoreCase(codigo)) {
                reservaActual = reserva;
                break;
            }
        }

        if (reservaActual == null) {

            limpiarDetalle();

            JOptionPane.showMessageDialog(vista,
                    "No se encontro la reserva");

            return;
        }

        mostrarDatosReserva();
        configurarOpciones();
    }

    private void mostrarDatosReserva() {

        vista.getLblNombreHuesped().setText(
                reservaActual.getHuesped().getNombreCompleto());

        vista.getLblNombreHabitacion().setText(
                reservaActual.getHabitacion().getNumero());

        vista.getLblFechaEntrada().setText(
                reservaActual.getFechaEntrada().toString());

        String salida = reservaActual.getFechaSalida().toString();

        if (reservaActual.getFechaSalidaReal() != null) {

            salida += " | Real: "
                    + reservaActual.getFechaSalidaReal().toString();
        }

        vista.getLblFechaSalida().setText(salida);

        vista.getLblEstadoActual().setText(
                reservaActual.getEstado().toString());
    }

    private void configurarOpciones() {

        vista.getButtonGroup1().clearSelection();

        boolean puedeCheckIn = reservaActual.getEstado()
                == EstadoReserva.CONFIRMADA
                && reservaActual.getFechaEntrada().equals(LocalDate.now());

        boolean puedeCheckOut = reservaActual.getEstado()
                == EstadoReserva.HOSPEDADO;

        vista.getRdbCheckIn().setEnabled(puedeCheckIn);

        vista.getRdbCheckOut().setEnabled(puedeCheckOut);

        vista.getRdbCheckIn().setToolTipText(
                puedeCheckIn ? null
                : "El check-in solo se permite en la fecha de entrada");

        vista.getBtnProcesar().setEnabled(false);
    }

    private void actualizarBotonProcesar() {

        boolean seleccionado = vista.getRdbCheckIn().isSelected()
                || vista.getRdbCheckOut().isSelected();

        vista.getBtnProcesar().setEnabled(
                reservaActual != null && seleccionado);
    }

    private void procesarOperacion() {

        if (reservaActual == null) {
            return;
        }

        String resultado;

        if (vista.getRdbCheckIn().isSelected()) {

            resultado = gestor.realizarCheckIn(
                    reservaActual.getCodigo());

        } else if (vista.getRdbCheckOut().isSelected()) {

            resultado = gestor.realizarCheckOut(
                    reservaActual.getCodigo());

        } else {

            JOptionPane.showMessageDialog(vista,
                    "Seleccione una operacion");

            return;
        }

        mostrarDatosReserva();

        configurarOpciones();

        JOptionPane.showMessageDialog(vista, resultado);
    }

    private void limpiarDetalle() {

        reservaActual = null;

        vista.getLblNombreHuesped().setText("");

        vista.getLblNombreHabitacion().setText("");

        vista.getLblFechaEntrada().setText("");

        vista.getLblFechaSalida().setText("");

        vista.getLblEstadoActual().setText("");

        vista.getButtonGroup1().clearSelection();

        vista.getRdbCheckIn().setEnabled(false);

        vista.getRdbCheckOut().setEnabled(false);

        vista.getBtnProcesar().setEnabled(false);
    }
}