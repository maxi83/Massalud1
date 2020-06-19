
package massalud.modelo;

public class Horario {
    private int IdHorario = -1;
    private String dia;
    private int horarioAtencion; 
    private Prestador idPrestador;

    public Horario() {
    }

    public Horario(String dia, int horarioAtencion, Prestador idPrestador) {
        this.dia = dia;
        this.horarioAtencion = horarioAtencion;
        this.idPrestador = idPrestador;
    }

    @Override
    public String toString() {
        return "Nombre Prestador: " + idPrestador.getNombre() + ", dia: " + dia +
                ", Horario de atencion: " + horarioAtencion + "hs.";
    }

    
    
//    Getters y Setters

    public int getIdHorario() {
        return IdHorario;
    }

    public void setIdHorario(int IdHorario) {
        this.IdHorario = IdHorario;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getHorarioAtencion() {
        return horarioAtencion;
    }

    public void setHorarioAtencion(int horarioAtencion) {
        this.horarioAtencion = horarioAtencion;
    }

    public Prestador getIdPrestador() {
        return idPrestador;
    }

    public void setIdPrestador(Prestador idPrestador) {
        this.idPrestador = idPrestador;
    }
    
}
