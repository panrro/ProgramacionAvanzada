package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JOptionPane;

import bll.Paciente;
import bll.Usuario;

public class Dtopaciente {

	private static Connection con = Conexion.getInstance().getConnection();
      
			public static boolean InsertarPaciente(String nombre, String apellido, String dni, int obraSocialId, int edad, int doctorId) {
			    try {
			    	  PreparedStatement statement = con.prepareStatement(
			            		"INSERT INTO `paciente`(`nombre`, `apellido`, `dni`, `obraSocial_id`, `edad`, `doctor_id`) VALUES 					(?,?,?,?,?,?) ");
			           
						statement.setString(1, nombre);
						statement.setString(2, apellido);
						statement.setString(3, dni);
						statement.setInt(4, obraSocialId);
						statement.setInt(5, edad);
						statement.setInt(6, doctorId);			        
			        int filas = statement.executeUpdate();
			        if (filas > 0) {
			            System.out.println("Paciente agregado correctamente.");
			            return true;
			        }
			    } catch (java.sql.SQLIntegrityConstraintViolationException e) { // <--- ¡Cambio Importante!
			        // Este catch maneja específicamente los errores de UNIQUE o FOREIGN KEY.
			        JOptionPane.showMessageDialog(null, "El DNI o ID del paciente ya se encuentra registrado o hay un ID de Obra Social/Doctor inválido.");
			        // Imprime el error real en la consola para depurar
			        e.printStackTrace(); 
			        return false;
			    } catch (java.sql.SQLException e) { 
			        // Este catch maneja otros errores de SQL
			        JOptionPane.showMessageDialog(null, "Error de SQL desconocido.");
			        e.printStackTrace(); 
			        return false;
			    } catch (Exception e) {
			        // Para cualquier otro error no previsto (ej: error de conexión)
			        JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.");
			        e.printStackTrace();
			        return false;
			    }
			    return false;
			}
}
