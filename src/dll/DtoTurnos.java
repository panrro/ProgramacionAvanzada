package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class DtoTurnos {

	private static Connection con = Conexion.getInstance().getConnection();

	public static boolean InsertarTurno(String fecha, int recepcionista_id, int doctor_id, int paciente_id) {
	    try {
	    	  PreparedStatement statement = con.prepareStatement(
	    			  "INSERT INTO `turnos`(`fecha`, `recepcionista_id`, `doctor_id`, `paciente_id`) VALUES (?,?,?,?");	           
				statement.setString(1, fecha);
				statement.setInt(2, recepcionista_id);
				statement.setInt(3, doctor_id);
				statement.setInt(4, paciente_id);
							        
	        int filas = statement.executeUpdate();
	        if (filas > 0) {
	            System.out.println("Paciente agregado correctamente.");
	            return true;
	        }
	    } catch (java.sql.SQLIntegrityConstraintViolationException e) { 
	        JOptionPane.showMessageDialog(null, "El DNI o ID del paciente ya se encuentra registrado o hay un ID de Obra 			Social/Doctor inválido.");
	        e.printStackTrace(); 
	        return false;
	    } catch (java.sql.SQLException e) { 
	        JOptionPane.showMessageDialog(null, "Error de SQL desconocido.");
	        e.printStackTrace(); 
	        return false;
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.");
	        e.printStackTrace();
	        return false;
	    }
	    return false;
	}
	
}
