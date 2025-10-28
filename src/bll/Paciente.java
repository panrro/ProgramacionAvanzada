package bll;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import dll.DtoDoctor;
import dll.DtoEspecialidad;
import dll.DtoObraSocial;
import dll.Dtopaciente;
import repository.Validaciones;

public class Paciente {
	
	private int id;
	private String nombre;
	private String apellido;
	private String dni;
	private int edad;
	
	public Paciente (int id, String nombre, String apellido, String dni, int edad) {
		this.id = id;
		this.nombre=nombre;
		this.apellido=apellido;
		this.dni = dni;
		this.edad= edad;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	
	
	
	
}
