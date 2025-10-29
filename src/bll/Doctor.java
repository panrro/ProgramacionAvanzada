package bll;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import dll.DtoDoctor;
import dll.DtoEspecialidad;
import dll.DtoObraSocial;
import dll.DtoRecepcionista;
import dll.DtoUsuario;

public class Doctor extends Usuario {

	
	protected int id2;
	private String nombreUsuario, nombreEspecialidad, nombreObraSocial;
	
	public Doctor(int id, String nombre, String apellido, String mail, String contrasenia, String dni, String tipo) {
		super(id, nombre, apellido, mail, contrasenia, dni, tipo);
		this.id = id;

	}
	
	public Doctor(String nombre, String apellido, String mail, String contrasenia, String dni, String tipo) {
		super(nombre, apellido, mail, contrasenia, dni, tipo);

	}
    
	public Doctor(int id2, String nombreUsuario, String nombreEspecialidad, String nombreObraSocial) {
		super();
		this.id2= id2;
		this.nombreUsuario= nombreUsuario;
		this.nombreEspecialidad= nombreEspecialidad;
		this.nombreObraSocial= nombreObraSocial;
	}

	public int getId() {
		return id;
	}

	public int getId2() {
		return id2;
	}

	public void setId2(int id2) {
		this.id2 = id2;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombreEspecialidad() {
		return nombreEspecialidad;
	}

	public void setNombreEspecialidad(String nombreEspecialidad) {
		this.nombreEspecialidad = nombreEspecialidad;
	}

	public String getNombreObraSocial() {
		return nombreObraSocial;
	}

	public void setNombreObraSocial(String nombreObraSocial) {
		this.nombreObraSocial = nombreObraSocial;
	}

	@Override
    public void menu() {
        String[] opcionesMenu = {
        	"Gestionar perfil",
            "Ver turnos",          
            "Cerrar Sesión"             
        };
        
        int opcionSeleccionada;
        
        do {
            opcionSeleccionada = JOptionPane.showOptionDialog(null, "Elija una opcion", null, 0,0, null,opcionesMenu,  opcionesMenu[0]);

            switch (opcionSeleccionada) {
                case 0:
                		String[] opcionesDesplegable = {"Agregar especialidad", "Agregar obra social","Editar Perfil", "Ver perfil"};
                		String elegida = (String) JOptionPane.showInputDialog(null, "Ingrese opcion", "", 0, null, 						opcionesDesplegable,opcionesDesplegable[0]);
                		
                		switch (elegida) {
						case "Agregar especialidad":
							break;
							
						case "Agregar obraSocial":
							break;

						case "Editar Perfil":
							
							break;
							
						case "Ver Perfil":
							break;
							
						
						} //Fin del submenu              			
                    break;
                case 1:

            		String[] opciones2 = {"Ver Turnos"};
            		String elegida2 = (String) JOptionPane.showInputDialog(null, "Ingrese opcion", "", 0, null, 					opciones2,opciones2[0]);
                	switch (elegida2) {
					case "Ver turnos":
						break;

					
					}
                	
                	
                    break;
                case 2:
                	
                    break;
                    
                case 3:
                	JOptionPane.showMessageDialog(null, "Cerrando sesión...");
                    break;
                                          }
        } while (opcionSeleccionada != 2);
    }
	
}