/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author maria
 */
public class Registro {
    private String codigo;
    private int Mascota;
    private String Propetario;
    private int TipoServico;
    private double Tarifa;
    private int Estado;
    
    public Registro(String codigo, int Mascota, String Propetario, int TipoServicio, double Tarifa, int Estado){
        this.codigo=codigo;
        this.Propetario=Propetario;
        this.Mascota=Mascota;
        this.TipoServicio=TipoServicio;
        this.Tarifa=Tarifa;
        this.Estado=Estado;
        
    }

    public String getCodigo() {
        return codigo;
    }

    public int getMascota() {
        return Mascota;
    }

    public String getPropetario() {
        return Propetario;
    }

    public int getTipoServico() {
        return TipoServico;
    }

    public double getTarifa() {
        return Tarifa;
    }

    public int getEstado() {
        return Estado;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setMascota(int Mascota) {
        this.Mascota = Mascota;
    }

    public void setPropetario(String Propetario) {
        this.Propetario = Propetario;
    }

    public void setTipoServico(int TipoServico) {
        this.TipoServico = TipoServico;
    }

    public void setTarifa(double Tarifa) {
        this.Tarifa = Tarifa;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

    @Override
    public String toString() {
        return "Registro{" + "codigo=" + codigo + ", Mascota=" + Mascota + ", Propetario=" + Propetario + ", TipoServico=" + TipoServico + ", Tarifa=" + Tarifa + ", Estado=" + Estado + '}';
    }
   
}
