package bll;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import dll.DtoDoctor;
import dll.DtoEspecialidad;
import dll.DtoHistoriaClinica;
import dll.DtoRecepcionista;
import dll.DtoTurnos;
import dll.DtoUsuario;
import dll.Dtopaciente;
import repository.Validaciones;

public class HistoriaClinica {

	private String descripcion, fecha;
	private int doctorId, pacienteId;
	
	public HistoriaClinica ( String descripcion, String fecha, int doctorId, int pacienteId) {
		this.descripcion=descripcion;
		this.fecha=fecha;
		this.doctorId=doctorId;
		this.pacienteId=pacienteId;
		
	}
	

	public HistoriaClinica ( String descripcion, String fecha ) {
		this.descripcion=descripcion;
		this.fecha=fecha;
		
	}

	
	
	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public String getFecha() {
		return fecha;
	}



	public void setFecha(String fecha) {
		this.fecha = fecha;
	}



	public int getDoctorId() {
		return doctorId;
	}



	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}



	public int getPacienteId() {
		return pacienteId;
	}



	public void setPacienteId(int pacienteId) {
		this.pacienteId = pacienteId;
	}



	
	
	
}
