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
	private String nombreDoctor, especialidad;
	
	public HistoriaClinica ( String descripcion, String fecha, int doctorId, int pacienteId) {
		this.descripcion=descripcion;
		this.fecha=fecha;
		this.doctorId=doctorId;
		this.pacienteId=pacienteId;
		
	}
	
	

	public HistoriaClinica(String fecha, String descripcion, String nombreDoctor) {
		this.fecha=fecha;
		this.descripcion=descripcion;
		this.nombreDoctor = nombreDoctor;
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


	public String getNombreDoctor() {
		return nombreDoctor;
	}


	public void setNombreDoctor(String nombreDoctor) {
		this.nombreDoctor = nombreDoctor;
	}


	public String getEspecialidad() {
		return especialidad;
	}


	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}


	@Override
	public String toString() {
		return "HistoriaClinica [descripcion=" + descripcion + ", fecha=" + fecha + ", doctorId=" + doctorId
				+ ", pacienteId=" + pacienteId + ", nombreDoctor=" + nombreDoctor + ", especialidad=" + especialidad
				+ "]";
	}

	


	
	
	
}
