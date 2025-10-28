package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import bll.Especialidad;
import bll.Usuario;

public class DtoEspecialidad {

	private static Connection con = Conexion.getInstance().getConnection();
 
	  
	    public static boolean agregarEspecialidad(String especialidad) {
	        try {             	
	            PreparedStatement stmt = con.prepareStatement(
	            		"INSERT INTO `especialidad`(`nombre`) VALUES (?)");	           
	            		stmt.setString(1, especialidad);
	            
	            int filas = stmt.executeUpdate();
	            if (filas > 0) {
	                System.out.println("Especialidad agregada correctamente.");
	                return true;
	            }
	        } catch (Exception e) {
	            JOptionPane.showMessageDialog(null, "La especialidad ya se encuentra registrada.");
	            return false;
	        }
	        return false;
	}
	    
	    public static LinkedList<Especialidad> VerEspecialidad() {
	    	LinkedList<Especialidad> especialidades = new LinkedList<Especialidad>();
	        try {             	
	            PreparedStatement stmt = con.prepareStatement(
	            		"SELECT * FROM especialidad");	           

				
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) { //Aca en ves de if, va un while ya que al ser una lista especialidades, voy a querer que mientra que 		hayan datos se repitam y que no me traiga solo la fila afectada con el if
					 int id = rs.getInt("id");
					 String nombre = rs.getString("nombre");
					 especialidades.add(new Especialidad(id,nombre));	        
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return especialidades;
		}
		
}
