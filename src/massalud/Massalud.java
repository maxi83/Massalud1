
package massalud;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
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
            Afiliado testAfiliado = ad.buscarAfiliado(30157163);
            
            HorarioData hd = new HorarioData(con);
            
            Horario horario1 = hd.buscarHorario(1);
            Orden o1 = new Orden(LocalDate.now(), "efectivo", 199f, testAfiliado, horario1, true);
//            Orden o2 = ad.comprarOrden(f1, p1, "efectivo");

            //TESTEANDO ORDENDATA
            OrdenData od = new OrdenData(con);
//            od.generarOrden(o1);
            
            //TESTEANDO ESPECIALIDADDATA
            // ActualizarEspecialidad
//            Especialidad dentista = ed.buscarEspecialidad(2);
//            dentista.setEspecialidad("Dentista");
//            ed.actualizarEspecialidad(dentista);
            
            // TESTEANDO PRESTADORDATA
            // ListarPrestadores
//            List<Prestador> listaDePrestadores = pd.obtenerPrestadores();
//            for(Prestador p : listaDePrestadores){
//                System.out.println(p);
//            }
            
            // ListarPrestadoresActivos
//            List<Prestador> listaDePrestadoresActivos = pd.obtenerPrestadoresActivos();
//            for(Prestador p : listaDePrestadoresActivos){
//                System.out.println(p);
//            }
            
            // ListarPrestadoresPorEspecialidad
//            List<Prestador> listaDePrestadoresEspecialidad = pd.obtenerPrestadoresPorEspecialidad(1);
//            for(Prestador p : listaDePrestadoresEspecialidad){
//                System.out.println(p);
//            }            
            

            //TESTEANDO PRESTADORDATA
            //ActualizarPrestador
//            Prestador pAActualizar = pd.buscarPrestador(3);
//            Especialidad eAActualizar = ed.buscarEspecialidad(2);
//            
//            pAActualizar.setNombre("Jorge");
//            pAActualizar.setEspecialidad(eAActualizar);
//            
//            pd.actualizarPrestador(pAActualizar);
            

            // TESTEANDO HORARIODATA
            // Actualizando Horario
//           Horario h1 = hd.buscarHorario(2);
//           h1.setDia("jueves");
//           hd.actualizarHorario(h1);
           
//            Lista de Horarios por Prestador
//            List<Horario> listaHorarios = hd.obtenerHorarioPorPrestador(3);
//            for(Horario h : listaHorarios){
//                System.out.println(h);
//            }

            // Agregar Horario
//            Prestador pRuben = pd.buscarPrestador(5);
//            Horario h2 = new Horario("martes", 17, pRuben);            
//            hd.agregarHorario(h2);
            
            // TESTEANDO ORDENDATA
            //Buscar Orden
            Orden o3 = od.buscarOrden(8);
//            System.out.println(o3);
            
            //Actualizar Orden
//            o3.setFormaPago("efectivo");
//            System.out.println(o3);
//            od.actualizarOrden(o3);
//            System.out.println(o3);

            //Actualizar Estado Orden
//            o3.setActivo(false);
//            od.actualizarEstadoOrden(o3);
           

            //Listar Ordenes por Dni Afiliado
//            List<Orden> listaOrdenDni = od.listarOrdenesPorAfiliado(28000111);
//            for(Orden o : listaOrdenDni){
//                System.out.println(o);
//            }

            // Listar Ordenes por Fecha
//            List<Orden> listaOrdenFecha = od.listarOrdenesPorFecha(LocalDate.of(2020, 6, 19));
//            
//            for(Orden o : listaOrdenFecha){
//                System.out.println(o);
//            }

            //TESTEANDO AFILIADODATA
            //Buscar Afiliado Por Dni
            Afiliado aPorDni = ad.buscarAfiliadoPorDni(28000111);
            System.out.println(aPorDni);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Massalud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
