/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;
import vista.FrmLogin;
import javax.swing.SwingUtilities;
/**
 *
 * @author maria
 */
public class Principal {
    
 public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        new FrmLogin().setVisible(true);
        });
    }
}