
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
import massalud.modelo.Especialidad;
import massalud.modelo.Horario;
import massalud.modelo.Orden;
import massalud.modelo.Prestador;


public class HorarioData {
    private Conexion conexion;
    private Connection con;

    public HorarioData(Conexion conexion) {
        try {
            this.conexion = conexion;
            con = conexion.getConexion();
            
            System.out.println("Conecetado a la base de datos");
            
        } catch (SQLException ex) {
            System.out.println("Error sl obtener la conexion");
        }
    }
    
    // agregarHorario(Horario horario)
    public void agregarHorario(Horario horario){
        try {
            
            String sql = "INSERT INTO horario (dia, horarioAtencion, idPrestador) VALUES (?, ?, ?);";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, horario.getDia());
            ps.setInt(2, horario.getHorarioAtencion());
            ps.setInt(3, horario.getIdPrestador().getIdPrestador());
            
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                horario.setIdHorario(rs.getInt(1));
                System.out.println("Se ha guardado el Horario: " + horario);
                
            } else {
                System.out.println("No se pudo obtener el id luego de insertar el horario");
            }
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al guardar el horario: " + ex.getMessage());
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
    
    //ActualizarHorario(Horario horario)
    
    public void actualizarHorario(Horario horario){
    
        try {
            
            String sql = "UPDATE horario SET dia = ?, horarioAtencion = ? , "
                    + "idPrestador =? WHERE idHorario = ?;";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, horario.getDia());            
            ps.setInt(2, horario.getHorarioAtencion());          
            ps.setInt(3, horario.getIdPrestador().getIdPrestador());
            ps.setInt(4, horario.getIdHorario());
            
            
            
            ps.executeUpdate();
            
          
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al actualizar el horario: " + ex.getMessage());
        }
    
    }
    
    
    // Listar Horarios por prestador(idprestador)
    
//    public List<Horario> obtenerHorarioPorPrestador(int idPrestador){
    public List<Horario> obtenerHorarioPorPrestador(Prestador idPrestador){
        List<Horario> listaHorario = new ArrayList<Horario>();
            

        try {
            String sql = "SELECT * FROM horario WHERE idPrestador = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idPrestador.getIdPrestador());
            ResultSet resultSet = ps.executeQuery();
            
            while(resultSet.next()){
                Horario horario = new Horario();
                horario.setIdHorario(resultSet.getInt("idHorario"));
                horario.setDia(resultSet.getString("dia"));
                horario.setHorarioAtencion(resultSet.getInt("horarioAtencion"));
                
//                Prestador p = buscarPrestador(resultSet.getInt("idPrestador"));
                horario.setIdPrestador(idPrestador);
                
                
                listaHorario.add(horario);
                
            }      
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los horarios: " + ex.getMessage());
        }
       
        return  listaHorario;
    }
    
    
  // Consulta: Y atiende en cada uno de esos horarios un solo paciente (para simplificar el proceso).
}
