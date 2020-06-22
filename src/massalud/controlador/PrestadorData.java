
package massalud.controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import massalud.modelo.Afiliado;
import massalud.modelo.Especialidad;
import massalud.modelo.Prestador;


public class PrestadorData {
    private Connection connection = null;
    private Conexion conexion;
    

    public PrestadorData(Conexion conexion) {
        try {
            this.conexion=conexion;
            connection = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion");
        }
    }
    
    
    
    // agregarPrestador()
    public void agregarPrestador(Prestador prestador){
        try {
            
            String sql = "INSERT INTO prestador (nombre, apellido, dni, activo, idEspecialidad) VALUES (?, ?, ?, ?, ? );";

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, prestador.getNombre());
            ps.setString(2, prestador.getApellido());
            ps.setInt(3, prestador.getDni());
            ps.setBoolean(4, prestador.isActivo());
            ps.setInt(5, prestador.getEspecialidad().getIdEspecialidad());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                prestador.setIdPrestador(rs.getInt(1));
                System.out.println("Se ha guardado el prestador: "+prestador.getNombre());
                
            } else {
                System.out.println("No se pudo obtener el id luego de insertar el prestador");
            }
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al guardar el prestador: " + ex.getMessage());
        }
    }

    public Prestador buscarPrestador(int idPrestador) {
        Prestador prestador = null;
        
        try {
            
            String sql = "SELECT * FROM prestador WHERE idPrestador =?;";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idPrestador);
           
            
            ResultSet resultSet=ps.executeQuery();
            
            if(resultSet.next()){
                prestador = new Prestador();
                prestador.setIdPrestador(resultSet.getInt("idPrestador"));
                prestador.setNombre(resultSet.getString("nombre"));
                prestador.setApellido(resultSet.getString("apellido"));
                prestador.setDni(resultSet.getInt("dni"));
                prestador.setActivo(resultSet.getBoolean("activo"));
                
                Especialidad e = buscarEspecialidad(resultSet.getInt("idEspecialidad"));
                prestador.setEspecialidad(e);

                
            }
         
            ps.close();
            
            
        } catch (SQLException ex) {
            System.out.println("Error al buscar un alumno: " + ex.getMessage());
        }
        
        return prestador;
    }
    
    public Especialidad buscarEspecialidad(int idEspecialidad){
    
        EspecialidadData ed= new EspecialidadData(conexion);
        
        return ed.buscarEspecialidad(idEspecialidad);
        
    }          
    
    
    //ListarPrestadores
    
    public List<Prestador> obtenerPrestadores(){
        List<Prestador> listaPrestadores = new ArrayList<Prestador>();
            

        try {
            String sql = "SELECT * FROM prestador;";
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ResultSet resultSet = ps.executeQuery();
            
            Prestador prestador;
            while(resultSet.next()){
                prestador = new Prestador();
                prestador.setIdPrestador(resultSet.getInt("idPrestador"));
                prestador.setNombre(resultSet.getString("nombre"));
                prestador.setApellido(resultSet.getString("apellido"));
                prestador.setDni(resultSet.getInt("dni"));
                prestador.setActivo(resultSet.getBoolean("activo"));
                
                Especialidad e = buscarEspecialidad(resultSet.getInt("idEspecialidad"));
                prestador.setEspecialidad(e);
                

                listaPrestadores.add(prestador);
                //System.out.println("Lista de prestadores/a: "+prestador.getNombre());
            }      
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los prestadores: " + ex.getMessage());
        }
        
        
        return  listaPrestadores;
    }
    
    //ListarPrestadoresActivos
    
    public List<Prestador> obtenerPrestadoresActivos(){
        List<Prestador> listaPrestadores = new ArrayList<Prestador>();
            

        try {
            String sql = "SELECT * FROM prestador WHERE activo = true;";
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ResultSet resultSet = ps.executeQuery();
            
            Prestador prestador;
            while(resultSet.next()){
                prestador = new Prestador();
                prestador.setIdPrestador(resultSet.getInt("idPrestador"));
                prestador.setNombre(resultSet.getString("nombre"));
                prestador.setApellido(resultSet.getString("apellido"));
                prestador.setDni(resultSet.getInt("dni"));
                prestador.setActivo(resultSet.getBoolean("activo"));
                
                Especialidad e = buscarEspecialidad(resultSet.getInt("idEspecialidad"));
                prestador.setEspecialidad(e);
                

                listaPrestadores.add(prestador);
                //System.out.println("Lista de prestadores/a: "+prestador.getNombre());
            }      
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los prestadores: " + ex.getMessage());
        }
        
        
        return  listaPrestadores;
    }
    
    //ListarPrestadorSegunEspecialidad
    
    public List<Prestador> obtenerPrestadoresPorEspecialidad(int idEspecialidad){
        List<Prestador> listaPrestadores = new ArrayList<Prestador>();
            

        try {
            String sql = "SELECT * FROM prestador WHERE idEspecialidad = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,idEspecialidad);
            ResultSet resultSet = ps.executeQuery();
            
            Prestador prestador;
            while(resultSet.next()){
                prestador = new Prestador();
                prestador.setIdPrestador(resultSet.getInt("idPrestador"));
                prestador.setNombre(resultSet.getString("nombre"));
                prestador.setApellido(resultSet.getString("apellido"));
                prestador.setDni(resultSet.getInt("dni"));
                prestador.setActivo(resultSet.getBoolean("activo"));
                
                Especialidad e = buscarEspecialidad(resultSet.getInt("idEspecialidad"));
                prestador.setEspecialidad(e);
                

                listaPrestadores.add(prestador);
                //System.out.println("Lista de prestadores/a: "+prestador.getNombre());
            }      
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los prestadores: " + ex.getMessage());
        }
       
        return  listaPrestadores;
    }
    
    
    //ActualizarPrestador(Prestador prestador)
    
    public void actualizarPrestador(Prestador prestador){
    
        try {
            
            String sql = "UPDATE prestador SET nombre = ?, apellido = ? , "
                    + "dni =?, activo =?, idEspecialidad = ? WHERE idPrestador = ?;";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, prestador.getNombre());
            ps.setString(2, prestador.getApellido());
            ps.setInt(3, prestador.getDni());            
            ps.setBoolean(4, prestador.isActivo());     
            ps.setInt(5, prestador.getEspecialidad().getIdEspecialidad());
            ps.setInt(6, prestador.getIdPrestador());
            
            
            
            ps.executeUpdate();
            
          
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al actualizar el prestador: " + ex.getMessage());
        }
    
    }
}
