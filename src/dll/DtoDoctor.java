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
import repository.Encriptador;

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
    
    public static boolean agregarDoctor(int idEspecialidad, int obraSocialId) {
        try {             	
            PreparedStatement statement = con.prepareStatement(
            		"INSERT INTO `doctor`( `especialidad_id`, `usuario_id`, `obrasocial_id` ) VALUES (?,?,?)");
            statement.setInt(1, idEspecialidad);
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
        	PreparedStatement stmt = con.prepareStatement("""
        		    SELECT d.id,
        		           u.nombre AS nombre_usuario,
        		           e.nombre AS nombre_especialidad,
        		           o.nombre AS nombre_obrasocial
        		    FROM doctor d
        		    JOIN usuario u ON d.usuario_id = u.id
        		    JOIN especialidad e ON d.especialidad_id = e.id
        		    JOIN obrasocial o ON d.obrasocial_id = o.id
        		""");

        		ResultSet rs = stmt.executeQuery();
        		while (rs.next()) {
        		    int id = rs.getInt("id");
        		    String nombreUsuario = rs.getString("nombre_usuario");
        		    String nombreEspecialidad = rs.getString("nombre_especialidad");
        		    String nombreObraSocial = rs.getString("nombre_obrasocial");

        		    doctores.add(new Doctor(id, nombreUsuario, nombreEspecialidad, nombreObraSocial));	        
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctores;
	}
    
    public static Doctor VerPerfilDoctor(int idDoctor) {
        Doctor doctor = null;
        try {
            PreparedStatement stmt = con.prepareStatement("""
                SELECT 
                    d.id AS doctor_id,
                    u.nombre AS nombre_usuario,
                    u.apellido AS apellido_usuario,
                    e.nombre AS nombre_especialidad,
                    o.nombre AS nombre_obrasocial
                FROM doctor d
                JOIN usuario u ON d.usuario_id = u.id
                JOIN especialidad e ON d.especialidad_id = e.id
                JOIN obrasocial o ON d.obrasocial_id = o.id
                WHERE d.id = ?
            """);

            stmt.setInt(1, idDoctor);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("doctor_id");
                String nombreUsuario = rs.getString("nombre_usuario") + " " + rs.getString("apellido_usuario");
                String nombreEspecialidad = rs.getString("nombre_especialidad");
                String nombreObraSocial = rs.getString("nombre_obrasocial");

                doctor = new Doctor(id, nombreUsuario, nombreEspecialidad, nombreObraSocial);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctor;
    }

    
    public static boolean EditarDoctor(Doctor doctor) {
        try {
        	PreparedStatement statement = con.prepareStatement(
            	"UPDATE usuario SET nombre = ?, apellido = ?, dni = ?, mail = ?, contrasenia = ?, tipo = ? WHERE id = ?"
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
    
    public static boolean EditarPerfilDoctor(int idUsuario, int idObraSocial, String mail, String contrasenia) {
        try {
            

            PreparedStatement stmtUsuario = con.prepareStatement(
                "UPDATE usuario SET mail = ?, contrasenia = ? WHERE id = ?"
            );
            stmtUsuario.setString(1, mail);
            stmtUsuario.setString(2, Encriptador.encriptar(contrasenia)); 
            stmtUsuario.setInt(3, idUsuario);
            stmtUsuario.executeUpdate();

            PreparedStatement stmtDoctor = con.prepareStatement(
                "UPDATE doctor SET obrasocial_id = ? WHERE usuario_id = ?"
            );
            stmtDoctor.setInt(1, idObraSocial);
            stmtDoctor.setInt(2, idUsuario);

            int filas = stmtDoctor.executeUpdate();
            System.out.println("Filas afectadas en doctor: " + filas);

            if (filas > 0) {
                System.out.println("Perfil actualizado correctamente");
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al editar el perfil");
            return false;
        }
        return false;
    }


    
}