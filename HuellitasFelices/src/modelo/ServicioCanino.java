/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author maria
 */
public class ServicioCanino { 
private int codigo;
    private String mascota;
    private String propietario;
    private String tipoServicio;
    private int tarifa;
    private String estado;

    public ServicioCanino(int codigo, String mascota,
            String propietario, String tipoServicio,
            int tarifa, String estado) {
        this.codigo = codigo;
        this.mascota = mascota;
        this.propietario = propietario;
        this.tipoServicio = tipoServicio;
        this.tarifa = tarifa;
        this.estado = estado;
    }

  public int getCodigo() { return codigo; }
    public String getMascota() { return mascota; }
    public String getPropietario() { return propietario; }
    public String getTipoServicio() { return tipoServicio; }
    public int getTarifa() { return tarifa; }
    public String getEstado() { return estado; }
}

