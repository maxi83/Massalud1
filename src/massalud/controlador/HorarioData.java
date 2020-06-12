
package massalud.controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import massalud.modelo.Afiliado;
import massalud.modelo.Horario;
import massalud.modelo.Orden;
import massalud.modelo.Prestador;


public class HorarioData {
    private Conexion conexion;
    private Connection con;

    public HorarioData(Conexion conexion) {
        try {
            con = conexion.getConexion();
            
            System.out.println("Conecetado a la base de datos");
            
        } catch (SQLException ex) {
            System.out.println("Error sl obtener la conexion");
        }
    }
    
    public Horario buscarHorario(int idHorario){
    Horario horario = null;
    
   
    try {
            
            String sql = "SELECT * FROM horario WHERE idHorario =?;";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idHorario);
           
            
            ResultSet resultSet=ps.executeQuery();
            
            if(resultSet.next()){
                horario = new Horario();
                horario.setIdHorario(resultSet.getInt("idHorario"));
                horario.setDia(resultSet.getString("dia"));
                horario.setHorarioAtencion(resultSet.getInt("horarioAtencion"));
                
                Prestador p = buscarPrestador(resultSet.getInt("idPrestador"));
                horario.setIdPrestador(p);

                
            }      
            ps.close();
            
            
        } catch (SQLException ex) {
            System.out.println("Error al buscar el horario: " + ex.getMessage());
        }
        
        return horario;
    }
    
    public Prestador buscarPrestador(int idPrestador){
    
        PrestadorData pd=new PrestadorData(conexion);
        
        return pd.buscarPrestador(idPrestador);
        
    }
    
    
    
    
  
}
