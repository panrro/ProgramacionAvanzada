package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import bll.Usuario;
import bll.ObraSocial;

public class DtoObraSocial {
	private static Connection con = Conexion.getInstance().getConnection();

	
	
	 public static boolean agregarObraSocial(String nombre, double descuento ) {
	        try {
	            PreparedStatement statement = con.prepareStatement(
	                "INSERT INTO obrasocial (nombre, descuento) VALUES (?, ?)"
	            );
	            statement.setString(1, nombre);
	            statement.setDouble(2, descuento); 

	            int filas = statement.executeUpdate();
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
	 
	 public static LinkedList<ObraSocial> mostrarObraSociales() {
	        LinkedList<ObraSocial> obraSocial = new LinkedList<ObraSocial>();
	        try {
	            PreparedStatement stmt = con.prepareStatement("SELECT * FROM obrasocial");
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String nombre = rs.getString("nombre");
	                double descuento = rs.getDouble("descuento"); 

	                obraSocial.add(new ObraSocial(id,nombre, descuento));
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return obraSocial;
	    }
	 
	 public static ObraSocial buscarPorId() {
			int id = 0;
			
			List<ObraSocial> obraSocial = ObraSocial.mostrarObraSociales();
			
			String[] obraSocialarray = new String [obraSocial.size()];
			for (int i = 0; i < obraSocialarray.length; i++) {
				obraSocialarray [i] = obraSocial.get(i).getNombre() + "/" + obraSocial.get(i).getId();
			}
			
			String elegido = (String) JOptionPane.showInputDialog(null, "Elija obra social", null,0,null, obraSocialarray, 			obraSocialarray[0]);
			
			id = Integer.parseInt(elegido.split("/")[1]);
			ObraSocial obra = null;
			
			try {
				PreparedStatement stmt = con.prepareStatement(
						"SELECT * from obrasocial WHERE id = ?");
				stmt.setInt(1, id);
				
				ResultSet rs = stmt.executeQuery();
				
				if (rs.next()) {
					 	String nombre = rs.getString("nombre");
			            double descuento = rs.getDouble("descuento");
			            obra = new ObraSocial( id,nombre, descuento);	        
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return obra;
		}
	 
	 public static boolean EditarObraSocial(ObraSocial obraSocial) {
	        try {
	        	PreparedStatement statement = con.prepareStatement(
	        			"UPDATE obrasocial SET nombre = ?, descuento = ? WHERE id = ?");
	            			statement.setString(1, obraSocial.getNombre());
	            			statement.setDouble(2, obraSocial.getDescuento());	            			
	            			statement.setInt(3, obraSocial.getId());

	            int filas = statement.executeUpdate();
	            if (filas > 0) {
	                System.out.println("ObraSocial editada correctamente.");
	                return true;
	            }
	        } catch (Exception e) {
	        	JOptionPane.showMessageDialog(null, "Error al intentar editar la Obra Social: " + e.getMessage());
	            e.printStackTrace();
	            return false;
	        }
	        return false;
	    }
	
}
