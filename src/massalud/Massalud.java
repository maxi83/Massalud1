
package massalud;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import massalud.controlador.*;
import massalud.modelo.*;

public class Massalud {

    public static void main(String[] args) {
        try {
        
            Afiliado f1 = new Afiliado("Maxi", "Capo", 30157165, true);
        
        
            // Creamos conexion
            Conexion con = new Conexion();
            
            // TESTEANDO AFIALIADODATA
            // Creamos AfiliadoData
            AfiliadoData ad = new AfiliadoData(con);
//            ad.agregarAfiliado(f1);

            // Listar Afiliados Activos
//            List<Afiliado> listaAfiliados = ad.listarAfiliadosActivos();
//            for(Afiliado af : listaAfiliados){
//                System.out.println(af);
//            }
            
            // Actualizamos estado de un afiliado
//            f1.setActivo(true);
//            ad.actualizarEstadoAfiliado(f1);

            //actualizar un Afiliado
//            f1.setApellido("Perez");
//            f1.setNombre("Marco");
//            ad.actualizarAfiliado(f1);

            //Buscamos a un afiliado
//            Afiliado f2 = ad.buscarAfiliado(28000111);
//            System.out.println(f2.toString());
            
            //Obteniendo lista de afiliados
//            List<Afiliado> f3 = ad.listarAfiliados();                       
//                 
//            for(Afiliado afiliado : f3){
//                System.out.println(afiliado.toString());
//            }
            
                     
            // TESTEANDO ESPECIALIDADDATA
            EspecialidadData ed = new EspecialidadData(con);
            // Agregando especialidad
            Especialidad e1 = new Especialidad("Cardiología");
//            ed.agregarEspecialidad(e1);
            
            // buscarEspecialidad
//            Especialidad e2 = ed.buscarEspecialidad(1);
//            System.out.println(e2);
            
            //TESTEANDO PRESTADORDATA
            Prestador p1 = new Prestador("Carlos", "Torres", 11199923, true, e1);
            PrestadorData pd = new PrestadorData(con);
//            pd.agregarPrestador(p1);
            
            // TESTEANDO AFILIADODATA
            Afiliado testAfiliado = ad.buscarAfiliado(28000111);
            HorarioData hd = new HorarioData(con);
            
            Horario horario1 = hd.buscarHorario(1);
            Orden o1 = new Orden(LocalDate.now(), "tarjeta", 100f, testAfiliado, horario1, true);
//            Orden o2 = ad.comprarOrden(f1, p1, "efectivo");

            //TESTEANDO ORDENDATA
            OrdenData od = new OrdenData(con);
            od.generarOrden(o1);
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Massalud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}