
package massalud.modelo;

public class Afiliado {
    private int idAfiliado = -1;
    private String nombre;
    private String apellido;
    private int dni;
    private boolean activo;

    public Afiliado() {
    }

    public Afiliado(String nombre, String apellido, int dni, boolean activo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.activo = activo;
    }
    
    @Override
    public String toString(){
        return idAfiliado + " - " + nombre + " - " + apellido + " - " + dni + " - " + activo;
    }
    
//    Getters y Setters

    public int getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(int idAfiliado) {
        this.idAfiliado = idAfiliado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
}
