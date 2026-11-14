/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.GestorHotel;
import modelo.Huesped;
/**
 *
 * @author maria
 */
public class ControladorHuespedes {
     private GestorHotel gestor;

    private JTextField txtIdentificacion;
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtCorreo;

    private JTable tblHuespedes;
    private DefaultTableModel modeloTabla;

    public ControladorHuespedes(
            JTextField txtIdentificacion,
            JTextField txtNombre,
            JTextField txtTelefono,
            JTextField txtCorreo,
            JTable tblHuespedes,
            JButton btnRegistrar,
            JButton btnLimpiar) {

        this.gestor = GestorHotel.getInstancia();

        this.txtIdentificacion = txtIdentificacion;
        this.txtNombre = txtNombre;
        this.txtTelefono = txtTelefono;
        this.txtCorreo = txtCorreo;
        this.tblHuespedes = tblHuespedes;

        modeloTabla = new DefaultTableModel(
            new Object[]{"Identificación", "Nombre completo",
                "Teléfono", "Correo electrónico"}, 0) {

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tblHuespedes.setModel(modeloTabla);

        cargarHuespedes();

        btnRegistrar.addActionListener(e -> registrarHuesped());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void registrarHuesped() {

        String resultado = gestor.registrarHuesped(
                txtIdentificacion.getText(),
                txtNombre.getText(),
                txtTelefono.getText(),
                txtCorreo.getText()
        );

        JOptionPane.showMessageDialog(null, resultado);

        if (resultado.equals("Huesped registrado correctamente")) {

            cargarHuespedes();
            limpiarCampos();
        }
    }

    private void cargarHuespedes() {

        modeloTabla.setRowCount(0);

        ArrayList<Huesped> lista = gestor.getHuespedes();

        for (int i = 0; i < lista.size(); i++) {

            Huesped huesped = lista.get(i);

            modeloTabla.addRow(new Object[]{
                huesped.getIdentificacion(),
                huesped.getNombreCompleto(),
                huesped.getTelefono(),
                huesped.getCorreoElectronico()
            });
        }
    }

    private void limpiarCampos() {

        txtIdentificacion.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
    }
}

