/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.GestorHotel;
import modelo.Habitacion;
import modelo.HabitacionFactory;
/**
 *
 * @author maria
 */
public class ControladorHabitaciones {
     private GestorHotel gestor;

    private JTextField txtNumero;
    private JComboBox<String> cmbTipo;
    private JTextField txtCapacidad;
    private JTextField txtTarifa;

    private JTable tblHabitaciones;
    private DefaultTableModel modeloTabla;

    public ControladorHabitaciones(
            JTextField txtNumero,
            JComboBox<String> cmbTipo,
            JTextField txtCapacidad,
            JTextField txtTarifa,
            JTable tblHabitaciones,
            JButton btnRegistrar,
            JButton btnLimpiar) {

        this.gestor = GestorHotel.getInstancia();
        this.txtNumero = txtNumero;
        this.cmbTipo = cmbTipo;
        this.txtCapacidad = txtCapacidad;
        this.txtTarifa = txtTarifa;
        this.tblHabitaciones = tblHabitaciones;

        modeloTabla = new DefaultTableModel(
                new Object[]{"Número", "Tipo",
                    "Capacidad", "Tarifa por noche"}, 0) {

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tblHabitaciones.setModel(modeloTabla);
        txtCapacidad.setEditable(false);
        txtTarifa.setEditable(false);
        cmbTipo.addActionListener(e -> actualizarDatosTipo());
        btnRegistrar.addActionListener(e -> registrarHabitacion());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        actualizarDatosTipo();
        cargarHabitaciones();
    }

    private void actualizarDatosTipo() {
        String tipo = (String) cmbTipo.getSelectedItem();
        if (tipo != null) {
            Habitacion habitacion =
                    HabitacionFactory.crearHabitacion("", tipo);
            txtCapacidad.setText(
                    String.valueOf(habitacion.getCapacidadMaxima()));
            txtTarifa.setText(
                    String.valueOf(habitacion.getTarifaPorNoche()));
        }
    }

    private void registrarHabitacion() {
        String resultado = gestor.registrarHabitacion(
                txtNumero.getText(),
                (String) cmbTipo.getSelectedItem()
        );
        JOptionPane.showMessageDialog(null, resultado);
        if (resultado.equals("Habitacion registrada correctamente")) {
            cargarHabitaciones();
            limpiarCampos();
        }
    }

    private void cargarHabitaciones() {
        modeloTabla.setRowCount(0);
        ArrayList<Habitacion> lista = gestor.getHabitaciones();
        for (int i = 0; i < lista.size(); i++) {
            Habitacion habitacion = lista.get(i);
            modeloTabla.addRow(new Object[]{
                habitacion.getNumero(),
                habitacion.getTipo(),
                habitacion.getCapacidadMaxima(),
                habitacion.getTarifaPorNoche()
            });
        }
    }
    private void limpiarCampos() {
        txtNumero.setText("");
        cmbTipo.setSelectedIndex(0);
        actualizarDatosTipo();
    }
}

