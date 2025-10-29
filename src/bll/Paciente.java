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
	private int obrasocial_id;
	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public int getObrasocial_id() {
		return obrasocial_id;
	}

	public void setObrasocial_id(int obrasocial_id) {
		this.obrasocial_id = obrasocial_id;
	}

	private int doctor_id;
	
	public Paciente (int id, String nombre, String apellido, String dni, int edad) {
		this.id = id;
		this.nombre=nombre;
		this.apellido=apellido;
		this.dni = dni;
		this.edad= edad;
	}

	public Paciente(int id, String nombre, String apellido, String dni, int obrasocial_id, int edad,
			int doctor_id) {
		this.id = id;
		this.nombre=nombre;
		this.apellido=apellido;
		this.dni = dni;
		this.obrasocial_id=obrasocial_id;
		this.edad= edad;
		this.doctor_id=doctor_id;
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
