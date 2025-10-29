package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import bll.Recepcionista;
import bll.Turnos;
import bll.Usuario;

public class DtoTurnos {

	private static Connection con = Conexion.getInstance().getConnection();

	public static boolean InsertarTurno(String fecha, int recepcionista_id, int doctor_id, int paciente_id) {
	    try {
	    	  PreparedStatement statement = con.prepareStatement(
	    			    "INSERT INTO `turnos`(`fecha`, `recepcionista_id`, `doctor_id`, `paciente_id`) VALUES (?,?,?,?)");
				statement.setString(1, fecha);
				statement.setInt(2, recepcionista_id);
				statement.setInt(3, doctor_id);
				statement.setInt(4, paciente_id);
							        
	        int filas = statement.executeUpdate();
	        if (filas > 0) {
	            System.out.println("Turno agregado correctamente.");
	            return true;
	        }
	    } catch (java.sql.SQLIntegrityConstraintViolationException e) { 
	        JOptionPane.showMessageDialog(null, "El turno ya se encuentra asignado");
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
	
	public static LinkedList<Turnos> VerTurnos() {
	    LinkedList<Turnos> turnos = new LinkedList<>();
	try {
	    PreparedStatement stmt = con.prepareStatement(
	    		 "SELECT t.id AS turno_id, t.fecha, " +
	    				    "u_doc.nombre AS nombre_doctor, u_doc.apellido AS apellido_doctor, " +
	    				    "p.apellido AS apellido_paciente " +
	    				    "FROM turnos t " +
	    				    "JOIN doctor d ON t.doctor_id = d.id " +
	    				    "JOIN usuario u_doc ON d.usuario_id = u_doc.id " +
	    				    "JOIN paciente p ON t.paciente_id = p.id"
	    );

	    ResultSet rs = stmt.executeQuery();

	    while (rs.next()) {        
	    	 int id= rs.getInt("turno_id");
	            String fecha=  rs.getString("fecha");
	             String nombreDoc = rs.getString("nombre_doctor");
		            String apellidoDoc = rs.getString("apellido_doctor");
	             String apellidoPaciente = rs.getString("apellido_paciente");
		            turnos.add(new Turnos(id, fecha, nombreDoc, apellidoDoc, apellidoPaciente));
	    }

		} catch (Exception e) {
		    e.printStackTrace();
		}
		return turnos;
	}
	
	public static void cancelarTurno(int id) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "DELETE FROM `turnos` WHERE id = ?"
            );
            statement.setInt(1, id);

            int filas = statement.executeUpdate();
            if (filas > 0) {
                System.out.println("Turno cancelado correctamente.");
            } else {
                 JOptionPane.showMessageDialog(null, "No se encontró ningún turno con ese ID.");
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public static Turnos buscarPorId() {
	    int id = 0;

	    List<Turnos> turnos = Turnos.mostrarTurnos();

	    String[] turnosArray = new String[turnos.size()];
	    for (int i = 0; i < turnos.size(); i++) {
	        turnosArray[i] = turnos.get(i).getApellidoPaciente() + "/" +
	                         turnos.get(i).getFecha() + "/" +
	                         turnos.get(i).getId();
	    }

	    String elegido = (String) JOptionPane.showInputDialog(null, "Elija el turno a cancelar", null,0,null, turnosArray, turnosArray[0]);
		id = Integer.parseInt(elegido.split("/")[2]);
		Turnos turno = null;

	    id = Integer.parseInt(elegido.split("/")[2]);

	    try {
	    	PreparedStatement stmt = con.prepareStatement(
	    			 "SELECT t.id AS turno_id, t.fecha, " +
		    		"u_doc.nombre AS nombre_doctor, u_doc.apellido AS apellido_doctor, " +
		    		"p.apellido AS apellido_paciente " +
		    		"FROM turnos t " +
		    		"JOIN doctor d ON t.doctor_id = d.id " +
		    		"JOIN usuario u_doc ON d.usuario_id = u_doc.id " +
		    		"JOIN paciente p ON t.paciente_id = p.id " +
		    		"WHERE t.id = ?"
	     );
	        stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

	            if (rs.next()) {
	                int turnoId = rs.getInt("turno_id");
	                String fecha = rs.getString("fecha");
	                String nombreDoc = rs.getString("nombre_doctor");
	                String apellidoDoc = rs.getString("apellido_doctor");
	                String apellidoPac = rs.getString("apellido_paciente");

	                turno = new Turnos(turnoId, fecha, nombreDoc, apellidoDoc, apellidoPac);
	            }
	        
	    }catch (Exception e) {
	        e.printStackTrace();
	    }

	    return turno;
	}


}
