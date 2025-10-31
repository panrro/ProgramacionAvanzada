package bll;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import dll.DtoObraSocial;
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
	
	@Override
	public String toString() {
	    return "Turno N° " + id +
	           " | Fecha: " + fecha +
	           " | Doctor: " + nombreDoc + " " + apellidoDoc +
	           " | Paciente: " + apellidoPaciente;
	}

	public static LinkedList<Turnos> mostrarTurnos(){
		return DtoTurnos.VerTurnos();
	}
	
	public static void verListadoTurnos() { 
	    List<Turnos> listaTurnos = mostrarTurnos(); 
	    
	    if (listaTurnos.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No hay turnos registrados en el sistema.");
	        return;
	    }

	    StringBuilder sb = new StringBuilder(" Listado de Turnos \n");
	    for (Turnos t : listaTurnos) {
	        sb.append(t.toString()).append("\n"); 
	    }

	    JOptionPane.showMessageDialog(null, sb.toString());
	}
	
}
