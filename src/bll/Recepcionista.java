package bll;

import javax.swing.JOptionPane;

import dll.DtoRecepcionista;
import dll.DtoUsuario;
import repository.Validaciones;

public class Recepcionista {

	protected int id;

	public Recepcionista(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public static boolean agregarRecepcionista() {
		
		Usuario.agregarUsuario();
		DtoRecepcionista.agregarRecepcionista();
		
		return true;
	}
	
	
}
