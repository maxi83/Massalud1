/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package massalud.modelo;

/**
 *
 * @author Maxi
 */
public class Cirugia {
    private int idCirugia = -1;
    private String nombre;
    private int idPrestador;
    private int idHorario;
    private boolean activo;

    public Cirugia(String nombre, int idPrestador, int idHorario, boolean activo) {
        this.nombre = nombre;
        this.idPrestador = idPrestador;
        this.idHorario = idHorario;
        this.activo = activo;
    }

    public int getIdCirugia() {
        return idCirugia;
    }

    public void setIdCirugia(int idCirugia) {
        this.idCirugia = idCirugia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(int idPrestador) {
        this.idPrestador = idPrestador;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void getIdCirugia(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
