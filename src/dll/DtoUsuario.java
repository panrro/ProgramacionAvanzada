package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import bll.Doctor;
import bll.Recepcionista;
import bll.Usuario;
import repository.Encriptador; // Mantenemos el nombre de paquete de tu profesor

public class DtoUsuario {
    
	private static Connection con = Conexion.getInstance().getConnection();

  
	public static Doctor login(String mail, String contrasenia) {	
	    Doctor doctor = null;
	    try {
	        // Verifico si se encuentra el usuario en la base de datos
	        PreparedStatement stmt = con.prepareStatement(
	            "SELECT * FROM usuario WHERE mail = ? AND contrasenia = ?" 
	        );
	        stmt.setString(1, mail);
	        stmt.setString(2, Encriptador.encriptar(contrasenia)); 
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            int usuarioId = rs.getInt("id");
	            String nombre = rs.getString("nombre");
	            String apellido = rs.getString("apellido");
	            String dni = rs.getString("dni");
	            String tipo = rs.getString("tipo");

	            // Verifico si ese usuario está existe en la tabla doctor
	            PreparedStatement stmtDoctor = con.prepareStatement(
	                "SELECT * FROM doctor WHERE usuario_id = ?"
	            );
	            stmtDoctor.setInt(1, usuarioId);
	            ResultSet rsDoctor = stmtDoctor.executeQuery();

	            if (rsDoctor.next()) {
	                int doctorId = rsDoctor.getInt("id");
	                int especialidadId = rsDoctor.getInt("especialidad_id");
	                int obraSocialId = rsDoctor.getInt("obrasocial_id");

	                doctor = new Doctor(doctorId, especialidadId, usuarioId, obraSocialId);
	                doctor.setNombre(nombre);
	                doctor.setApellido(apellido);
	                doctor.setDni(dni);
	                doctor.setTipo(tipo);

	            } else {
	                JOptionPane.showMessageDialog(null, "Este usuario no tiene rol de Doctor.");
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Mail o contraseña incorrectos.");
	        }

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Error de conexión o consulta: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return doctor;
	}


	public static Recepcionista loginRecepcionista(String mail, String contrasenia) {	
	    Recepcionista recepcionista = null;
	    try {
	        // 1️⃣ Validar usuario base
	        PreparedStatement stmt = con.prepareStatement(
	            "SELECT * FROM usuario WHERE mail = ? AND contrasenia = ?" 
	        );
	        stmt.setString(1, mail);
	        stmt.setString(2, Encriptador.encriptar(contrasenia)); 
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            int usuarioId = rs.getInt("id");
	            String nombre = rs.getString("nombre");
	            String apellido = rs.getString("apellido");
	            String dni = rs.getString("dni");
	            String tipo = rs.getString("tipo");

	            // 2️⃣ Verificar si ese usuario está en la tabla recepcionista
	            PreparedStatement stmtRecep = con.prepareStatement(
	                "SELECT * FROM recepcionista WHERE usuario_id = ?"
	            );
	            stmtRecep.setInt(1, usuarioId);
	            ResultSet rsRecep = stmtRecep.executeQuery();

	            if (rsRecep.next()) {
	                int id = rsRecep.getInt("id");
	                recepcionista = new Recepcionista(id, usuarioId);
	                recepcionista.setNombre(nombre);
	                recepcionista.setApellido(apellido);
	                recepcionista.setDni(dni);
	                recepcionista.setTipo(tipo);

	            } else {
	                JOptionPane.showMessageDialog(null, "Este usuario no tiene rol de Recepcionista.");
	            }
	        } else {
	            JOptionPane.showMessageDialog(null, "Mail o contraseña incorrectos.");
	        }

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(null, "Error de conexión o consulta: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return recepcionista;
	}

	
	public static Usuario buscarPorId() {
		int id = 0;
		
		List<Usuario> usuarios = Usuario.mostrarUsuarios();
		
		String[] usuariosArray = new String [usuarios.size()];
		for (int i = 0; i < usuariosArray.length; i++) {
			usuariosArray [i] = usuarios.get(i).getMail() + "/" + usuarios.get(i).getId();
		}
		
		String elegido = (String) JOptionPane.showInputDialog(null, "Elija usuario", null,0,null, usuariosArray, usuariosArray[0]);
		id = Integer.parseInt(elegido.split("/")[1]);
		Usuario usuario = null;
		
		try {
			PreparedStatement stmt = con.prepareStatement(
					"SELECT * from usuario WHERE id = ?"
					);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				 String nombre = rs.getString("nombre");
		            String apellido = rs.getString("apellido");
		            String mail = rs.getString("mail");
		            String dni = rs.getString("dni");
		            String contrasenia = rs.getString("contrasenia");
		            String tipo = rs.getString("tipo");

		            usuario = new Usuario(id, nombre, apellido, mail, contrasenia, dni, tipo);	        
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	
  
    public static boolean EditarUsuario(Usuario usuario) {
        try {
        	PreparedStatement statement = con.prepareStatement(
            	"UPDATE usuario SET nombre = ?, apellido = ?, dni = ?, mail = ?, contrasenia = ?, tipo = ? WHERE id = ?"
            			);
            			statement.setString(1, usuario.getNombre());
            			statement.setString(2, usuario.getApellido());
            			statement.setString(3, usuario.getDni());
            			statement.setString(4, usuario.getMail());
            			statement.setString(5, usuario.getContrasenia());
            			statement.setString(6, usuario.getTipo());
            			statement.setInt(7, usuario.getId());

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

    
    public static void EliminarUsuario(int id) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "DELETE FROM `usuario` WHERE id = ?"
            );
            statement.setInt(1, id);

            int filas = statement.executeUpdate();
            if (filas > 0) {
                System.out.println("Usuario eliminado correctamente.");
            } else {
                 JOptionPane.showMessageDialog(null, "No se encontró ningún usuario con ese ID.");
            }
        } catch (MySQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "ERROR: El usuario tiene registros asociados (turnos) y no puede ser eliminado.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public static boolean agregarUsuario(Usuario usuario) {
        try {
            PreparedStatement statement = con.prepareStatement(
                "INSERT INTO usuario (nombre, apellido, dni, mail, contrasenia, tipo) VALUES (?, ?, ?, ?, ?, ?)"
            );
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido()); 
            statement.setString(3, usuario.getDni());      
            statement.setString(4, usuario.getMail());     
            statement.setString(5, Encriptador.encriptar(usuario.getContrasenia())); 
            statement.setString(6, usuario.getTipo());

            int filas = statement.executeUpdate();
            if (filas > 0) {
                System.out.println("Usuario agregado correctamente.");
                return true;
            }
        } catch (MySQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Error: El DNI o mail ya se encuentran registrados.");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    
    public static LinkedList<Usuario> mostrarUsuarios() {
        LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
        try {
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM usuario");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido"); 
                String mail = rs.getString("mail");
                String contrasenia = rs.getString("contrasenia");
                String dni = rs.getString("dni");
                String tipo = rs.getString("tipo");

                usuarios.add(new Usuario(id, nombre, apellido, mail,contrasenia, dni, tipo));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuarios;
    }
    
}