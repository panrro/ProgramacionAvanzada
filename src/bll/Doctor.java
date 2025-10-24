package bll;

import dll.DtoDoctor;
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
		DtoDoctor.agregarDoctor();
		
		return true;
	}
   
    
    
    
    	
    
    
    
}