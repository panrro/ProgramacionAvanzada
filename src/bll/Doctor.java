package bll;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import dll.DtoDoctor;
import dll.DtoEspecialidad;
import dll.DtoObraSocial;
import dll.DtoRecepcionista;

public class Doctor extends Usuario {

	protected int id;
	public Doctor(int id, String nombre, String apellido, String mail, String contrasenia, String dni, String tipo) {
		super(id, nombre, apellido, mail, contrasenia, dni, tipo);
		this.id = id;

	}
	
	public Doctor(String nombre, String apellido, String mail, String contrasenia, String dni, String tipo) {
		super(nombre, apellido, mail, contrasenia, dni, tipo);

	}
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public static boolean agregarDoctor() {
		
		Usuario.agregarUsuario();
		LinkedList<Especialidad> lista = DtoEspecialidad.VerEspecialidad();
		String[] especialidades = new String[lista.size()];
		for (int i = 0; i < especialidades.length; i++) {
			especialidades[i] = lista.get(i).getNombre();
		}
		int idElegido = JOptionPane.showOptionDialog(null, "", null, 0, 0, null, especialidades, especialidades[0]);
		idElegido++;
		
		LinkedList<ObraSocial> listaObraSocial = DtoObraSocial.mostrarObraSociales();
		String [] obrasSociales = new String [listaObraSocial.size()];
		for (int i = 0; i < obrasSociales.length; i++) {
			obrasSociales[i] = listaObraSocial.get(i).getNombre();
		}
		int idElegidoObraSocial = JOptionPane.showOptionDialog(null, "", null, 0, 0, null, obrasSociales, obrasSociales[0]);
		idElegido++;
		
		DtoDoctor.agregarDoctor(idElegido, idElegidoObraSocial);
		return true;
	}
   
    
    
    
    	
    
    
    
}