
package massalud.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import massalud.modelo.Especialidad;


public class EspecialidadData {
    private Connection con;

    public EspecialidadData(Conexion conexion) {
        try {
            con = conexion.getConexion();
            
            System.out.println("Conecetado a la base de datos");
            
        } catch (SQLException ex) {
            System.out.println("Error sl obtener la conexion");
        }
    }
    
    // agregarEspecialidad()
    public void agregarEspecialidad(Especialidad especialidad){
        try {
            
            String sql = "INSERT INTO especialidad (especialidad) VALUES (?);";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, especialidad.getEspecialidad());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                especialidad.setIdEspecialidad(rs.getInt(1));
                System.out.println("Se ha guardado la especialiadad: "+especialidad.getEspecialidad());
                
            } else {
                System.out.println("No se pudo obtener el id luego de insertar la especialidad");
            }
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al guardar la especialidad: " + ex.getMessage());
        }
    }
        
    //buscarEspecialidad
    public Especialidad buscarEspecialidad(int idEspecialidad){
    Especialidad especialidad = null;
    
   
    try {
            
            String sql = "SELECT * FROM especialidad WHERE idEspecialidad = ?;";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idEspecialidad);
           
            
            ResultSet resultSet=ps.executeQuery();
            
            if(resultSet.next()){
                especialidad = new Especialidad();
                especialidad.setIdEspecialidad(resultSet.getInt("idEspecialidad"));
                especialidad.setEspecialidad(resultSet.getString("especialidad"));
                

                
            }      
            ps.close();
            
            
        } catch (SQLException ex) {
            System.out.println("Error al buscar la especialidad: " + ex.getMessage());
        }
        
        return especialidad;
    }
    
    //ActulizarEspecialidad
    public void actualizarEspecialidad(Especialidad especialidad){
    
        try {
            
            String sql = "UPDATE especialidad SET especialidad = ? WHERE idEspecialidad = ?;";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, especialidad.getEspecialidad());
            ps.setInt(2, especialidad.getIdEspecialidad());
            ps.executeUpdate();
            
          
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al actualizar la especialidad: " + ex.getMessage());
        }
    
    }
    
    
    
}
