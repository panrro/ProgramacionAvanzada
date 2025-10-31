package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import bll.HistoriaClinica;

public class DtoHistoriaClinica {

	private static Connection con = Conexion.getInstance().getConnection();

	
	public static boolean InsertarHistoriaClinica(String descripcion, String fecha, int doctor_id, int paciente_id) {
	    try {
	    	  PreparedStatement statement = con.prepareStatement(
	    				"INSERT INTO `historia_clinica`(`descripcion`, `fecha`, `doctor_id`, `paciente_id`) VALUES (?,?,?,?)");
				statement.setString(1, descripcion);
				statement.setString(2, fecha);
				statement.setInt(3, doctor_id);
				statement.setInt(4, paciente_id);
							        
	        int filas = statement.executeUpdate();
	        if (filas > 0) {
	            System.out.println("Turno agregado correctamente.");
	            return true;
	        }
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado.");
	        e.printStackTrace();
	        return false;
	    }
	    return false;
	}


	

}


