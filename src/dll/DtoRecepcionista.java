package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

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
	

	
}
