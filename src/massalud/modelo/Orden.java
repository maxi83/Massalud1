
package massalud.modelo;

import java.time.LocalDate;

public class Orden {
    private int idOrden = -1;
    private LocalDate fecha;
    private String formaPago;
    private float importe;
    private Afiliado idAfiliado; 
    private Horario idHorario; 
    private boolean activo;

    public Orden() {
    }

    public Orden(LocalDate fecha, String formaPago, float importe, Afiliado idAfiliado, Horario idHorario, boolean activo) {
        this.fecha = fecha;
        this.formaPago = formaPago;
        this.importe = importe;
        this.idAfiliado = idAfiliado;
        this.idHorario = idHorario;
        this.activo = activo;
    }

    @Override
    public String toString() {
        return " IdOrden: " + idOrden + "\t Fecha: " + fecha + "\t Importe: " + importe + 
                "\n Afiliado: " + idAfiliado.getNombre() + " " + idAfiliado.getApellido() +
                "\n Horario: " + idHorario.getDia() + " " + idHorario.getHorarioAtencion() + "hs. \n" +
                idHorario.getIdPrestador() + "\n";
    }

    
    
//    Getters y Setters

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public Afiliado getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(Afiliado idAfiliado) {
        this.idAfiliado = idAfiliado;
    }

    public Horario getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(Horario idHorario) {
        this.idHorario = idHorario;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    
    
}
