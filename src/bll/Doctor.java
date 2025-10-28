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
	int especialidad_id;
	int usuario_id;
	int obrasocial_id;
	
	public Doctor(int id, String nombre, String apellido, String mail, String contrasenia, String dni, String tipo) {
		super(id, nombre, apellido, mail, contrasenia, dni, tipo);
		this.id = id;

	}
	
	public Doctor(String nombre, String apellido, String mail, String contrasenia, String dni, String tipo) {
		super(nombre, apellido, mail, contrasenia, dni, tipo);

	}
    
	public Doctor(int id2, int especialidad_id, int usuario_id, int obrasocial_id) {
		super();
		this.id2= id2;
		this.especialidad_id= especialidad_id;
		this.usuario_id= usuario_id;
		this.obrasocial_id= obrasocial_id;
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

	public int getEspecialidad_id() {
		return especialidad_id;
	}

	public void setEspecialidad_id(int especialidad_id) {
		this.especialidad_id = especialidad_id;
	}

	public int getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}

	public int getObrasocial_id() {
		return obrasocial_id;
	}

	public void setObrasocial_id(int obrasocial_id) {
		this.obrasocial_id = obrasocial_id;
	}

	public void setId(int id) {
		this.id = id;
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