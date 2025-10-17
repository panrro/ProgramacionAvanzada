package dll;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private static String URL = "jdbc:mysql://localhost:3306/programacion_avanzada"; 
	private static String USER = "root";
	private static String PASSWORD = "";
	
	private static Connection conect;
	private static Conexion instance;
	
	private Conexion() {
		try {
			//Me da un objeto de la clase connecion y se guarda en conect si se pudo conectar
			conect = (Connection) DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Se conecto");
		} catch (SQLException e) {
			System.out.println("No se conecto " + e.getMessage());
		}
	}
	
	//La funcion de getInstancia, si no tengo creada la instancia, la creo
	public static Conexion getInstance() {
		if (instance == null) {
			instance = new Conexion();	
		}
		return instance; //si tengo creda la instancia la devuelvo y listo
	}
	
	public Connection getConnection() {
		return conect;
	}
}
