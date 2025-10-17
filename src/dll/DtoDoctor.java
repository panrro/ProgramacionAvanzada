package dll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class DtoDoctor {

    private static Connection con = Conexion.getInstance().getConnection();

    public static List<String> cargarEspecialidades(int doctorId) {
        List<String> especialidades = new ArrayList<>();
        
        try {
            PreparedStatement stmt = con.prepareStatement(
                "SELECT especialidad FROM doctor WHERE usuario_id = ?" 
            );
            stmt.setInt(1, doctorId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                especialidades.add(rs.getString("especialidad"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar especialidades (Tabla doctor): " + e.getMessage());
            e.printStackTrace();
        }
        return especialidades;
    }

    public static boolean agregarNuevaEspecialidad(int doctorId, String nombreEspecialidad) {
        try {
            PreparedStatement stmt = con.prepareStatement(
                "UPDATE doctor SET especialidad = ? WHERE usuario_id = ?"
            );
            stmt.setString(1, nombreEspecialidad);
            stmt.setInt(2, doctorId);

            int filasAfectadas = stmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Especialidad principal actualizada correctamente.");
                return true;
            }
            return false;

        } catch (Exception e) {
        	e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar especialidad");
            return false;
        }
    }
    
    
}