/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import modelo.GestorHotel;
import vista.FrmLogin;
import vista.FrmHotel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
/**
 *
 * @author maria
 */
public class ControladorLogin {
private GestorHotel gestor;
    private FrmLogin vista;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;

    public ControladorLogin(FrmLogin vista, JTextField txtUsuario,
            JPasswordField txtContrasena) {

        this.vista = vista;
        this.txtUsuario = txtUsuario;
        this.txtContrasena = txtContrasena;

        gestor = GestorHotel.getInstancia();
    }

    public void iniciarSesion() {

        String usuario = txtUsuario.getText().trim();
        String contrasena = new String(txtContrasena.getPassword());

        if (gestor.iniciarSesion(usuario, contrasena)) {
            vista.limpiarMensaje();
            int vencidas = gestor.actualizarReservasVencidas();
       
if (vencidas > 0) {
    JOptionPane.showMessageDialog(vista,
            "Reservas vencidas actualizadas: " + vencidas);
}

            FrmHotel ventana = new FrmHotel();
            ventana.setRecepcionista(gestor.getRecepcionistaActivo());
            ventana.setVisible(true);

            vista.dispose();

        } else {

            vista.mostrarMensaje("Usuario o contraseña incorrectos");
            txtContrasena.setText("");
        }
    }
}