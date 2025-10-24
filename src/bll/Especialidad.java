package bll;

import java.sql.Connection;

import javax.swing.JOptionPane;

import dll.Conexion;
import dll.DtoEspecialidad;
import dll.DtoUsuario;
import repository.Validaciones;

public class Especialidad {

	private static Connection con = Conexion.getInstance().getConnection();

	protected int id;
	private String nombre;
	
	public Especialidad (int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}
	
	public Especialidad (String nombre) {
		this.nombre = nombre;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	public static boolean agregarEspecialidad() {
		 String nombre;
		 do {
			nombre=  JOptionPane.showInputDialog("Ingrese el nombre");
			if (Validaciones.validate(nombre)==false) {
				JOptionPane.showMessageDialog(null, "Nombre incorrecto");
			}
		} while (Validaciones.validate(nombre)==false);
		 
		 Especialidad nueva = new Especialidad(nombre);
		 DtoEspecialidad.agregarEspecialidad(nombre);	
		return true;
	}
}
