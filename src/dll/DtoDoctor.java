package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import bll.Doctor;
import bll.Especialidad;
import bll.Usuario;

public class DtoDoctor {

    private static Connection con = Conexion.getInstance().getConnection();

//    public static List<String> cargarEspecialidades() {
//        List<String> especialidades = new ArrayList<>();
//        
//        try {
//            PreparedStatement stmt = con.prepareStatement(
//                "SELECT especialidad FROM doctor WHERE usuario_id = ?" 
//            );
//            stmt.setInt(1, doctorId);
//
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                especialidades.add(rs.getString("especialidad"));
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error al cargar especialidades (Tabla doctor): " + e.getMessage());
//            e.printStackTrace();
//        }
//        return especialidades;
//    }

//    public static boolean agregarNuevaEspecialidad() {
//        try {
//            PreparedStatement stmt = con.prepareStatement(
//                    "INSERT INTO especialidad (nombre) VALUES (?)"
//            );
//            stmt.setString(1, Especialidad.);
//            stmt.setInt(2, doctorId);
//
//            int filasAfectadas = stmt.executeUpdate();
//            
//            if (filasAfectadas > 0) {
//                JOptionPane.showMessageDialog(null, "Especialidad principal actualizada correctamente.");
//                return true;
//            }
//            return false;
//
//        } catch (Exception e) {
//        	e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Error al actualizar especialidad");
//            return false;
//        }
//    }
    
    public static boolean agregarDoctor(int id, int obraSocialId) {
        try {             	
            PreparedStatement statement = con.prepareStatement(
            		"INSERT INTO `doctor`( `especialidad_id`, `usuario_id`, `obrasocial_id` ) VALUES (?,?,?)");
            statement.setInt(1, id);
            statement.setInt(2, Usuario.UltimoUsuario().getId());
            statement.setInt(3, obraSocialId);

            
            int filas = statement.executeUpdate();
            if (filas > 0) {
                System.out.println("Doctor agregado correctamente.");
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El doctor ya se encuentra registrado.");
            return false;
        }
        return false;
}
    
}