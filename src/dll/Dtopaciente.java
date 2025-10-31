package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import bll.Doctor;
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
			
			public static LinkedList<Paciente> VerPacientes() {
		    	LinkedList<Paciente> pacientes = new LinkedList<Paciente>();
		        try {             	
		            PreparedStatement stmt = con.prepareStatement(
		            		"SELECT * FROM paciente");	           
					ResultSet rs = stmt.executeQuery();
					
					while (rs.next()) { //Aca en ves de if, va un while ya que al ser una lista especialidades, voy a querer que mientra que 		hayan datos se repitam y que no me traiga solo la fila afectada con el if
						 int id = rs.getInt("id");
						 String nombre = rs.getString("nombre");
						 String apellido = rs.getString("apellido");
						 String dni = rs.getString("dni");
						 int obrasocial_id = rs.getInt("obrasocial_id");
						 int edad = rs.getInt("edad");
						 int doctor_id = rs.getInt("doctor_id");
						 pacientes.add(new Paciente(id,nombre, apellido, dni,obrasocial_id, edad, doctor_id));	        
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return pacientes;
			}
			
			public static LinkedList<Paciente> VerPacientesPorDoctor(int idDoctor) {
			    LinkedList<Paciente> pacientes = new LinkedList<>();
			    try {
			        PreparedStatement stmt = con.prepareStatement(
			            "SELECT * FROM paciente WHERE doctor_id = ?"
			        );
			        stmt.setInt(1, idDoctor);
			        ResultSet rs = stmt.executeQuery();

			        while (rs.next()) {
			            int id = rs.getInt("id");
			            String nombre = rs.getString("nombre");
			            String apellido = rs.getString("apellido");
			            String dni = rs.getString("dni");
			            int obrasocial_id = rs.getInt("obrasocial_id");
			            int edad = rs.getInt("edad");
			            int doctor_id = rs.getInt("doctor_id");

			            pacientes.add(new Paciente(id, nombre, apellido, dni, obrasocial_id, edad, doctor_id));
			        }
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			    return pacientes;
			}
}
