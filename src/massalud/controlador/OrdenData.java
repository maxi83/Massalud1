
package massalud.controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import massalud.modelo.Afiliado;
import massalud.modelo.Orden;
import massalud.modelo.Prestador;

public class OrdenData {
    private Connection con;

    public OrdenData(Conexion conexion) {
        try {
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
    
    
    // ListarOrdenesPorDniAfiliado(int dniAfiliado)
    
    // ListarOrdenesPorFecha(LocalDate localdate)
    
    //ActualizarOrden(Orden orden)
    
    //CambiarEstadoOrden(Orden orden)
    
}
