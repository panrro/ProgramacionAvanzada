package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import bll.Doctor;
import bll.HistoriaClinica;
import bll.Paciente;
import bll.Turnos;

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

	
	public static LinkedList<HistoriaClinica> VerHistorias(int idPaciente) {
	    LinkedList<HistoriaClinica> lista = new LinkedList<>();

	    try {
	        PreparedStatement stmt = con.prepareStatement("""
	            SELECT 
	                h.descripcion,
	                h.fecha,
	                u.nombre AS nombre_doctor,
	                u.apellido AS apellido_doctor
	            FROM historia_clinica h
	            JOIN doctor d ON h.doctor_id = d.id
	            JOIN usuario u ON d.usuario_id = u.id
	            WHERE h.paciente_id = ?
	            ORDER BY h.fecha DESC
	        """);

	        stmt.setInt(1, idPaciente);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            String descripcion = rs.getString("descripcion");
	            String fecha = rs.getString("fecha");
	            String nombreDoctor = rs.getString("nombre_doctor") + " " + rs.getString("apellido_doctor");

	            HistoriaClinica historia = new HistoriaClinica(fecha, descripcion, nombreDoctor);
	            lista.add(historia);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error al obtener historias clínicas");
	    }

	    return lista;
	}


	

}


