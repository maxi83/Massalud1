
package massalud.controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import massalud.modelo.Afiliado;
import massalud.modelo.Especialidad;
import massalud.modelo.Horario;
import massalud.modelo.Orden;
import massalud.modelo.Prestador;

public class OrdenData {
    private Connection con;
    private Conexion conexion;

    public OrdenData(Conexion conexion) {
        try {
            this.conexion = conexion;
            con = conexion.getConexion();
            
            System.out.println("Conecetado a la base de datos");
            
        } catch (SQLException ex) {
            System.out.println("Error sl obtener la conexion");
        }
    }
    
    
    public void generarOrden(Orden orden){
        if(orden.getIdAfiliado().isActivo()){ 
            try {
            
            String sql = "INSERT INTO orden (fecha, formaPago, importe, idAfiliado, idHorario, activo) VALUES ( ? , ? , ?, ? , ?, ? );";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, Date.valueOf(orden.getFecha()));
            ps.setString(2, orden.getFormaPago());
            ps.setFloat(3, orden.getImporte());
            ps.setInt(4, orden.getIdAfiliado().getIdAfiliado());
            ps.setInt(5, orden.getIdHorario().getIdHorario());
            ps.setBoolean(6, orden.isActivo());
            
        
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                orden.setIdOrden(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id luego de insertar la orden");
            }
            ps.close();
    
            } catch (SQLException ex) {
                System.out.println("Error al generar la orden: " + ex.getMessage());
            }
        }else{
            System.out.println("El Afiliado esta inactivo por lo tanto no puede sacar ordenes");
        }
        
        
    }
    
    public Afiliado buscarAfiliado(int idAfiliado){
    
        AfiliadoData ad= new AfiliadoData(conexion);
        
        return ad.buscarAfiliado(idAfiliado);
        
    }
    
    public Horario buscarHorario(int idHorario){
    
        HorarioData hd= new HorarioData(conexion);
        
        return hd.buscarHorario(idHorario);
        
    }
    
    //Buscar Orden()
    public Orden buscarOrden(int idOrden) {
        Orden orden = null;
        
        try {
            
            String sql = "SELECT * FROM orden WHERE idOrden =?;";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idOrden);
           
            
            ResultSet resultSet=ps.executeQuery();
            
            if(resultSet.next()){
                orden = new Orden();
                orden.setIdOrden(resultSet.getInt("idOrden"));
                orden.setFecha(resultSet.getDate("fecha").toLocalDate());
                orden.setFormaPago(resultSet.getString("formaPago"));
                orden.setImporte(resultSet.getFloat("importe"));
                
                Afiliado a = buscarAfiliado(resultSet.getInt("idAfiliado"));
                orden.setIdAfiliado(a);
                
                Horario h = buscarHorario(resultSet.getInt("idHorario"));
                orden.setIdHorario(h);
                
                orden.setActivo(resultSet.getBoolean("activo"));
                
                              
            }      
            ps.close();
            
            
        } catch (SQLException ex) {
            System.out.println("Error al buscar la orden: " + ex.getMessage());
        }
        
        return orden;
    }
    
    // ListarOrdenesPorDniAfiliado(int dniAfiliado)
    
    // ListarOrdenesPorFecha(LocalDate localdate)
    
    
    //ActualizarOrden(Orden orden)
    public void actualizarOrden(Orden orden){
    
        try {
            
            String sql = "UPDATE orden SET fecha = ?, formaPago = ?, "
                    + "importe = ?, idAfiliado = ?, idHorario = ?, activo = ? WHERE idOrden = ?;";

            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDate(1, Date.valueOf(orden.getFecha()));   
            ps.setString(2, orden.getFormaPago());
            ps.setFloat(3, orden.getImporte());          
            ps.setInt(4, orden.getIdAfiliado().getIdAfiliado());
            ps.setInt(5, orden.getIdHorario().getIdHorario());
            ps.setBoolean(6, orden.isActivo());
            ps.setInt(7, orden.getIdOrden());
            
            ps.executeUpdate();
            
          
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al actualizar la orden: " + ex.getMessage());
        }
    
    }
    
    //CambiarEstadoOrden(Orden orden)
    public void actualizarEstadoOrden(Orden orden){
    
        try {
            
            String sql = "UPDATE orden SET activo = ? WHERE idOrden = ?;";

            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1, orden.isActivo());   
            ps.setInt(2, orden.getIdOrden());
            
            ps.executeUpdate();
            
          
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al actualizar el estado de la orden: " + ex.getMessage());
        }
    
    }
}
