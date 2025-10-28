package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import bll.Doctor;
import bll.Especialidad;
import bll.Usuario;

public class DtoDoctor {

    private static Connection con = Conexion.getInstance().getConnection();

    public static boolean EditarUsuario(Doctor doctor) {
        try {
        	PreparedStatement statement = con.prepareStatement(
            	"UPDATE usuario SET nombre = ?, apellido = ?, dni = ?, mail = ?, contrasenia = ?  WHERE id = ?"
            			);
            			statement.setString(1, doctor.getNombre());
            			statement.setString(2, doctor.getApellido());
            			statement.setString(3, doctor.getDni());
            			statement.setString(4, doctor.getMail());
            			statement.setString(5, doctor.getContrasenia());
            			statement.setString(6, doctor.getTipo());
            			statement.setInt(7, doctor.getId());

            int filas = statement.executeUpdate();
            if (filas > 0) {
                System.out.println("Usuario editado correctamente.");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
    
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
    
    public static LinkedList<Doctor> VerDoctores() {
    	LinkedList<Doctor> doctores = new LinkedList<Doctor>();
        try {             	
            PreparedStatement stmt = con.prepareStatement(
            		"SELECT * FROM doctor");	           

			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) { //Aca en ves de if, va un while ya que al ser una lista especialidades, voy a querer que mientra que 		hayan datos se repitam y que no me traiga solo la fila afectada con el if
				 int id = rs.getInt("id");
				 int especialidad_id = rs.getInt("especialidad_id");
				 int usuario_id = rs.getInt("usuario_id");
				 int obrasocial_id = rs.getInt("obrasocial_id");
				 doctores.add(new Doctor(id,especialidad_id, usuario_id,obrasocial_id));	        
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctores;
	}
    
}