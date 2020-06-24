/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package massalud.controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import massalud.modelo.Cirugia;


/**
 *
 * @author Maxi
 */
public class CirugiaData {
     private Conexion conexion;
    private Connection con;

    public CirugiaData(Conexion conexion) {
        try {
            this.conexion = conexion;
            con = conexion.getConexion();
            
            System.out.println("Conecetado a la base de datos");
            
        } catch (SQLException ex) {
            System.out.println("Error sl obtener la conexion");
        }
    }
    
    public void agregarCirugia(Cirugia cirugia){
        
        try {
            
            String sql = "INSERT INTO cirugia (nombre, idPrestador, idHorario, activo) VALUES (?, ?, ?, ? );";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, cirugia.getNombre());
            ps.setInt(2, cirugia.getIdPrestador());
            ps.setInt(3, cirugia.getIdPrestador());
            ps.setBoolean(4, cirugia.isActivo());
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                cirugia.getIdCirugia(rs.getInt(1));
                System.out.println("Se ha guardado la cirugia: "+cirugia.getNombre());
                
            } else {
                System.out.println("No se pudo obtener el id luego de insertar el afiliado");
            }
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al guardar la cirugia: " + ex.getMessage());
        }
    }
}
