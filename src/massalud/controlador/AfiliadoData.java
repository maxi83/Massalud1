
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


public class AfiliadoData {
    private Connection con;

    public AfiliadoData(Conexion conexion) {
        try {
            con = conexion.getConexion();
            
            System.out.println("Conecetado a la base de datos");
            
        } catch (SQLException ex) {
            System.out.println("Error sl obtener la conexion");
        }
    }
    
    // agregarAfiado()
    public void agregarAfiliado(Afiliado afiliado){
        try {
            
            String sql = "INSERT INTO afiliado (nombre, apellido, dni, activo) VALUES (?, ?, ?, ? );";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, afiliado.getNombre());
            ps.setString(2, afiliado.getApellido());
            ps.setInt(3, afiliado.getDni());
            ps.setBoolean(4, afiliado.isActivo());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                afiliado.setIdAfiliado(rs.getInt(1));
                System.out.println("Se ha guardado al afiliado/a: "+afiliado.getNombre());
                
            } else {
                System.out.println("No se pudo obtener el id luego de insertar el afiliado");
            }
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al guardar el afiliado: " + ex.getMessage());
        }
    }
            
    // actualizarEstadoAfiliado()
    public void actualizarEstadoAfiliado(Afiliado afiliado){ 
    
        try {
            
            String sql = "UPDATE afiliado SET activo = ? WHERE dni = ?;";

            PreparedStatement ps = con.prepareStatement(sql);         
            ps.setBoolean(1, afiliado.isActivo());    
            ps.setInt(2, afiliado.getDni());  
            ps.executeUpdate();
            
          
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al actualizar un afiliado: " + ex.getMessage());
        }
    
    }
    
    // actualizarAfiliado()    
    public void actualizarAfiliado(Afiliado afiliado){
    
        try {
            
            String sql = "UPDATE afiliado SET nombre = ?, apellido = ? , "
                    + "dni = ? ,activo =? WHERE dni = ?;";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, afiliado.getNombre());
            ps.setString(2, afiliado.getApellido());
            ps.setInt(3, afiliado.getDni());
            ps.setBoolean(4, afiliado.isActivo());
            ps.setInt(5, afiliado.getDni());
            ps.executeUpdate();
            
          
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al actualizar el afiliado: " + ex.getMessage());
        }
    
    }
    
    // buscarAfliado()
    public Afiliado buscarAfiliado(int idAfiliado){
    Afiliado afiliado=null;
    
   
    try {
            
            String sql = "SELECT * FROM afiliado WHERE idAfiliado =?;";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idAfiliado);
           
            
            ResultSet resultSet=ps.executeQuery();
            
            if(resultSet.next()){
                afiliado = new Afiliado();
                afiliado.setIdAfiliado(resultSet.getInt("idAfiliado"));
                afiliado.setNombre(resultSet.getString("nombre"));
                afiliado.setApellido(resultSet.getString("apellido"));
                afiliado.setDni(resultSet.getInt("dni"));
                afiliado.setActivo(resultSet.getBoolean("activo"));

                
            }      
            ps.close();
            
            
        } catch (SQLException ex) {
            System.out.println("Error al buscar el afiliado: " + ex.getMessage());
        }
        
        return afiliado;
    }
    
    // buscarAfliadoPorDni()
    public Afiliado buscarAfiliadoPorDni(int dniAfiliado){
    Afiliado afiliado=null;
    
   
    try {
            
            String sql = "SELECT * FROM afiliado WHERE dni =?;";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dniAfiliado);
           
            
            ResultSet resultSet=ps.executeQuery();
            
            if(resultSet.next()){
                afiliado = new Afiliado();
                afiliado.setIdAfiliado(resultSet.getInt("idAfiliado"));
                afiliado.setNombre(resultSet.getString("nombre"));
                afiliado.setApellido(resultSet.getString("apellido"));
                afiliado.setDni(resultSet.getInt("dni"));
                afiliado.setActivo(resultSet.getBoolean("activo"));

                
            }      
            ps.close();
            
            
        } catch (SQLException ex) {
            System.out.println("Error al buscar el afiliado por dni: " + ex.getMessage());
        }
        
        return afiliado;
    }
    
    // listarAfiliados()
    public List<Afiliado> listarAfiliados(){
        List<Afiliado> listaAfiliados = new ArrayList<>();
            

        try {
            String sql = "SELECT * FROM afiliado;";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet resultSet = ps.executeQuery();
            
//            Afiliado afiliado;
            while(resultSet.next()){
                Afiliado afiliado = new Afiliado();
                afiliado.setIdAfiliado(resultSet.getInt("idAfiliado"));
                afiliado.setNombre(resultSet.getString("nombre"));
                afiliado.setApellido(resultSet.getString("apellido"));
                afiliado.setDni(resultSet.getInt("dni"));
                afiliado.setActivo(resultSet.getBoolean("activo"));

                listaAfiliados.add(afiliado);
                //System.out.println("Lista de afiliados: "+afiliado.getNombre());
            }      
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener la lista de afiliados: " + ex.getMessage());
        }
        
        
        return  listaAfiliados;
    }
    
    //Listar Afiliados Activos
    public List<Afiliado> listarAfiliadosActivos(){
        List<Afiliado> listaAfiliados = new ArrayList<>();
            

        try {
            String sql = "SELECT * FROM afiliado WHERE activo = true;";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet resultSet = ps.executeQuery();
            
//            Afiliado afiliado;
            while(resultSet.next()){
                Afiliado afiliado = new Afiliado();
                afiliado.setIdAfiliado(resultSet.getInt("idAfiliado"));
                afiliado.setNombre(resultSet.getString("nombre"));
                afiliado.setApellido(resultSet.getString("apellido"));
                afiliado.setDni(resultSet.getInt("dni"));
                afiliado.setActivo(resultSet.getBoolean("activo"));

                listaAfiliados.add(afiliado);
                //System.out.println("Lista de afiliados: "+afiliado.getNombre());
            }      
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener la lista de afiliados: " + ex.getMessage());
        }
        
        
        return  listaAfiliados;
    }
    
        
}
