package bll;
import bll.ObraSocial;
import javax.swing.JOptionPane;
import dll.DtoUsuario; // Necesario para la eliminación
import repository.Validaciones; // Necesario para la validación de datos

public class Admin extends Usuario {

    // Constructor completo 
    public Admin(int id, String nombre, String apellido, String mail, String contrasenia, String dni, String tipo) {
        super(id, nombre, apellido, mail, contrasenia, dni, tipo);
    }
    
    public Admin (String nombre, String apellido, String mail, String dni) {
    	super(nombre, apellido, mail, dni);
    	this.nombre=nombre;
    	this.apellido=apellido;
    	this.mail=mail;
    	this.dni=dni;
    }

    public Admin(String nombre, String apellido, String mail, String contrasenia, String dni, String tipo) {
        super(nombre, apellido, mail, contrasenia, dni, tipo);
    }

	@Override
    public void menu() {
        String[] opcionesMenu = {
            "Gestionar usuarios",          
            "Gestionar ObraSocial",
            "Agregar recepcionista",
            "Cerrar Sesión"             
        };
        
        int opcionSeleccionada;
        
        do {
            opcionSeleccionada = JOptionPane.showOptionDialog(null, "Elija una opcion", null, 0,0, null,opcionesMenu,  opcionesMenu[0]);

            switch (opcionSeleccionada) {
                case 0:
                		String[] opcionesDesplegable = {"Agregar Recepcionista", "Agregar Doctor", "Eliminar Usuario", "Editar Usuario", "Ver Usuarios"};
                		String elegida = (String) JOptionPane.showInputDialog(null, "Ingrese opcion", "", 0, null, 						opcionesDesplegable,opcionesDesplegable[0]);
                		
                		switch (elegida) {
						case "Agregar Recepcionista":
							Recepcionista.agregarRecepcionista();
							break;
							
						case "Agregar Doctor":
							Doctor.agregarDoctor();
							break;

						case "Eliminar Usuario":
							int id = DtoUsuario.buscarPorId().getId();
	                		DtoUsuario.EliminarUsuario(id);
							break;
							
						case "Editar Usuario":
							Usuario.EditarUsuario();
							break;
							
						case "Ver Usuarios":
							Usuario.verListadoUsuarios();
							break;
						} //Fin del submenu              			
                    break;
                case 1:

            		String[] opciones2 = {"Agregar obraSocial", "Eliminar ObraSocial", "Editar ObraSocial", "Ver ObrasSociales"};
            		String elegida2 = (String) JOptionPane.showInputDialog(null, "Ingrese opcion", "", 0, null, 					opciones2,opciones2[0]);
                	switch (elegida2) {
					case "Agregar obraSocial":
						ObraSocial.agregarObraSocial();
						break;

					case "Eliminar ObraSocial":
						break;
						
					case "Editar ObraSocial":
						ObraSocial.EditarObraSocial();
						break;
						
					case "Ver ObrasSociales":
						ObraSocial.verListadoObraSociales();
						break;
					}
                	
                	
                    break;
                case 2:
                	
                    break;
                    
                case 3:
                	JOptionPane.showMessageDialog(null, "Cerrando sesión...");
                    break;
                                          }
        } while (opcionSeleccionada != 3);
    }
}