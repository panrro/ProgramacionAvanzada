package bll;

import java.util.LinkedList;

import dll.DtoTurnos;
import dll.DtoUsuario;

public class Turnos {

	int id; String fecha, nombreDoc,  apellidoDoc,  apellidoPaciente;
	
	public Turnos(int id, String fecha, String nombreDoc, String apellidoDoc, String apellidoPaciente) {
		this.id=id;
		this.fecha=fecha;
		this.nombreDoc=nombreDoc;
		this.apellidoDoc=apellidoDoc;
		this.apellidoPaciente= apellidoPaciente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNombreDoc() {
		return nombreDoc;
	}

	public void setNombreDoc(String nombreDoc) {
		this.nombreDoc = nombreDoc;
	}

	public String getApellidoDoc() {
		return apellidoDoc;
	}

	public void setApellidoDoc(String apellidoDoc) {
		this.apellidoDoc = apellidoDoc;
	}

	public String getApellidoPaciente() {
		return apellidoPaciente;
	}

	public void setApellidoPaciente(String apellidoPaciente) {
		this.apellidoPaciente = apellidoPaciente;
	}

	public static LinkedList<Turnos> mostrarTurnos(){
		return DtoTurnos.VerTurnos();
	}
	
}
