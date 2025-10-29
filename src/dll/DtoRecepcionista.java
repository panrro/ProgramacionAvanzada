package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import bll.Doctor;
import bll.Recepcionista;
import bll.Usuario;
import repository.Encriptador;

public class DtoRecepcionista {
	
	

	private static Connection con = Conexion.getInstance().getConnection();

	
	
	public static boolean agregarRecepcionista() {
        try {
        	       	
            PreparedStatement statement = con.prepareStatement(
            		"INSERT INTO `recepcionista`(usuario_id) VALUES (?) ");
            statement.setInt(1, Usuario.UltimoUsuario().getId());
            

            int filas = statement.executeUpdate();
            if (filas > 0) {
                System.out.println("Recepcionista agregado correctamente.");
                return true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El recepcionista ya se encuentra registrado.");
            return false;
        }
        return false;
}

	public static LinkedList<Recepcionista> VerRecepcionistas() {
	    LinkedList<Recepcionista> recepcionistas = new LinkedList<>();

	    try {
	        PreparedStatement stmt = con.prepareStatement(
	            "SELECT r.id AS recepcionista_id, r.usuario_id, " +
	            "u.nombre, u.apellido " +   
	            "FROM recepcionista r " +
	            "JOIN usuario u ON r.usuario_id = u.id"
	        );

	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            int id = rs.getInt("recepcionista_id");
	            int usuario_id = rs.getInt("usuario_id");
	            String nombre = rs.getString("nombre");
	            String apellido = rs.getString("apellido");
	            

	            recepcionistas.add(new Recepcionista(id, nombre, apellido));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return recepcionistas;
	}

	
}
